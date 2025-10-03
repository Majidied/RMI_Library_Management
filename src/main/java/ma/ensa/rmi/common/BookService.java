package ma.ensa.rmi.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Remote interface for book management operations
 * All remote methods must throw RemoteException
 */
public interface BookService extends Remote {
    
    /**
     * Add a new book to the library
     * @param book The book to add
     * @return true if book was added successfully, false if book already exists
     * @throws RemoteException if a communication error occurs
     */
    boolean addBook(Book book) throws RemoteException;
    
    /**
     * Search for a book by ISBN
     * @param isbn The ISBN to search for
     * @return The book if found, null otherwise
     * @throws RemoteException if a communication error occurs
     */
    Book searchBookByIsbn(String isbn) throws RemoteException;
    
    /**
     * Search for books by title (partial match)
     * @param title The title to search for
     * @return List of books matching the title
     * @throws RemoteException if a communication error occurs
     */
    List<Book> searchBooksByTitle(String title) throws RemoteException;
    
    /**
     * Search for books by author (partial match)
     * @param author The author to search for
     * @return List of books by the author
     * @throws RemoteException if a communication error occurs
     */
    List<Book> searchBooksByAuthor(String author) throws RemoteException;
    
    /**
     * Get all books in the library
     * @return List of all books
     * @throws RemoteException if a communication error occurs
     */
    List<Book> getAllBooks() throws RemoteException;
    
    /**
     * Get the total number of books in the library
     * @return Number of books
     * @throws RemoteException if a communication error occurs
     */
    int getBookCount() throws RemoteException;
}
