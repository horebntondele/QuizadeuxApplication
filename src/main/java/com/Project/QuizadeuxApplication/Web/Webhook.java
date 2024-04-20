package com.Project.QuizadeuxApplication.Web;

import com.Project.QuizadeuxApplication.Entities.*;
import com.Project.QuizadeuxApplication.Mpesa.C2BAPI;
import com.Project.QuizadeuxApplication.Service.*;
import com.Project.QuizadeuxApplication.WhatSappWebAPI.WhatsappWeb;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static com.Project.QuizadeuxApplication.Constant.Environment.Whatsapp_token;

@RestController
@RequestMapping("/Webhook")
@CrossOrigin("*")
public class Webhook {

    private Whatsapp whatsapp;
    private valueService valueService;
    private profileService profileService;
    private metadataService metadataService;
    private messageService messageService;
    private entryService entryService;
    private textService textService;
    private contactsService contactsService;
    private SessionManagementService sessionManagementService;
    private InteractionService interactionService;
    private changesService changesService;

    private  StatusesService statusesService;

    private ConversationService conversationService;

    private OriginService originService;

    private PricingService pricingService;
    private InteractiveService interactiveService;

    private ContextService contextService;
    private ReplyService replyService;
    private CategoryService categoryService;
    private  C2BTokenService c2BTokenService;
    private PaymentService paymentService;
    private QuestionsListService questionsListService;

    private PartieService partieService;

    public Webhook(Whatsapp whatsapp, com.Project.QuizadeuxApplication.Service.valueService valueService, com.Project.QuizadeuxApplication.Service.profileService profileService, com.Project.QuizadeuxApplication.Service.metadataService metadataService, com.Project.QuizadeuxApplication.Service.messageService messageService, com.Project.QuizadeuxApplication.Service.entryService entryService, com.Project.QuizadeuxApplication.Service.textService textService, com.Project.QuizadeuxApplication.Service.contactsService contactsService, SessionManagementService sessionManagementService, InteractionService interactionService, com.Project.QuizadeuxApplication.Service.changesService changesService, StatusesService statusesService, ConversationService conversationService, OriginService originService, PricingService pricingService, InteractiveService interactiveService, ContextService contextService, ReplyService replyService, CategoryService categoryService, C2BTokenService c2BTokenService, PaymentService paymentService, QuestionsListService questionsListService, PartieService partieService) {
        this.whatsapp = whatsapp;
        this.valueService = valueService;
        this.profileService = profileService;
        this.metadataService = metadataService;
        this.messageService = messageService;
        this.entryService = entryService;
        this.textService = textService;
        this.contactsService = contactsService;
        this.sessionManagementService = sessionManagementService;
        this.interactionService = interactionService;
        this.changesService = changesService;
        this.statusesService = statusesService;
        this.conversationService = conversationService;
        this.originService = originService;
        this.pricingService = pricingService;
        this.interactiveService = interactiveService;
        this.contextService = contextService;
        this.replyService = replyService;
        this.categoryService = categoryService;
        this.c2BTokenService = c2BTokenService;
        this.paymentService = paymentService;
        this.questionsListService = questionsListService;
        this.partieService = partieService;
    }


