package com.ll;

public class Quote {
    Integer id;
    String sentence;
    String author;


    public Quote() {}

    //getter
    public String getAuthor() {
        return author;
    }

    public String getSentence() {
        return sentence;
    }

    public Integer getId() {
        return id;
    }

    //setter
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
