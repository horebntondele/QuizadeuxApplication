package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConversationDao extends JpaRepository<Conversation, Long> {
}
