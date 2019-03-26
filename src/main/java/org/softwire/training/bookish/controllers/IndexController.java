package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Checkout;
import org.softwire.training.bookish.models.page.CheckoutModel;
import org.softwire.training.bookish.services.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {

    private CheckoutService checkoutService;

    @Autowired
    public IndexController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @RequestMapping("/")
    ModelAndView home() {
        List<Checkout> allBooksOnLoan = checkoutService.getAllBooksOnLoan();
        CheckoutModel checkoutModel = new CheckoutModel();
        checkoutModel.setBooksOnLoan(allBooksOnLoan);
        return new ModelAndView("index", "model", checkoutModel);
    }

}
