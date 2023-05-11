package net.javaguides.sms.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book{
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
    private String author;
    private String name;
    private String price;
    private String title;
    private String publisher;
    private String isbn;

    public Book(int id, String author, String name, String price, String title, String publisher, String isbn) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.price = price;
        this.title = title;
        this.isbn = isbn;
        this.publisher= publisher;
    }
    public Book(){
        super();
    }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getPublisher() {return publisher;}

    public void setPublisher(String publisher) {this.publisher = publisher;}

    public String getIsbn() {return isbn;}

    public void setIsbn(String isbn) {this.isbn = isbn;}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCover(byte[] coverData) {
    }
}
