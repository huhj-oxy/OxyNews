package com.example.oxynews;

public class ArticleData {
    private String articleId;
    private String title;
    private String author;
    private Integer date;
    private String[] tags;

    public ArticleData(){
        this.articleId = "";
        this.title = "";
        this.author = "";
        this.date = 0;
        this.tags = new String[3];
    }

    public ArticleData(String articleId, String title, String author, Integer date, String tags){
        this.articleId = articleId;
        this.title = title;
        this.author = author;
        this.date = date;
        this.tags = tags.split(",");
    }

    public ArticleData(String[] words){
        this.articleId = words[0];
        this.title = words[1];
        this.author = words[2];
        this.date = Integer.parseInt(words[3]);
        this.tags = words[4].split(",");
    }

    public String getArticleId(){
        return this.articleId;
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public Integer getDate(){
        return this.date;
    }

    public String getDateToString(){
        String str = "";
        str += ((date % 10000) / 100) + "/" + (date % 100) + "/" + (date / 10000);
        return str;
    }

    public String[] getTags(){
        return this.tags;
    }
}