    @GetMapping
    public ResponseEntity<String> webhookVerify(@RequestParam("hub.mode") String mode,
                                                @RequestParam("hub.challenge") String challenge,
                                                @RequestParam("hub.verify_token") String token) {
        System.out.println(mode);
        System.out.println(challenge);
        System.out.println(token);
        if (mode.equals("subscribe") && token.equals(Whatsapp_token)) {
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Verification token or mode mismatch", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping
    public ResponseEntity<String> getRequest(@RequestBody String payload) throws Exception {

        System.out.println(payload);
        WhatsappWeb whatsappWeb = new WhatsappWeb(whatsapp, valueService, profileService, metadataService, messageService, entryService, textService, contactsService, changesService,statusesService , conversationService, originService, pricingService, interactiveService, contextService, replyService);
        Interaction interaction = whatsappWeb.PersisteWhatsappLoad(payload);
        System.out.println("Mon interaction MSISDN is " + interaction.getMSISDN());
        interactionService.NewInteraction(interaction);
        SessionManagement sessionManagement = sessionManagementService.FindSessionbyMSISDN(interaction.getMSISDN());

            if (sessionManagement!= null) {
                String Interaction_type = interaction.getInteractiontype();
                if(Interaction_type==null){
                    System.out.println("reponse de Whatsapp");
                    return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                else {
                    System.out.println("-------------------------------------------------------------------------------------------");
                    System.out.println(Interaction_type);
                    System.out.println("-------------------------------------------------------------------------------------------");
                    String WorkflowStatus = sessionManagement.getWorkflowStatus();
                    System.out.println(" le numero qui chat est " + sessionManagement.getSessionMSISDN() + " le status du workflow est " + sessionManagement.getWorkflowStatus());
                    if (WorkflowStatus.equals("Start")) {
                        if (Interaction_type.equals("interactive")) {
                            String choix =interaction.getReplyID();
                            if(choix.equals("Accepter")){
                                System.out.println("il faut choisir le type de question");
                                ArrayList<Section> sections= new ArrayList<>();
                                List<Category> categories=categoryService.AllCategory();
                                categories.forEach(category -> {
                                    Row row= new Row();
                                    ArrayList<Row> rows = new ArrayList<>();
                                    Section section= new Section();
                                    row.setId(category.getCategoryId());
                                    row.setDescription(category.getCategorydescription());
                                    row.setTitle(category.getCategoryTitle());
                                    section.setTitle(category.getSectiontitle());
                                    rows.add(row);
                                    section.setRows(rows);
                                    sections.add(section);

                                });

                                Action action= new Action();
                                action.setButton("Cliquer pour Choisir");
                                action.setSections(sections);

                                Body body= new Body();
                                body.setText("Voici un petit appercu des différents cadeaux que vous recevrez si vous obtenez la note maximale à ce jeu: ");
                                Footer footer= new Footer();
                                footer.setText("Mariage Reussie");
                                Header header= new Header();
                                header.setType("text");
                                header.setText("Veuillez choisir votre catégorie préférée: ");

                                InteractiveMessage interactive= new InteractiveMessage();
                                interactive.setType("list");
                                interactive.setHeader(header);
                                interactive.setBody(body);
                                interactive.setFooter(footer);
                                interactive.setAction(action);
                                whatsappWeb.SendInteractiveMessage(interaction.getMSISDN(),interactive);
                                sessionManagement.setWorkflowStatus("Accepted_to_Participate");
                                sessionManagement.setAccept_Participation(true);
                                sessionManagementService.NewSession(sessionManagement);

                            }else {
                                String Msg = "Vous avez rennoncé au jeu Suka na Mariage pour l'instant. Pour en savoir plus sur notre organisation visitez nos plate-forme en cliquant sur : https://is.gd/pA4diD ou sur  http://v.gd/1GlUI5.";
                                whatsappWeb.SendtextMessage(interaction.getMSISDN(), Msg);
                                sessionManagement.setWorkflowStatus("Closed");
                                sessionManagement.setStatus(false);
                                sessionManagementService.NewSession(sessionManagement);
                            }

                        } else {
                            System.out.println("Mauvaise input ");

                        }
                    }
                    else if (WorkflowStatus.equals("Accepted_to_Participate")) {
                        String interactivetype= interaction.getInteractive_type();
                        if(interactivetype==null){
                            System.out.println("Mauvaise input ");
                        }
                        else if(interactivetype.equals("list_reply")){
                        System.out.println("Accepter le Payement");
                        String categorychoisie=interaction.getReplyID();
                        Category category= categoryService.findCategoryById(categorychoisie);

                        ReplyMessage reply= new ReplyMessage();
                        ReplyMessage reply1= new ReplyMessage();
                        Buttons buttons= new Buttons();
                        Action action= new Action();
                        InteractiveMessage interactive= new InteractiveMessage();
                        reply.setId("Payer");
                        reply.setTitle("Accepter");
                        buttons.setType("reply");
                        buttons.setReply(reply);
                        buttons.setReply(reply);
                        Buttons buttons1 = new Buttons();
                        reply1.setId("PasPayer");
                        reply1.setTitle("Refuser");
                        buttons1.setType("reply");
                        buttons1.setReply(reply1);
                        ArrayList<Buttons>buttons2 = new ArrayList<>();
                        buttons2.add(buttons);
                        buttons2.add(buttons1);
                        action.setButtons(buttons2);
                        Body body= new Body();
                        body.setText("Vous avez Choisie la Category "+category.getCategoryTitle() + ". Pour Confirmer cette catégorie Payez svp "+category.getPrice()+" via votre Mobile Money ou votre compte Maxi-cash");
                        Footer footer= new Footer();
                        footer.setText("Mariage Reussie");
                        Header header= new Header();
                        header.setType("text");
                        header.setText("Accepter le Payement");
                        interactive.setType("button");
                        interactive.setBody(body);
                        interactive.setHeader(header);
                        interactive.setFooter(footer);
                        interactive.setAction(action);
                        whatsappWeb.SendInteractiveMessage(interaction.getMSISDN(),interactive);
                        sessionManagement.setCategory(categorychoisie);
                        sessionManagement.setWorkflowStatus("Accepter/Refuser Payement");
                        sessionManagementService.NewSession(sessionManagement);
                        } else {
                            System.out.println(" mauvaise input");
                        }
                    }
                    else if (WorkflowStatus.equals("Accepter/Refuser Payement")) {
                        String interactive_type=interaction.getInteractive_type();
                        if (interactive_type==null){
                            System.out.println("mauvaise input");
                        } else if (interactive_type.equals("button_reply")) {
                            String Reponse =interaction.getReplyID();
                            if(Reponse.equals("Payer")){
                                ArrayList<Section> sections= new ArrayList<>();
                                    Row row= new Row();
                                    Row row1= new Row();
                                    Row row2= new Row();
                                    Row row3= new Row();
                                    ArrayList<Row> rows = new ArrayList<>();
                                    ArrayList<Row> rows1 = new ArrayList<>();
                                    ArrayList<Row> rows2 = new ArrayList<>();
                                    ArrayList<Row> rows3 = new ArrayList<>();
                                    Section section= new Section();
                                    Section section1= new Section();
                                    Section section2= new Section();
                                    Section section3= new Section();
                                    row.setId("M-Pesa");
                                    row1.setId("Orange");
                                    row2.setId("Airtel");
                                    row3.setId("Maxi-cash");
                                    row.setDescription("Le Payement sera effectuer à partir de votre compte M-Pesa");
                                    row1.setDescription("Le Payement sera effectuer à partir de votre compte Orange Money");
                                    row2.setDescription("Le Payement sera effectuer à partir de votre compte Airtel Money");
                                    row3.setDescription("Le Payement sera effectuer à partir de votre compte Maxi-Cash");
                                    row.setTitle("M-Pesa");
                                    row1.setTitle("Orange Money");
                                    row2.setTitle("Airtel Money");
                                    row3.setTitle("Maxi-Cash");
                                    section.setTitle("Payez Par M-Pesa");
                                    section1.setTitle("Payez Par Orange");
                                    section2.setTitle("Payez Par Airtel");
                                    section3.setTitle("Payez Par Maxi-Cash");
                                    rows.add(row);
                                    rows1.add(row1);
                                    rows2.add(row2);
                                    rows3.add(row3);
                                    section.setRows(rows);
                                    section1.setRows(rows1);
                                    section2.setRows(rows2);
                                    section3.setRows(rows3);
                                    sections.add(section);
                                    sections.add(section1);
                                    sections.add(section2);
                                    sections.add(section3);

                                Action action= new Action();
                                action.setButton("Selectionner ici");
                                action.setSections(sections);

                                Body body= new Body();
                                body.setText("Vous devez Selectionner le moyen par le quel vous allez effectuer votre paiement ");
                                Footer footer= new Footer();
                                footer.setText("Mariage Reussie");
                                Header header= new Header();
                                header.setType("text");
                                header.setText("Veuillez Selection Votre type de Paiement: ");

                                InteractiveMessage interactive= new InteractiveMessage();
                                interactive.setType("list");
                                interactive.setHeader(header);
                                interactive.setBody(body);
                                interactive.setFooter(footer);
                                interactive.setAction(action);
                                whatsappWeb.SendInteractiveMessage(interaction.getMSISDN(),interactive);
                                sessionManagement.setWorkflowStatus("Selection_duPayement");
                                sessionManagementService.NewSession(sessionManagement);

                            }else {
                                System.out.println("Annuler et fermer la session");
                            }
                        }else {
                            System.out.println("mauvaise input");
                        }

                    }
                    else if (WorkflowStatus.equals("Selection_duPayement")) {
                        String interactive_type=interaction.getInteractive_type();
                        if(interactive_type==null){
                            System.out.println("mauvaise input");
                        } else if (interactive_type.equals("list_reply")) {
                            String MoyenPayement = interaction.getReplyID();
                            if(MoyenPayement.equals("M-Pesa")){
                                System.out.println("demander le numero de telephone");
                                String Msg= "Priere de donner  votre numero M-Pesa au format 243";
                                whatsappWeb.SendtextMessage(interaction.getMSISDN(),Msg);
                                sessionManagement.setWorkflowStatus("Confirmer_le_Payement");
                                sessionManagement.setPayement_type("M-Pesa");
                                sessionManagementService.NewSession(sessionManagement);
                            }


                        }else {
                            System.out.println("mauvaise input");
                        }

                    }
                    else if (WorkflowStatus.equals("Confirmer_le_Payement")){
                        String interactiontype = interaction.getInteractiontype();
                        if(interactiontype==null){
                            System.out.println("reponse de Whatsapp");
                        }else {

                            C2BAPI c2BAPI= new C2BAPI();
                            String formatMSISDN=c2BAPI.MsisdnFormatCheck(interaction.getMessagetext(),sessionManagement.getPayement_type());
                            if(formatMSISDN.equals("true")){

                                C2BToken c2BToken = c2BTokenService.GetOpenToken();
                                Category category= categoryService.findCategoryById(sessionManagement.getCategory());
                                Payment payment= new Payment("M-Pesa",interaction.getMessagetext(),category.getPrice().replace("$",""));
                                Payment payment1= c2BAPI.C2BAPICall(payment,c2BToken.getToken());
                                if(payment1!=null){
                                    payment1.setInteraction(interaction);
                                    paymentService.NewPayment(payment1);
                                    String PaymentStatus=payment1.getPayment_Status();
                                    if(PaymentStatus.equals("Request processed successfully")){
                                        String Msg = "Félicitation, Votre Payment est éffectuer avec Success.  Prière de donner le numero Watsapp de votre Fiancé(e) NB: Verifier Votre numero avant la soumission, une mauvaise soumission entrainera votre echec";
                                        whatsappWeb.SendtextMessage(interaction.getMSISDN(),Msg);
                                        sessionManagement.setWorkflowStatus("ConjointNumber");
                                        sessionManagementService.NewSession(sessionManagement);
                                    }
                                    else {
                                        String Msg = "Votre Payment à échouer priere de Verifier votre Moyen de paiement et re-essayer";
                                        whatsappWeb.SendtextMessage(interaction.getMSISDN(),Msg);
                                    }
                                }else {
                                    System.out.println("Timeout Pas de reponse");
                                }
                            }
                        }
                    }
                    else if(WorkflowStatus.equals("ConjointNumber")){
                        String interactiontype = interaction.getInteractiontype();
                        if(interactiontype==null){
                            System.out.println("reponse de Whatsapp");
                        }else {
                                sessionManagement.setConjointMSISDN(interaction.getMessagetext());
                            SessionManagement sessionManagementconjoint = sessionManagementService.FindSessionbyMSISDN(sessionManagement.getConjointMSISDN());
                            if(sessionManagementconjoint!=null){
                                sessionManagementconjoint.setStatus(false);
                                sessionManagementService.NewSession(sessionManagementconjoint);
                            }
                            sessionManagementconjoint=new SessionManagement(sessionManagement.getConjointMSISDN(),true);
                            Partie partie= new Partie();
                            partie.setParticipant(interaction.getMSISDN());
                            partie.setConjoint(sessionManagement.getConjointMSISDN());
                            partie.setPartieStatus(true);
                            partie.setSessionManagement(sessionManagement);
                            partieService.NewPartie(partie);
                            whatsappWeb.SendTemplateMessage(sessionManagement.getConjointMSISDN(), "sukanamariage1","en");
                            whatsappWeb.SendtextMessage(interaction.getMSISDN(), "Prière de demander à votre Conjoint d'accepter la Participation aux jeux afin de Commencer");
                            sessionManagementconjoint.setSessionType("Conjoint");
                            sessionManagementconjoint.setConjointMSISDN(interaction.getMSISDN());

                            sessionManagement.setWorkflowStatus("AwaitingConjointfeedback");
                            sessionManagementconjoint.setWorkflowStatus("AwaitingConjointfeedback");
                            sessionManagementService.NewSession(sessionManagement);
                            sessionManagementService.NewSession(sessionManagementconjoint);
                        }

                    } else if (WorkflowStatus.equals("AwaitingConjointfeedback")){
                        String Sessiontype =sessionManagement.getSessionType();
                        if (Sessiontype.equals("Participant")){
                            whatsappWeb.SendtextMessage(interaction.getMSISDN(), "Prière de demander à votre Conjoint d'accepter la Participation aux jeux afin de Commencer");
                        }
                        else if(Sessiontype.equals("Conjoint")){
                            String Msg="Veuillez Patienter quelques instants, vous allez recevoir les question une apres l'autre, vous avez 30 sec pour repondre à Chaque question";
                           whatsappWeb.SendtextMessage(interaction.getMSISDN(), Msg);
                           whatsappWeb.SendtextMessage(sessionManagement.getConjointMSISDN(), Msg);
                           List<QuestionsList> MesQuestions = new ArrayList<>();
                           Partie partie=partieService.getPartybyParticipant(sessionManagement.getConjointMSISDN());
                           System.out.println("Partie SessionID  ----------- "+ partie.getSessionManagement().getSessionId());
                           for (int i= 0; i<3; i++){
                               QuestionsList questionsList=questionsListService.getQuestion();
                               if(!MesQuestions.contains(questionsList)){
                                   MesQuestions.add(questionsList);
                               }else {
                                   i=i-1;
                               }

                           }
                            partie.setQuestionsList(MesQuestions);
                           partieService.NewPartie(partie);
                            ArrayList<Section> sections= new ArrayList<>();
                            Row row= new Row();
                            Row row1= new Row();
                            Row row2= new Row();
                            Row row3= new Row();
                            ArrayList<Row> rows = new ArrayList<>();
                            ArrayList<Row> rows1 = new ArrayList<>();
                            ArrayList<Row> rows2 = new ArrayList<>();
                            ArrayList<Row> rows3 = new ArrayList<>();
                            Section section= new Section();
                            Section section1= new Section();
                            Section section2= new Section();
                            Section section3= new Section();
                            row.setId("Reponse 1");
                            row1.setId("Reponse 2");
                            row2.setId("Reponse 3");
                            row3.setId("Reponse 4");
                            row.setDescription(MesQuestions.get(0).getReponse1());
                            row1.setDescription(MesQuestions.get(0).getReponse2());
                            row2.setDescription(MesQuestions.get(0).getReponse3());
                            row3.setDescription(MesQuestions.get(0).getReponse4());
                            row.setTitle("Reponse 1");
                            row1.setTitle("Reponse 2");
                            row2.setTitle("Reponse 3");
                            row3.setTitle("Reponse 4");
                            section.setTitle("Choisie la 1er Reponse");
                            section1.setTitle("Choisie la 2e Reponse");
                            section2.setTitle("Choisie la 3e Reponse");
                            section3.setTitle("Choisie la 4e Reponse");
                            rows.add(row);
                            rows1.add(row1);
                            rows2.add(row2);
                            rows3.add(row3);
                            section.setRows(rows);
                            section1.setRows(rows1);
                            section2.setRows(rows2);
                            section3.setRows(rows3);
                            sections.add(section);
                            sections.add(section1);
                            sections.add(section2);
                            sections.add(section3);

                            Action action= new Action();
                            action.setButton("Choisie la Reponse");
                            action.setSections(sections);

                            Body body= new Body();
                            body.setText(MesQuestions.get(0).getQuestion());
                            Footer footer= new Footer();
                            footer.setText("Mariage Reussie");
                            Header header= new Header();
                            header.setType("text");
                            int Question =sessionManagement.getQuestion()+1;
                            header.setText("Veuillez Repondre à la Question N "+ Question);

                            InteractiveMessage interactive= new InteractiveMessage();
                            interactive.setType("list");
                            interactive.setHeader(header);
                            interactive.setBody(body);
                            interactive.setFooter(footer);
                            interactive.setAction(action);
                            whatsappWeb.SendInteractiveMessage(interaction.getMSISDN(),interactive);
                            whatsappWeb.SendInteractiveMessage(sessionManagement.getConjointMSISDN(),interactive);
                            SessionManagement sessionManagementconjoint = sessionManagementService.FindSessionbyMSISDN(sessionManagement.getConjointMSISDN());

                            sessionManagementconjoint.setQuestion(sessionManagementconjoint.getQuestion()+1);
                            sessionManagement.setQuestion(sessionManagement.getQuestion()+1);

                            sessionManagement.setWorkflowStatus("StartQuestions"+sessionManagement.getQuestion());
                            sessionManagementconjoint.setWorkflowStatus("StartQuestions"+ sessionManagementconjoint.getQuestion());
                            sessionManagementService.NewSession(sessionManagement);
                            sessionManagementService.NewSession(sessionManagementconjoint);

                        }

                    }
                    else if (WorkflowStatus.equals("StartQuestions"+sessionManagement.getQuestion())){

                        String sessiontype1 =sessionManagement.getSessionType();
                        List<String>ListReponse= new ArrayList<>();
                        Partie partie= new Partie();
                        String bonneReponse=null;
                        String Reponse = null;
                        String Choix= interaction.getReplyID();
                        int Questionnumber =sessionManagement.getQuestion()-1;
                        if(sessiontype1.equals("Participant")){
                             partie= partieService.getPartybyParticipant(sessionManagement.getSessionMSISDN());
                             ListReponse=partie.getParticipantResponse();
                             ListReponse.add(interaction.getReplyID());
                             partie.setParticipantResponse(ListReponse);
                             bonneReponse= partie.getQuestionsList().get(Questionnumber).getBonneReponse();
                             if (Choix.equals("Reponse 1")){
                                 Reponse= partie.getQuestionsList().get(Questionnumber).getReponse1();
                             }
                             else if (Choix.equals("Reponse 2")){
                                 Reponse= partie.getQuestionsList().get(Questionnumber).getReponse2();
                             }
                             else if (Choix.equals("Reponse 3")){
                                 Reponse= partie.getQuestionsList().get(Questionnumber).getReponse3();
                             }
                             else if (Choix.equals("Reponse 4")){
                                 Reponse= partie.getQuestionsList().get(Questionnumber).getReponse4();
                             }
                            int point= sessionManagement.getReponseParticipant();
                             if (bonneReponse.equals(Reponse)){
                                 point=point+1;
                                 System.out.println("Session "+ sessiontype1+ " Bonne Reponse : "+bonneReponse +" Reponse du Participant "+Reponse + " Point "+ point);
                                 sessionManagement.setReponseParticipant(point);
                             }
                        }
                        else if(sessiontype1.equals("Conjoint")){
                             partie= partieService.getPartybyParticipant(sessionManagement.getConjointMSISDN());
                            bonneReponse= partie.getQuestionsList().get(Questionnumber).getBonneReponse();
                            if (Choix.equals("Reponse 1")){
                                Reponse= partie.getQuestionsList().get(Questionnumber).getReponse1();
                            }
                            else if (Choix.equals("Reponse 2")){
                                Reponse= partie.getQuestionsList().get(Questionnumber).getReponse2();
                            }
                            else if (Choix.equals("Reponse 3")){
                                Reponse= partie.getQuestionsList().get(Questionnumber).getReponse3();
                            }
                            else if (Choix.equals("Reponse 4")){
                                Reponse= partie.getQuestionsList().get(Questionnumber).getReponse4();
                            }
                            ListReponse=partie.getConjointResponse();
                            ListReponse.add(interaction.getReplyID());
                            partie.setConjointResponse(ListReponse);

                            int point= sessionManagement.getReponseConjoint();
                            if (bonneReponse.equals(Reponse)){
                                point=point+1;
                                System.out.println("Session "+ sessiontype1+ " Bonne Reponse : "+bonneReponse +" Reponse du Participant "+Reponse + " Point "+ point);
                                sessionManagement.setReponseConjoint(point);
                            }
                        }
//                        QuestionsList questionsList=questionsListService.getQuestion();
                        List<QuestionsList> MesQuestions = partie.getQuestionsList();
                        partieService.NewPartie(partie);
                        int Question =sessionManagement.getQuestion();
                        if(Question<3) {
                            Question=Question+1;
                        ArrayList<Section> sections= new ArrayList<>();
                        Row row= new Row();
                        Row row1= new Row();
                        Row row2= new Row();
                        Row row3= new Row();
                        ArrayList<Row> rows = new ArrayList<>();
                        ArrayList<Row> rows1 = new ArrayList<>();
                        ArrayList<Row> rows2 = new ArrayList<>();
                        ArrayList<Row> rows3 = new ArrayList<>();
                        Section section= new Section();
                        Section section1= new Section();
                        Section section2= new Section();
                        Section section3= new Section();
                        row.setId("Reponse 1");
                        row1.setId("Reponse 2");
                        row2.setId("Reponse 3");
                        row3.setId("Reponse 4");
                        row.setDescription(MesQuestions.get(sessionManagement.getQuestion()).getReponse1());
                        row1.setDescription(MesQuestions.get(sessionManagement.getQuestion()).getReponse2());
                        row2.setDescription(MesQuestions.get(sessionManagement.getQuestion()).getReponse3());
                        row3.setDescription(MesQuestions.get(sessionManagement.getQuestion()).getReponse4());
                        row.setTitle("Reponse 1");
                        row1.setTitle("Reponse 2");
                        row2.setTitle("Reponse 3");
                        row3.setTitle("Reponse 4");
                        section.setTitle("Choisie la 1er Reponse");
                        section1.setTitle("Choisie la 2e Reponse");
                        section2.setTitle("Choisie la 3e Reponse");
                        section3.setTitle("Choisie la 4e Reponse");
                        rows.add(row);
                        rows1.add(row1);
                        rows2.add(row2);
                        rows3.add(row3);
                        section.setRows(rows);
                        section1.setRows(rows1);
                        section2.setRows(rows2);
                        section3.setRows(rows3);
                        sections.add(section);
                        sections.add(section1);
                        sections.add(section2);
                        sections.add(section3);
                        Action action= new Action();
                        action.setButton("Choisie la Reponse");
                        action.setSections(sections);
                        Body body= new Body();
                        body.setText(MesQuestions.get(sessionManagement.getQuestion()).getQuestion());
                        Footer footer= new Footer();
                        footer.setText("Mariage Reussie");
                        Header header= new Header();
                        header.setType("text");
                        header.setText("Veuillez Repondre à la Question N: "+Question);
                        InteractiveMessage interactive= new InteractiveMessage();
                        interactive.setType("list");
                        interactive.setHeader(header);
                        interactive.setBody(body);
                        interactive.setFooter(footer);
                        interactive.setAction(action);
                        whatsappWeb.SendInteractiveMessage(interaction.getMSISDN(),interactive);
                            sessionManagement.setQuestion(sessionManagement.getQuestion() + 1);
                            sessionManagement.setWorkflowStatus("StartQuestions" + sessionManagement.getQuestion());
                            sessionManagementService.NewSession(sessionManagement);
                        }else {
                            sessionManagement.setWorkflowStatus("TestCompleted");
                            sessionManagementService.NewSession(sessionManagement);
                            String sessiontype =sessionManagement.getSessionType();
                            if (sessiontype.equals("Participant")){
                                SessionManagement Conjoinsession = sessionManagementService.FindSessionbyMSISDN(sessionManagement.getConjointMSISDN());
                                String conjointStatus = Conjoinsession.getWorkflowStatus();
                                if(conjointStatus.equals("TestCompleted")){
                                    whatsappWeb.SendtextMessage(sessionManagement.getSessionMSISDN(),"La Partie est terminer Vous avez Obtenue " +sessionManagement.getReponseParticipant() + "/"+Question+ " et Votre Conjoint à Obtenue " + Conjoinsession.getReponseConjoint() + "/"+Question);
                                    whatsappWeb.SendtextMessage(sessionManagement.getConjointMSISDN(), "La Partie est terminer Vous avez Obtenue " +Conjoinsession.getReponseConjoint() + "/"+Question+ " et Votre Conjoint à Obtenue " + sessionManagement.getReponseParticipant() + "/"+Question);
                                    sessionManagement.setStatus(false);
                                    Conjoinsession.setStatus(false);
                                    partie.setPartieStatus(false);
                                    sessionManagementService.NewSession(sessionManagement);
                                    sessionManagementService.NewSession(Conjoinsession);
                                    partieService.NewPartie(partie);
                                System.out.println("Afficher Resultat, Partie Completed");
                                }else {
                                    String Msg= "Vous avez terminer votre Partie, Priere de demander à votre conjoint de finir sa part";
                                    whatsappWeb.SendtextMessage(interaction.getMSISDN(), Msg);
                                }
                            } else if (sessiontype.equals("Conjoint")) {
                                SessionManagement ParticipantConjoint = sessionManagementService.FindSessionbyMSISDN(sessionManagement.getConjointMSISDN());
                                String ParticipantStatus= ParticipantConjoint.getWorkflowStatus();
                                if(ParticipantStatus.equals("TestCompleted")) {
                                    whatsappWeb.SendtextMessage(sessionManagement.getConjointMSISDN(),"La Partie est terminer Vous avez Obtenue " +ParticipantConjoint.getReponseParticipant() + "/"+Question+ " et Votre Conjoint à Obtenue " + sessionManagement.getReponseConjoint() + "/"+Question);
                                    whatsappWeb.SendtextMessage(sessionManagement.getSessionMSISDN(), "La Partie est terminer Vous avez Obtenue " +sessionManagement.getReponseConjoint() + "/"+Question+ " et Votre Conjoint à Obtenue " + ParticipantConjoint.getReponseParticipant() + "/"+Question);
                                    sessionManagement.setStatus(false);
                                    ParticipantConjoint.setStatus(false);
                                    partie.setPartieStatus(false);
                                    sessionManagementService.NewSession(sessionManagement);
                                    sessionManagementService.NewSession(ParticipantConjoint);
                                    partieService.NewPartie(partie);
                                    System.out.println("Afficher Resultat, Partie Completed");
                                }else {
                                    String Msg= "Vous avez terminer votre Partie, Priere de demander à votre conjoint de finir sa part";
                                    whatsappWeb.SendtextMessage(interaction.getMSISDN(), Msg);
                                }
                            }
                        }
                    }

                    System.out.println("il y a une session ouverte");
                    return  new ResponseEntity<>(HttpStatus.OK);
                }


            }

            else {
                String interactiontype = interaction.getInteractiontype();
                if(interactiontype==null){
                    System.out.println("reponse de Whatsapp");
                    return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }else {
               sessionManagement =new SessionManagement(interaction.getMSISDN(), true);
                String Msg = "Bonjour et bienvenu au jeu ludique à propos du mariage dénommé: QUIZ POUR 2. A travers ce Quiz, testez vos connaissance sur le mariage et gagnez de nombreux cadeaux utiles pour l'organisation de vos cérémonies de mariage.";
                whatsappWeb.SendtextMessage(interaction.getMSISDN(), Msg);
                sessionManagement.setWorkflowStatus("Start");
                sessionManagement.setSessionType("Participant");
                ReplyMessage reply= new ReplyMessage();
                ReplyMessage reply1= new ReplyMessage();
                Buttons buttons= new Buttons();
                Action action= new Action();
                InteractiveMessage interactive= new InteractiveMessage();
                reply.setId("Accepter");
                reply.setTitle("Accepter");
                buttons.setType("reply");
                buttons.setReply(reply);
                buttons.setReply(reply);
                Buttons buttons1 = new Buttons();
                reply1.setId("Refuser");
                reply1.setTitle("Refuser");
                buttons1.setType("reply");
                buttons1.setReply(reply1);
                ArrayList<Buttons>buttons2 = new ArrayList<>();
                buttons2.add(buttons);
                buttons2.add(buttons1);
                action.setButtons(buttons2);
                Body body= new Body();
                body.setText("Voulez-vous confirmer votre participation au jeu QUIZ POUR 2 par une inscription formelle?");
                Footer footer= new Footer();
                footer.setText("Mariage Reussie");
                Header header= new Header();
                header.setType("text");
                header.setText("QUIZ POUR 2");
                interactive.setType("button");
                interactive.setBody(body);
                interactive.setHeader(header);
                interactive.setFooter(footer);
                interactive.setAction(action);
                whatsappWeb.SendInteractiveMessage(interaction.getMSISDN(),interactive);
                sessionManagementService.NewSession(sessionManagement);
                    return  new ResponseEntity<>(HttpStatus.OK);
            } }


    }
}