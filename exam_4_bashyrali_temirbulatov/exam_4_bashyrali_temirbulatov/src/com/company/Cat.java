package com.company;

import com.company.strategy.MiddleCat;
import com.company.strategy.OldCat;
import com.company.strategy.Strategy;
import com.company.strategy.YoungCat;

import java.util.HashMap;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Cat {
    private String name;
    private int age;
    private int mood;
    private int health;
    private int satiety;
    private int mid;
    private Strategy strategy;
    private boolean feed = true;
    private  boolean play = true;
    private boolean heal = true;

    public Cat(String name, int age){
        this.name = name;
        this.age = age;
        this.mood = randomNum(20,80);
        this.health = randomNum(20,80);
        this.satiety = randomNum(20, 80);
        this.mid = midLevel();

        if(age <= 5 && age > 1) {
            strategy = new YoungCat();
        }
        else if (age > 5 && age < 11){
            strategy = new MiddleCat();
        }
        else if (age >= 11 && age <= 18){
            strategy = new OldCat();
        }


    }

    public boolean isFeed() {
        return feed;
    }

    public void setFeed(boolean feed) {
        this.feed = feed;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public boolean isHeal() {
        return heal;
    }

    public void setHeal(boolean heal) {
        this.heal = heal;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;

//        HashMap<String,Predicate<Integer>> ageHash = new HashMap<>();
//        ageHash.put("Young",(x)->x > 0 && x<5);
//        ageHash.put("Middle", (x)->x >= 5 && x < 11);
//        ageHash.put("Old", (x)-> x>=11 && x < 19);
//        for (entry: ageHash.entrySet()); {
//
//        }

    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getSatiety() {
        return satiety;
    }

    public void setSatiety(int satiety) {
        this.satiety = satiety;
    }

    public int randomNum(int a, int b){
        Random random = new Random();
        return  a + random.nextInt(b - a + 1);
    }
    public int midLevel(){
        return (health+mood+satiety)/3;
    }
}
