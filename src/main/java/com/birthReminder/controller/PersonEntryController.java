package com.birthReminder.controller;

import com.birthReminder.person.Person;
import com.birthReminder.person.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public ResponseEntity<String> createPerson(@ModelAttribute Person person) {
        person.setTimestamp(LocalDateTime.now(ZoneId.of("Europe/Zagreb")));
        personRepository.save(person);
        return new ResponseEntity<String>(person.toString(), HttpStatus.CREATED);
    }

    @GetMapping("/delete")
    public ResponseEntity<String> deletePerson(@RequestParam("id") Long personId) {
        personRepository.deleteById(personId);
        return new ResponseEntity<String>(personId.toString() + " deleted", HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public String listAll(Model model) {
        Iterable<Person> listt = personRepository.findAll();
        model.addAttribute("persons", listt);
        return "personsList";
    }

}
