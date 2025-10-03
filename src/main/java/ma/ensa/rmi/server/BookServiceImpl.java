package ma.ensa.rmi.server;

import ma.ensa.rmi.common.Book;
import ma.ensa.rmi.common.BookService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation of the remote book service
 * Extends UnicastRemoteObject to enable RMI functionality
 */
public class BookServiceImpl extends UnicastRemoteObject implements BookService {
    
    // Data storage using HashMap for fast ISBN lookups
    private Map<String, Book> books;
    
    /**
     * Constructor
     * @throws RemoteException if the object cannot be exported
     */
    public BookServiceImpl() throws RemoteException {
        super();
        this.books = new HashMap<>();
        // Initialize with some sample data
        initializeSampleData();
    }
    
    /**
     * Initialize the library with some sample books
     */
    private void initializeSampleData() {
        try {
            addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", 2017, "Programming"));
            addBook(new Book("978-0596009205", "Head First Design Patterns", "Eric Freeman", 2004, "Programming"));
            addBook(new Book("978-0321356680", "Effective Java Programming", "Joshua Bloch", 2008, "Programming"));
        } catch (RemoteException e) {
            System.err.println("Error initializing sample data: " + e.getMessage());
        }
    }
    
    @Override
    public boolean addBook(Book book) throws RemoteException {
        if (book == null || book.getIsbn() == null || book.getIsbn().trim().isEmpty()) {
            return false;
        }
        
        // Check if book already exists
        if (books.containsKey(book.getIsbn())) {
            return false; // Book already exists
        }
        
        books.put(book.getIsbn(), book);
        System.out.println("Book added: " + book.getTitle() + " by " + book.getAuthor());
        return true;
    }
    
    @Override
    public Book searchBookByIsbn(String isbn) throws RemoteException {
        if (isbn == null || isbn.trim().isEmpty()) {
            return null;
        }
        return books.get(isbn);
    }
    
    @Override
    public List<Book> searchBooksByTitle(String title) throws RemoteException {
        if (title == null || title.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String searchTitle = title.toLowerCase();
        return books.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(searchTitle))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Book> searchBooksByAuthor(String author) throws RemoteException {
        if (author == null || author.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String searchAuthor = author.toLowerCase();
        return books.values().stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(searchAuthor))
                .collect(Collectors.toList());
    }
    
    @Override
    public List<Book> getAllBooks() throws RemoteException {
        return new ArrayList<>(books.values());
    }
    
    @Override
    public int getBookCount() throws RemoteException {
        return books.size();
    }
}
