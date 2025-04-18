package com.tnite.jobwinner.controller;

import com.tnite.jobwinner.model.Person;
import com.tnite.jobwinner.service.PersonService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        return ResponseEntity.ok("Person created successfully");
    }

    @GetMapping("/list")
    public ResponseEntity<List<Person>> listPersons() {
        List<Person> all = personService.list();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        boolean removed = personService.removeById(id);
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}