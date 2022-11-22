package com.company.lesson.lesson45;




public class Task {
    private int id;
    private String title;
    private boolean status;
    private String nameExecutor;
    private String date;
    private String description;

    public Task(int id, String title, boolean status, String nameExecutor, String date, String description) {
        this.id = id;
        this.title = title;
        this.status = false;
        this.nameExecutor = nameExecutor;
        this.date = date;
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNameExecutor() {
        return nameExecutor;
    }

    public void setNameExecutor(String nameExecutor) {
        this.nameExecutor = nameExecutor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
