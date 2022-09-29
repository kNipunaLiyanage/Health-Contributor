package com.danushi.healthtrackperioad;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;

public class MusicPlayer extends AppCompatActivity {
    ImageView playerbtn;
    TextView currentttime,totalduration;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    private Handler handler=new Handler();
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_music_player);


        try {


            String idz = DbConnection.music_loadingid;
            String sqlload = "select * from musicplayerdetailsz where id='"+idz+"'";
            ResultSet rs = DbConnection.search(sqlload);
            if (rs.next()){

                url =rs.getString("urlofmusic");


            }


        }catch (Exception ee){
            Toast.makeText(this, "err--"+ee, Toast.LENGTH_SHORT).show();
        }

        playerbtn = findViewById(R.id.imageplaypause);
        currentttime = findViewById(R.id.currenttexttime);
        totalduration = findViewById(R.id.totalduration);
        seekBar = findViewById(R.id.playerseekbar);
        mediaPlayer = new MediaPlayer();
        seekBar.setMax(100);
        playerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    playerbtn.setImageResource(R.drawable.ic_play);
                }else {
                    mediaPlayer.start();
                    playerbtn.setImageResource(R.drawable.ic_pause);
                    updateSeekBar();
                }
            }
        });

        prepareMediaPlayer(url);
        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar = (SeekBar) v;
                int position = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                mediaPlayer.seekTo(position);
                currentttime.setText(milisecondsTotime(mediaPlayer.getCurrentPosition()));
                return false;
            }
        });

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                seekBar.setSecondaryProgress(percent);
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                seekBar.setProgress(0);
                playerbtn.setImageResource(R.drawable.ic_play);
                currentttime.setText("0:00");
                totalduration.setText("0:00");
                mediaPlayer.reset();
                prepareMediaPlayer(url);

            }
        });
    }

    private void prepareMediaPlayer(String urlz){

        try {
            System.out.println("Loadingz"+urlz);
            mediaPlayer.setDataSource(urlz);
            mediaPlayer.prepare();
            totalduration.setText(milisecondsTotime(mediaPlayer.getDuration()));

        }catch (Exception ee){

            Toast.makeText(MusicPlayer.this, "plater error"+ee, Toast.LENGTH_LONG).show();
        }


    }


    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            long currentduration = mediaPlayer.getCurrentPosition();
            currentttime.setText(milisecondsTotime(currentduration));
        }
    };


    private void updateSeekBar(){

        if(mediaPlayer.isPlaying()){
            seekBar.setProgress((int)(((float)mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration())*100));
            handler.postDelayed(updater,1000);
        }


    }


    private String milisecondsTotime(long miliseconds){
        String timerstring = "";
        String secondstring;

        int hours = (int) (miliseconds / (1000 * 60 *60));
        int minutes = (int) (miliseconds % (1000 * 60 *60) /(1000*60));
        int seconds = (int) (miliseconds % (1000 * 60 *60) % (1000*60) / (1000));
        if(hours>0){
            timerstring = hours+":";
        }
        if(seconds < 10){
            secondstring = "0"+seconds;
        }else{
            secondstring = ""+seconds;
        }
        timerstring = timerstring + minutes +":"+secondstring;

        return timerstring;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        seekBar.setProgress(0);
        playerbtn.setImageResource(R.drawable.ic_play);
        currentttime.setText("0:00");
        totalduration.setText("0:00");
        mediaPlayer.reset();
        prepareMediaPlayer(url);
    }
}