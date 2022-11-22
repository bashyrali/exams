package com.company.strategy;

import com.company.Cat;

import java.util.List;

public interface Strategy {
    public void feed(Cat cat);
    public void heal(Cat cat);
    public void play(Cat cat);
}
