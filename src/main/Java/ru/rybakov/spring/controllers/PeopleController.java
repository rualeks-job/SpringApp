package ru.rybakov.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rybakov.spring.dao.BookDAO;
import ru.rybakov.spring.dao.PersonDAO;
import ru.rybakov.spring.model.Person;

import javax.validation.Valid;

@Controller
@RequestMapping("/readers")
public class PeopleController {


    private final PersonDAO personDAO;
    private final BookDAO bookDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people", personDAO.getPeople());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id,
                         Model model){
        model.addAttribute("person", personDAO.getPerson(id));
        model.addAttribute("books", bookDAO.getBooksOnUserId(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }

    @PostMapping()
    public String createPerson(@ModelAttribute("person") @Valid Person person,
                               BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);

        return "redirect:/readers";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(@PathVariable("id") int id,
                             Model model){
        model.addAttribute("person", personDAO.getPerson(id));

        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String patchPerson(@PathVariable("id") int id,
                              @ModelAttribute("person") @Valid Person person,
                              BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/edit";
        personDAO.editPerson(id, person);

        return "redirect:/readers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/readers";
    }
}
