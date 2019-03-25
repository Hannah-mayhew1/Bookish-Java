package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Checkout;
import org.springframework.stereotype.Service;

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
}
