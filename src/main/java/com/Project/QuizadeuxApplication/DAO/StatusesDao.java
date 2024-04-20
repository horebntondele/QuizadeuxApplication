package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.Statuses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusesDao extends JpaRepository<Statuses, Long> {
}
