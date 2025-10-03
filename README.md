# RMI Library Management System

A distributed library management system implemented using Java RMI (Remote Method Invocation) technology.

## Project Structure

``` bash
src/
├── main/
│   └── java/
│       └── ma/
│           └── ensa/
│               ├── Main.java
│               └── rmi/
│                   ├── common/
│                   │   ├── Book.java              # Serializable book model
│                   │   └── IBookService.java      # Remote interface
│                   ├── server/
│                   │   ├── BookServiceImpl.java   # Remote service implementation
│                   │   └── RMIServer_GestionLivres.java  # RMI Server
│                   └── client/
│                       └── RMIClient_GestionLivres.java  # RMI Client
```

## Components

### 1. Common Components (Shared)

- **Book.java**: Serializable model class with ISBN, title, author, publication year, and genre
- **IBookService.java**: Remote interface defining all available operations

### 2. Server Components

- **BookServiceImpl.java**: Implementation of the remote interface extending UnicastRemoteObject
- **RMIServer_GestionLivres.java**: Server application that creates and registers the remote service

### 3. Client Components

- **RMIClient_GestionLivres.java**: Interactive client application with menu interface

## Features

The system provides the following functionality:

1. **Add Book (Ajouter un livre)**
   - Add new books to the library
   - Validates ISBN uniqueness

2. **Search Books (Rechercher un livre)**
   - Search by ISBN (exact match)
   - Search by title (partial match)
   - Search by author (partial match)

3. **List All Books (Lister tous les livres)**
   - Display all books in the library
   - Shows total count

4. **Statistics**
   - View library statistics
   - Total book count

## How to Run

### Prerequisites

- Java 17 or higher
- Maven (optional, for building)

### Method 1: Using Maven

1. **Compile the project:**

   ```bash
   mvn compile
   ```

2. **Start the RMI Server:**

   ```bash
   mvn exec:java -Dexec.mainClass="ma.ensa.rmi.server.RMIServer_GestionLivres"
   ```

3. **In another terminal, start the Client:**

   ```bash
   mvn exec:java -Dexec.mainClass="ma.ensa.rmi.client.RMIClient_GestionLivres"
   ```

### Method 2: Using Java directly

1. **Compile all Java files:**

   ```bash
   javac -d target/classes src/main/java/ma/ensa/rmi/*/*.java src/main/java/ma/ensa/rmi/*/*/*.java src/main/java/ma/ensa/*.java
   ```

2. **Start the RMI Server:**

   ```bash
   cd target/classes
   java ma.ensa.rmi.server.RMIServer_GestionLivres
   ```

3. **In another terminal, start the Client:**

   ```bash
   cd target/classes
   java ma.ensa.rmi.client.RMIClient_GestionLivres
   ```

## Usage

1. Start the server first - it will create an RMI registry on port 1099
2. Start the client - it will connect to the server automatically
3. Use the interactive menu to perform library operations
4. The server comes pre-loaded with sample books for testing

## Technical Details

- **RMI Port**: 1099 (default)
- **Service Name**: "BookService"
- **Data Storage**: In-memory HashMap for fast ISBN lookups
- **Serialization**: Book objects implement Serializable for RMI transmission
- **Error Handling**: Comprehensive exception handling for network and input errors

## Sample Data

The server initializes with these sample books:

- Effective Java by Joshua Bloch (2017)
- Head First Design Patterns by Eric Freeman (2004)
- Effective Java Programming by Joshua Bloch (2008)

## Architecture

```
Client Application                    Server Application
┌──────────────────────┐              ┌─────────────────────┐
│  RMIClient_          │              │  RMIServer_         │
│  GestionLivres       │              │  GestionLivres      │
│                      │              │                     │
│  ┌───────────────┐   │              │  ┌───────────────┐  │
│  │ User Interface│   │              │  │ RMI Registry  │  │
│  │ (Scanner Menu)│   │              │  │ (Port 1099)   │  │
│  └───────────────┘   │              │  └───────────────┘  │
│           │          │              │           │         │
│           │ RMI Call │              │           │         │
│           └──────────┼──────────────┼───────────┘         │
│                      │              │                     │
└──────────────────────┘              │  ┌─────────────────┐│
                                      │  │ BookServiceImpl ││
Shared Components                     │  │ (Business Logic)││
┌──────────────────────┐              │  └─────────────────┘│
│ IBookService         │              │           │         │
│ (Remote Interface)   │              │  ┌───────────────┐  │
│                      │              │  │   Data Store  │  │
│ Book                 │              │  │   (HashMap)   │  │
│ (Serializable)       │              │  └───────────────┘  │
└──────────────────────┘              └─────────────────────┘
```

This implementation fulfills all the requirements for the TP n°1 Advanced Java EE course exercise.
