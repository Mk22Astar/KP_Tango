package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity  extends AppCompatActivity {
//    private ListView listView;
//    private ArrayAdapter<String> adapter;
//    private List<String> listData;
//    private DatabaseReference mDatabase;
//    private String USER_KEY = "User";
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.abonementi);
//        listView = findViewById(R.id.list_item);
//        listData = new ArrayList<>();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
//        mDatabase = FirebaseDatabase.getInstance().getReference(USER_KEY);
//        listView.setAdapter(adapter);
//        getDataFromBD();
//    }
//    private  void getDataFromBD(){
//        ValueEventListener vListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(listData.size() > 0)listData.clear();
//                for(DataSnapshot ds: snapshot.getChildren()){
//                    User user = ds.getValue(User.class);
//                    assert user != null;
//                    listData.add(user.name);
//                }
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        };
//        mDatabase.addValueEventListener(vListener);
//    }
}
