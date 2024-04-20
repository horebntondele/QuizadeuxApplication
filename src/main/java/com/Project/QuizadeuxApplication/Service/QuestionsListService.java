package com.Project.QuizadeuxApplication.Service;

import com.Project.QuizadeuxApplication.Entities.QuestionsList;

import java.util.List;

public interface QuestionsListService {

    QuestionsList NewQuestion(QuestionsList questionsList);

     int NumberOfQuestions();

    QuestionsList getQuestion();
}
