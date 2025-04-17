package com.tnite.jobwinner.controller;


import com.tnite.jobwinner.model.Person;
import com.tnite.jobwinner.service.PersonService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/create")
    public boolean createPerson(@RequestBody Person person) {
        return personService.save(person);
    }

    @GetMapping("/delete/{id}")
    public boolean deletePerson(@PathVariable UUID id) {
        return personService.removeById(id);
    }

    @GetMapping("/list")
    public List<Person> listPersons() {
        return personService.list();
    }
}