package com.example.todoapp.repository.impl.jpa;

import com.example.todoapp.model.jpa.TaskJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositoryJpa extends JpaRepository<TaskJPA, Long> {

}
