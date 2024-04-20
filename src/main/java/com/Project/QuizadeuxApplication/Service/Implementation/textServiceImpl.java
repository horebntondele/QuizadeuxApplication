package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.textDao;
import com.Project.QuizadeuxApplication.Entities.text;
import com.Project.QuizadeuxApplication.Service.textService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class textServiceImpl implements textService {
    private textDao textDao;

    public textServiceImpl(com.Project.QuizadeuxApplication.DAO.textDao textDao) {
        this.textDao = textDao;
    }

    @Override
    public text Newtext(text text) {
        return textDao.save(text);
    }
}
