package com.example.todoapp.repository.impl.manual;

import com.example.todoapp.dto.TaskDTO;
import com.example.todoapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TaskRepositoryManualAdapter implements TaskRepository {
    private final TaskRepositoryManual repositoryManual;
    @Override
    public List<TaskDTO> getAll() {
        return repositoryManual.getAll();
    }

    @Override
    public Optional<TaskDTO> getOne(long id) {
        return repositoryManual.getTask(id);
    }

    @Override
    public void editOne(long id, TaskDTO taskDTO) {
        repositoryManual.editTask(id, taskDTO);
    }

    @Override
    public void addOne(TaskDTO taskDTO) {
        repositoryManual.addTask(taskDTO);
    }

    @Override
    public void check(long id, boolean isChecked) throws RuntimeException{
        Optional<TaskDTO> taskDTO = repositoryManual.getTask(id);

        if(taskDTO.isEmpty()) {
            throw new RuntimeException("No such task");
        } else {
            taskDTO.get().setIsCompleted(isChecked);
        }
    }


}
