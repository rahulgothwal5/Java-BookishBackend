# Bookish Backend

Welcome to the Bookish Backend repository! This project provides CRUD APIs for managing books and authors in a library system.

## Postman Collection

You can use the following Postman collection to interact with the APIs:

| API Name               | Method | Endpoint                                | Description                               |
|------------------------|--------|-----------------------------------------|-------------------------------------------|
| Create Author          | POST   | `localhost:8080/library/authors`        | Create a new author                       |
| Create and Update Book| PUT    | `localhost:8080/library/books/{isbn}`   | Create or update a book                   |
| Get All Books          | GET    | `localhost:8080/library/books`          | Retrieve all books                        |
| Get All Authors        | GET    | `localhost:8080/library/authors`        | Retrieve all authors                      |
| Find Author            | GET    | `localhost:8080/library/authors/{id}`   | Retrieve a specific author by ID          |
| Find Book              | GET    | `localhost:8080/library/books/{isbn}`   | Retrieve a specific book by ISBN          |
| Complete Update Author| PUT    | `localhost:8080/library/authors/{id}`   | Update complete details of an author      |
| Partial Update Book    | PATCH  | `localhost:8080/library/books/{isbn}`   | Partially update details of a book        |
| Partial Update Author  | PATCH  | `localhost:8080/library/authors/{id}`   | Partially update details of an author     |
| Delete Author          | DELETE | `localhost:8080/library/authors/{id}`   | Delete an author by ID                    |
| Delete Book            | DELETE | `localhost:8080/library/books/{isbn}`   | Delete a book by ISBN                     |

Feel free to clone the repository and use the provided Postman collection to explore the APIs!

## Technologies Used

- **Spring Boot**: Framework used for building robust and scalable Java applications.
- **PostgreSQL**: Database used for storing production data.
- **H2 Database**: In-memory database used for unit testing.
- **Spring Data JPA**: Simplifies data access and manipulation using the Java Persistence API (JPA).
- **MVC Architecture**: Follows the Model-View-Controller design pattern for organizing code and separating concerns.
- **Docker**: Containerization technology used for packaging the application and its dependencies into a standardized unit for deployment.

## Setup Instructions

1. Clone the repository: `git clone https://github.com/rahulgothwal5/Java-BookishBackend.git`
2. Navigate to the project directory: `cd Java-BookishBackend`
3. Build the project: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

Make sure you have Maven and Java installed on your system before proceeding.

## Contributing

Contributions are welcome! If you have any suggestions, bug reports, or feature requests, please open an issue or submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
