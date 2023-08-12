package com.example.todoapp.services;

import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Object getAll() {
        return taskRepository.getAll();
    }

    public TaskDTO getOne(long id) throws RuntimeException {
        Optional<TaskDTO> taskDTOOptional = taskRepository.getOne(id);
        if(taskDTOOptional.isPresent())
            return taskDTOOptional.get();
        else
            throw new RuntimeException("No such element");
    }

    @Transactional
    public void editOne(long id, TaskDTO taskDTO) throws RuntimeException {
        taskRepository.editOne(id, taskDTO);
    }

    @Transactional
    public void addOne(TaskDTO taskDTO) {
        taskDTO.setIsCompleted(false);
        taskRepository.addOne(taskDTO);
    }

    @Transactional
    public void check(Long itemId, boolean isChecked) throws RuntimeException{
        taskRepository.check(itemId, isChecked);
    }
}
