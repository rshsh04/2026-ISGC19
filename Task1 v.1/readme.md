# Task 1

## How to run

1. Open the project in IDE  (IntelliJ)
2. Run Application.java
3. API runs on http://localhost:8080

## Endpoints

- GET /books - get all books
- GET /books/{id} - get book by id
- GET /books/title?query=java - search by title
- GET /books/category?value=IT - filter by category (IT, MATH, PHYSICS)
- POST /books - create a book
- DELETE /books/{id} - delete a book

## example POST
```
{
  "title": "book test",
  "author": "rashed",
  "description": "test",
  "publishedYear": "2022",
  "category": "IT"
}
```

## H2 console
go to http://localhost:8080/h2
- JDBC URL: jdbc:h2:mem:booksdb
- Username: rashed
- Password: leave empty

## Client/console
Run BookConsole.java (make sure the API is running first)

## Notes
- title and author are required
- description max 500 characters
- database deleted after stopping the program