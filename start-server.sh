#!/bin/bash

# RMI Library Management System - Server Launcher
echo "==================================="
echo "Starting RMI Server..."
echo "==================================="

# Compile if needed
echo "Compiling project..."
mvn compile -q

# Start the RMI Server
echo "Starting RMI Server on port 1099..."
mvn exec:java -Dexec.mainClass="ma.ensa.rmi.server.RMIServer_GestionLivres" -q
