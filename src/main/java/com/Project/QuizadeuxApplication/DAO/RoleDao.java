package com.Project.QuizadeuxApplication.DAO;

import com.Project.QuizadeuxApplication.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleDao extends JpaRepository<Role, Long> {
    @Query(value = "select r from Role as r where r.Role_Name=:Role_Name")
    Role findRoleByName(@Param("Role_Name") String Role_Name);


}
