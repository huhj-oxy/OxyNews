package com.example.oxynews;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Search {
    public static PriorityQueue<ArticleSearchScore> searchByTitle(ArrayList<ArticleData> articleArr, String search){
        PriorityQueue<ArticleSearchScore> result = new PriorityQueue<ArticleSearchScore>(new ArticleSearchScoreComparator());

        for(ArticleData article: articleArr){
            if(article.getArticleId().equals("id0")){
                continue;
            }

            int score = 0;
            String title = article.getTitle();
            String[] words = search.split(" ");
            for(String word: words){
                if(title.toLowerCase().contains(word.toLowerCase())) {
                    score++;
                }
            }
            if(score > 0){
                result.add(new ArticleSearchScore(article, score));
            }
        }

        return result;
    }
}




