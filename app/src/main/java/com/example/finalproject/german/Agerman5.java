package com.example.finalproject.german;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.finalproject.R;
import com.example.finalproject.french.Afrench3;
import com.example.finalproject.french.Afrench5;
import com.example.finalproject.french.SplashScreen3;
import com.example.finalproject.home;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Locale;

public class Agerman5 extends AppCompatActivity {
    ImageView ib;
    EditText e1;
    Button b;
    ImageButton b1;
    static final int REQUEST_PERMISSION_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agerman5);
        getSupportActionBar().hide();
        ib = findViewById(R.id.ib2);
        e1 = findViewById(R.id.ts1);
        b = findViewById(R.id.b1);
        b1 = findViewById(R.id.mic);
        MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.gta5);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
            }
        });
        ImageView backbtn = findViewById(R.id.close);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Agerman5.this, home.class);
                startActivity(i);
                finish();
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Say something");
                try {
                    startActivityForResult(intent, REQUEST_PERMISSION_CODE);
                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(Agerman5.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((e1.getText().toString().trim().equals("nice to meet you"))||(e1.getText().toString().trim().equals("Nice to meet you")))
                {
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Agerman5.this);
                    bottomSheetDialog.setContentView(R.layout.correct);
                    Button button1 = bottomSheetDialog.findViewById(R.id.con);
                    bottomSheetDialog.show();
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(Agerman5.this, GSplashScreen3.class);
                            startActivity(i);
                            bottomSheetDialog.hide();
                        }
                    });
                }
                else
                {
                    BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Agerman5.this);
                    bottomSheetDialog.setContentView(R.layout.incorr);
                    Button button1 = bottomSheetDialog.findViewById(R.id.incon);
                    bottomSheetDialog.show();
                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            e1.setText("");
                            bottomSheetDialog.hide();
                        }
                    });
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Agerman5.this, home.class);
        startActivity(i);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_CODE){
            if(resultCode == RESULT_OK && data != null)
            {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                e1.setText(result.get(0));
            }

        }
    }
}