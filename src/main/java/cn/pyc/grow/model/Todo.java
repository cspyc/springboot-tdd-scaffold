package cn.pyc.grow.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author pi
 * @date 2020/11/29 12:53:53
 **/
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Todo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Size(min = 5, max = 20)
    private String title;

    private boolean completed;

    public Todo(String title, boolean completed) {
        this.completed = completed;
        this.title = title;
    }
}
