package net.javaguides.sms.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MyBooks")
public class MyBookList {

    @Id
    private int id;
    private long isbn;
    private String name;
    private String author;
    private String price;
    private String title;
    private String publisher;

    public MyBookList(int id, String name, String author, String price, String title, String publisher) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.title = title;
        this.isbn = isbn;
        this.publisher= publisher;
    }

    public MyBookList() {
        super();
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}