package com.danushi.healthtrackperioad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PregnancyTips extends AppCompatActivity {

    ListView listView;
    ArrayList<LoadPregnancyTips> arrayList;
    ArrayAdapter adp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pregnancy_tips);
        listView = findViewById(R.id.listviewassignedorderz);
        arrayList = new ArrayList<LoadPregnancyTips>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();


        try {

            String loading_emailz = DbConnection.emailzloadingz;
            String stat = "Active";
            String sqlload = "select * from pregnancytopics where statusz='"+stat+"'";
            ResultSet rs = DbConnection.search(sqlload);
            while (rs.next()){
                LoadPregnancyTips i = new LoadPregnancyTips();
                i.tipid =rs.getString("id");
                i.tipname=rs.getString("pregnancytopic");

                arrayList.add(i);
            }

            missingloadAdapter my = new missingloadAdapter(this,arrayList);
            listView.setAdapter(my);

        }catch (Exception ee){
            Toast.makeText(this, "err--"+ee, Toast.LENGTH_SHORT).show();
        }


    }

    class missingloadAdapter extends ArrayAdapter {
        Context c;
        ArrayList<LoadPregnancyTips> list;

        public missingloadAdapter(Context context ,ArrayList<LoadPregnancyTips> ar) {

            super(context, R.layout.layout_loading_prgnancy_tips,ar);
            c= context;
            list = ar;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater li =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View v= li.inflate(R.layout.layout_loading_prgnancy_tips,null);

            final LoadPregnancyTips loadTimeTables = list.get(position);



            TextView orderidhidden =(TextView) v.findViewById(R.id.hiddenidload);
            orderidhidden.setText(loadTimeTables.tipname);
            // localVari.Preg_topic_Name = loadTimeTables.topicname;


            final TextView topicnamez =(TextView) v.findViewById(R.id.tip_topic);
            topicnamez.setText(""+loadTimeTables.tipname);


            ImageView vieworderdetailsz =(ImageView) v.findViewById(R.id.loadsingleview);



            vieworderdetailsz.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String ordidz = orderidhidden.getText().toString();
                    DbConnection.load_id_preg_tips = ordidz;
                    Intent ii = new Intent(PregnancyTips.this, SingleviewPregnancyTips.class);
                    startActivity(ii);

                }
            });


            return v;
        }


    }

}