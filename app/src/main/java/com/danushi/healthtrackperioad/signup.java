package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.kosalgeek.asynctask.AsyncResponse;
import com.kosalgeek.asynctask.PostResponseAsyncTask;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Random;

public class signup extends AppCompatActivity {
    TextView back;
    EditText nameinfull,emailaddress,pass1z,pass2z,contactnumb;
    Button btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);


        nameinfull = findViewById(R.id.studentname);
        emailaddress = findViewById(R.id.contactpersonname);
        pass1z = findViewById(R.id.passwordz);
        pass2z = findViewById(R.id.passwordz2);
        contactnumb = findViewById(R.id.contactz);

        back = findViewById(R.id.acctxtview);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(signup.this,signin.class);
                startActivity(intent);
            }
        });



        btnreg = findViewById(R.id.createaccountbtn);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name = nameinfull.getText().toString();
                String email = emailaddress.getText().toString();
                String pass1 = pass1z.getText().toString();
                String pass2 = pass2z.getText().toString();
                String contz = contactnumb.getText().toString();
                if(name.equals("") || email.equals("") || pass1.equals("") || pass2.equals("") || contz.equals("")){

                    Toast.makeText(signup.this, "Please fill all feilds to continue..!", Toast.LENGTH_SHORT).show();
                }else if(!(pass1.equals(pass2))){

                    Toast.makeText(signup.this, "Passwords are not matched..!", Toast.LENGTH_SHORT).show();

                }else if(contz.length() >10 || contz.length()<10){
                    Toast.makeText(signup.this, "Please enter valid contact number..!", Toast.LENGTH_SHORT).show();

                }else{
                    try {
                        Random random = new Random();
                        String verfinaction_id = String.format("%04d", random.nextInt(10000));
                        String stat = "Pending";
                        String utype = "user";
                        String ss = "select * from userdetails where useremailz='"+email+"'";
                        ResultSet rs = DbConnection.search(ss);
                        if(rs.next()){

                            Toast.makeText(signup.this, "This Email address is already registered...!", Toast.LENGTH_SHORT).show();
                        }else{

                            String que_fir = "INSERT INTO `userdetails`\n" +
                                    "            (`usernameinfull`,\n" +
                                    "             `useremailz`,\n" +
                                    "             `usertypez`,\n" +
                                    "             `usercontact`,\n" +
                                    "             `userpasswordz`,\n" +
                                    "             `statuz`,\n" +
                                    "             `pinidgenarated`)\n" +
                                    "VALUES ('"+name+"',\n" +
                                    "        '"+email+"',\n" +
                                    "        '"+utype+"',\n" +
                                    "        '"+contz+"',\n" +
                                    "        '"+pass1+"',\n" +
                                    "        '"+stat+"',\n" +
                                    "        '"+verfinaction_id+"');";

                                    DbConnection.iud(que_fir);
                                    sendMail(email,verfinaction_id);
                                    Toast.makeText(signup.this, "User Registered Succescfully..", Toast.LENGTH_LONG).show();
                                    Intent intent =new Intent(signup.this,signin.class);
                                    startActivity(intent);
                        }



                }catch (Exception ee){
                        Toast.makeText(signup.this, "err"+ee, Toast.LENGTH_LONG).show();
                    }
                }

            }
        });





    }

    private void sendMail(String sentemailz,String descriptionz) {
        try {
            String subject = "Your email verification from Health App";

            //Send Mail
            JavaMailApi javaMailAPI = new JavaMailApi(this,sentemailz,subject,descriptionz);

            javaMailAPI.execute();

        }catch (Exception emer){
            Toast.makeText(this, "email"+emer, Toast.LENGTH_SHORT).show();
        }

    }
//    @Override
//    public void processFinish(String s) {
//        String getresponse =s;
//        //Toast.makeText(signup.this, "---"+s, Toast.LENGTH_LONG).show();
//
//        if(getresponse.equals("ok")){
//
//            Toast.makeText(signup.this, "User Registered Succescfully..", Toast.LENGTH_LONG).show();
//        }else if(getresponse.equals("Already")){
//
//            Toast.makeText(signup.this, "This Email is Alredy Registered..", Toast.LENGTH_LONG).show();
//        }
//    }


}