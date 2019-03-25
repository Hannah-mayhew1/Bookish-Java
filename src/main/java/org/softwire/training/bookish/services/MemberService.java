package org.softwire.training.bookish.services;

import org.softwire.training.bookish.models.database.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService extends DatabaseService {

    public List<Member> getAllMembers() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM members")
                        .mapToBean(Member.class)
                        .list()
        );
    }

    public Optional<Member> getMemberWithName(String firstName, String secondName) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM members WHERE first_name = :firstName AND second_name = :secondName")
                    .bind("firstName", firstName)
                    .bind("secondName", secondName)
                    .mapToBean(Member.class)
                    .findFirst()
        );
    }

    public Optional<Member> getMemberWithId(int memberId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM members WHERE id = :memberId")
                    .bind("memberId" , memberId)
                    .mapToBean(Member.class)
                    .findFirst()
        );
    }

    public void addMember(Member member) {
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO members (first_name, second_name) VALUES (:first_name, :second_name)")
                    .bind("first_name", member.getFirstName())
                    .bind("second_name", member.getSecondName())
                    .execute()
        );
    }

    public void deleteMember(int memberID) {
        jdbi.useHandle(handle ->
                handle.createUpdate("DELETE FROM members WHERE id = :id")
                        .bind("id", memberID)
                        .execute()
        );
    }

    public void editMember(Member member) {
        jdbi.useHandle(handle ->
                handle.createUpdate("UPDATE members SET first_name = :firstName, second_name = :secondName WHERE id = :id")
                        .bind("id", member.getId())
                        .bind("firstName", member.getFirstName())
                        .bind("secondName", member.getSecondName())
                        .execute()
        );
    }
}
