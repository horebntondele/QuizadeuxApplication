package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.SessionManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SessionManagementDao extends JpaRepository<SessionManagement, Long> {
    @Query(value = "select s from SessionManagement as s where s.SessionMSISDN=:msisdn and s.Status=true ")
    SessionManagement FindSessionOpenSessionbyMSISDN(@Param("msisdn") String msisdn);
}
