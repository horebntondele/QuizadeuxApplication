package com.Project.QuizadeuxApplication.runner;

import com.Project.QuizadeuxApplication.DAO.RoleDao;
import com.Project.QuizadeuxApplication.DAO.UserDao;
import com.Project.QuizadeuxApplication.Entities.*;
import com.Project.QuizadeuxApplication.Mpesa.GenerateSessionID;
import com.Project.QuizadeuxApplication.Service.CategoryService;
import com.Project.QuizadeuxApplication.Service.QuestionsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private QuestionsListService questionsListService;
    @Override
    public void run(String... args) throws Exception {

test();
AddCategory();
AddQuestion();
    }

    private   void test() throws Exception {
        User user= new User("Whatsapp", "Whatsapp");
        Role role= new Role("Admin");
        roleDao.save(role);
        user.assignRoleToUser(role);
        userDao.save(user);

    }

    public void AddCategory(){
        Category category= new Category("DavidetDalila","Roi David et dalila","Participation: 1$ Prix: Coiffure gratuite Homme/Femme","1$","Categorie N°1");
        categoryService.NewCategory(category);
        Category category1 =new Category("SalomonetReinedeSaba","Roi salomon Reine d Saba","Participation: 5$ Prix: MakeUp Femme/Parfum Homme","5$","Categorie N°2");
        categoryService.NewCategory(category1);
        Category category2= new Category("MarieetJoseph","Amour Marie et Joseph", "Participation: 20$ Prix: Sejour pour 2 de 2 Jrs dans un Hotel 3 étoiles","20$","Categorie N°3");
        categoryService.NewCategory(category2);
        Category category3= new Category("IsaacetRebecca","Amour Issac et Rebecca","Participation: 10$ Prix: 1 Dinner pour 2 dans un top restaurant","10$","Categorie N°4");
        categoryService.NewCategory(category3);
    }

    public void  AddQuestion(){
        QuestionsList questionsList= new QuestionsList("Etes-vous prêt pour le mariage ?","Prèsque prêt ", "J'ai encore besoin d'une mise à niveau","Non","Oui", "J'ai encore besoin d'une mise à niveau");
        questionsListService.NewQuestion(questionsList);
        QuestionsList questionsList1= new QuestionsList("Qu'attendez-vous de votre couple ?","Vivre le bonheur","accomplir une mission commune","devenir un couple célèbre","Aucune bonne Reponse","accomplir une mission commune");
        questionsListService.NewQuestion(questionsList1);
        QuestionsList questionsList2= new QuestionsList("Avec votre fiancé (e) Etes-vous plutôt : ","Fusionnel ?","autonome ?","A et B sont vrai","A et B sont faux","Fusionnel ?");
        questionsListService.NewQuestion(questionsList2);
        QuestionsList questionsList3= new QuestionsList("Dans votre vécu quotidien avec votre fiancé (e), qu’appréciez-vous le plus: ","la régularité des actions habituelles ?","Les surprises de l'improvisation tout le temps?","A et B sont vrai","A et B sont faux?","la régularité des actions habituelles ?");
        questionsListService.NewQuestion(questionsList3);
        QuestionsList questionsList4= new QuestionsList("En matière de goûts (livres, films, sorties) et d'idées (politiques, religieuses, culturelles) : ", "Nous avons peu de points communs et nous en parlons peu?","Nous partageons exactement les mêmes?","Nous partageons plutôt ses goûts à elle/lui?","Nous sommes plutôt différents et c'est très bien comme ça?","Nous sommes plutôt différents et c'est très bien comme ça?");
        questionsListService.NewQuestion(questionsList4);

    }
}
