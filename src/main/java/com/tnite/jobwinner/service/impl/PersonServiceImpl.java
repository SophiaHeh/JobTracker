package com.tnite.jobwinner.service.impl;


import com.tnite.jobwinner.model.Company;
import com.tnite.jobwinner.model.Person;
import com.tnite.jobwinner.repository.CompanyRepository;
import com.tnite.jobwinner.service.PersonService;
import com.tnite.jobwinner.repository.PersonRepository;
import com.tnite.jobwinner.utils.UUIDUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.Iterator;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final CompanyRepository companyRepository;
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(CompanyRepository companyRepository, PersonRepository personRepository) {
        this.companyRepository = companyRepository;
        this.personRepository = personRepository;
    }

    @Transactional
    public Person save(Person person) {
        try {
            // Extract company name
            String companyName = person.getCompany() != null ?
                person.getCompanyName() :
                null;

//            if (companyName == null) {
//                return false; // Company name is required
//            }

            // Find or create the company
            Company company = companyRepository.findByName(companyName)
                .orElseGet(() -> {
                    Company newCompany = new Company();
                    newCompany.setName(companyName);
                    return companyRepository.save(newCompany);
                });

            // Set the actual company entity on the person
            person.setCompany(company);

            // Save the person
            personRepository.save(person);
            return personRepository.save(person);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
