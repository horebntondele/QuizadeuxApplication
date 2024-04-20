package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.WatsAppMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WhatsappmessageDao extends JpaRepository<WatsAppMessage, Long> {
}
