package com.birthReminder.controller;

import com.birthReminder.person.Person;
import com.birthReminder.person.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(PersonEntryController.class);

    @Autowired
    PersonRepository personRepository;

    @GetMapping("/")
    public String getPersonEntryForm(Model model) {
        LOG.info("Loading new person entry form");
        model.addAttribute("person", new Person());
        return "PersonEntryForm";
    }

    @PostMapping("/submit")
    public String createPerson(@Valid @ModelAttribute Person person, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "PersonEntryForm";
        }
        LOG.info("Saving new person");
        person.setTimestamp(LocalDateTime.now(ZoneId.of("Europe/Zagreb")));
        personRepository.save(person).toString();
        model.addAttribute("person", new Person());
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deletePerson(@RequestParam("id") Long personId) {
        LOG.info("Deleting entry with id {}", personId);
        personRepository.deleteById(personId);
        return "redirect:/listAll";
    }

    @GetMapping("/listAll")
    public String listAll(Model model) {
        LOG.info("Listing all saved entries");
        Iterable<Person> listt = personRepository.findAll();
        model.addAttribute("persons", listt);
        return "personsList";
    }

}
