#!/bin/bash

# Demo script for RMI Library Management System
echo "========================================"
echo "RMI LIBRARY MANAGEMENT SYSTEM - DEMO"
echo "========================================"
echo

# Compile the project
echo "1. Compiling the project..."
mvn compile -q
echo "   ✓ Compilation successful"
echo

# Check if server is already running
SERVER_PID=$(jps -l | grep "ma.ensa.rmi.server.RMIServer_GestionLivres" | cut -d' ' -f1)
if [ ! -z "$SERVER_PID" ]; then
    echo "2. Server is already running (PID: $SERVER_PID)"
else
    echo "2. Starting RMI Server..."
    echo "   Note: Server will run in background"
    nohup mvn exec:java -Dexec.mainClass="ma.ensa.rmi.server.RMIServer_GestionLivres" > server.log 2>&1 &
    SERVER_PID=$!
    echo "   ✓ Server started (PID: $SERVER_PID)"
    sleep 3  # Give server time to start
fi

echo
echo "3. Server is ready! You can now:"
echo "   - Run the client: mvn exec:java -Dexec.mainClass=\"ma.ensa.rmi.client.RMIClient_GestionLivres\""
echo "   - Or use the script: ./start-client.sh"
echo
echo "4. Available features:"
echo "   • Add books to the library"
echo "   • Search books by ISBN, title, or author"
echo "   • List all books"
echo "   • View statistics"
echo
echo "5. Pre-loaded sample data:"
echo "   • Effective Java by Joshua Bloch (2017)"
echo "   • Head First Design Patterns by Eric Freeman (2004)"
echo "   • Effective Java Programming by Joshua Bloch (2008)"
echo
echo "6. To stop the server:"
echo "   pkill -f \"ma.ensa.rmi.server.RMIServer_GestionLivres\""
echo
echo "========================================"
echo "Demo setup complete! Happy testing!"
echo "========================================"
