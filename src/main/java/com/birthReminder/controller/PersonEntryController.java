package com.birthReminder.controller;

import com.birthReminder.person.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonEntryController {

    @GetMapping("/")
    public String getPersonEntryForm(Model model) {
        model.addAttribute("person", new Person());
        return "personEntryForm";
    }
}
