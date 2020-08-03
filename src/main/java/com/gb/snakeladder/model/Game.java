package com.gb.snakeladder.model;

import lombok.Getter;
import org.apache.commons.lang3.RandomUtils;

import java.util.*;

@Getter
public class Game {
    private int numberOfSnakes;
    private int numberOfLadders;

    private List<Player> players;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    private Board board;

    public Game(int numberOfLadders, int numberOfSnakes,
                int boardSize) {
        this.numberOfLadders = numberOfLadders;
        this.numberOfSnakes = numberOfSnakes;
        this.players = new ArrayList<>();
        snakes = new ArrayList<>(numberOfSnakes);
        ladders = new ArrayList<>(numberOfLadders);
        board = new Board(boardSize);
        initBoard();
    }

    private void initBoard() {
        Set<String> slSet = new HashSet<>();
        for (int i = 0; i < numberOfSnakes; i++) {
            while (true) {
                int snakeStart = RandomUtils.nextInt(board.getStart(), board.getSize());
                int snakeEnd = RandomUtils.nextInt(board.getStart(), board.getSize());
                String startEndPair = String.valueOf(snakeStart) + snakeEnd;
                if (!slSet.contains(startEndPair)) {
                    Snake snake = new Snake(snakeStart, snakeEnd);
                    snakes.add(snake);
                    slSet.add(startEndPair);
                    break;
                }
            }
        }
        for (int i = 0; i < numberOfLadders; i++) {
            while (true) {
                int ladderStart = RandomUtils.nextInt(board.getStart(), board.getSize());
                int ladderEnd = RandomUtils.nextInt(board.getStart(), board.getSize());
                String startEndPair = String.valueOf(ladderStart) + ladderEnd;
                if (!slSet.contains(startEndPair)) {
                    Ladder ladder = new Ladder(ladderStart, ladderEnd);
                    ladders.add(ladder);
                    slSet.add(startEndPair);
                    break;
                }
            }
        }
    }
}
