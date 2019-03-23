package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Checkout;

import java.util.List;

public class CheckoutModel {

    private List<Checkout> booksOnLoan;

    public List<Checkout> getBooksOnLoan() { return booksOnLoan; }
    public void setBooksOnLoan(List<Checkout> booksOnLoan) { this.booksOnLoan = booksOnLoan; }
}
