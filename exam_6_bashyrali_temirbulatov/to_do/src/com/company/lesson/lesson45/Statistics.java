package com.company.lesson.lesson45;

import java.util.stream.Collectors;

public class Statistics {
int done;
int inProcess;
int allTask;

    public Statistics(Tasks tasks) {
        this.allTask = tasks.getTasks().size();
        setDone(tasks);
        this.done = done*100/allTask;
        setInProcess(tasks);
        this.inProcess = inProcess*100/allTask;


    }

    public int getDone() {
        return done;
    }

    public void setDone(Tasks tasks) {
        this.done = tasks.getTasks().stream().filter(a->a.isStatus()==true).collect(Collectors.toList()).size();
    }

    public int getInProcess() {
        return inProcess;
    }

    public void setInProcess(Tasks tasks) {
        this.inProcess = tasks.getTasks().stream().filter(a->a.isStatus()==false).collect(Collectors.toList()).size();;
    }
}
