package com.Project.QuizadeuxApplication.Service;

import com.Project.QuizadeuxApplication.Entities.SessionManagement;

public interface SessionManagementService {

    SessionManagement NewSession(SessionManagement sessionManagement);

    SessionManagement FindSessionbyMSISDN(String MSISDN);
}
