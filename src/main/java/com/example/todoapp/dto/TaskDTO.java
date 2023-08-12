package com.example.todoapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    @JsonIgnore
    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @NotNull
    private LocalDateTime dueDate;

    @JsonIgnore
    private Boolean isCompleted;

    public boolean equals(Object o) {
        if(!o.getClass().equals(this.getClass()))
            return false;

        TaskDTO dto = (TaskDTO) o;

        return dto.getId() == this.getId();
    }
}
