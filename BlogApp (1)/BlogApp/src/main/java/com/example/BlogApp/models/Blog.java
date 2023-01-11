package com.example.BlogApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "blog")
public class Blog {
    @NotEmpty
    @Size(min = 2, max = 255)
    private String title;

    @NotEmpty
    @Size(min = 2, max = 255)
    private String author;
    @NotEmpty
    @Size(min = 2, max = 500)
    private String content;
//    private Date dateAdded;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    public Blog() {
        author = null;
        title = null;
        content = null;
    }

    @Column(unique = true, nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(unique = true, nullable = false)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(unique = true, nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


//    public Date getDateAdded() {
//        return dateAdded;
//    }
//
//    public void setDateAdded(Date dateAdded) {
//        this.dateAdded = dateAdded;
//    }

    @Column(unique = true, nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
