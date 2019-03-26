package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.database.Technology;
import org.softwire.training.bookish.models.page.AboutPageModel;
import org.softwire.training.bookish.models.page.CatalogueModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/catalogue")
public class CatalogueController {

    private final BookService bookService;

    @Autowired
    public CatalogueController(BookService bookService) { this.bookService = bookService; }

    @RequestMapping("")
    ModelAndView catalogue(@ModelAttribute("message") String message) {

        List<Book> allBooks = bookService.getAllBooks();

        CatalogueModel catalogueModel = new CatalogueModel();
        catalogueModel.setBooks(allBooks);
        catalogueModel.setMessage(message);

        return new ModelAndView("catalogue", "model", catalogueModel);
    }

    @RequestMapping("/add-book")
    RedirectView addBook(@ModelAttribute Book book, RedirectAttributes attributes) {

        Optional<Book> existingBook = bookService.getBookWithIsbn(book.getIsbn());

        if (!existingBook.isPresent()) {
            bookService.addBook(book);
            return new RedirectView("/catalogue");
        } else {
            bookService.addBook(book);
            attributes.addFlashAttribute("message", "This book already exists! Added a new copy");
            return new RedirectView("/catalogue");
        }
    }

    @RequestMapping("/delete-book")
        RedirectView deleteBook(@RequestParam String isbn) {

        bookService.deleteBook(isbn);

        return new RedirectView("/catalogue");
    }

    @RequestMapping("/edit-book")
    RedirectView editMember(@ModelAttribute Book book) {
        bookService.editBook(book);
        return new RedirectView("/catalogue");
    }

}
