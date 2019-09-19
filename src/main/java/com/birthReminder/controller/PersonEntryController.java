package com.birthReminder.controller;

import com.birthReminder.person.Person;
import com.birthReminder.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Controller
public class PersonEntryController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/")
    public String getPersonEntryForm(Model model) {
        model.addAttribute("person", new Person());
        return "PersonEntryForm";
    }

    @PostMapping("/submit")
    public String createPerson(@Valid @ModelAttribute Person person, BindingResult result) {
        if (result.hasErrors()){
            return "PersonEntryForm";
        }
        person.setTimestamp(LocalDateTime.now(ZoneId.of("Europe/Zagreb")));
        return personRepository.save(person).toString();
    }

    @GetMapping("/delete")
    public String deletePerson(@RequestParam("id") Long personId) {
        personRepository.deleteById(personId);
        return "redirect:/listAll";
    }

    @GetMapping("/listAll")
    public String listAll(Model model) {
        Iterable<Person> listt = personRepository.findAll();
        model.addAttribute("persons", listt);
        return "personsList";
    }

}
