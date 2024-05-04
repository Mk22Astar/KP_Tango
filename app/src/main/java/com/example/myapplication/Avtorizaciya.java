package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Avtorizaciya extends AppCompatActivity {
    private DatabaseReference mDatabase;
private String USER_KEY = "User";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        EditText edtTelephone = findViewById(R.id.edtTelephone);
        EditText edtPassword = findViewById(R.id.edtPassword);

        mDatabase = FirebaseDatabase.getInstance().getReference(USER_KEY);

        // Обработчик нажатия кнопки "Войти"
        Button signInButton = findViewById(R.id.btnInclud);
        signInButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
//                String id = mDatabase.getKey();
//                String telephone = edtTelephone.getText().toString();
//                String password = edtPassword.getText().toString();
//                if(!TextUtils.isEmpty(telephone) && !TextUtils.isEmpty(password)){
//                    User user = new User();
//
//                }
//                else{
//
//                    Toast.makeText(Avtorizaciya.this,"Пустое поле! \nЗаполните все поля", Toast.LENGTH_SHORT).show();
//                    }



            }
        });


    }


    public void onClickBack(Void v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}