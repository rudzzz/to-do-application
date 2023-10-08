package dev.project.todolist.controllers;

import dev.project.todolist.entities.ToDo;
import dev.project.todolist.services.ToDoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class ToDoController {
    private ToDoService toDoService;

    public ToDoController(ToDoService toDoService){
        this.toDoService = toDoService;
    }
    @PostMapping
    public List<ToDo> create(@RequestBody ToDo todo){
        return toDoService.create(todo);
    }

    @GetMapping
    public List<ToDo> list(){
        return toDoService.listToDos();
    }

    @GetMapping("{id}")
    public Optional getOne(@PathVariable Long id){
        return toDoService.GetOneToDo(id);
    }

    @PutMapping
    public List<ToDo> update(@RequestBody ToDo todo){
        return toDoService.update(todo);
    }

    @PostMapping("delete/{id}")
    public List<ToDo> delete(@PathVariable Long id){
        return toDoService.delete(id);
    }
}
