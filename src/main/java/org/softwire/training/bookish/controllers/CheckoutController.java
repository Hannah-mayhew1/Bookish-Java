package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Checkout;
import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.database.Technology;
import org.softwire.training.bookish.models.page.AboutPageModel;
import org.softwire.training.bookish.models.page.CatalogueModel;
import org.softwire.training.bookish.models.page.CheckoutModel;
import org.softwire.training.bookish.models.page.MembersModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.CheckoutService;
import org.softwire.training.bookish.services.MemberService;
import org.softwire.training.bookish.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService) { this.checkoutService = checkoutService; }

    @RequestMapping("")
    ModelAndView booksOnLoan() {

        List<Checkout> allBooksOnLoan = checkoutService.getAllBooksOnLoan();

        CheckoutModel checkoutModel = new CheckoutModel();
        checkoutModel.setBooksOnLoan(allBooksOnLoan);

        return new ModelAndView("booksOnLoan", "model", checkoutModel);
    }
}