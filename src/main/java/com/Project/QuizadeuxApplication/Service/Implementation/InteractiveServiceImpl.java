package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.InteractiveDao;
import com.Project.QuizadeuxApplication.Entities.Interactive;
import com.Project.QuizadeuxApplication.Service.InteractiveService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InteractiveServiceImpl implements InteractiveService {
    private InteractiveDao interactiveDao;

    public InteractiveServiceImpl(InteractiveDao interactiveDao) {
        this.interactiveDao = interactiveDao;
    }

    @Override
    public Interactive NewInteractive(Interactive interactive) {
        return interactiveDao.save(interactive);
    }
}
