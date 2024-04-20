package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.OriginDao;
import com.Project.QuizadeuxApplication.Entities.Origin;
import com.Project.QuizadeuxApplication.Service.OriginService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OriginServiceImpl implements OriginService {
    private OriginDao originDao;

    public OriginServiceImpl(OriginDao originDao) {
        this.originDao = originDao;
    }

    @Override
    public Origin NewOrigin(Origin origin) {
        return originDao.save(origin);
    }
}
