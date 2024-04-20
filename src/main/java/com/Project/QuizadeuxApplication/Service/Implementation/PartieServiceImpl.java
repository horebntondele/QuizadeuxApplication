package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.PartieDao;
import com.Project.QuizadeuxApplication.Entities.Partie;
import com.Project.QuizadeuxApplication.Service.PartieService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PartieServiceImpl implements PartieService {
    PartieDao partieDao;

    public PartieServiceImpl(PartieDao partieDao) {
        this.partieDao = partieDao;
    }

    @Override
    public Partie NewPartie(Partie partie) {
        return partieDao.save(partie);
    }

    @Override
    public Partie getPartybyParticipant(String participant) {
        return partieDao.getPartybyParticipant(participant);
    }
}
