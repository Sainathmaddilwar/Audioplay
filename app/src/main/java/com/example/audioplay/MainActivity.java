package com.example.audioplay;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediap;
    AudioManager audio;

    public void playMe(View view) {
        mediap.start();
    }

    public void pauseMe(View view) {
        mediap.stop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediap = MediaPlayer.create(this, R.raw.alone);

        audio =(AudioManager)getSystemService(AUDIO_SERVICE);
        int max=audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current=audio.getStreamVolume(AudioManager.STREAM_MUSIC);
        final SeekBar volume=(SeekBar)findViewById(R.id.seekBar2);
        volume.setMax(max);
        volume.setProgress(current);
        volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audio.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final SeekBar timeline =(SeekBar)findViewById(R.id.Timeline);
        timeline.setMax(mediap.getDuration());
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            timeline.setProgress(mediap.getCurrentPosition());
            }
        },0,1000);
        timeline.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mediap.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
