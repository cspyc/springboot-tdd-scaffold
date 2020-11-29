package cn.pyc.grow.service;

import cn.pyc.grow.model.Todo;

import java.util.List;

/**
 * @author pi
 * @date 2020/11/29 16:04:29
 **/
public interface TodoService {
    List<Todo> getAllTodos();

    Todo createTodos(Todo todos);
}
