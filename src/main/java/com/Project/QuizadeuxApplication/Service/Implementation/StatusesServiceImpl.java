package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.StatusesDao;
import com.Project.QuizadeuxApplication.Entities.Statuses;
import com.Project.QuizadeuxApplication.Service.StatusesService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StatusesServiceImpl implements StatusesService {
    private StatusesDao statusesDao;

    public StatusesServiceImpl(StatusesDao statusesDao) {
        this.statusesDao = statusesDao;
    }

    @Override
    public Statuses NewStatuses(Statuses statuses) {
        return statusesDao.save(statuses);
    }
}
