package ma.ensa.rmi.server;

import ma.ensa.rmi.common.BookService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * RMI Server for the Library Management System
 * This class creates and registers the remote book service
 */
public class RMIServer_GestionLivres {
    
    public static final String SERVICE_NAME = "BookService";
    public static final int RMI_PORT = 1099;
    
    public static void main(String[] args) {
        try {
            // Create the remote object
            BookService bookService = new BookServiceImpl();
            
            Registry registry;
            try {
                // Try to get existing registry first
                registry = LocateRegistry.getRegistry(RMI_PORT);
                // Test if registry is working
                registry.list();
                System.out.println("Connected to existing RMI registry on port " + RMI_PORT);
            } catch (Exception e) {
                // Create new registry if none exists
                registry = LocateRegistry.createRegistry(RMI_PORT);
                System.out.println("Created new RMI registry on port " + RMI_PORT);
            }
            
            // Bind the remote object to the registry
            registry.rebind(SERVICE_NAME, bookService);
            
            System.out.println("=================================");
            System.out.println("RMI Server - Library Management System");
            System.out.println("=================================");
            System.out.println("Server started successfully!");
            System.out.println("Service Name: " + SERVICE_NAME);
            System.out.println("Port: " + RMI_PORT);
            System.out.println("Server is ready to accept client connections...");
            System.out.println("Press Ctrl+C to stop the server");
            System.out.println("=================================");
            
            // Keep the server running
            Thread.currentThread().join();
            
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
