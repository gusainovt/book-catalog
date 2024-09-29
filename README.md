## Description

This is a simple RESTful web application for managing a book catalog, developed using Spring Boot.
The application allows users to add, delete, update, and retrieve information about books. 
Data is stored in memory as a list, simplifying development and testing.

## Technology Stack
- **Java**: 17 
- **Spring boot**
- **Maven**

## REST API
1. **Get all books**
- **Method**: `GET`
- **URL**: `/books`
- **Description**: Returns a list of all books in JSON format.
  
2. **Get the book by ID**
- **Method**: `GET`
- **URL**: `/books/{id}`
- **Description**: Returns information about a book by the specified ID.
  
3. **Create a new book**
- **Method**: `POST`
- **URL**: `/books`
- **Description**: Adds a new book. Data should be sent in JSON format.
- **Example JSON**:
```
{
    "id": 0,
    "title": "Book Title",
    "author": "Book Author",
    "description": "Book Description",
    "price": 19.99
}
```

4. **Update the book by ID**
- **Method**: `PUT`
- **URL**: `/books/{id}`
- **Description**: Updates information about a book by the specified ID.
- **Example JSON**:
```
{
    "id": 0,
    "title": "Book Title",
    "author": "Book Author",
    "description": "Book Description",
    "price": 19.99
}
```

5. **Delete the book by ID**
- **Method**: `DELETE`
- **URL**: `/books/{id}`
- **Description**: Deletes a book by the specified ID.

## How to Run the Application
1. Clone the repository.
2. Build the project using Maven: `mvn clean install`
3. Run the application: `mvn spring-boot:run`
4. Open Swagger UI in your browser: `http://localhost:8080/swagger-ui.html`
