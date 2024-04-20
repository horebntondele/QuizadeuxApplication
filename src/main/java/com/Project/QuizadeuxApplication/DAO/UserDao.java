package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long> {
    @Query(value = "select u from User as u where u.username=:username")
    User findUserByName ( @Param("username") String username);

}
