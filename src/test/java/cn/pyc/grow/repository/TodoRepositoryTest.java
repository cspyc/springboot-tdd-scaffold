package cn.pyc.grow.repository;

import cn.pyc.grow.model.Todo;
import javafx.scene.canvas.GraphicsContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import static org.junit.Assert.*;

/**
 * @author pi
 * @date 2020/11/29 22:51:01
 **/
@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoRepositoryTest {
    @Autowired
    private TodoRepository todoRepository;

    @Test(expected = Exception.class)
    public void should_throw_exception_given_todo_have_title_less_than_5() {
        Todo todo = new Todo("abd", false);
        todoRepository.save(todo);
    }

    @Test(expected = Exception.class)
    public void should_throw_exception_given_todo_have_title_more_than_20() {
        Todo todo = new Todo("abcd abcd abcd abcd abcd", false);
        todoRepository.save(todo);
    }

}