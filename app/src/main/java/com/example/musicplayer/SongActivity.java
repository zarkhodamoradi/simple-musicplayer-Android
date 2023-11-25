package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;

public class SongActivity extends AppCompatActivity {
    TextView txtSongName;
    TextView txtSingerName;
    ImageButton imgbtnPlay ;
    ImageButton imgbtnNext ;
    ImageButton imgbtnPrev ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        SetUpView();


if(MainActivity.flag)imgbtnPlay.setImageResource(R.drawable.baseline_pause_circle_outline_24);
else imgbtnPlay.setImageResource(R.drawable.baseline_play_circle_24);
        txtSongName.setText(MainActivity.SongsList.get(MainActivity.currentIndex).getSongName());
        txtSingerName.setText(MainActivity.SongsList.get(MainActivity.currentIndex).getSingerName());
        imgbtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.flag== false) {
                    if (MainActivity.mediaPlayer != null) {
                        MainActivity.mediaPlayer.start();
                    } else {

                        //  mediaPlayer = new MediaPlayer();
                        try {
                            MainActivity.mediaPlayer.setDataSource(MainActivity.path);
                            MainActivity.mediaPlayer.prepare();
                            MainActivity.mediaPlayer.start();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    MainActivity.imgbtnPlay.setImageResource(android.R.drawable.ic_media_pause);
                    imgbtnPlay.setImageResource(R.drawable.baseline_pause_circle_outline_24);
                    MainActivity.flag= true;
                } else {
                    if (MainActivity.mediaPlayer != null && MainActivity.mediaPlayer.isPlaying()) {
                        MainActivity.mediaPlayer.pause();
                        MainActivity.imgbtnPlay.setImageResource(android.R.drawable.ic_media_play);
                        imgbtnPlay.setImageResource(R.drawable.baseline_play_circle_24);
                        MainActivity.flag= false;
                    }
                }

                MainActivity.adapter.notifyDataSetChanged();
            }
        });

        imgbtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.SongsList.get(MainActivity.currentIndex).running = false;
                if (MainActivity.currentIndex < MainActivity.SongsList.size() - 1) {
                    MainActivity.currentIndex++;
                    MainActivity.SongsList.get(MainActivity.currentIndex).running = true;
                    MainActivity.playSong(MainActivity.SongsList.get(MainActivity.currentIndex).getPath());

                    if (MainActivity.flag== false) {
                        MainActivity.flag= true;
                        MainActivity.imgbtnPlay.setImageResource(android.R.drawable.ic_media_pause);

                        imgbtnPlay.setImageResource(R.drawable.baseline_pause_circle_outline_24);
                    }
                    txtSongName.setText(MainActivity.SongsList.get(MainActivity.currentIndex).getSongName());
                    txtSingerName.setText(MainActivity.SongsList.get(MainActivity.currentIndex).getSingerName());
                    MainActivity.adapter.notifyDataSetChanged();
                }
            }
        });

        imgbtnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.SongsList.get(MainActivity.currentIndex).running = false;
                if (MainActivity.currentIndex> 0) {
                    MainActivity.currentIndex--;
                    MainActivity.SongsList.get(MainActivity.currentIndex).running = true;
                    MainActivity.playSong(MainActivity.SongsList.get(MainActivity.currentIndex).getPath());
                    if (MainActivity.flag== false) {
                        MainActivity.flag= true;
                        MainActivity.imgbtnPlay.setImageResource(android.R.drawable.ic_media_pause);

                        imgbtnPlay.setImageResource(R.drawable.baseline_pause_circle_outline_24);
                    }
                    MainActivity.adapter.notifyDataSetChanged();
                }
            }
        });

    }

    public void SetUpView() {
        txtSongName = findViewById(R.id.txtSongName);
        txtSingerName = findViewById(R.id.txtSingerName);
        imgbtnPlay = findViewById(R.id.imgbtnPlay);
        imgbtnNext = findViewById(R.id.imgbtnNext);
        imgbtnPrev = findViewById(R.id.imgbtnPrev);

    }
}