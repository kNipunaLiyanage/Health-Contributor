package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);


    }


        public void loginscreenngo(View view){

            Intent intent =new Intent(getApplicationContext(),signin.class);
            Pair[] pair = new Pair[1];

            pair[0] = new Pair<View,String>(findViewById(R.id.logz),"transition_login");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginPage.this,pair);
                startActivity(intent,activityOptions.toBundle());
            }else{
                startActivity(intent);
            }

    }
    public void createscreenngo(View view){

            Intent intent =new Intent(getApplicationContext(),signup.class);
            Pair[] pair = new Pair[1];

            pair[0] = new Pair<View,String>(findViewById(R.id.signz),"transition_login");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(LoginPage.this,pair);
                startActivity(intent,activityOptions.toBundle());
            }else{
                startActivity(intent);
            }

    }
}