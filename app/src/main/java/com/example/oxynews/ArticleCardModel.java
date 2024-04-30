package com.example.oxynews;

public class ArticleCardModel {

    //DATA
    String cardTitle;
    String cardAuthor;
    String cardDate;
    String text;
    int image;

    //CREATE CONSTRUCTOR
    public ArticleCardModel(String cardTitle, String cardAuthor, String cardDate, int image, String text) {
        this.cardTitle = cardTitle;
        this.cardAuthor = cardAuthor;
        this.cardDate = cardDate;
        this.image = image;
        this.text = text;
    }

    //CREATE GETTER METHODS
    public String getCardTitle() {
        return cardTitle;
    }

    public String getCardAuthor() {
        return cardAuthor;
    }

    public String getCardDate() {
        return cardDate;
    }

    public int getImage() {
        return image;
    }

    public String getText(){
        return text;
    }
}
