package com.company.lesson.lesson45;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    List<Task> tasks = new ArrayList<>();

    public Tasks() {

    }

    public void addTask(Task task){
        tasks.add(task);
    }
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
