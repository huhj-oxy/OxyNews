package com.example.oxynews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {

    EditText fontSizeBox;
    float currentTextSize;
    Button darkOff;
    Button darkOn;
    Button home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_menu);

        //LINK IDs
        fontSizeBox = findViewById(R.id.fontSizeBox);
        darkOff = findViewById(R.id.darkOffButton);
        darkOn = findViewById(R.id.darkOnButton);
        home = findViewById(R.id.button3);

        currentTextSize = TextSizeSingleton.getInstance().getCurrentTextSize();
        fontSizeBox.setText(String.valueOf(currentTextSize));

        fontSizeBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    float newSize = Float.parseFloat(fontSizeBox.getText().toString());
                    TextSizeSingleton.getInstance().setCurrentTextSize(newSize);
                    return true;
                }
                return false;
            }
        });

        darkOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                darkOff(view);
            }
        });

        darkOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                darkOn(view);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeView.class);

                startActivity(intent);
            }
        });

    }//END onCreate

    public void darkOn(View v){AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);}
    public void darkOff(View v){AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);}

}//END MAINACTIVITY CLASS