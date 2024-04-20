package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.ContextDao;
import com.Project.QuizadeuxApplication.Entities.Context;
import com.Project.QuizadeuxApplication.Service.ContextService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ContextServiceImpl implements ContextService {
    private ContextDao contextDao;

    public ContextServiceImpl(ContextDao contextDao) {
        this.contextDao = contextDao;
    }

    @Override
    public Context NewContext(Context context) {
        return contextDao.save(context);
    }
}
