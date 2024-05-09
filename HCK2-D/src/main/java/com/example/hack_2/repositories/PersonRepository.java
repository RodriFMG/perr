package com.example.hack_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hack_2.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {}
