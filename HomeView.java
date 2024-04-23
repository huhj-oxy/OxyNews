package com.example.oxynews;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeView extends AppCompatActivity {

    ArrayList<ArticleCardModel> articleCardModels = new ArrayList<>();

    int[] articleCardImages = {R.drawable.option};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_view);

        RecyclerView recyclerView = findViewById(R.id.cardRecyclerView);

        setUpArticleCardModels();

        //Create adapter AFTER setting up ArticleCardModels
        CardArticle_RecyclerViewAdapter adapter = new CardArticle_RecyclerViewAdapter(this,
                articleCardModels);

        //Attach adapter to our recyclerView
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpArticleCardModels(){
        String[] articleCardTitles = getResources().getStringArray(R.array.article_card_titles);
        String[] articleCardAuthors = getResources().getStringArray(R.array.article_card_authors);
        String[] articleCardDates = getResources().getStringArray(R.array.article_card_dates);

        //REPLACE '10' WITH LENGTH OF 'articleCardTitles' FOR ACTUAL APP
        for(int i = 0; i<10; i++){
            articleCardModels.add(new ArticleCardModel(articleCardTitles[0], articleCardAuthors[0],
                    articleCardDates[0], articleCardImages[0]));
        }


    }

}