package com.company.strategy;

import com.company.Cat;

import java.util.List;

public class YoungCat implements Strategy{

    @Override
    public void feed(Cat cat) {
        if(cat.randomNum(1,4) == 1){
            System.out.println("Кот отравился");
            cat.setMood(cat.getMood()-20);
            cat.setHealth(cat.getHealth()-20);
        }
        else {
            cat.setSatiety(cat.getSatiety()+7);
            cat.setMood(cat.getMood()+7);
        }



    }

    @Override
    public void heal(Cat cat) {
        cat.setHealth(cat.getHealth()+7);
        cat.setMood(cat.getMood()-3);
        cat.setSatiety(cat.getSatiety()-3);
    }

    @Override
    public void play(Cat cat) {
        if (cat.randomNum(1, 5) == 1){
            System.out.println("Кот травмировался во время игр =(");
            cat.setMood(cat.getMood()-30);
            cat.setHealth(cat.getHealth()-30);
        }
        else {
            cat.setMood(cat.getMood()+7);
            cat.setHealth(cat.getHealth()+7);
        }
        cat.setSatiety(cat.getSatiety()-3);
    }
}
