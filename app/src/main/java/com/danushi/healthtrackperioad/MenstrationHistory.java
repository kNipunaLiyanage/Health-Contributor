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

public class MenstrationHistory extends AppCompatActivity {

    ListView listView;
    ArrayList<LoadMensHistory> arrayList;
    ArrayAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menstration_history);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();

        listView = findViewById(R.id.listviewassignedorderzcontrahistory);
        arrayList = new ArrayList<LoadMensHistory>();

        try {

            String loading_emailz = DbConnection.emailzloadingz;
            String stat = "checked";
            String sqlload = "select * from usermenstruationtrack where useremailz='" + loading_emailz + "' and statusz='"+stat+"'";
            ResultSet rs = DbConnection.search(sqlload);
            while (rs.next()) {
                LoadMensHistory i = new LoadMensHistory();
                i.mens_predict_date = rs.getString("nextdatez");
                i.actual_date = rs.getString("actualhappenz");

                arrayList.add(i);
            }
            missingloadAdapterHistory my = new missingloadAdapterHistory(this,arrayList);
            listView.setAdapter(my);
        }catch(Exception ee){
            Toast.makeText(this, ""+ee, Toast.LENGTH_SHORT).show();

        }

    }
    class missingloadAdapterHistory extends ArrayAdapter {
        Context c;
        ArrayList<LoadMensHistory> list;

        public missingloadAdapterHistory(Context context ,ArrayList<LoadMensHistory> ar) {

            super(context, R.layout.layout_menshistorydisplay,ar);
            c= context;
            list = ar;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater li =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View v= li.inflate(R.layout.layout_menshistorydisplay,null);

            final LoadMensHistory loadTimeTables = list.get(position);


            final TextView predict_date =(TextView) v.findViewById(R.id.load_predictmens_date_history);
            predict_date.setText("Predicted date is -"+loadTimeTables.mens_predict_date);

            final TextView actualdatez =(TextView) v.findViewById(R.id.load_actualmens_date_history);
            actualdatez.setText("Actual date is -"+loadTimeTables.actual_date);









            return v;
        }


    }
}