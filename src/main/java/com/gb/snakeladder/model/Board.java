package com.gb.snakeladder.model;

import lombok.Getter;

@Getter
public class Board {
    private int size;
    private int start;
    private int end;

    public Board(int size) {
        this.start = 1;
        this.end = start + size - 1;
        this.size = size;
    }
}
