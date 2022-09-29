package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MenstrationDashboard extends AppCompatActivity {

    Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menstration_dashboard);

        btn1 = findViewById(R.id.btn1z);
        btn2 = findViewById(R.id.btn2z);
        btn3 = findViewById(R.id.btn3z);
        btn4 = findViewById(R.id.btn4z);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(MenstrationDashboard.this,MensHomenew.class);
                startActivity(ii);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(MenstrationDashboard.this,UpdateMensHome.class);
                startActivity(ii);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(MenstrationDashboard.this,MenstrationHistory.class);
                startActivity(ii);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(MenstrationDashboard.this,Staytuned.class);
                startActivity(ii);
            }
        });



    }
}