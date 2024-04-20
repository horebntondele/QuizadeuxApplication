package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.changesDao;
import com.Project.QuizadeuxApplication.Entities.changes;
import com.Project.QuizadeuxApplication.Service.changesService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class changesServiceImpl implements changesService {

    private changesDao changeDao;

    public changesServiceImpl(changesDao changeDao) {
        this.changeDao = changeDao;
    }

    @Override
    public changes NewChanges(changes changes) {
        return changeDao.save(changes);
    }
}
