# Microservices Learning Project

Welcome to the **Microservices Learning Project** repository! This project is a hands-on learning platform for exploring microservices architecture, RabbitMQ, AWS SNS, and more, using Spring Boot.

---

## Overview

This repository includes three microservices that work together to demonstrate the fundamental concepts of:

- Microservices communication
- Asynchronous messaging with RabbitMQ
- Notifications with AWS SNS
- Containerized deployment using Docker
- Database management with PostgreSQL

The microservices included are:

1. **Main App**
    - Receives user requests.
    - Saves data to the database.
    - Sends messages to RabbitMQ queues.

2. **Notification Service**
    - Listens to RabbitMQ queues.
    - Sends notifications via AWS SNS.

3. **Credit Analysis Service**
    - Contains the business logic for credit analysis.
    - Processes data and evaluates credit requests.

---

## Technologies Used

- **Spring Boot**: Java-based framework for building microservices.
- **RabbitMQ**: Message broker for asynchronous communication.
- **AWS SNS**: Amazon Simple Notification Service for sending notifications.
- **Docker**: Containerization platform for deploying applications.
- **Docker Compose**: Tool for defining and running multi-container Docker applications.
- **PostgreSQL**: Relational database for data persistence.

---
