package cn.pyc.grow.controller;

import cn.pyc.grow.model.Todo;
import cn.pyc.grow.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author pi
 * @date 2020/11/29 13:51:14
 **/
@RestController
@RequestMapping("/api")
public class TodoRestController {
    @Autowired
    private TodoService todoService;


    @GetMapping("/todos")
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }

    @PostMapping("/todos")
    public Todo saveTodos(@RequestBody Todo todos) {
        return todoService.createTodos(todos);
    }
}
