package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.C2BToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface C2BTokenDao extends JpaRepository<C2BToken, Long> {
    @Query("select c from C2BToken as c where c.tokenStatus=true")
    C2BToken GetOpentoken();
}
