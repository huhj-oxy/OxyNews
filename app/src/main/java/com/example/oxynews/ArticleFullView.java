package com.example.oxynews;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ArticleFullView extends AppCompatActivity {

    //DECLARE CLASS VARIABLES
    //private ArticleData articleData;
    //private List<ArticleCardView> recommendedArticles;
    private ScrollView articleScrollView;
    private TextView articleTitle;
    private TextView articleAuthor;
    private TextView articleDate;
    private TextView articleText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_article_full_view);

        //GET INTENTS FROM HOMEVIEW
        String title = getIntent().getStringExtra("TITLE");
        String author = getIntent().getStringExtra("AUTHOR");
        String date = getIntent().getStringExtra("DATE");
        String text = getIntent().getStringExtra("TEXT");

        //MATCH VARIABLES TO THEIR LAYOUT ID's
        articleScrollView = findViewById(R.id.articleScrollView);
        articleTitle = findViewById(R.id.articleTitle);
        articleAuthor = findViewById(R.id.articleAuthor);
        articleDate = findViewById(R.id.articleDate);
        articleText = findViewById(R.id.articleText);

        //SET VARIABLES TO THEIR ACTUAL VALUES
        articleText.setText(text);
        articleTitle.setText(title);
        articleAuthor.setText(author);
        articleDate.setText(date);

    }
}