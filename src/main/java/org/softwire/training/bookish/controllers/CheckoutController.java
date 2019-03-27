package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
<<<<<<< HEAD
=======
import org.softwire.training.bookish.models.database.Checkout;
import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.page.CheckoutPageModel;
import org.softwire.training.bookish.models.page.IndexPageModel;
>>>>>>> 0f846e3b31a1f4ad8b173126d89136f01b12b82d
import org.softwire.training.bookish.models.page.SingleMemberModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.CheckoutService;
import org.softwire.training.bookish.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/checkoutPage")
public class CheckoutController {

    private final CheckoutService checkoutService;
    private final BookService bookService;
    private final MemberService memberService;

    @Autowired
    public CheckoutController(CheckoutService checkoutService, BookService bookService, MemberService memberService) {
        this.checkoutService = checkoutService;
        this.bookService = bookService;
        this.memberService = memberService;
    }

    @RequestMapping("")
    ModelAndView checkoutBook(@RequestParam(value = "isbn", required = false) String isbn,
                              @RequestParam(value = "memberId", required = false) Integer memberId) {

        Optional<Member> member = memberId == null ? Optional.empty() : memberService.getMemberWithId(memberId);

        CheckoutPageModel checkoutPageModel = new CheckoutPageModel();
        checkoutPageModel.setOnebook(isbn);
        if (member.isPresent()) {
            List<Checkout> allBooksOnLoan = checkoutService.getAllBooksOnLoan();
            IndexPageModel indexPageModel = new IndexPageModel();
            indexPageModel.setBooksOnLoan(allBooksOnLoan);
        }
        return new ModelAndView("checkoutPage", "model", checkoutPageModel);
    }
}