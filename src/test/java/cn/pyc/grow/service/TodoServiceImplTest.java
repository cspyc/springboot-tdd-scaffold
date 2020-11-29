package cn.pyc.grow.service;

import cn.pyc.grow.model.Todo;
import cn.pyc.grow.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

/**
 * @author pi
 * @date 2020/11/29 22:13:25
 **/
@RunWith(MockitoJUnitRunner.class)
public class TodoServiceImplTest {

    @InjectMocks
    private TodoServiceImpl todoService;

    @Mock
    private TodoRepository todoRepository;

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_given_todo_completed() throws Exception {
        Todo learn_tdd = new Todo("Learn TDD", true);
        todoService.createTodos(learn_tdd);
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_throw_exception_given_uncompleted_todo_when_existed() throws Exception {
        Todo existedTodo = new Todo(1l, "Learn TDD", true);
        when(todoRepository.findByTitleAndCompleted("Learn TDD", false)).thenReturn(existedTodo);

        todoService.createTodos(new Todo("Learn TDD", false));
    }

}