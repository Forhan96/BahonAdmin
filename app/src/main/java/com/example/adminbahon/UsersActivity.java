package com.example.adminbahon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class UsersActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public List<ModelClass> modelClassList;
    Adapter adapter;
    ImageView imageView;
    TextView name;
    public String userKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        setTitle("Users");

        recyclerView= findViewById(R.id.recyclerView);
        //name = findViewById(R.id.nameId);
        //imageView = findViewById(R.id.imageView);

        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);
        modelClassList=new ArrayList<>();

        adapter =new Adapter(modelClassList);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        //Log.i("user", userKey);
                        Intent detail = new Intent(getApplicationContext(), UserDetails.class);
                        startActivity(detail);

//
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("RidersInformation");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                modelClassList.clear();
                for (DataSnapshot dataSnapshot1 : snapshot.getChildren()){
                    String user = String.valueOf(dataSnapshot1.getKey());
                    String name = String.valueOf(dataSnapshot1.child("name").getValue());
                    String phone = String.valueOf(dataSnapshot1.child("phone").getValue());
                    String url = String.valueOf(dataSnapshot1.child("avatarUrl").getValue());

                    modelClassList.add(new ModelClass(url,name,phone, user));

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}

