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
    private String publisher;
    private int isbn;

    private String coverName;

    public String getCoverName() {
        return coverName;
    }
    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    public Book(int id, int isbn, String name, String author, String price, String publisher) {
        this.id = id;
        this.author = author;
        this.name = name;
        this.price = price;
        this.isbn = isbn;
        this.publisher= publisher;
    }

    public Book(){
        super();
    }

    public String getPublisher() {return publisher;}

    public void setPublisher(String publisher) {this.publisher = publisher;}

    public int getIsbn() {return isbn;}

    public void setIsbn(int isbn) {this.isbn = isbn;}

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