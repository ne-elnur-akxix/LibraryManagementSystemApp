package net.javaguides.sms.dto;

import lombok.Data;

@Data
public class BooksDTO {
    private int id;
    private int isbn;
    private String name;
    private String author;
    private String publisher;
    private String price;
    private String coverName;
}