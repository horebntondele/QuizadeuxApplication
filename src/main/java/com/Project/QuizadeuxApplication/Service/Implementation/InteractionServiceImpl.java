package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.InteractionDao;
import com.Project.QuizadeuxApplication.Entities.Interaction;
import com.Project.QuizadeuxApplication.Service.InteractionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InteractionServiceImpl implements InteractionService {

    private InteractionDao interactionDao;

    public InteractionServiceImpl(InteractionDao interactionDao) {
        this.interactionDao = interactionDao;
    }

    @Override
    public Interaction NewInteraction(Interaction interaction) {
        return interactionDao.save(interaction);
    }
}
