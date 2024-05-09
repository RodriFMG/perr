package com.example.hack_2.controllers;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import com.example.hack_2.dto.PersonDTO;
import com.example.hack_2.entities.Group;
import com.example.hack_2.entities.Person;
import com.example.hack_2.services.PersonService;


@Controller
@RequestMapping("/persons")
@CrossOrigin(origins = "http://localhost:3000")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        Assert.notNull(personService, "PersonService must not be null!");
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<PersonDTO>> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        List<PersonDTO> personDTOs = persons.stream()
                                            .map(this::toDTO)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(personDTOs);
    }

    private PersonDTO toDTO(Person person) {
        return new PersonDTO(person.getId(), person.getName());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        Person person = personService.getPersonById(id);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/groups/{id}")
    public ResponseEntity<Set<Group>> getGroupsByPersonId(@PathVariable Long id) {
        Set<Group> groups = personService.getPersonGroups(id);
        return ResponseEntity.ok(groups);
    }
    

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }
}
