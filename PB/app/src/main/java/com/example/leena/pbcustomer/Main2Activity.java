package com.example.leena.pbcustomer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    EditText username, password;
    Button loginbtn;
    String user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        username = findViewById(R.id.nametxt);
        password = findViewById(R.id.passwordtxt);
        loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                String pass = password.getText().toString();
                Toast.makeText(getApplicationContext(),"Login successful.",Toast.LENGTH_SHORT).show();
                sendUserToMainActivity();
            }
        });
    }

    private void sendUserToMainActivity() {
        Intent mainIntent=new Intent(Main2Activity.this,MainActivity.class);
        //mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mainIntent.putExtra("user",user);
        startActivity(mainIntent);
        finish();

    }
}
