package com.danushi.healthtrackperioad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

public class NEwHome extends AppCompatActivity {
    Button b1,b2,b3;
    TextView t1,tvloadz1,tvloadz2;
    ImageView setz;
    CardView mens,fertili;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_n_ew_home);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);


        tvloadz1 = findViewById(R.id.tvload1);
        tvloadz2 = findViewById(R.id.tvload2);
        try {
            String quert ="Select * from newuserdet where unmez='"+DbConnection.emailzloadingz+"'";
            ResultSet oo = DbConnection.search(quert);
            if(oo.next()){
                String qq2 = "select * from usermenstruationtrack where useremailz='"+DbConnection.emailzloadingz+"'  and statusz='notchecked'";
                ResultSet rs = DbConnection.search(qq2);
                if(rs.next()){
                    tvloadz1.setText("Your next menstration date is");
                    tvloadz2.setText(""+rs.getString("nextdatez"));
                    DbConnection.load_nextdate = rs.getString("nextdatez");
                    DbConnection.load_avetagecycle = rs.getString("avaragecyclez");
                }
            }else{
                tvloadz1.setText("Track your menstration");
                tvloadz2.setText("Properly");
            }


        }catch (Exception zz){
            Toast.makeText(this, ""+zz, Toast.LENGTH_SHORT).show();

        }



        b1 =findViewById(R.id.gotopregnancytips);
        b2 =findViewById(R.id.musicbtn);
        b3 =findViewById(R.id.contraceptiontipsz);
        t1 = findViewById(R.id.t2z);
        t1.setText(""+DbConnection.usernameloadingz);
        setz = findViewById(R.id.setting_user);
        mens = findViewById(R.id.mestrationHomebtn);
        fertili = findViewById(R.id.fertilizermain);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NEwHome.this, PregnancyTips.class);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NEwHome.this, LoadMusicTopicList.class);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NEwHome.this, ContraceptionTips.class);
                startActivity(intent);
            }
        });

        setz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NEwHome.this, SettingsPage.class);
                startActivity(intent);
            }
        });
        mens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NEwHome.this, MenstrationDashboard.class);
                startActivity(intent);
            }
        });
        fertili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NEwHome.this, Loadfertily.class);
                startActivity(intent);
            }
        });



    }



}