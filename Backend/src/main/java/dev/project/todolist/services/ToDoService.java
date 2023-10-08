package dev.project.todolist.services;

import dev.project.todolist.entities.ToDo;
import dev.project.todolist.repositories.ToDoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }
    public List<ToDo> create(ToDo todo){
        toDoRepository.save(todo);
        return listToDos();
    }
    public List<ToDo> listToDos() {
        Sort sort = Sort.by(Sort.Order.desc("priority"), Sort.Order.asc("name"));
        return toDoRepository.findByDeleted(false, sort);
    }

    public Optional<ToDo> GetOneToDo(Long id) {
        return toDoRepository.findById(id);
    }
    public List<ToDo> update(ToDo todo){
        toDoRepository.save(todo);
        return listToDos();
    }
    public List<ToDo> delete(Long id){
        ToDo todo = toDoRepository.findById(id).orElse(null);
        if (todo != null) {
            todo.setDeleted(true);
            toDoRepository.save(todo);
        }
        return listToDos();
    }
}
