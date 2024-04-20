package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.ReplyDao;
import com.Project.QuizadeuxApplication.Entities.Reply;
import com.Project.QuizadeuxApplication.Service.ReplyService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private ReplyDao replyDao;

    public ReplyServiceImpl(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    @Override
    public Reply Newreply(Reply reply) {
        return replyDao.save(reply);
    }
}
