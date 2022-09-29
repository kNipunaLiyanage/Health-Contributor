package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SelectNewMensDate extends AppCompatActivity {
    EditText selectdate2;
    Button menssave;
    Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_new_mens_date);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);

        selectdate2 = findViewById(R.id.menstrationdatenewselect);

        menssave = findViewById(R.id.doneupdate);
        myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        selectdate2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(SelectNewMensDate.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        menssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    String searchz = "Select * from usermenstruationtrack where useremailz='"+DbConnection.emailzloadingz+"'";
                    ResultSet rs = DbConnection.search(searchz);
                    int resultsize = 0;
                    if(rs != null){
                        rs.beforeFirst();
                        rs.last();
                        resultsize = rs.getRow();
                    }

                    if(resultsize==1){

                        String selected_date ="";
                        String searchz2 = "Select * from usermenstruationtrack where useremailz='"+DbConnection.emailzloadingz+"'";
                        ResultSet rs2 = DbConnection.search(searchz2);
                        if (rs2.next()){
                            selected_date = rs.getString("selecteddatez");
                        }


                        String actualhappen_date =selectdate2.getText().toString();

                        SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
                        Date d1 = sdf.parse(selected_date);
                        Date d2 = sdf.parse(actualhappen_date);

                        long difference_In_Time
                                = d2.getTime() - d1.getTime();
                        long difference_In_Days
                                = TimeUnit
                                .MILLISECONDS
                                .toDays(difference_In_Time)
                                % 365;
                        String cycleof_date = ""+difference_In_Days;
                        int average_cycle_date2 = Integer.parseInt(cycleof_date);
                        Calendar c = Calendar.getInstance();
                        DateFormat format = new SimpleDateFormat("yyyy/M/d", Locale.ENGLISH);
                        Date date = format.parse(actualhappen_date);
                        c.setTime(date);
                        c.add(Calendar.DATE, average_cycle_date2);
                        int month_in_calender =  c.get(Calendar.MONTH)+1;
                        String final_date = c.get(Calendar.YEAR)+"/"+month_in_calender+"/"+c.get(Calendar.DATE);

                        String st1 = "notchecked";
                        String st2 = "checked";
                        String hh="insert into usermenstruationtrack(usernamez,useremailz,selecteddatez,nextdatez,avaragecyclez,statusz) values('"+DbConnection.usernameloadingz+"','"+DbConnection.emailzloadingz+"','"+final_date+"','"+final_date+"','"+cycleof_date+"','"+st1+"')";
                        String hh2="update usermenstruationtrack set statusz='"+st2+"',actualhappenz='"+actualhappen_date+"',avaragecyclez='"+cycleof_date+"' where useremailz='"+DbConnection.emailzloadingz+"' and statusz='"+st1+"'";



                        DbConnection.iud(hh2);
                        DbConnection.iud(hh);
                        Toast.makeText(SelectNewMensDate.this, "Data saved...", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SelectNewMensDate.this,NEwHome.class);
                        startActivity(i);



                    }else if(resultsize > 1){

                        String selected_date ="";
                        String searchz2 = "Select * from usermenstruationtrack where useremailz='"+DbConnection.emailzloadingz+"' and statusz= 'notchecked'";
                        ResultSet rs2 = DbConnection.search(searchz2);
                        if (rs2.next()){
                            selected_date = rs.getString("selecteddatez");
                        }


                        String actualhappen_date =selectdate2.getText().toString();

                        SimpleDateFormat sdf= new SimpleDateFormat("yyyy/MM/dd");
                        Date d1 = sdf.parse(selected_date);
                        Date d2 = sdf.parse(actualhappen_date);

                        long difference_In_Time
                                = d2.getTime() - d1.getTime();
                        long difference_In_Days
                                = TimeUnit
                                .MILLISECONDS
                                .toDays(difference_In_Time)
                                % 365;

                        String cycleof_date = ""+difference_In_Days;


                        double insertedcount =0;
                        double avaragetotal =0;
                        String searchz3 = "Select * from usermenstruationtrack where useremailz='"+DbConnection.emailzloadingz+"' and statusz='checked'";
                        ResultSet rs3 = DbConnection.search(searchz3);
                        while (rs3.next()){
                            insertedcount = insertedcount + 1;
                            avaragetotal = avaragetotal + Double.parseDouble(rs2.getString("avaragecyclez"));
                        }


                         double fz = avaragetotal + Double.parseDouble(cycleof_date);
                         double bedz = insertedcount+1;

                        double avgcycle = fz / bedz;
                        String ss = ""+avgcycle;
                        String dd= ss.split("\\.")[0];
                        int average_cycle_date2 = Integer.parseInt(dd);
                        //Toast.makeText(SelectNewMensDate.this, "ekathuwena dawas gana."+average_cycle_date2, Toast.LENGTH_LONG).show();
                        Calendar c = Calendar.getInstance();

                        DateFormat format = new SimpleDateFormat("yyyy/M/d", Locale.ENGLISH);
                        Date date = format.parse(selectdate2.getText().toString());
                        c.setTime(date);
                        c.add(Calendar.DATE, average_cycle_date2);
                        int month_in_calender =  c.get(Calendar.MONTH)+1;
                        String final_date = c.get(Calendar.YEAR)+"/"+month_in_calender+"/"+c.get(Calendar.DATE);
                        //Toast.makeText(SelectNewMensDate.this, "dawsa"+final_date, Toast.LENGTH_LONG).show();


//
                        String st1 = "notchecked";
                        String st2 = "checked";
                        String hh="insert into usermenstruationtrack(usernamez,useremailz,selecteddatez,nextdatez,avaragecyclez,statusz) values('"+DbConnection.usernameloadingz+"','"+DbConnection.emailzloadingz+"','"+selectdate2.getText().toString()+"','"+final_date+"','"+average_cycle_date2+"','"+st1+"')";
                        String hh2="update usermenstruationtrack set statusz='"+st2+"',actualhappenz='"+selectdate2.getText().toString()+"',avaragecyclez='"+cycleof_date+"' where useremailz='"+DbConnection.emailzloadingz+"' and statusz='"+st1+"'";



                        DbConnection.iud(hh2);
                        DbConnection.iud(hh);
                        Toast.makeText(SelectNewMensDate.this, "Data saved...", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(SelectNewMensDate.this,NEwHome.class);
                        startActivity(i);

                    }










                }catch (Exception ee){
                    Toast.makeText(SelectNewMensDate.this, ""+ee, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        selectdate2.setText(sdf.format(myCalendar.getTime()));
    }
}