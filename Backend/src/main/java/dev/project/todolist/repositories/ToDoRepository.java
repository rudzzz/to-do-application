package dev.project.todolist.repositories;

import dev.project.todolist.entities.ToDo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByDeleted(boolean isDeleted, Sort list);
}
