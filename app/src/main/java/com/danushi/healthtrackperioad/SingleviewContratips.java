package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;

public class SingleviewContratips extends AppCompatActivity {
    TextView tc1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_singleview_contratips);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        tc1 = findViewById(R.id.detailzcontra);
        try {

            String preg_tip_id = DbConnection.load_id_contra_tips;
            String sqlload = "select * from contraceptivedescriptionz where contraceptivetopic='"+preg_tip_id+"'";
            ResultSet rs = DbConnection.search(sqlload);
            if (rs.next()){

                String topic_des=rs.getString("descriptionz");
                tc1.setText(""+topic_des);
            }



        }catch (Exception ee){
            Toast.makeText(this, "err--"+ee, Toast.LENGTH_SHORT).show();
        }

    }
}