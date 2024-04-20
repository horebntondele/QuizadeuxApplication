package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.C2BTokenDao;
import com.Project.QuizadeuxApplication.Entities.C2BToken;
import com.Project.QuizadeuxApplication.Mpesa.GenerateSessionID;
import com.Project.QuizadeuxApplication.Service.C2BTokenService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Transactional
public class C2BTokenServiceImpl implements C2BTokenService {
    private C2BTokenDao c2BTokenDao;

    public C2BTokenServiceImpl(C2BTokenDao c2BTokenDao) {
        this.c2BTokenDao = c2BTokenDao;
    }

    @Override
    public C2BToken GetOpenToken() throws Exception {
        C2BToken c2BToken= c2BTokenDao.GetOpentoken();
        if (c2BToken != null) {
            LocalDateTime Tokenexpirytime= c2BToken.getExpirytime();
            if(Tokenexpirytime.isBefore(LocalDateTime.now())){
                return  c2BToken;
            }else {
                c2BToken.setTokenStatus(false);
                c2BTokenDao.save(c2BToken);
                GenerateSessionID generateSessionID= new GenerateSessionID();
                C2BToken c2BToken1= new C2BToken();
                c2BToken1.setToken(generateSessionID.GenerateSessionID());
                c2BToken1.setTokenStatus(true);
                c2BToken1.setExpirytime(LocalDateTime.now().minusMinutes(2));

                return  c2BTokenDao.save(c2BToken1);
            }
        }else {
            GenerateSessionID generateSessionID= new GenerateSessionID();
            C2BToken c2BToken1= new C2BToken();
            c2BToken1.setToken(generateSessionID.GenerateSessionID());
            c2BToken1.setTokenStatus(true);
            c2BToken1.setExpirytime(LocalDateTime.now().minusMinutes(2));
           return c2BTokenDao.save(c2BToken1);

        }
    }

    @Override
    public C2BToken Newtoken(C2BToken c2BToken) {
        return c2BTokenDao.save(c2BToken);
    }
}
