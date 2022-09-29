package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.sql.ResultSet;
import java.util.HashMap;

public class signin extends AppCompatActivity{

    Button btn;
    String typuname;
    EditText username,passw;
    TextView ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signin);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);

        username = findViewById(R.id.uname);
        passw = findViewById(R.id.pw);
        ss = findViewById(R.id.createz);


        btn = findViewById(R.id.loginbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String unman = username.getText().toString();
                typuname = unman;
                String pass1 = passw.getText().toString();
                try{
                    if(unman.equals("") || pass1.equals("")){
                        Toast.makeText(signin.this, "Please fill all the feilds..", Toast.LENGTH_SHORT).show();
                    }else {
                        String searchquer = "select * from userdetails where useremailz='" + unman + "' and userpasswordz='" + pass1 + "'";
                        ResultSet rs = DbConnection.search(searchquer);
                        if (rs.next()) {
                            String userstat = rs.getString("statuz");
                            DbConnection.emailzloadingz = unman;
                            DbConnection.usernameloadingz = rs.getString("usernameinfull");
                            if (userstat.equals("Pending")) {
                                DbConnection.pin_veryfy = rs.getString("pinidgenarated");
                                Intent intent = new Intent(signin.this, verification.class);
                                startActivity(intent);
                            } else {
                                DbConnection.load_user_passwordz = pass1;
                                Toast.makeText(signin.this, "Login sucess", Toast.LENGTH_SHORT).show();
                                 Intent intent = new Intent(signin.this, NEwHome.class);
                                startActivity(intent);
                            }
                        } else {
                            Toast.makeText(signin.this, "Invalid login details", Toast.LENGTH_SHORT).show();
                        }
                    }

                }catch(Exception e){
                    Toast.makeText(signin.this, "---"+e, Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public void onBackPressed() {

    }
}