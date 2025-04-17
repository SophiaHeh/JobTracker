package com.tnite.jobwinner.service.impl;


import com.tnite.jobwinner.model.Person;
import com.tnite.jobwinner.service.PersonService;
import com.tnite.jobwinner.repository.PersonRepository;
import com.tnite.jobwinner.utils.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public boolean save(Person person) {
        personRepository.save(person);
        return true;
    }

    @Override
    public boolean removeById(UUID id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Person> list() {
        return personRepository.findAll();
    }
}
