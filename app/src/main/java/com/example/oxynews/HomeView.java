package com.example.oxynews;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class HomeView extends AppCompatActivity {

    ArrayList<ArticleData> articleArr = new ArrayList<ArticleData>();

    ArrayList<ArticleCardModel> articleCardModels = new ArrayList<>();

    int[] articleCardImages = {R.drawable.option};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_view);

        readTsvFile();

        RecyclerView recyclerView = findViewById(R.id.cardRecyclerView);

        setUpArticleCardModels();

        //Create adapter AFTER setting up ArticleCardModels
        CardArticle_RecyclerViewAdapter adapter = new CardArticle_RecyclerViewAdapter(this,
                articleCardModels);

        //Attach adapter to our recyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void readTsvFile(){
        try(InputStream stream = getResources().openRawResource(R.raw.test)){
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));

            int lineCounter = 0;
            String line;
            while((line = reader.readLine()) != null) {
                if (lineCounter >= 1) {
                    String[] words = line.split("&&");
                    Log.i("XXXXX", words[0]);
                    Log.i("XXXXX", words[1]);
                    Log.i("XXXXX", words[2]);
                    Log.i("XXXXX", words[3]);
                    Log.i("XXXXX", words[4]);

                    ArticleData article = new ArticleData(words);
                    articleArr.add(article);
                }
                lineCounter++;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private void setUpArticleCardModels(){
        //REPLACE '10' WITH LENGTH OF 'articleCardTitles' FOR ACTUAL APP
        for(int i = 0; i < articleArr.size(); i++){
            ArticleCardModel cm = new ArticleCardModel(articleArr.get(i).getTitle(), articleArr.get(i).getAuthor(),
                    articleArr.get(i).getDateToString(), articleCardImages[0]);
            articleCardModels.add(cm);
        }

    }
}