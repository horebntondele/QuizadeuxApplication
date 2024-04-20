package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.QuestionsList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsListDao extends JpaRepository<QuestionsList, Long> {
}
