 Customer Management CRUD Application

## Overview
This project is a CRUD application for managing customers. It includes functionalities for creating, updating, retrieving, and deleting customer records. Additionally, it has a feature to sync customer data from a remote API.

## Technologies Used
- Spring Boot
- Spring Data JPA
- Spring Security with JWT
- MySQL
- HTML/CSS/JavaScript

## Setup Instructions
1. Clone the repository.
2. Configure the MySQL database in the `application.properties` file.
3. Run the application using `mvn spring-boot:run`.
4. Access the application in your browser at `http://localhost:8080`.

## API Endpoints
- `POST /api/customers`: Create a new customer.
- `PUT /api/customers/{id}`: Update an existing customer.
- `GET /api/customers`: Get a list of customers with pagination.
- `GET /api/customers/{id}`: Get a customer by ID.
- `DELETE /api/customers/{id}`: Delete a customer.
- `POST /authenticate`: Authenticate and get a JWT token.

## Frontend
The frontend consists of three screens:
1. Login Screen
2. Customer List Screen
3. Add Customer Screen

## Sync Feature
- Fetches the customer list from a remote API and saves or updates the customers in the local database.
