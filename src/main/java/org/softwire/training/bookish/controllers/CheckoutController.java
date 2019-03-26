package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Checkout;
import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.page.CatalogueModel;
import org.softwire.training.bookish.models.page.CheckoutModel;
import org.softwire.training.bookish.models.page.MembersModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.CheckoutService;
import org.softwire.training.bookish.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import org.softwire.training.bookish.services.BookService;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final MemberService memberService;
    private final CheckoutService checkoutService;
    private final BookService bookService;

    @Autowired
    public CheckoutController(MemberService memberService, CheckoutService checkoutService, BookService bookService) {
        this.memberService = memberService;
        this.checkoutService = checkoutService;
        this.bookService = bookService;
    }

    @RequestMapping("")
    ModelAndView booksOnLoan() {

        List<Checkout> allBooksOnLoan = checkoutService.getAllBooksOnLoan();
        CheckoutModel checkoutModel = new CheckoutModel();
        checkoutModel.setBooksOnLoan(allBooksOnLoan);

        List<Member> allMembers = memberService.getAllMembers();
        MembersModel membersModel = new MembersModel();
        membersModel.setMembers(allMembers);

        List<Book> allBooks = bookService.getAllBooks();
        CatalogueModel catalogueModel = new CatalogueModel();
        catalogueModel.setBooks(allBooks);

        return new ModelAndView("checkoutPage", "model", checkoutModel);
    }
}