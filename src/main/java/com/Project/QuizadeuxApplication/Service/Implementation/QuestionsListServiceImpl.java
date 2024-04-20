package com.Project.QuizadeuxApplication.Service.Implementation;

import com.Project.QuizadeuxApplication.DAO.QuestionsListDao;
import com.Project.QuizadeuxApplication.Entities.QuestionsList;
import com.Project.QuizadeuxApplication.Service.QuestionsListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@Transactional
public class QuestionsListServiceImpl implements QuestionsListService {
    private QuestionsListDao questionsListDao;

    public QuestionsListServiceImpl(QuestionsListDao questionsListDao) {
        this.questionsListDao = questionsListDao;
    }

    @Override
    public QuestionsList NewQuestion(QuestionsList questionsList) {
        return questionsListDao.save(questionsList);
    }

    @Override
    public int NumberOfQuestions() {
        List<QuestionsList> questionsList= questionsListDao.findAll();
        return questionsList.size();
    }

//    @Override
//    public QuestionsList getQuestion(List<QuestionsList> existedquestion) {
//        return null;
//    }

    @Override
    public QuestionsList getQuestion() {
        List<QuestionsList> questionsList= questionsListDao.findAll();
        Random random= new Random();
        int questionnumber= random.nextInt(questionsList.size());
        return questionsList.get(questionnumber);
    }


}
