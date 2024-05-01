package com.example.oxynews;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class HomeView extends AppCompatActivity implements RecyclerViewInterface{


    ArrayList<ArticleData> articleArr = new ArrayList<ArticleData>();

    ArrayList<ArticleCardModel> articleCardModels = new ArrayList<>();

    int[] articleCardImages = {R.drawable.option};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        openHomeView();
    }

    public void searchByTitle(String search){
        PriorityQueue<ArticleSearchScore> result = Search.searchByTitle(articleArr, search);

        articleCardModels.clear();

        while(!result.isEmpty()){
            ArticleData article = result.poll().getArticle();
            ArticleCardModel cm = new ArticleCardModel(article.getTitle(), article.getAuthor(),
                    article.getDateToString(), articleCardImages[0], article.getText());
            articleCardModels.add(cm);
        }
    }

    public void searchByAuthor(String search){
        PriorityQueue<ArticleSearchScore> result = Search.searchByAuthor(articleArr, search);

        articleCardModels.clear();

        while(!result.isEmpty()){
            ArticleData article = result.poll().getArticle();
            ArticleCardModel cm = new ArticleCardModel(article.getTitle(), article.getAuthor(),
                    article.getDateToString(), articleCardImages[0], article.getText());
            articleCardModels.add(cm);
        }
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
        for(int i = 1; i < articleArr.size(); i++){
            ArticleCardModel cm = new ArticleCardModel(articleArr.get(i).getTitle(), articleArr.get(i).getAuthor(),
                    articleArr.get(i).getDateToString(), articleCardImages[0], articleArr.get(i).getText());
            articleCardModels.add(cm);
        }
    }


    public void toSettings(View v){setContentView(R.layout.settings_menu);

        EditText fontSizeBox = findViewById(R.id.fontSizeBox);

        float currentTextSize = TextSizeSingleton.getInstance().getCurrentTextSize();
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


    } // end of toSettings




    public void toMain(View v){openHomeView();}

    public void darkOn(View v){AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);}
    public void darkOff(View v){AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);}

    // needed to add this method because otherwise returning to the home page wouldn't work
    public void openHomeView(){
        setContentView(R.layout.activity_home_view);

        //updateTextSizeConstraint(findViewById(R.id.main));

        //CLEAR articleArr before reading TSV file
        articleArr.clear();
        readTsvFile();

        RecyclerView recyclerView = findViewById(R.id.cardRecyclerView);

        //CLEAR articleCardModels before setting it up
        articleCardModels.clear();
        setUpArticleCardModels();

        searchByTitle("");
        searchByAuthor("James");

        //Create adapter AFTER setting up ArticleCardModels
        CardArticle_RecyclerViewAdapter adapter = new CardArticle_RecyclerViewAdapter(this,
                articleCardModels, this);

        //Attach adapter to our recyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(HomeView.this, ArticleFullView.class);

        //Log.w("HomeView", "onItemClick: CLICKED");

        intent.putExtra("TITLE", articleCardModels.get(position).getCardTitle());
        intent.putExtra("AUTHOR", articleCardModels.get(position).getCardAuthor());
        intent.putExtra("DATE", articleCardModels.get(position).getCardDate());
        intent.putExtra("TEXT", articleCardModels.get(position).getText());

        startActivity(intent);
    }
}// end of HomeView




