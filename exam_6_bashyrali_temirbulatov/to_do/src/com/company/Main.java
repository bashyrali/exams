package com.company;

import com.company.lesson.lesson45.LessonServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new LessonServer("localhost", 8000).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
