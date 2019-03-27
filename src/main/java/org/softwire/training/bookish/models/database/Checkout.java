package org.softwire.training.bookish.models.database;

import java.time.LocalDate;

public class Checkout {

    String isbn;
    String bookTitle;
    int memberId;
    LocalDate dateCheckedOut;
    LocalDate dateDueBack;
    int checkoutId;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getDateCheckedOut() {
        return dateCheckedOut;
    }

    public void setDateCheckedOut(LocalDate dateCheckedOut) {
        this.dateCheckedOut = dateCheckedOut;
    }

    public LocalDate getDateDueBack() {
        return dateDueBack;
    }

    public void setDateDueBack(LocalDate dateDueBack) {
        this.dateDueBack = dateDueBack;
    }

    public int getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(int checkoutId) {
        this.checkoutId = checkoutId;
    }
}
