package ru.rybakov.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rybakov.spring.dao.BookDAO;
import ru.rybakov.spring.dao.PersonDAO;
import ru.rybakov.spring.model.Book;
import ru.rybakov.spring.model.Person;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String getBook(Model model) {
        model.addAttribute("books", bookDAO.getBooks());

        return "book/index";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("books", new Book());

        return "book/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("person") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "people/new";

        bookDAO.save(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("books", bookDAO.getBook(id));
        if (bookDAO.getBook(id).getUser_id() == null) {
           Person person1 = new Person();
           person1.setFIO("No user");
            model.addAttribute("person", person1);
        } else {
            model.addAttribute("person", personDAO.getPerson(bookDAO.getBook(id).getUser_id()));
        }
        return "book/show";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id,
                           Model model) {

        model.addAttribute("book", bookDAO.getBook(id));

        return "book/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id,
                             @ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/edit";
        bookDAO.editBook(id, book);

        return "redirect:/books";
    }

    @GetMapping("/{id}/give")
    public String giveBook(@PathVariable("id") int id,
                           @ModelAttribute("person") Person person,
                           Model model) {
        model.addAttribute("book", bookDAO.getBook(id));
        model.addAttribute("people", personDAO.getPeople());

        return "book/give";
    }

    @PatchMapping("/{id}/given")
    public String givenBook(@PathVariable("id") int id,
                            @ModelAttribute("person") Person person,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "book/give";
        bookDAO.giveBook(id, person);

        return "redirect:/books";
    }
    @GetMapping("/{id}/return")
    public String returnBook(@PathVariable("id") int id) {
        bookDAO.returnBook(id);

        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

}
