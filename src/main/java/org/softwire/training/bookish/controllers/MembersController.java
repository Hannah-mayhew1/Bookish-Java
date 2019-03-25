package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.database.Technology;
import org.softwire.training.bookish.models.page.AboutPageModel;
import org.softwire.training.bookish.models.page.CatalogueModel;
import org.softwire.training.bookish.models.page.MembersModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.MemberService;
import org.softwire.training.bookish.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/members")
public class MembersController {
    private final MemberService memberService;

    @Autowired
    public MembersController(MemberService memberService) { this.memberService = memberService; }

    @RequestMapping("")
    ModelAndView members(@ModelAttribute("message") String message) {

        List<Member> allMembers = memberService.getAllMembers();

        MembersModel membersModel = new MembersModel();
        membersModel.setMembers(allMembers);
        membersModel.setMessage(message);

        return new ModelAndView("members", "model", membersModel);
    }

    @RequestMapping("/add-member")
    RedirectView addMember(@ModelAttribute Member member, RedirectAttributes attributes) {

        Optional<Member> existingMember = memberService.getMemberWithName(
                member.getFirstName(), member.getSecondName()
        );
        if (!existingMember.isPresent()) {
            memberService.addMember(member);
            return new RedirectView("/members");
        } else {
            attributes.addFlashAttribute("message", "This member already exists!");
            return new RedirectView("/members");
        }
    }

    @RequestMapping("/delete-member")
    RedirectView deleteMember(@RequestParam int memberID) {

        memberService.deleteMember(memberID);

        return new RedirectView("/members");

    }
}
