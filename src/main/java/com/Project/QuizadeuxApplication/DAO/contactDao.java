package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface contactDao extends JpaRepository<contacts, Long> {
}
