#!/bin/bash

# RMI Library Management System - Client Launcher
echo "==================================="
echo "Starting RMI Client..."
echo "==================================="

# Compile if needed
echo "Compiling project..."
mvn compile -q

# Start the RMI Client
echo "Connecting to RMI Server..."
mvn exec:java -Dexec.mainClass="ma.ensa.rmi.client.RMIClient_GestionLivres" -q
