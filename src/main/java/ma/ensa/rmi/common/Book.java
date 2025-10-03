package ma.ensa.rmi.common;

import java.io.Serializable;

/**
 * Book model class representing a book in the library
 * Implements Serializable to allow transmission over RMI
 */
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String isbn;
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    
    // Default constructor
    public Book() {
    }
    
    // Parameterized constructor
    public Book(String isbn, String title, String author, int publicationYear, String genre) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.genre = genre;
    }
    
    // Getters and Setters
    public String getIsbn() {
        return isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }
    
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    @Override
    public String toString() {
        return String.format("Book{ISBN='%s', title='%s', author='%s', year=%d, genre='%s'}",
                isbn, title, author, publicationYear, genre);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return isbn != null ? isbn.equals(book.isbn) : book.isbn == null;
    }
    
    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }
}
