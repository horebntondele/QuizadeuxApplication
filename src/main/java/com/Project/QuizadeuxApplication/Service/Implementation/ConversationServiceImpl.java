package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.ConversationDao;
import com.Project.QuizadeuxApplication.Entities.Conversation;
import com.Project.QuizadeuxApplication.Service.ConversationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ConversationServiceImpl implements ConversationService {
    private ConversationDao conversationDao;

    public ConversationServiceImpl(ConversationDao conversationDao) {
        this.conversationDao = conversationDao;
    }

    @Override
    public Conversation NewConversation(Conversation conversation) {
        return conversationDao.save(conversation);
    }
}
