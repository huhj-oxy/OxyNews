package com.example.oxynews;

public class ArticleData {
    private String articleId;
    private String title;
    private String author;
    private Integer date;
    private String[] tags;
    private String text;
    private int image;

    public ArticleData(){
        this.articleId = "";
        this.title = "";
        this.author = "";
        this.date = 0;
        this.tags = new String[3];
        this.text = "";
    }

    public ArticleData(String[] words){
        this.articleId = words[0];
        this.title = words[1];
        this.author = words[2];
        this.date = Integer.parseInt(words[3]);
        this.tags = words[4].split(",");
        this.text = words[5];
    }

    public ArticleData(String[] words, int image){
        this.articleId = words[0];
        this.title = words[1];
        this.author = words[2];
        this.date = Integer.parseInt(words[3]);
        this.tags = words[4].split(",");
        this.text = words[5];
        this.image = image;
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

    public String getText(){return this.text;}

    public int getImage(){return this.image;}
}
