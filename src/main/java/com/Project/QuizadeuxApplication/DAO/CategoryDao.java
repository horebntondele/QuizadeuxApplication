package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Long> {
    @Query("select c from Category as c where c.categoryId=:categoryId")
    Category findbyCategoryId(@Param("categoryId") String categoryId);

}
