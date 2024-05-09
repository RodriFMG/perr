package com.example.hack_2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hack_2.entities.Group;

public interface GroupRepository extends JpaRepository<Group, Long>
{
}
