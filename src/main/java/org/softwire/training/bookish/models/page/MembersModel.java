package org.softwire.training.bookish.models.page;

import org.softwire.training.bookish.models.database.Member;

import java.util.List;

public class MembersModel {

    private String message;
    private List<Member> members;

    public List<Member> getMembers() { return members; }
    public void setMembers(List<Member> members) { this.members = members; }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
