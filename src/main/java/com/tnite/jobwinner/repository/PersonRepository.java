package com.tnite.jobwinner.repository;

import com.tnite.jobwinner.model.Person;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, UUID> {
}