package com.company;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Scanner in = new Scanner(System.in);
        game.addCat("Peach", 11);
        game.addCat("Seth", 9);
        game.addCat("Jasper", 12);
        boolean start = true;
        while (game.isStart()){
            game.printCat();
            game.menu();
        }


    }
}
