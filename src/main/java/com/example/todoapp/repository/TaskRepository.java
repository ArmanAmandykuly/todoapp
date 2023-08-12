package com.example.todoapp.repository;

import org.springframework.stereotype.Repository;
import com.example.todoapp.dto.TaskDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository {

    List<TaskDTO> getAll();

    Optional<TaskDTO> getOne(long id);

    void editOne(long id, TaskDTO taskDTO);

    void addOne(TaskDTO taskDTO);

    void check(long id, boolean isChecked);
}
