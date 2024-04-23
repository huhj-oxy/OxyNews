package com.example.oxynews;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

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
    String MY_FILE_NAME;
    ArrayList<ArticleData> articleArr = new ArrayList<ArticleData>();

    TextView articleTitle;
    TextView articleAuthor;
    TextView articleDate;
    TextView articleText;

    public String readTextFile(String filePath) throws IOException {
        MY_FILE_NAME = filePath + ".txt";
        File path = getApplicationContext().getFilesDir();
        File readFrom = new File(path, MY_FILE_NAME);
        byte[] content = new byte[(int) readFrom.length()];
        try{
            FileInputStream fileis = new FileInputStream(readFrom);
            fileis.read(content);
            String stringContent = new String(content);
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
        setContentView(R.layout.activity_article_full_view);

        articleTitle = findViewById(R.id.articleTitle);
        articleAuthor = findViewById(R.id.articleAuthor);
        articleDate = findViewById(R.id.articleDate);
        articleText = findViewById(R.id.articleText);

        readTsvFile();

        ArticleData test = articleArr.get(0);
        articleTitle.setText(test.getTitle());
        articleAuthor.setText(test.getAuthor());
        articleDate.setText(test.getDateToString());
        try {
            articleText.setText(readTextFile(test.getArticleId()));
        } catch(Exception e){
            e.printStackTrace();
        }

        /*for(ArticleData article: articleArr){
            String str = article.getTitle();

            Log.i("XXX", article.getAuthor());
            Log.i("XXX", article.getDateToString());
            try {
                Log.i("XXX", readTextFile("id1"));
            } catch(Exception e){
                e.printStackTrace();
            }
            Log.i("XXX", "- - - - -");
        }*/

        for(ArticleData article: searchByTags("stem")){
            String str = article.getTitle();

            Log.i("XXX", article.getAuthor());
            Log.i("XXX", article.getDateToString());
            try {
                Log.i("XXX", readTextFile("id1"));
            } catch(Exception e){
                e.printStackTrace();
            }
            Log.i("XXX", "- - - - -");
        }
    }

    public ArrayList<ArticleData> searchByTitle(String str){
        ArrayList<ArticleData> result = new ArrayList<ArticleData>();
        String[] words = str.split(" ");
        if(words.length == 0){
            return result;
        }
        for(ArticleData article: articleArr){
            for(String word: words) {
                if (article.getTitle().contains(word)) {
                    result.add(article);
                }
            }
        }
        return result;
    }

    public ArrayList<ArticleData> searchByAuthor(String str){
        ArrayList<ArticleData> result = new ArrayList<ArticleData>();
        String[] words = str.split(" ");
        if(words.length == 0){
            return result;
        }
        for(ArticleData article: articleArr){
            for(String word: words) {
                if (article.getAuthor().contains(word)) {
                    result.add(article);
                }
            }
        }
        return result;
    }

    public PriorityQueue<ArticleData> searchByTags(String str){
        PriorityQueue<ArticleData> result = new PriorityQueue<ArticleData>(5, new ArticleSearchScoreComparator());
        String[] words = str.split(" ");
        if(words.length == 0){
            return result;
        }
        for(ArticleData article: articleArr){
            for(String word: words) {
                for (String tag : article.getTags()) {
                    if (tag.contains(word)) {
                        result.add(article);
                    }
                }
            }
        }
        return result;
    }

    class ArticleSearchScore{
        private ArticleData article;
        private Integer score;
        public ArticleSearchScore(ArticleData article, Integer score){
            this.article = article;
            this.score = score;
        }

        public Integer getScore(){
            return score;
        }
        public ArticleData getArticle(){
            return article;
        }
    }

    class ArticleSearchScoreComparator implements Comparator<ArticleSearchScore>{
        public int compare(ArticleSearchScore article1, ArticleSearchScore article2){
            if(article1.getScore() < article2.getScore()){
                return 1;
            }
            else {
                return -1;
            }
        }
    }
}