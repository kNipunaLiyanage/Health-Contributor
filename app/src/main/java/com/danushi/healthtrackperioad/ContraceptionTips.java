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

public class ContraceptionTips extends AppCompatActivity {
    ListView listView;
    ArrayList<LoadConsTips> arrayList;
    ArrayAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contraception_tips);
        listView = findViewById(R.id.listviewassignedorderzcontra);
        arrayList = new ArrayList<LoadConsTips>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();


        try {

            String loading_emailz = DbConnection.emailzloadingz;
            String stat = "Active";
            String sqlload = "select * from contraceptiontips where statusz='" + stat + "'";
            ResultSet rs = DbConnection.search(sqlload);
            while (rs.next()) {
                LoadConsTips i = new LoadConsTips();
                i.tipid = rs.getString("id");
                i.tipname = rs.getString("contraceptivetopic");

                arrayList.add(i);
            }
            missingloadAdapterzz my = new missingloadAdapterzz(this,arrayList);
            listView.setAdapter(my);
        }catch(Exception ee){
            Toast.makeText(this, ""+ee, Toast.LENGTH_SHORT).show();

        }
    }
    class missingloadAdapterzz extends ArrayAdapter {
        Context c;
        ArrayList<LoadConsTips> list;

        public missingloadAdapterzz(Context context ,ArrayList<LoadConsTips> ar) {

            super(context, R.layout.layout_contraception_tips_tips,ar);
            c= context;
            list = ar;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater li =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View v= li.inflate(R.layout.layout_contraception_tips_tips,null);

            final LoadConsTips loadTimeTables = list.get(position);



            TextView orderidhidden =(TextView) v.findViewById(R.id.hiddenidloadcontra);
            orderidhidden.setText(loadTimeTables.tipname);
            // localVari.Preg_topic_Name = loadTimeTables.topicname;


            final TextView topicnamez =(TextView) v.findViewById(R.id.tip_topiccontra);
            topicnamez.setText(""+loadTimeTables.tipname);


            ImageView vieworderdetailsz =(ImageView) v.findViewById(R.id.loadsingleviewcontra);



            vieworderdetailsz.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String ordidz = orderidhidden.getText().toString();
                    DbConnection.load_id_contra_tips = ordidz;
                    Intent ii = new Intent(ContraceptionTips.this, SingleviewContratips.class);
                    startActivity(ii);

                }
            });


            return v;
        }


    }
}