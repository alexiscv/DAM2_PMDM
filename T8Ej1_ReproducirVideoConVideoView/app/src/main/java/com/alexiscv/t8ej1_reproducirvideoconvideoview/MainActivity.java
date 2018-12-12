package com.alexiscv.t8ej1_reproducirvideoconvideoview;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private VideoView mVideoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos
        mVideoView = findViewById(R.id.video);

        // De forma alternativa si queremos un streaming usar
        mVideoView.setVideoURI(Uri.parse("http://estudiosestigia.com/video.mp4"));
        //File directorioDescargas = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //mVideoView.setVideoPath(directorioDescargas.toString()+"/video.mp4");

        // Añade un MediaController
        MediaController myController = new MediaController(this);

        // Vincular el MediaController
        myController.setAnchorView(mVideoView);

        // Para vincular el video view al media controler
        mVideoView.setMediaController(myController);

        // Inicia el video
        mVideoView.start();

        // Solicitar el foco
        mVideoView.requestFocus();

    }
}
