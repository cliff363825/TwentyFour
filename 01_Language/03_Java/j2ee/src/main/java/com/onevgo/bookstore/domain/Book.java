package com.onevgo.bookstore.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class Book {
    private Integer id;
    private String author;
    private String title;
    private float price;
    private Date publishingDate;
    private int salesAmount;
    private int storeNumber;
    private String remark;
}
