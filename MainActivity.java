package com.example.oxynews2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String MY_FILE_NAME;
    ArrayList<ArticleData> articleArr = new ArrayList<ArticleData>();

    public String readTextFile(){
        MY_FILE_NAME = ".txt";
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, MY_FILE_NAME);
        byte[] content = new byte[(int) readFrom.length()];
        try{
            FileInputStream fileis = new FileInputStream(readFrom);
            fileis.read(content);
            String stringContent = new String(content);

            Log.i("XXX", stringContent);
            return stringContent;
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void readTsvFile(){
        try(InputStream stream = getResources().openRawResource(R.raw.test)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

            int lineCounter = 0;
            String line;
            while((line = reader.readLine()) != null) {
                if (lineCounter >= 1) {
                    String[] words = line.split("\t");

                    ArticleData article = new ArticleData(words);
                    articleArr.add(article);
                }
                lineCounter++;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        readTsvFile();
        for(ArticleData article: articleArr){
            Log.i("XXX", article.getTitle());
            Log.i("XXX", article.getAuthor());
            Log.i("XXX", article.getDate());
            Log.i("XXX", "- - - - -");
        }
    }
}