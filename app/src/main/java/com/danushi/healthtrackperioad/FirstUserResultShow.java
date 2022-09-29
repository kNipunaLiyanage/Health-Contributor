package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;

public class FirstUserResultShow extends AppCompatActivity {
    TextView mensUser_display;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_first_user_result_show);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        mensUser_display = findViewById(R.id.loadingmensdatefirst);
        b1 = findViewById(R.id.savemensdatadetails);

        Intent intent = getIntent();
        String loaded_date = intent.getStringExtra("days");
        String loaded_cycle = intent.getStringExtra("cycle");
        String loaded_selecteddate = intent.getStringExtra("seledate");


        mensUser_display.setText(""+loaded_date);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    try {
                        String useremail = DbConnection.emailzloadingz;
                        String sqarch = "select * from newuserdet where unmez='"+useremail+"'";
                        ResultSet tsd = DbConnection.search(sqarch);
                        if(tsd.next()){
                            Toast.makeText(FirstUserResultShow.this, "You have previous details please update your details...", Toast.LENGTH_LONG).show();
                        }else{

                        String st = "notchecked";
                        String hh="insert into usermenstruationtrack(usernamez,useremailz,selecteddatez,nextdatez,avaragecyclez,statusz) values('"+DbConnection.usernameloadingz+"','"+DbConnection.emailzloadingz+"','"+loaded_selecteddate+"','"+loaded_date+"','"+loaded_cycle+"','"+st+"')";
                        String hh2 = "insert into newuserdet(unmez) values('"+useremail+"')";

                        DbConnection.iud(hh);
                        DbConnection.iud(hh2);

                        Toast.makeText(FirstUserResultShow.this, "Data saved...", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(FirstUserResultShow.this,NEwHome.class);
                            startActivity(i);

                        }

                    }catch (Exception gg){
                        Toast.makeText(FirstUserResultShow.this, ""+gg, Toast.LENGTH_SHORT).show();
                    }
            }
        });


    }
}