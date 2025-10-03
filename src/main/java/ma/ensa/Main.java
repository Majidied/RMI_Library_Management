package ma.ensa;

/**
 * Main class for the RMI Library Management System
 * This serves as an entry point and provides instructions for running the system
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("RMI LIBRARY MANAGEMENT SYSTEM");
        System.out.println("=================================");
        System.out.println();
        System.out.println("To run this distributed application:");
        System.out.println();
        System.out.println("1. Start the RMI Server:");
        System.out.println("   java ma.ensa.rmi.server.RMIServer_GestionLivres");
        System.out.println();
        System.out.println("2. In another terminal, start the Client:");
        System.out.println("   java ma.ensa.rmi.client.RMIClient_GestionLivres");
        System.out.println();
        System.out.println("Components included:");
        System.out.println("- Book model (ma.ensa.rmi.common.Book)");
        System.out.println("- Remote interface (ma.ensa.rmi.common.IBookService)");
        System.out.println("- Server implementation (ma.ensa.rmi.server.BookServiceImpl)");
        System.out.println("- RMI Server (ma.ensa.rmi.server.RMIServer_GestionLivres)");
        System.out.println("- RMI Client (ma.ensa.rmi.client.RMIClient_GestionLivres)");
        System.out.println();
        System.out.println("Features:");
        System.out.println("- Add books to the library");
        System.out.println("- Search books by ISBN, title, or author");
        System.out.println("- List all books");
        System.out.println("- View library statistics");
        System.out.println("=================================");
    }
}