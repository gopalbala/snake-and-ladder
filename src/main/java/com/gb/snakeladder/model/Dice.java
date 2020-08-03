package com.gb.snakeladder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.RandomUtils;

@AllArgsConstructor
@Getter
public class Dice {
    private int minValue;
    private int maxValue;
    private int currentValue;

    public int roll() {
        return RandomUtils.nextInt(minValue, maxValue + 1);
    }
}
