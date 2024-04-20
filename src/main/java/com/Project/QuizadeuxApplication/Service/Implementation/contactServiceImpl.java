package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.Entities.contacts;
import com.Project.QuizadeuxApplication.Service.contactsService;
import com.Project.QuizadeuxApplication.DAO.contactDao;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class contactServiceImpl implements contactsService {
    private contactDao contactDao;

    public contactServiceImpl(com.Project.QuizadeuxApplication.DAO.contactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public contacts NewContact(contacts contacts) {
        return contactDao.save(contacts);
    }
}
