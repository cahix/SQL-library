package model;

public class Book {

    private String name;
    private String autor;
    private String published;
    private String isbn;
    private String status;

    public Book(String name, String autor, String published, String isbn) {
        this.name = name;
        this.autor = autor;
        this.published = published;
        this.isbn = isbn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getAutor() {
        return autor;
    }

    public String getPublished() {
        return published;
    }

    public String getIsbn() {
        return isbn;
    }
}