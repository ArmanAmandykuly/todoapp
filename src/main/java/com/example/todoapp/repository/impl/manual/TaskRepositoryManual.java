package com.example.todoapp.repository.impl.manual;

import com.example.todoapp.dto.TaskDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TaskRepositoryManual {
    private List<TaskDTO> tasksList;

    private final ModelMapper modelMapper;

    @Autowired
    public TaskRepositoryManual(ModelMapper modelMapper) {
        this.tasksList = new ArrayList<>();
        this.modelMapper = modelMapper;
    }

    public List<TaskDTO> getAll() {
        return tasksList;
    }

    public Optional<TaskDTO> getTask(long id) {
        return tasksList.stream().filter(taskDTO -> taskDTO.getId() == id).findAny();
    }

    public void addTask(TaskDTO taskDTO) {
        if(tasksList.isEmpty()) {
            taskDTO.setId(0L);
            tasksList.add(taskDTO);
        } else {
            TaskDTO lastTaskDTO = tasksList.get(tasksList.size() - 1);
            taskDTO.setId(lastTaskDTO.getId() + 1);
            tasksList.add(taskDTO);
        }
    }

    public void editTask(long id, TaskDTO newTask) throws RuntimeException{
        Optional<TaskDTO> taskDTOOptional = tasksList.stream().filter((taskDTO) -> taskDTO.getId() == id).findAny();

        if (taskDTOOptional.isPresent()) {
            TaskDTO taskDTO = taskDTOOptional.get();
            modelMapper.map(newTask, taskDTO);
        } else {
            throw new RuntimeException("No such task");
        }
    }

    public void deleteTask(long id) {
        tasksList.remove(TaskDTO.builder().id(id).build());
    }
}
