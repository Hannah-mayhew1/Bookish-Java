package org.softwire.training.bookish;

import org.jdbi.v3.core.Jdbi;
import org.softwire.training.bookish.models.database.Book;

import java.sql.*;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws SQLException {
        String hostname = "localhost";
        String database = "bookish";
        String user = "root";
        String password = "password";
        String connectionString = "jdbc:mysql://" + hostname + "/" + database + "?user=" + user + "&password=" + password + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=GMT&useSSL=false";

        bookTitlesBasedOnCategory(connectionString);
    }

    private static void bookTitlesInAscOrder(String connectionString) {

        Jdbi jdbi = Jdbi.create(connectionString);
        String query = "SELECT * FROM bookish.books ORDER BY book_title ASC";
        List<Book> books = jdbi.withHandle(handle -> {
            return handle.createQuery(query)
                    .mapToBean(Book.class)
                    .list();
        });

        for (Book book : books) {
            System.out.println(book.getBookTitle());
        }
    }

    private static void bookTitlesBasedOnCategory(String connectionString) {
        Jdbi jdbi = Jdbi.create(connectionString);

        String categoriesQuery = "SELECT DISTINCT category FROM books";
        String booksQuery = "SELECT * FROM bookish.books WHERE category = :category";

        List<Book> books = jdbi.withHandle(handle -> {

            List<String> categories = handle.createQuery(categoriesQuery)
                    .mapTo(String.class)
                    .list();

            Scanner categoryInputScanner = new Scanner(System.in);
            for (String category : categories) {
                System.out.print(category + " ");
            }
            System.out.println("\nWhich category would you like to search for?");
            String categoryInput = categoryInputScanner.next();

            return handle.createQuery(booksQuery)
                    .bind("category", categoryInput)
                    .mapToBean(Book.class)
                    .list();
        });

        for (Book book : books) {
            System.out.println(book.getBookTitle());
        }
    }
}
