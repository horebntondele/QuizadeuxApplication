package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.Entities.profile;
import com.Project.QuizadeuxApplication.Service.profileService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.Project.QuizadeuxApplication.DAO.profileDao;

@Service
@Transactional
public class profileServiceImpl implements profileService {
    private  profileDao profileDao;

    public profileServiceImpl(com.Project.QuizadeuxApplication.DAO.profileDao profileDao) {
        this.profileDao = profileDao;
    }

    @Override
    public profile NewProfile(profile profile) {
        return profileDao.save(profile);
    }
}
