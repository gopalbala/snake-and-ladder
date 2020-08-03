package com.gb.snakeladder.model;

import lombok.Getter;
import org.apache.commons.lang3.RandomUtils;

import java.util.*;

@Getter
public class Game {
    private int numberOfSnakes;
    private int numberOfLadders;

    private Queue<Player> players;
    private List<Snake> snakes;
    private List<Ladder> ladders;

    private Board board;
    private Dice dice;

    public Game(int numberOfLadders, int numberOfSnakes,
                int boardSize) {
        this.numberOfLadders = numberOfLadders;
        this.numberOfSnakes = numberOfSnakes;
        this.players = new ArrayDeque<>();
        snakes = new ArrayList<>(numberOfSnakes);
        ladders = new ArrayList<>(numberOfLadders);
        board = new Board(boardSize);
        dice = new Dice(1, 6, 2);
        initBoard();
    }

    private void initBoard() {
        Set<String> slSet = new HashSet<>();
        for (int i = 0; i < numberOfSnakes; i++) {
            while (true) {
                int snakeStart = RandomUtils.nextInt(board.getStart(), board.getSize());
                int snakeEnd = RandomUtils.nextInt(board.getStart(), board.getSize());
                if (snakeEnd >= snakeStart)
                    continue;
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
                if (ladderEnd <= ladderStart)
                    continue;
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

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void playGame() {
        while (players.size() >= 2) {
            Player player = players.poll();
            int val = dice.roll();
            int newPosition = player.getPosition() + val;
            if (player.getPosition() > board.getEnd())
                player.setPosition(player.getPosition());
            else if (player.getPosition() == board.getEnd()) {
                player.setWon(true);
            } else {
                player.setPosition(getNewPosition(newPosition));
                players.offer(player);
            }
        }
    }

    private int getNewPosition(int newPosition) {
        for (Snake snake : snakes) {
            if (snake.getHead() == newPosition)
                return snake.getTail();
        }
        for (Ladder ladder : ladders) {
            if (ladder.getStart() == newPosition) {
                return ladder.getEnd();
            }
        }
        return newPosition;
    }
}
