package ma.ensa.rmi.client;

import ma.ensa.rmi.common.Book;
import ma.ensa.rmi.common.BookService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

/**
 * RMI Client for the Library Management System
 * Provides an interactive menu interface for book operations
 */
public class RMIClient_GestionLivres {
    
    private static final String SERVER_HOST = "localhost";
    private static final int RMI_PORT = 1099;
    private static final String SERVICE_NAME = "BookService";
    
    private BookService bookService;
    private Scanner scanner;
    
    public RMIClient_GestionLivres() {
        scanner = new Scanner(System.in);
    }
    
    /**
     * Connect to the RMI server
     * @return true if connection successful, false otherwise
     */
    public boolean connect() {
        try {
            Registry registry = LocateRegistry.getRegistry(SERVER_HOST, RMI_PORT);
            bookService = (BookService) registry.lookup(SERVICE_NAME);
            System.out.println("Successfully connected to the library server!");
            return true;
        } catch (Exception e) {
            System.err.println("Failed to connect to server: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Display the main menu
     */
    public void displayMenu() {
        System.out.println("\n=================================");
        System.out.println("  LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=================================");
        System.out.println("1. Ajouter un livre");
        System.out.println("2. Rechercher un livre");
        System.out.println("3. Lister tous les livres");
        System.out.println("4. Statistiques");
        System.out.println("0. Quitter");
        System.out.println("=================================");
        System.out.print("Choisissez une option: ");
    }
    
    /**
     * Add a new book
     */
    public void addBook() {
        try {
            System.out.println("\n--- Ajouter un nouveau livre ---");
            
            System.out.print("ISBN: ");
            String isbn = scanner.nextLine().trim();
            
            System.out.print("Titre: ");
            String title = scanner.nextLine().trim();
            
            System.out.print("Auteur: ");
            String author = scanner.nextLine().trim();
            
            System.out.print("Année de publication: ");
            int year = Integer.parseInt(scanner.nextLine().trim());
            
            System.out.print("Genre: ");
            String genre = scanner.nextLine().trim();
            
            Book book = new Book(isbn, title, author, year, genre);
            
            if (bookService.addBook(book)) {
                System.out.println("✓ Livre ajouté avec succès!");
            } else {
                System.out.println("✗ Échec de l'ajout. Le livre existe peut-être déjà.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Année invalide. Veuillez entrer un nombre.");
        } catch (Exception e) {
            System.out.println("✗ Erreur lors de l'ajout: " + e.getMessage());
        }
    }
    
    /**
     * Search for books
     */
    public void searchBooks() {
        try {
            System.out.println("\n--- Rechercher des livres ---");
            System.out.println("1. Rechercher par ISBN");
            System.out.println("2. Rechercher par titre");
            System.out.println("3. Rechercher par auteur");
            System.out.print("Choisissez le type de recherche: ");
            
            int searchType = Integer.parseInt(scanner.nextLine().trim());
            
            switch (searchType) {
                case 1:
                    searchByIsbn();
                    break;
                case 2:
                    searchByTitle();
                    break;
                case 3:
                    searchByAuthor();
                    break;
                default:
                    System.out.println("Option invalide.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("✗ Veuillez entrer un nombre valide.");
        } catch (Exception e) {
            System.out.println("✗ Erreur lors de la recherche: " + e.getMessage());
        }
    }
    
    /**
     * Search book by ISBN
     */
    private void searchByIsbn() throws Exception {
        System.out.print("Entrez l'ISBN: ");
        String isbn = scanner.nextLine().trim();
        
        Book book = bookService.searchBookByIsbn(isbn);
        if (book != null) {
            System.out.println("\n✓ Livre trouvé:");
            System.out.println(book);
        } else {
            System.out.println("✗ Aucun livre trouvé avec cet ISBN.");
        }
    }
    
    /**
     * Search books by title
     */
    private void searchByTitle() throws Exception {
        System.out.print("Entrez le titre (ou une partie): ");
        String title = scanner.nextLine().trim();
        
        List<Book> books = bookService.searchBooksByTitle(title);
        displaySearchResults(books, "titre");
    }
    
    /**
     * Search books by author
     */
    private void searchByAuthor() throws Exception {
        System.out.print("Entrez l'auteur (ou une partie): ");
        String author = scanner.nextLine().trim();
        
        List<Book> books = bookService.searchBooksByAuthor(author);
        displaySearchResults(books, "auteur");
    }
    
    /**
     * Display search results
     */
    private void displaySearchResults(List<Book> books, String searchType) {
        if (books.isEmpty()) {
            System.out.println("✗ Aucun livre trouvé pour ce " + searchType + ".");
        } else {
            System.out.println("\n✓ " + books.size() + " livre(s) trouvé(s):");
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
    }
    
    /**
     * List all books
     */
    public void listAllBooks() {
        try {
            System.out.println("\n--- Liste de tous les livres ---");
            List<Book> books = bookService.getAllBooks();
            
            if (books.isEmpty()) {
                System.out.println("Aucun livre dans la bibliothèque.");
            } else {
                System.out.println("Nombre total de livres: " + books.size());
                System.out.println();
                for (int i = 0; i < books.size(); i++) {
                    System.out.println((i + 1) + ". " + books.get(i));
                }
            }
            
        } catch (Exception e) {
            System.out.println("✗ Erreur lors de la récupération des livres: " + e.getMessage());
        }
    }
    
    /**
     * Show statistics
     */
    public void showStatistics() {
        try {
            System.out.println("\n--- Statistiques ---");
            int bookCount = bookService.getBookCount();
            System.out.println("Nombre total de livres: " + bookCount);
            
        } catch (Exception e) {
            System.out.println("✗ Erreur lors de la récupération des statistiques: " + e.getMessage());
        }
    }
    
    /**
     * Main program loop
     */
    public void run() {
        System.out.println("=================================");
        System.out.println("CLIENT - LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=================================");
        
        if (!connect()) {
            System.out.println("Impossible de se connecter au serveur. Vérifiez que le serveur est démarré.");
            return;
        }
        
        boolean running = true;
        while (running) {
            displayMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        searchBooks();
                        break;
                    case 3:
                        listAllBooks();
                        break;
                    case 4:
                        showStatistics();
                        break;
                    case 0:
                        running = false;
                        System.out.println("Au revoir!");
                        break;
                    default:
                        System.out.println("Option invalide. Veuillez choisir entre 0 et 4.");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("Veuillez entrer un nombre valide.");
            }
        }
        
        scanner.close();
    }
    
    public static void main(String[] args) {
        RMIClient_GestionLivres client = new RMIClient_GestionLivres();
        client.run();
    }
}
