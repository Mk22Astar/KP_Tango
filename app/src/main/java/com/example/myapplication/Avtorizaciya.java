package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Avtorizaciya extends AppCompatActivity {
private ArrayAdapter<String> adapter;
private List<String> listData;
private DatabaseReference mDatabase;
private String USER_KEY = "User";
private Button btnInclud,btnBack;
private EditText edtTelephone, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        edtTelephone = findViewById(R.id.edtTelephone);
        edtPassword = findViewById(R.id.edtPassword);
        btnInclud = findViewById(R.id.btnInclud);
        btnBack = findViewById(R.id.btnBack);
        TextView errorTelephone = findViewById(R.id.error_telephone);
        errorTelephone.setVisibility(View.GONE);
        TextView errorPassword = findViewById(R.id.error_password);
        errorPassword.setVisibility(View.GONE);
        mDatabase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        listData = new ArrayList<>();
        adapter = new ArrayAdapter<>(Avtorizaciya.this, android.R.layout.simple_list_item_1, listData);


        // Обработчик нажатия кнопки "Войти"

        btnInclud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String telephone = edtTelephone.getText().toString();
                String password = edtPassword.getText().toString();
                errorTelephone.setVisibility(View.GONE);
                errorPassword.setVisibility(View.GONE);
                if(!TextUtils.isEmpty(telephone) && !TextUtils.isEmpty(password)){
                    if(telephone.length() == 11){
                        if (password.length() == 4){
                            Log.w("teg2", " a");
                            getDataBD();
                        }
                        else {
                            errorPassword.setVisibility(View.INVISIBLE);
                            errorPassword.setVisibility(View.VISIBLE);
                        }
                    }
                    else {

                        errorTelephone.setVisibility(View.INVISIBLE);
                        errorTelephone.setVisibility(View.VISIBLE);

                    }
                }
                else{
                    Toast.makeText(Avtorizaciya.this,"Пустое поле! \nЗаполните все поля", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Avtorizaciya.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private  void getDataBD() {
        long lTelephone = Long.parseLong(edtTelephone.getText().toString());
        long lPassword = Long.parseLong(edtPassword.getText().toString());
        ValueEventListener vListener = new ValueEventListener() {

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User user = ds.getValue(User.class);
                    assert user != null;
                    Log.w("teg4",user.Telephone.toString() + " == " + edtTelephone.getText().toString() + " и " +  user.Password.toString() + " == " +  edtPassword.getText().toString());
                    if (user.Telephone == lTelephone && user.Password == lPassword) {
                        Toast.makeText(Avtorizaciya.this, "Вы вошли как: " + user.Surname + " " + user.Name + " " + user.MiddleName, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Avtorizaciya.this, MainActivity.class);
                        intent.putExtra("user_name", user.Name);
                        intent.putExtra("user_surname", user.Surname);
                        intent.putExtra("user_middleName", user.MiddleName);
                        startActivity(intent);

                        break;
                    } else {
                        Log.w("teg3", " Зашел в else");
                        Toast.makeText(Avtorizaciya.this, "Упс! что-то пошло не так", Toast.LENGTH_SHORT).show();
                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        };

        mDatabase.addValueEventListener(vListener);
    }


}