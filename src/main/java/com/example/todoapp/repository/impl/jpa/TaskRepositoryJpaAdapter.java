package com.example.todoapp.repository.impl.jpa;

import com.example.todoapp.dto.TaskDTO;
import com.example.todoapp.model.jpa.TaskJPA;
import com.example.todoapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Primary
public class TaskRepositoryJpaAdapter implements TaskRepository {
    private final TaskRepositoryJpa taskRepository;
    private final ModelMapper modelMapper;


    @Override
    public List<TaskDTO> getAll() {
        return taskRepository.findAll().stream()
                .map(taskJPA -> modelMapper.map(taskJPA, TaskDTO.class))
                .toList();
    }

    @Override
    public Optional<TaskDTO> getOne(long id) {
        return taskRepository.findById(id).stream()
                .map(taskJPA -> modelMapper.map(taskJPA, TaskDTO.class))
                .findAny();
    }

    @Override
    public void editOne(long id, TaskDTO taskDTO) throws RuntimeException{
        Optional<TaskJPA> taskJPAOptional = taskRepository.findById(id);

        if(taskJPAOptional.isEmpty())
            throw new RuntimeException("No such task");

        TaskJPA edittedTask = taskJPAOptional.get();
        modelMapper.map(modelMapper.map(taskDTO, TaskJPA.class), edittedTask);
    }

    @Override
    public void addOne(TaskDTO taskDTO) {
        taskRepository.save(modelMapper.map(taskDTO, TaskJPA.class));
    }

    @Override
    public void check(long id, boolean isChecked) throws RuntimeException{
        Optional<TaskJPA> taskJPAOptional = taskRepository.findById(id);
        if(taskJPAOptional.isEmpty()) {
            throw new RuntimeException("No such task");
        } else {
            taskJPAOptional.get().setIsCompleted(isChecked);
        }
    }
}
