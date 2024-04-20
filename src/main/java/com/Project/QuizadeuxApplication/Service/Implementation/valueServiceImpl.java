package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.Entities.value;
import com.Project.QuizadeuxApplication.Service.valueService;
import  com.Project.QuizadeuxApplication.DAO.valueDao;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class valueServiceImpl implements valueService {
    private valueDao valueDao;

    public valueServiceImpl(com.Project.QuizadeuxApplication.DAO.valueDao valueDao) {
        this.valueDao = valueDao;
    }

    @Override
    public value NewValue(value value) {
        return valueDao.save(value);
    }
}
