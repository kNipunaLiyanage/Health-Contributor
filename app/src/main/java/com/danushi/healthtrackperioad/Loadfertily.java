package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.transition.Visibility;

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

public class Loadfertily extends AppCompatActivity {

    TextView tv1,tv2,tv3;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loadfertily);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(policy);
        tv1 = findViewById(R.id.loadmensdatez_fertily);
        tv2 = findViewById(R.id.fertily_period);
        tv3 = findViewById(R.id.pregnancy_range);
        b1 = findViewById(R.id.gobackzfer);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Loadfertily.this,NEwHome.class);
                startActivity(i);
            }
        });
        try {
            String useremail = DbConnection.emailzloadingz;
            String statz = "notchecked";
            String sqarch = "select * from usermenstruationtrack where useremailz='"+useremail+"' and statusz='"+statz+"'";
            ResultSet tsd = DbConnection.search(sqarch);
            String loadingdatez = "";
            if(tsd.next()){
                loadingdatez = tsd.getString("nextdatez");
                tv1.setText("Your next menstration date is "+loadingdatez);

                Calendar c = Calendar.getInstance();
                DateFormat format = new SimpleDateFormat("yyyy/M/d", Locale.ENGLISH);
                Date date = format.parse(loadingdatez);
                c.setTime(date);
                Date dateBefore = new Date(date.getTime() - 19 * 24 * 3600 * 1000l );
                Date dateBefore2 = new Date(date.getTime() - 9 * 24 * 3600 * 1000l );
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
                String minusdatex1 = sdf.format(dateBefore);
                String minusdatex2 = sdf.format(dateBefore2);
                //c.re(Calendar.DATE, average_cycle_date2);
                tv2.setText("Your Fertility Period range is\n "+minusdatex1+" to "+minusdatex2);
                Date dateBeforey1 = new Date(date.getTime() - 14 * 24 * 3600 * 1000l );
                Date dateBeforey2 = new Date(date.getTime() - 17 * 24 * 3600 * 1000l );
                String minusdatex1y1 = sdf.format(dateBeforey1);
                String minusdatex2y1 = sdf.format(dateBeforey2);
                tv3.setText("High possibility of getting Pregnant date range is \n"+minusdatex1y1+" to "+minusdatex2y1);





            }else{
                    tv1.setText("Menstration details not found");
                    tv2.setAlpha(0.0f);
                    tv3.setAlpha(0.0f);
            }


        }catch (Exception ee){
            Toast.makeText(Loadfertily.this, ""+ee, Toast.LENGTH_LONG).show();
        }


    }
}