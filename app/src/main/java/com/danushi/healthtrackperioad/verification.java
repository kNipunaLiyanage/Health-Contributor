package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.util.HashMap;

public class verification extends AppCompatActivity {
    Button verifyz;
    PinView pinView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verification);

        verifyz = findViewById(R.id.verify);

        pinView = findViewById(R.id.pin_view);

        verifyz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = localVari.useremail;
                //Toast.makeText(verification.this, ""+email, Toast.LENGTH_LONG).show();

                String gg=pinView.getText().toString();
                if(gg.equals(DbConnection.pin_veryfy)){
                    try {

                        String status = "Active";
                        String que = "update userdetails set statuz='"+status+"' where useremailz='"+ DbConnection.emailzloadingz+"'";
                        DbConnection.iud(que);
                        Toast.makeText(verification.this, "Account Verified Login to continue..!", Toast.LENGTH_LONG).show();
                        Intent ii = new Intent(verification.this,signin.class);
                        startActivity(ii);

                    }catch (Exception ee){
                        Toast.makeText(verification.this, "eee"+ee, Toast.LENGTH_SHORT).show();
                    }

                }else{

                    Toast.makeText(verification.this, "Invalid PIN number..!", Toast.LENGTH_LONG).show();
                }


            }
        });



    }


}