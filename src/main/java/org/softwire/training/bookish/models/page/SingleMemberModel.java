package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Checkout;
import org.softwire.training.bookish.models.database.Member;

import java.util.List;

public class SingleMemberModel {

    private Member member;
    private List<Checkout> booksOnLoanByMember;

    public String getOnebook() {
        return onebook;
    }

    public void setOnebook(String onebook) {
        this.onebook = onebook;
    }

    private String onebook;

    public Member getMember() { return member; }
    public void setMember(Member member) {
        this.member = member;
    }

    public List<Checkout> getBooksOnLoanByMember() {
        return booksOnLoanByMember;
    }
    public void setBooksOnLoanByMember(List<Checkout> booksOnLoanByMember) { this.booksOnLoanByMember = booksOnLoanByMember; }
}
