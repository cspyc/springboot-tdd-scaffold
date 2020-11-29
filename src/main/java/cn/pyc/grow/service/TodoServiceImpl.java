package cn.pyc.grow.service;

import cn.pyc.grow.model.Todo;
import cn.pyc.grow.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author pi
 * @date 2020/11/29 16:07:05
 **/
@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo createTodos(Todo todos) {
        if (todos.isCompleted()) {
            throw new IllegalArgumentException();
        }
        Todo found = todoRepository.findByTitleAndCompleted(todos.getTitle(), todos.isCompleted());
        if (found != null) {
            throw new IllegalArgumentException();
        }
        return todoRepository.save(todos);
    }
}
