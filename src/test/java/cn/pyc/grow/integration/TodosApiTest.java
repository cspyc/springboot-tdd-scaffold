package cn.pyc.grow.integration;

import cn.pyc.grow.SpringbootTddScaffoldApplication;
import cn.pyc.grow.model.Todo;
import cn.pyc.grow.repository.TodoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author pi
 * @date 2020/11/29 12:16:38
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = SpringbootTddScaffoldApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TodosApiTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void should_givenTodos_when_GetTodos_thenStatus200() throws Exception {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("Learn TDD", false));
        todos.add(new Todo("Learn SpringBoot", false));

        todoRepository.saveAll(todos);

        mvc.perform(get("/api/todos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Learn TDD")))
                .andExpect(jsonPath("$[0].completed", is(false)));
    }

    @Test
    public void should_givenTodos_whenPostTodos_thenStatus200() throws Exception {
        Todo todo = new Todo("Learn TDD", false);

        String json = new ObjectMapper().writeValueAsString(todo);
        mvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.title", is("Learn TDD")))
                .andExpect(jsonPath("$.completed", is(false)));

        assertThat(todoRepository.findAll().size(), is(1));

    }

}
