package net.javaguides.sms.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MyBooks")
public class MyBookList {

    @Id
    private int id;
    private int isbn;
    private String name;
    private String author;
    private String price;
    private String publisher;
    private String coverName;

    public String getCoverName() {
        return coverName;
    }
    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    public MyBookList(int id, int isbn, String name, String author, String price, String publisher, String coverName) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
        this.publisher = publisher;
        this.coverName = coverName;
    }

    public MyBookList() {
        super();
    }

    public int getIsbn() { return isbn; }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
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