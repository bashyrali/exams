package com.company.strategy;

import com.company.Cat;

import java.util.List;

public class OldCat implements Strategy{

    @Override
    public void feed(Cat cat) {
        if(cat.randomNum(1,4) == 1){
            System.out.println("Кот отравился");
            cat.setMood(cat.getMood()-20);
            cat.setHealth(cat.getHealth()-20);
        }
        else {
            cat.setSatiety(cat.getSatiety()+4);
            cat.setMood(cat.getMood()+4);
        }


    }

    @Override
    public void heal(Cat cat) {
        cat.setHealth(cat.getHealth()+4);
        cat.setMood(cat.getMood()-6);
        cat.setSatiety(cat.getSatiety()-6);
    }

    @Override
    public void play(Cat cat) {
        if (cat.randomNum(1, 5) == 1){
            System.out.println("Кот травмировался во время игр =(");
            cat.setMood(cat.getMood()-30);
            cat.setHealth(cat.getHealth()-30);
        }
        else {
            cat.setMood(cat.getMood()+4);
            cat.setHealth(cat.getHealth()+4);
        }
        cat.setSatiety(cat.getSatiety()-6);

    }
}
