package com.Project.QuizadeuxApplication.Entities;

import java.util.ArrayList;

public class Action {
    private String button;
    private ArrayList<Section> sections;

    private   ArrayList<Buttons> buttons;

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public ArrayList<Buttons> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<Buttons> buttons) {
        this.buttons = buttons;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }
}
