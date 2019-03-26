package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Member;
import org.springframework.stereotype.Service;
import org.softwire.training.bookish.models.database.Book;

import java.util.List;
import java.util.Optional;

@Service
public class BookService extends DatabaseService {

    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT books.*, books.total_copies - COUNT(checkout.checkout_id) total_copies_available FROM books LEFT JOIN checkout ON books.isbn = checkout.isbn GROUP BY books.isbn")
                        .mapToBean(Book.class)
                        .list()
        );
    }

    public Optional<Book> getBookWithIsbn(String Isbn) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books where isbn = :isbn")
                        .bind("isbn", Isbn)
                        .mapToBean(Book.class)
                        .findFirst()
        );
    }

    public void addBook(Book book) {
        Optional<Book> existingBook = getBookWithIsbn(book.getIsbn());
        if (existingBook.isPresent()) {
            jdbi.useHandle(handle ->
                    handle.createUpdate("UPDATE books SET total_copies = total_copies + 1 WHERE isbn = :isbn")
                            .bind("isbn", book.getIsbn())
                            .execute()
            );
        } else {
            jdbi.useHandle(handle ->
                    handle.createUpdate("INSERT INTO books (isbn, book_title, author_first_name, author_second_name, category, total_copies) VALUES (:isbn, :book_title, :author_first_name, :author_second_name, :category, :total_copies)")
                            .bind("isbn", book.getIsbn())
                            .bind("book_title", book.getBookTitle())
                            .bind("author_first_name", book.getAuthorFirstName())
                            .bind("author_second_name", book.getAuthorSecondName())
                            .bind("category", book.getCategory())
                            .bind("total_copies", 1)
                            .execute()
            );
        }
    }

    public void deleteBook(String isbn) {
        Optional<Integer> totalCopies = getTotalCopies(isbn);
        if (totalCopies.isPresent() && totalCopies.get() == 1) {
            jdbi.useHandle(handle ->
                    handle.createUpdate("DELETE FROM books WHERE isbn = :isbn")
                            .bind("isbn", isbn)
                            .execute()
            );
        } else {
            jdbi.useHandle(handle ->
                    handle.createUpdate("UPDATE books SET total_copies = total_copies - 1 WHERE isbn = :isbn")
                            .bind("isbn", isbn)
                            .execute()
            );
        }
    }

    public void editBook(Book book) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE books SET book_title = :bookTitle, author_first_name = :authorFirstName," +
                        " author_second_name = :authorSecondName, category = :category, total_copies = :totalCopies WHERE isbn = :isbn")
                        .bind("book_title", book.getBookTitle())
                        .bind("authorFirstName", book.getAuthorFirstName())
                        .bind("authorSecondName", book.getAuthorSecondName())
                        .bind("category", book.getCategory())
                        .bind("totalCopies", book.getTotalCopies())
                        .execute()
        );
    }

    private Optional<Integer> getTotalCopies(String isbn) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT total_copies FROM books WHERE isbn = :isbn")
                        .bind("isbn", isbn)
                        .mapTo(Integer.class)
                        .findFirst()
        );
    }
}