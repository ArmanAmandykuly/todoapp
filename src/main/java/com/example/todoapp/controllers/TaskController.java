package com.example.todoapp.controllers;

import com.example.todoapp.services.TaskService;
import com.example.todoapp.dto.TaskDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("tasks")
    public String allTasks(Model model) {
        model.addAttribute("tasks", taskService.getAll());

        return "tasks";
    }

    @GetMapping("tasks/{id}")
    public String getTask(@PathVariable long id, Model model) throws RuntimeException {
        model.addAttribute("task", taskService.getOne(id));

        return "show";
    }

    @PutMapping("tasks/{id}")
    public String editTask(@PathVariable long id, @ModelAttribute TaskDTO taskDTO) throws RuntimeException{
        taskService.editOne(id, taskDTO);

        return "redirect:show";
    }

    @GetMapping("tasks/{id}/edit")
    public String sendEditPage(@PathVariable long id, Model model) throws RuntimeException{
        model.addAttribute("task", taskService.getOne(id));

        return "edit";
    }

    @PostMapping("tasks")
    public String addTask(@ModelAttribute TaskDTO taskDTO) {
        taskService.addOne(taskDTO);

        return "redirect:tasks";
    }

    @GetMapping("tasks/add")
    public String sendAddingTask(@ModelAttribute TaskDTO taskDTO, Model model) {
        taskDTO.setId(0L);
        model.addAttribute("task", taskDTO);

        return "add";
    }

    @PostMapping("update-checkbox")
    @ResponseBody
    public ResponseEntity checkboxHandler(@RequestParam("itemId") Long itemId, @RequestParam("isChecked") boolean isChecked) throws RuntimeException{
        taskService.check(itemId, isChecked);

        return ResponseEntity.ok("The state is changed");
    }

    @ExceptionHandler(RuntimeException.class)
    public String exceptionHandler(HttpServletRequest request, RuntimeException e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());

        return "error";
    }
}
