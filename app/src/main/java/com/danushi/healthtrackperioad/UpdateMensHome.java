package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UpdateMensHome extends AppCompatActivity {
    TextView loadnextdatez;
    Button actdate,btnnewdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_update_mens_home);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);

        loadnextdatez = findViewById(R.id.loadnextdate);
        //loadnextdatez.setText(DbConnection.load_nextdate);
        loadnextdatez.setText(""+DbConnection.load_nextdate);

        actdate = findViewById(R.id.actualdatez);
        btnnewdate = findViewById(R.id.notactualdate);

        actdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        try {
                            String loaded_nextdate = DbConnection.load_nextdate;
                            //String loaded_averagecycle = DbConnection.load_avetagecycle;

                            String searchz = "Select * from usermenstruationtrack where useremailz='"+DbConnection.emailzloadingz+"'";
                            ResultSet rs = DbConnection.search(searchz);
                            int resultsize = 0;
                            if(rs != null){
                                rs.beforeFirst();
                                rs.last();
                                resultsize = rs.getRow();
                            }

                            if(resultsize == 1){

                                try {
                                    String avgl = DbConnection.load_avetagecycle;


                                    String avaragecycle = avgl;


                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
                                    Calendar c = Calendar.getInstance();
                                    DateFormat format = new SimpleDateFormat("yyyy/M/d", Locale.ENGLISH);
                                    Date date = format.parse(loaded_nextdate);
                                    c.setTime(date);
                                    String output = sdf.format(c.getTime());
                                    int average_cycle_date = Integer.parseInt(avaragecycle);
                                    c.add(Calendar.DATE, average_cycle_date);
                                    int month_in_calender =  c.get(Calendar.MONTH)+1;
                                    String final_date = c.get(Calendar.YEAR)+"/"+month_in_calender+"/"+c.get(Calendar.DATE);

                                    String st1 = "notchecked";
                                    String st2 = "checked";
                                    String hh="insert into usermenstruationtrack(usernamez,useremailz,selecteddatez,nextdatez,avaragecyclez,statusz) values('"+DbConnection.usernameloadingz+"','"+DbConnection.emailzloadingz+"','"+loaded_nextdate+"','"+final_date+"','"+avaragecycle+"','"+st1+"')";
                                    String hh2="update usermenstruationtrack set statusz='"+st2+"',actualhappenz='"+loaded_nextdate+"' where useremailz='"+DbConnection.emailzloadingz+"' and statusz='"+st1+"'";



                                    DbConnection.iud(hh2);
                                    DbConnection.iud(hh);
                                    Toast.makeText(UpdateMensHome.this, "Data saved...", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(UpdateMensHome.this,NEwHome.class);
                                    startActivity(i);


                                }
                                catch (NumberFormatException e) {
                                    Toast.makeText(UpdateMensHome.this, ""+e, Toast.LENGTH_SHORT).show();
                                }


                            }else if(resultsize > 1){

                                double insertedcount =0;
                                double avaragetotal =0;
                                String searchz2 = "Select * from usermenstruationtrack where useremailz='"+DbConnection.emailzloadingz+"' and statusz= 'checked'";
                                ResultSet rs2 = DbConnection.search(searchz2);
                                while (rs2.next()){
                                    insertedcount = insertedcount + 1;
                                    avaragetotal = avaragetotal + Double.parseDouble(rs2.getString("avaragecyclez"));
                                }
//                                double fz = avaragetotal + Double.parseDouble(cycleof_date);
//                                double bedz = insertedcount+1;

                                double avgcycle = avaragetotal / insertedcount;
                                String ss = ""+avgcycle;
                                String dd= ss.split("\\.")[0];
                                int average_cycle_date2 = Integer.parseInt(dd);
                                Calendar c = Calendar.getInstance();
                                DateFormat format = new SimpleDateFormat("yyyy/M/d", Locale.ENGLISH);
                                Date date = format.parse(loadnextdatez.getText().toString());
                                c.setTime(date);
                                c.add(Calendar.DATE, average_cycle_date2);
                                int month_in_calender =  c.get(Calendar.MONTH)+1;
                                String final_date = c.get(Calendar.YEAR)+"/"+month_in_calender+"/"+c.get(Calendar.DATE);

                                String st1 = "notchecked";
                                String st2 = "checked";
                                String hh="insert into usermenstruationtrack(usernamez,useremailz,selecteddatez,nextdatez,avaragecyclez,statusz) values('"+DbConnection.usernameloadingz+"','"+DbConnection.emailzloadingz+"','"+loaded_nextdate+"','"+final_date+"','"+dd+"','"+st1+"')";
                                String hh2="update usermenstruationtrack set statusz='"+st2+"',actualhappenz='"+loaded_nextdate+"' where useremailz='"+DbConnection.emailzloadingz+"' and statusz='"+st1+"'";



                                DbConnection.iud(hh2);
                                DbConnection.iud(hh);
                                Toast.makeText(UpdateMensHome.this, "Data saved...", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(UpdateMensHome.this,NEwHome.class);
                                startActivity(i);



                            }









                        }catch (Exception gg){
                            Toast.makeText(UpdateMensHome.this, ""+gg, Toast.LENGTH_SHORT).show();
                        }

            }
        });
        btnnewdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UpdateMensHome.this,SelectNewMensDate.class);
                startActivity(i);
            }
        });



    }
}