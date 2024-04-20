package com.Project.QuizadeuxApplication.Service;

import com.Project.QuizadeuxApplication.Entities.Partie;

import java.util.List;

public interface PartieService {

    Partie NewPartie(Partie partie);
    Partie getPartybyParticipant(String participant);
}
