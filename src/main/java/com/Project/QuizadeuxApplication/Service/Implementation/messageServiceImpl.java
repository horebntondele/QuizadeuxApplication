package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.messagesDao;
import com.Project.QuizadeuxApplication.Entities.messages;
import com.Project.QuizadeuxApplication.Service.messageService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class messageServiceImpl implements messageService {
    private messagesDao messagesDao;

    public messageServiceImpl(com.Project.QuizadeuxApplication.DAO.messagesDao messagesDao) {
        this.messagesDao = messagesDao;
    }

    @Override
    public messages NewMessage(messages messages) {
        return messagesDao.save(messages);
    }
}
