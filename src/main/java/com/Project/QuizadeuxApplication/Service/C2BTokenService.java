package com.Project.QuizadeuxApplication.Service;

import com.Project.QuizadeuxApplication.Entities.C2BToken;

public interface C2BTokenService {

    C2BToken GetOpenToken() throws Exception;
    C2BToken Newtoken(C2BToken c2BToken);
}
