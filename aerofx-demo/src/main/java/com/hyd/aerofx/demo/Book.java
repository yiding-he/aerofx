package com.hyd.aerofx.demo;

public class Book {

    private String name;

    private String author;

    private String publishDate;

    public Book() {
    }

    public Book(String name, String author, String publishDate) {
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }
}
