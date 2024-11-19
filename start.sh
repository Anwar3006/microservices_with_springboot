#!/bin/bash

# Name of the Spring Boot JAR file
# JAR_NAME="your-spring-boot-app.jar"

# Path to the JAR file (if needed, specify the absolute path)
# JAR_PATH="./$JAR_NAME"

# Log file location (optional)
LOG_FILE="/Users/anwa/Documents/microservices_with_springboot/application.log"


# Path to springboot apps
APP1_PATH="./service_registry/"
APP2_PATH="./gateway/"
APP3_PATH="./multiplication_service/"
APP4_PATH="./gamification_service/"


# Function to start the Spring Boot app
start_app() {
  APP_PATH=$1
  echo "Starting application at path: $APP_PATH"

  # Store the current directory
  CURRENT_DIR=$(pwd)

  # Change to app directory and start the app
  (cd "$APP_PATH" && nohup ./mvnw spring-boot:run > $LOG_FILE 2>&1 &)
  
  echo "Application at $APP_PATH started. Check application.log for logs."

  # Return to the original directory
  cd "$CURRENT_DIR"

  # Wait for 1 second before starting the next app
  sleep 1
}


# Function to stop the Spring Boot app
stop_app() {
  APP_PATH=$1
#   APP_NAME="$APP_PATH/*.java"  # Get the name of the app based on the directory path
  echo "Stopping application at path: $APP_PATH"

# Store the current location
  CURRENT_DIR=$(pwd)

  # Stop the application
  (cd "$APP_PATH" && nohup ./mvnw spring-boot:stop > $LOG_FILE 2>&1 &)

  # Return to the original directory
  cd "$CURRENT_DIR"
}


case "$1" in
  start)
    # Start apps with 1-second interval
    start_app "$APP1_PATH"
    start_app "$APP2_PATH"
    start_app "$APP3_PATH"
    start_app "$APP4_PATH"
    ;;
  stop)
    # To stop applications (you can call stop_app like this if needed)
    stop_app "$APP1_PATH"
    stop_app "$APP2_PATH"
    stop_app "$APP3_PATH"
    stop_app "$APP4_PATH"
    # remove the application.log file
    # rm $LOG_FILE
    ;;
  restart)
    stop_app
    start_app
    ;;
  *)
    echo "Usage: $0 {start|stop}"
    exit 1
    ;;
esac
