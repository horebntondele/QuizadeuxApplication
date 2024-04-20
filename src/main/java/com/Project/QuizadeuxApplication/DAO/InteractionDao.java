package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InteractionDao extends JpaRepository<Interaction, Long> {
}
