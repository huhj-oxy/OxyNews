package com.example.oxynews;

public class ArticleSearchScore {
    private ArticleData article;
    private Integer score;

    public ArticleSearchScore(ArticleData article, Integer score) {
        this.article = article;
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public ArticleData getArticle() {
        return article;

    }
}
