package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.Partie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PartieDao extends JpaRepository<Partie, Long> {
    @Query("select p from Partie as p where p.Participant=:Participant and p.PartieStatus=true")
    Partie getPartybyParticipant(@Param("Participant") String Participant);
}
