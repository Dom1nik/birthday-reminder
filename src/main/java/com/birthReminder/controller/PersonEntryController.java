package com.birthReminder.controller;

import com.birthReminder.person.Form;
import com.birthReminder.person.Person;
import com.birthReminder.person.PersonRepository;
import com.birthReminder.person.Settings;
import org.modelmapper.ModelMapper;
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

    @Autowired
    ModelMapper modelmapper;

    @GetMapping(value = {"/"})
    public String getPersonEntryForm(Model model) {
        LOG.info("Loading person entry form");
        model.addAttribute("form", new Form());
        return "PersonEntryForm";
    }

    @GetMapping(value = {"/new"})
    public String showNewPersonEntryForm(Model model) {
        LOG.info("Loading new person entry form");
        model.addAttribute("form", new Form(true));
        return "PersonEntryForm";
    }

    @PostMapping("/submit")
    public String createPerson(@Valid @ModelAttribute Form form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            form.setUserSaved(false);
            model.addAttribute("form", form);
            return "PersonEntryForm";
        }
        LOG.info("Saving new person");
        Person person = modelmapper.map(form, Person.class);
        person.setTimestamp(LocalDateTime.now(ZoneId.of("Europe/Zagreb")));
        personRepository.save(person).toString();
        return "redirect:/new";
    }

    @GetMapping("/delete")
    public String deletePerson(@RequestParam("id") Long personId) {
        LOG.info("Deleting entry with id {}", personId);
        personRepository.deleteById(personId);
        return "redirect:/listAll";
    }

    @GetMapping("/listAll")
    public String showAll(Model model) {
        LOG.info("Listing all saved entries");
        Iterable<Person> personsList = personRepository.findAll();
        model.addAttribute("persons", personsList);
        return "PersonsList";
    }

    @GetMapping("/settings")
    public String showSettings(Model model) {
        model.addAttribute("settings", new Settings());
        return "Settings";
    }
}
