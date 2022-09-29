package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MensHomenew extends AppCompatActivity {
    EditText selectdate,avaragecycle;
    Button menssave,dontremember;
    Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mens_homenew);


        selectdate = findViewById(R.id.menstrationdate);
        avaragecycle = findViewById(R.id.avaragedate);
        menssave = findViewById(R.id.savemens);
        dontremember = findViewById(R.id.dontrememberavaer);
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
        selectdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(MensHomenew.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        menssave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected_date = selectdate.getText().toString();
                String avarage_date = avaragecycle.getText().toString();
                if(selected_date.equals("")){
                    Toast.makeText(MensHomenew.this, "Select valid date..", Toast.LENGTH_SHORT).show();
                }else if(avarage_date.equals("")){
                    Toast.makeText(MensHomenew.this, "Enter date cycle..", Toast.LENGTH_SHORT).show();

                }else{
                    try {

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
                        Calendar c = Calendar.getInstance();
                        DateFormat format = new SimpleDateFormat("yyyy/M/d", Locale.ENGLISH);
                        Date date = format.parse(selected_date);
                        c.setTime(date);
                        String output = sdf.format(c.getTime());
                        int average_cycle_date = Integer.parseInt(avarage_date);
                        c.add(Calendar.DATE, average_cycle_date);
                        int month_in_calender =  c.get(Calendar.MONTH)+1;
                        String final_date = c.get(Calendar.YEAR)+"/"+month_in_calender+"/"+c.get(Calendar.DATE);


                        Intent i = new Intent(MensHomenew.this,FirstUserResultShow.class);
                        i.putExtra("days",final_date);
                        i.putExtra("cycle",""+average_cycle_date);
                        i.putExtra("seledate",""+selected_date);
                        startActivity(i);


                    }catch (Exception e){
                        AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(MensHomenew.this);
                        builder.setMessage("Please Select Menstruation date in calender...!");
                        builder.setTitle("Invalid Input");
                        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                }

            }
        });
        dontremember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selected_date = selectdate.getText().toString();
                String avarage_date = avaragecycle.getText().toString();
                if(selected_date.equals("")){
                    Toast.makeText(MensHomenew.this, "Select valid date..", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
                        Calendar c = Calendar.getInstance();
                        DateFormat format = new SimpleDateFormat("yyyy/M/d", Locale.ENGLISH);
                        Date date = format.parse(selected_date);
                        c.setTime(date);
                        String output = sdf.format(c.getTime());
                        int average_cycle_date = 28;
                        c.add(Calendar.DATE, average_cycle_date);
                        int month_in_calender =  c.get(Calendar.MONTH)+1;
                        String final_date = c.get(Calendar.YEAR)+"/"+month_in_calender+"/"+c.get(Calendar.DATE);
                        Intent i = new Intent(MensHomenew.this,FirstUserResultShow.class);
                        i.putExtra("days",final_date);
                        i.putExtra("cycle",""+average_cycle_date);
                        i.putExtra("seledate",""+selected_date);
                        startActivity(i);


                    }catch (Exception e){
                        AlertDialog.Builder builder
                                = new AlertDialog
                                .Builder(MensHomenew.this);
                        builder.setMessage("Please Select Menstruation date in calender...!");
                        builder.setTitle("Invalid Input");
                        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        builder.show();
                    }
                }
            }
        });


    }
    private void updateLabel() {
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        selectdate.setText(sdf.format(myCalendar.getTime()));
    }
}