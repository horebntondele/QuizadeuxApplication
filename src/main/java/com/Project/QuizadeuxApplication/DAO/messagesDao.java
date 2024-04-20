package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.messages;
import org.springframework.data.jpa.repository.JpaRepository;

public interface messagesDao extends JpaRepository<messages, Long> {
}
