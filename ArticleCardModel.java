package com.example.oxynews;

public class ArticleCardModel {

    //DATA
    String cardTitle;
    String cardAuthor;
    String cardDate;
    int image;

    //CREATE CONSTRUCTOR
    public ArticleCardModel(String cardTitle, String cardAuthor, String cardDate, int image) {
        this.cardTitle = cardTitle;
        this.cardAuthor = cardAuthor;
        this.cardDate = cardDate;
        this.image = image;
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
}
