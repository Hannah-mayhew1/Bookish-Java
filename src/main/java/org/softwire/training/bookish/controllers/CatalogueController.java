package org.softwire.training.bookish.controllers;

import org.softwire.training.bookish.models.database.Book;
import org.softwire.training.bookish.models.database.Technology;
import org.softwire.training.bookish.models.page.AboutPageModel;
import org.softwire.training.bookish.models.page.CatalogueModel;
import org.softwire.training.bookish.services.BookService;
import org.softwire.training.bookish.services.TechnologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/catalogue")
public class CatalogueController {

    private final BookService bookService;

    @Autowired
    public CatalogueController(BookService bookService) { this.bookService = bookService; }

    @RequestMapping("")
    ModelAndView catalogue() {

        List<Book> allBooks = bookService.getAllBooks();

        CatalogueModel catalogueModel = new CatalogueModel();
        catalogueModel.setBooks(allBooks);

        return new ModelAndView("Catalogue", "model", catalogueModel);
    }
}
