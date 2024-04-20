package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.SessionManagementDao;
import com.Project.QuizadeuxApplication.Entities.SessionManagement;
import com.Project.QuizadeuxApplication.Service.SessionManagementService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SessionManagementServiceImpl implements SessionManagementService {
    private SessionManagementDao sessionManagementDao;

    public SessionManagementServiceImpl(SessionManagementDao sessionManagementDao) {
        this.sessionManagementDao = sessionManagementDao;
    }

    @Override
    public SessionManagement NewSession(SessionManagement sessionManagement) {
        return sessionManagementDao.save(sessionManagement);
    }

    @Override
    public SessionManagement FindSessionbyMSISDN(String MSISDN) {
        return sessionManagementDao.FindSessionOpenSessionbyMSISDN(MSISDN);
    }
}
