package com.example.todoapp.model.jpa;

import com.example.todoapp.dto.TaskDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Entity
@Table(name = "Task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskJPA {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title")
    @Size(max = 255)
    @NotEmpty
    private String title;

    @Column(name = "description")
    @Size(max = 1000)
    @NotEmpty
    private String description;

    @Column(name = "due_date")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    private LocalDateTime dueDate;

    @Column(name = "is_completed")
    private Boolean isCompleted;

    public TaskJPA(String title, String description, LocalDateTime dueDate) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = false;
    }

    public boolean equals(Object o) {
        if(!o.getClass().equals(this.getClass()))
            return false;

        TaskDTO dto = (TaskDTO) o;

        return dto.getId() == this.getId();
    }
}
