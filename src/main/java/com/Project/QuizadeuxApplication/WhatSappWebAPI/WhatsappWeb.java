package com.Project.QuizadeuxApplication.WhatSappWebAPI;

import com.Project.QuizadeuxApplication.Entities.*;
import com.Project.QuizadeuxApplication.Service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.stream.Stream;

public class WhatsappWeb {


    private Whatsapp whatsapp;
    private com.Project.QuizadeuxApplication.Service.valueService valueService;
    private com.Project.QuizadeuxApplication.Service.profileService profileService;
    private com.Project.QuizadeuxApplication.Service.metadataService metadataService;
    private com.Project.QuizadeuxApplication.Service.messageService messageService;
    private entryService entryService;
    private textService textService;
    private contactsService contactsService;

    private changesService changesService;

    private StatusesService statusesService;

    private ConversationService conversationService;

    private OriginService originService;

    private PricingService pricingService;

    private InteractiveService interactiveService;

    private ContextService contextService;

    private ReplyService replyService;


    private String Whatsappurl = "https://graph.facebook.com/v18.0/237816439419505/messages";
    private String WhatsApptoken = "EAAFNKBPOflcBO5ocNoZA4JojtQEFwZCrvRZCv6wZAB0FCRmMcqbw1uErXJ5GDWHg17uXcRR6GPZBTa1VGNb8nAJIy9nWalfUwOsClEWq8xzH4qtlLjlZBbshVLbA33oZAcQodUZB8Hbrp4JVfSanu50Ps27ym4gaNo4UB1dAgC4MJTIDnbZByXzEOS7iCcXopcMpHmizIeV8bMTGZB2ivW";

    public WhatsappWeb() {
    }

    public WhatsappWeb(Whatsapp whatsapp, com.Project.QuizadeuxApplication.Service.valueService valueService, com.Project.QuizadeuxApplication.Service.profileService profileService, com.Project.QuizadeuxApplication.Service.metadataService metadataService, com.Project.QuizadeuxApplication.Service.messageService messageService, com.Project.QuizadeuxApplication.Service.entryService entryService, com.Project.QuizadeuxApplication.Service.textService textService, com.Project.QuizadeuxApplication.Service.contactsService contactsService, com.Project.QuizadeuxApplication.Service.changesService changesService, StatusesService statusesService, ConversationService conversationService, OriginService originService, PricingService pricingService, InteractiveService interactiveService, ContextService contextService, ReplyService replyService) {
        this.whatsapp = whatsapp;
        this.valueService = valueService;
        this.profileService = profileService;
        this.metadataService = metadataService;
        this.messageService = messageService;
        this.entryService = entryService;
        this.textService = textService;
        this.contactsService = contactsService;
        this.changesService = changesService;
        this.statusesService = statusesService;
        this.conversationService = conversationService;
        this.originService = originService;
        this.pricingService = pricingService;
        this.interactiveService = interactiveService;
        this.contextService = contextService;

        this.replyService = replyService;
    }

