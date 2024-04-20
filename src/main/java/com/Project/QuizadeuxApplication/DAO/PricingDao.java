package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingDao extends JpaRepository<Pricing, Long> {
}
