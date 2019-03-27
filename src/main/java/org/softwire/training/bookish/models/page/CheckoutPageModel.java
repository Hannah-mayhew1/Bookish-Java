package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Checkout;
import org.softwire.training.bookish.models.database.Member;

import java.util.List;

public class CheckoutPageModel {

    private Member member;
//    private List <Checkout> booksOnLoanByMember;
    private List <Book> books;
    private String onebook;
    private List<Checkout> booksOnLoan;

    public Member getMember() { return member; }

    public void setMember(Member member) { this.member = member; }

//    public List<Checkout> getBooksOnLoanByMember() { return booksOnLoanByMember; }
//
//    public void setBooksOnLoanByMember(List<Checkout> booksOnLoanByMember) { this.booksOnLoanByMember = booksOnLoanByMember; }

    public List<Book> getBooks() { return books; }

    public void setBooks(List<Book> books) { this.books = books; }

    public String getOnebook() { return onebook; }

    public void setOnebook(String onebook) { this.onebook = onebook; }

    public List<Checkout> getBooksOnLoan() {
        return booksOnLoan;
    }

    public void setBooksOnLoan(List<Checkout> booksOnLoan) {
        this.booksOnLoan = booksOnLoan;
    }
}
