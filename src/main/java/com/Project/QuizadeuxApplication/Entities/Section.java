package com.Project.QuizadeuxApplication.Entities;

import java.util.ArrayList;

public class Section {

    private String title;
    private ArrayList<Row> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }
}
