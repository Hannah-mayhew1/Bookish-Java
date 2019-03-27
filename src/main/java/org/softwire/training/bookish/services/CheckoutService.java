package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Checkout;
import org.softwire.training.bookish.models.database.Member;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckoutService extends DatabaseService{
    public List<Checkout> getAllBooksOnLoan() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM checkout")
                        .mapToBean(Checkout.class)
                        .list()
        );
    }

    public List<Checkout> getBooksOnLoanByMember(int memberId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM checkout WHERE member_id = :memberId")
                        .bind("memberId", memberId)
                        .mapToBean(Checkout.class)
                        .list()
        );
    }

    public void createCheckout(Checkout checkout) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO checkout (isbn, member_id, date_checked_out, date_due_back, checkout_id) VALUES (:isbn, :member_id, :date_checked_out, :date_due_back, :checkout_id)")
                        .bind("isbn", checkout.getIsbn())
                        .bind("member_id", checkout.getMemberId())
                        .bind("date_checked_out", checkout.getDateCheckedOut())
                        .bind("date_due_back", checkout.getDateDueBack())
                        .bind("checkout_id", checkout.getCheckoutId())
                        .execute()
        );
    }

}
