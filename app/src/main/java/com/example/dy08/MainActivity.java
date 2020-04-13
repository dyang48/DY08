package com.example.dy08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("message");
    private EditText keyEditText;
    private EditText valueEditText;
    private TextView keyTextView;
    private TextView valueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRef=database.getReference("Messages");
        myRef.child("practice").setValue("this is the practice message");
        keyEditText=findViewById(R.id.key);
        valueEditText=findViewById(R.id.value);



    }

    public void readfromcloud (View view){
        Button button2=(Button)findViewById(R.id.readbutton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference childRef = myRef.child(keyEditText.getText().toString());
                childRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            String loadedData = dataSnapshot.getValue(String.class);
                            valueEditText.setText(loadedData);
                        } else {
                            valueEditText.setText("");
                            Toast.makeText(MainActivity.this, "Cannot find key", Toast.LENGTH_SHORT).show();


                    }}
                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(MainActivity.this, "Cannot find Key", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });}
    public void writetocloud(View view){
       Button button1=(Button)findViewById(R.id.writebutton);
       button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               myRef=database.getReference("Messages");
               myRef.child(keyEditText.getText().toString()).setValue(valueEditText.getText().toString());

           }
       });



    }
}



