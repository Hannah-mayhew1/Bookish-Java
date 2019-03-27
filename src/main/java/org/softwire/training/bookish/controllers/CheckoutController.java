package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.page.SingleMemberModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final BookService bookService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService, BookService bookService) {
        this.checkoutService = checkoutService;
        this.bookService = bookService;
    }

   /* @RequestMapping("")
    ModelAndView checkoutBook(@RequestParam String isbn) {
        Optional<Book> book = bookService.getBookWithIsbn(isbn);
        if (book.isPresent()) {
            return new ModelAndView("checkoutPage");
        } else {

        }
    }
    */
}