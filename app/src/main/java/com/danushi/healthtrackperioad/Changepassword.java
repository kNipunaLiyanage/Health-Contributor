package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Changepassword extends AppCompatActivity {
    EditText editText1,editText2,editText3;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_changepassword);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);

        editText1 = findViewById(R.id.user_currentpass);
        editText2 = findViewById(R.id.usernewpwz);
        editText3 = findViewById(R.id.usernewpwz2);
        b1 = findViewById(R.id.changepassword);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String current_pw = editText1.getText().toString();
                    String new_pw = editText2.getText().toString();
                    String new_pw2 = editText3.getText().toString();
                    String sess_pw = DbConnection.load_user_passwordz;
                    if(current_pw.equals("") || new_pw.equals("") || new_pw2.equals("")){
                        Toast.makeText(Changepassword.this, "Fill All feilds..!", Toast.LENGTH_SHORT).show();
                    }else if(!(new_pw.equals(new_pw2))){

                        Toast.makeText(Changepassword.this, "New passwords are not matched..!", Toast.LENGTH_SHORT).show();
                    }else if(!(current_pw.equals(sess_pw))){

                        Toast.makeText(Changepassword.this, "Current password is not matched..!", Toast.LENGTH_SHORT).show();
                    }else{
                        String useremail_load = DbConnection.emailzloadingz;
                        String jj = "update userdetails set userpasswordz='"+new_pw+"' where useremailz='"+useremail_load+"'";
                        DbConnection.iud(jj);
                        Toast.makeText(Changepassword.this, "Password update..Now Login from your new password!", Toast.LENGTH_LONG).show();
                        Intent ii = new Intent(Changepassword.this,signin.class);
                        startActivity(ii);

                    }


                }catch (Exception ee){
                    Toast.makeText(Changepassword.this, "ee"+ee, Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}