package org.softwire.training.bookish.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.softwire.training.bookish.models.database.Book;

import java.util.List;

@Service
public class BookService extends DatabaseService {

    public List<Book> getAllBooks() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM books")
                        .mapToBean(Book.class)
                        .list()
        );
    }
}
