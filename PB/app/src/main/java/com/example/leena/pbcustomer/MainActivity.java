package com.example.leena.pbcustomer;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    TextView pname,address,phone,custid,delid,price,identifier;
    Button scanbtn;
    private IntentIntegrator qrScan;
    String value;
    String cust_id;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pname = findViewById(R.id.pname);
        address = findViewById(R.id.address);
        phone = findViewById(R.id.phone);
        custid = findViewById(R.id.name);
        delid = findViewById(R.id.delivery);
        price = findViewById(R.id.price);
        identifier = findViewById(R.id.identifier);
        scanbtn = findViewById(R.id.scan);
        qrScan = new IntentIntegrator(this);

        context = getApplicationContext();

        cust_id = "leena@m.com";
        Intent intent = getIntent();
        //cust_id = intent.getStringExtra("user");

        scanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qrScan.initiateScan();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {
                value= result.getContents();

            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
        //hud.show();
        //Product_Ret obj= new Product_Ret(user_id,value,context,datamodel,listView,adapter,txtprice,hud);
        //obj.execute();
        Data_ret obj = new Data_ret(value,cust_id,context,pname,address,phone,delid,price);
        obj.execute();


    }




}
