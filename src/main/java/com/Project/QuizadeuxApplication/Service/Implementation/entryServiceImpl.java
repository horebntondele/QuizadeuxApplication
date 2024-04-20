package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.entryDao;
import com.Project.QuizadeuxApplication.Entities.entry;
import com.Project.QuizadeuxApplication.Service.entryService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class entryServiceImpl implements entryService {
   private entryDao entryDao;

    public entryServiceImpl(com.Project.QuizadeuxApplication.DAO.entryDao entryDao) {
        this.entryDao = entryDao;
    }

    @Override
    public entry Newentry(entry entry) {
        return entryDao.save(entry);
    }
}
