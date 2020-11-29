package cn.pyc.grow.repository;

import cn.pyc.grow.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author pi
 * @date 2020/11/29 12:52:21
 **/
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Todo findByTitleAndCompleted(String title, boolean completed);
}
