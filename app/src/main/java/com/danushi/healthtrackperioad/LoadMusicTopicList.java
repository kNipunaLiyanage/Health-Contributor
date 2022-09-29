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

public class LoadMusicTopicList extends AppCompatActivity {
    ListView listView;
    ArrayList<LoadMusicList> arrayList;
    ArrayAdapter adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_load_music_topic_list);

        listView = findViewById(R.id.listviewmusic);
        arrayList = new ArrayList<LoadMusicList>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        try {


            String stat = "Active";
            String sqlload = "select * from musicplayerdetailsz where statusz='"+stat+"'";
            ResultSet rs = DbConnection.search(sqlload);
            while (rs.next()){
                LoadMusicList i = new LoadMusicList();
                i.music_id =rs.getString("id");
                i.music_topic=rs.getString("tipicnamez");

                arrayList.add(i);
            }

            missingloadAdapterMussic my = new missingloadAdapterMussic(this,arrayList);
            listView.setAdapter(my);

        }catch (Exception ee){
            Toast.makeText(this, "err--"+ee, Toast.LENGTH_SHORT).show();
        }



    }
    class missingloadAdapterMussic extends ArrayAdapter {
        Context c;
        ArrayList<LoadMusicList> list;

        public missingloadAdapterMussic(Context context ,ArrayList<LoadMusicList> ar) {

            super(context, R.layout.layout_music_list,ar);
            c= context;
            list = ar;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater li =(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View v= li.inflate(R.layout.layout_music_list,null);

            final LoadMusicList loadTimeTables = list.get(position);



            TextView orderidhidden =(TextView) v.findViewById(R.id.hiddenidloadmusic);
            orderidhidden.setText(loadTimeTables.music_id);
            // localVari.Preg_topic_Name = loadTimeTables.topicname;


            final TextView topicnamez =(TextView) v.findViewById(R.id.tip_topic_music);
            topicnamez.setText(""+loadTimeTables.music_topic);


            ImageView vieworderdetailsz =(ImageView) v.findViewById(R.id.loadsingleviewmusic);



            vieworderdetailsz.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String ordidz = orderidhidden.getText().toString();
                    DbConnection.music_loadingid = ordidz;
                    Intent ii = new Intent(LoadMusicTopicList.this, MusicPlayer.class);
                    startActivity(ii);

                }
            });


            return v;
        }


    }
}