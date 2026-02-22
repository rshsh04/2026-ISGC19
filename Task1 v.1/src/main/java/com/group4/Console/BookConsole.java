package com.group4.Console;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BookConsole {

       private static final String BASE_URL = "http://localhost:8080";
    private static final HttpClient HTTP = HttpClient.newHttpClient();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Retrieve all books");
            System.out.println("2. Retrieve a specific book by id");
            System.out.println("3. Retrieve books title");
            System.out.println("4. Retrieve books by category");
            System.out.println("5. Create a new book");
            System.out.println("6. Delete a book by id");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> get("/books");
                    case "2" -> {

                        System.out.print("Enter id: ");
                        String id = scanner.nextLine().trim();
                        get("/books/" + id);
                    }
                    case "3" -> {System.out.print("Enter title word: ");
                        String q = scanner.nextLine().trim();
                        get("/books/title?query=" + enc(q));
                    }
                    case "4" -> {
                        System.out.print("Enter category (IT/MATH/PHYSICS): ");
                        String c = scanner.nextLine().trim().toUpperCase();
                        get("/books/category?value=" + enc(c));
                    }
                    case "5" -> createBook(scanner);
                        case "6" -> {
                        System.out.print("Enter id to delete: ");
                        String id = scanner.nextLine().trim();
                        delete("/books/" + id);
                    }case "7" -> {
                        System.out.println("Bye!");
                        return;
                    }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void get(String path) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(BASE_URL + path))
                .GET()
                .build();

         HttpResponse<String> res = HTTP.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + res.statusCode());
        System.out.println(res.body());
    }

    private static void delete(String path) throws Exception {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + path))
                .DELETE()
                .build();

        HttpResponse<String> res = HTTP.send(req, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status: " + res.statusCode());
            if (res.statusCode() == 204) {
            System.out.println("Book deleted");
        }
    }

    private static void createBook(Scanner sc) throws Exception {
        System.out.print("Title: ");
        String title = sc.nextLine().trim();
             System.out.print("Author: ");
             String author = sc.nextLine().trim();

        System.out.print("Description (max 500): ");
        String description = sc.nextLine().trim();

        System.out.print("Published year: ");
        String year = sc.nextLine().trim();
        System.out.print("Category (IT/MATH/PHYSICS): ");
        String category = sc.nextLine().trim().toUpperCase();

        String json = "{"
                + "\"title\":\"" + title + "\","
                + "\"description\":\"" + description + "\","
                + "\"publishedYear\":\"" + year + "\","
                + "\"author\":\"" + author + "\","
                + "\"category\":\"" + category + "\""
                + "}";

        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(BASE_URL + "/books"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();

        HttpResponse<String> res = HTTP.send(req, HttpResponse.BodyHandlers.ofString());
         System.out.println("Status: " + res.statusCode());
        System.out.println(res.body());
    }

    private static String enc(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private static String esc(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}