    public String SendtextMessage(String destination, String body) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(Whatsappurl))
                .header("Authorization", "Bearer " + WhatsApptoken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"to\": \"" + destination + "\", \"type\": \"text\",\n" +
                        "\"text\":{\n" +
                        "\"body\": \"" + body + "\"\n" +
                        "}}"))
                .build();
        HttpClient http = HttpClient.newHttpClient();
        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return response.body();
    }

    public String SendTemplateMessage(String destination, String template, String languageCode) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(Whatsappurl))
                .header("Authorization", "Bearer " + WhatsApptoken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"to\": \"" + destination + "\", \"type\": \"template\",\n" +
                        "\"template\":{\n" +
                        "\"name\": \""+ template+"\", \"language\": { \"code\": \""+languageCode+"\" } }}"))
                .build();
        HttpClient http = HttpClient.newHttpClient();
        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return response.body();
    }

    public String SendInteractiveMessage(String destination, InteractiveMessage interactive) throws URISyntaxException, IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        System.out.println(mapper.writeValueAsString(interactive));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(Whatsappurl))
                .header("Authorization", "Bearer " + WhatsApptoken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{ \"messaging_product\": \"whatsapp\", \"to\": \"" + destination + "\", \"type\": \"interactive\",\n" +
                        "\"interactive\":" + mapper.writeValueAsString(interactive) + "}"))
                .build();
        HttpClient http = HttpClient.newHttpClient();
        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return response.body();
    }

    public Interaction PersisteWhatsappLoad(String payload) throws JsonProcessingException {
        Interaction interaction = new Interaction();
        ObjectMapper objectMapper = new ObjectMapper();
        WatsAppMessage parsedPayload = objectMapper.readValue(payload, WatsAppMessage.class);
        whatsapp.NewMessage(parsedPayload);
        Stream<entry> stream = Arrays.stream(parsedPayload.getEntry());
        stream.forEach(entry -> {
            entryService.Newentry(entry);
            Stream<changes> stream1 = Arrays.stream(entry.getChanges());
            stream1.forEach(changes -> {
                changesService.NewChanges(changes);
                value value = changes.getValue();
                valueService.NewValue(value);
                metadataService.NewMetadata(value.getMetadata());
                if (value.getContacts() != null) {
                    Stream<contacts> stream2 = Arrays.stream(value.getContacts());
                    stream2.forEach(contacts -> {
                        contactsService.NewContact(contacts);
                        profile profile = contacts.getProfile();
                        profileService.NewProfile(profile);
                        interaction.setMSISDN(contacts.getWa_id());
                        interaction.setName(profile.getName());
                        System.out.println(" Name " + profile.getName() + " and MSISDN " + contacts.getWa_id());

                    });
                }
                if (value.getStatuses() != null) {
                    Stream<Statuses> stream4 = Arrays.stream(value.getStatuses());
                    stream4.forEach(statuses -> {
                        statusesService.NewStatuses(statuses);
                        interaction.setMSISDN(statuses.getRecipient_id());
                        Conversation conversation = statuses.getConversation();
                        if(conversation!=null){
                            conversationService.NewConversation(conversation);
                            Origin origin = conversation.getOrigin();
                            originService.NewOrigin(origin);
                        }
                        Pricing pricing = statuses.getPricing();
                        if(pricing!=null){
                            pricingService.NewService(pricing);
                        }
                    });
                }

                System.out.println(value.getMessaging_product());
                if (value.getMessages() != null) {
                    Stream<messages> stream3 = Arrays.stream(value.getMessages());
                    stream3.forEach(messages -> {
                        interaction.setInteractiontype(messages.getType());
                        messageService.NewMessage(messages);
                        if (messages.getText() != null) {
                            textService.Newtext(messages.getText());
                            interaction.setMessagetext(messages.getText().getBody());
                            System.out.println("le contenu du message " + messages.getText().getBody());
                        } else {
                            contextService.NewContext(messages.getContext());
                            interactiveService.NewInteractive(messages.getInteractive());
                            interaction.setInteractive_type(messages.getInteractive().getType());
                            if(messages.getInteractive().getButton_reply()!=null){
                                replyService.Newreply(messages.getInteractive().getButton_reply());
                                interaction.setReplyID(messages.getInteractive().getButton_reply().getId());
                                System.out.println("la reponse du Participant " + messages.getInteractive().getButton_reply().getId());
                            }else {
                                replyService.Newreply(messages.getInteractive().getList_reply());
                                interaction.setReplyID(messages.getInteractive().getList_reply().getId());
                               System.out.println("La reponse du Participant " + messages.getInteractive().getList_reply().getId());
                            }

                        }
//                    interaction.setInteraction_Datetime(new Date(messages.getTimestamp()) );
                    });
                }
            });

        });
        System.out.println("Message de l'interaction " + interaction.getMessagetext());
        return interaction;
    }
}
