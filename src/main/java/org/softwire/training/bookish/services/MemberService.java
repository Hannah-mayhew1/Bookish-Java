package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Member;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService extends DatabaseService {

    public List<Member> getAllMembers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM members")
                        .mapToBean(Member.class)
                        .list()
        );
    }
}
