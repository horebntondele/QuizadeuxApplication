package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.WhatsappmessageDao;
import com.Project.QuizadeuxApplication.Entities.*;
import com.Project.QuizadeuxApplication.Service.Whatsapp;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Stream;

@Service
@Transactional
public class WhatsappImpl implements Whatsapp {

    private WhatsappmessageDao whatsappmessageDao;

    public WhatsappImpl(WhatsappmessageDao whatsappmessageDao) {
        this.whatsappmessageDao = whatsappmessageDao;
    }

    @Override
    public WatsAppMessage NewMessage(WatsAppMessage watsAppMessage) {

        return whatsappmessageDao.save(watsAppMessage);
    }
}
