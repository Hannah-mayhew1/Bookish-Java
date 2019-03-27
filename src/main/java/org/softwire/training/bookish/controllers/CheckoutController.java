package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Checkout;
import org.softwire.training.bookish.models.database.Member;
import org.softwire.training.bookish.models.page.CheckoutPageModel;
import org.softwire.training.bookish.models.page.IndexPageModel;
import org.softwire.training.bookish.models.page.SingleMemberModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.CheckoutService;
import org.softwire.training.bookish.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
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
        checkoutPageModel.setOnebook(isbn);{
            List<Checkout> allBooksOnLoan = checkoutService.getAllBooksOnLoan();
            CheckoutPageModel CheckoutPageModel = new CheckoutPageModel();
            checkoutPageModel.setBooksOnLoan(allBooksOnLoan);
        }
        return new ModelAndView("checkoutPage", "model", checkoutPageModel);
    }

    @RequestMapping("createCheckout")
    RedirectView createCheckout(@ModelAttribute CreateCheckoutFormData formData) {
        String[] nameParts = formData.getMemberName().split(" ");
        Optional<Member> memberWithName = memberService.getMemberWithName(nameParts[0], nameParts[1]);

        if (memberWithName.isPresent()) {
            Checkout checkout = new Checkout();
            checkout.setIsbn(formData.getIsbn());
            checkout.setMemberId(memberWithName.get().getId());
            checkout.setDateCheckedOut(LocalDate.now());
            checkout.setDateDueBack(LocalDate.now().plusWeeks(2));
            checkoutService.createCheckout(checkout);
        }

        return new RedirectView("/checkoutPage");
    }

    private static class CreateCheckoutFormData {
        private String isbn;
        private String memberName;

        public String getIsbn() {
            return isbn;
        }
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

        public String getMemberName() {
            return memberName;
        }
        public void setMemberName(String memberName) {
            this.memberName = memberName;
        }
    }
}