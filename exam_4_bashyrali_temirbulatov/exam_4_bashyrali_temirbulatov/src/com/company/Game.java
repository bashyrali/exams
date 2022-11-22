package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Game {
    List<Cat> cats = new ArrayList<>();
    Scanner in = new Scanner(System.in);
    private boolean start = true;




    public void addCat(String name, int age){
        cats.add(new Cat(name,age));
    }

    public List<Cat> getCats() {
        return cats;
    }
    public void menu(){
        System.out.print("1-Покормить\n2-Поиграть \n3-К ветеринару\n4-Завести нового питомца\n5-Следущий день\n6-Закончить игру\n");
        int input = in.nextInt();
        switch (input){
            case 1:
                System.out.print("Выбери номер кота:");
                int a = in.nextInt();
                feedCat(a);
                break;
            case 2:
                System.out.print("Выбери номер кота:");
                int b = in.nextInt();
                playWithCat(b);
                break;
            case 3:
                System.out.print("Выбери номер кота:");
                int c = in.nextInt();
                healCat(c);
                break;
            case 4:
                userAddCat();
                break;
            case 5:
                isDead();
                nextDay();
                break;
            case 6:
                setStart(false);


        }


    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public  void userAddCat(){
        int i = 0;
        while (cats.size()>i){
            System.out.print("Введите имя кота: ");
            String name = in.next();
            System.out.print("Введите возраст(1-18): ");
            int age = in.nextInt();
            if (name != null && age>0 && age<19){
                addCat(name, age);
                i = cats.size()+1;
            }
            else {
                System.out.println("Вы ввели некоректные данные");
            }
        }
    }
    public void  feedCat(int num){
       if (cats.get(num-1).isFeed()){
           cats.get(num-1).getStrategy().feed(cats.get(num-1));
           System.out.printf("Вы накормили кота %s, %s лет(год)\n",cats.get(num-1).getName(),cats.get(num-1).getAge());
           cats.get(num-1).setFeed(false);
       }
       else {
           System.out.println("Вы уже кормили сегодня этого кота");
       }
    }
    public void playWithCat(int num){
        if (cats.get(num-1).isPlay()){
            cats.get(num-1).getStrategy().play(cats.get(num-1));
            System.out.printf("Вы поиграли с котом %s, %s лет(год)\n",cats.get(num-1).getName(),cats.get(num-1).getAge());
            cats.get(num - 1).setPlay(false);
        }
        else {
            System.out.println("Вы уже играли сегодня с этим котом");
        }
    }
    public void healCat(int num){
       if (cats.get(num-1).isHeal()){
           cats.get(num-1).getStrategy().heal(cats.get(num-1));
           System.out.printf("Вы сводили кота %s к ветеринару , %s лет(год)\n",cats.get(num-1).getName(),cats.get(num-1).getAge());
           cats.get(num-1).setHeal(false);
       }
       else {
           System.out.println("Вы уже сводили этого кота к ветерениру");

       }
    }
    public void nextDay(){

        for (Cat cat:cats) {
            cat.setFeed(true);
            cat.setPlay(true);
            cat.setHeal(true);
            cat.setSatiety(cat.getSatiety() - cat.randomNum(1, 5));
            cat.setMood(cat.getMood() + cat.randomNum(-3, 3));
            cat.setHealth(cat.getHealth() + cat.randomNum(-3, 3));

        }
        System.out.println("Наступил следущий день!");

    }

    public void isDead(){
        cats.removeIf(cat -> {
            if(cat.getHealth()<0) {
                System.out.println("Кот сдох!");
                return true;
            }
            return false;
        });
    }
    public void printCat(){
        System.out.println("---+---------+----------+------------+--------------+----------+------------------+");
        System.out.println(" # |    имя  |  возраст |  здоровье  | настроение   |  сытость |  средний уровень |");
        System.out.println("---+---------+----------+------------+--------------+----------+------------------+");
        cats.sort(Comparator.comparing(Cat::getMid).reversed());
        int i = 1;
        for (Cat cat:cats) {
            System.out.printf(" %s | %7s |   %4s   |    %5s   |    %5s     |    %2s    |      %5s       |\n",i, cat.getName(), cat.getAge(), cat.getHealth(), cat.getMood(),cat.getSatiety(),cat.getMid() );
            i++;
        }
    }
}


