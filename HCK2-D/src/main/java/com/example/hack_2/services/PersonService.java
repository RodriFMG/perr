package com.example.hack_2.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hack_2.entities.Group;
import com.example.hack_2.entities.Person;
import com.example.hack_2.repositories.PersonRepository;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Set<Group> getPersonGroups(Long id) {
        return personRepository.findById(id).orElse(null).getGroups();
    }
}

