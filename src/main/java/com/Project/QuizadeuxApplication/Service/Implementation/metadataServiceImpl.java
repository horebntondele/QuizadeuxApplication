package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.metadataDao;
import com.Project.QuizadeuxApplication.Entities.metadata;
import com.Project.QuizadeuxApplication.Service.metadataService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class metadataServiceImpl implements metadataService {
    private metadataDao metadataDao;

    public metadataServiceImpl(com.Project.QuizadeuxApplication.DAO.metadataDao metadataDao) {
        this.metadataDao = metadataDao;
    }

    @Override
    public metadata NewMetadata(metadata metadata) {
        return metadataDao.save(metadata);
    }
}
