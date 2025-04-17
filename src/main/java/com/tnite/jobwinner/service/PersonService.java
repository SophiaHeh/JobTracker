package com.tnite.jobwinner.service;

import com.tnite.jobwinner.model.Person;

import java.util.List;
import java.util.UUID;

public interface PersonService {
    Person save(Person person);

    boolean removeById(UUID id);

    List<Person> list();

}
