package com.example.oxynews;

import java.util.Comparator;

public class ArticleSearchScoreComparator implements Comparator<ArticleSearchScore> {
    public int compare(ArticleSearchScore article1, ArticleSearchScore article2) {
        if (article1.getScore() < article2.getScore()) {
            return 1;
        } else {
            return -1;
        }
    }
}
