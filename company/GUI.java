package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;


public class GUI extends Game2048 {
    Scanner sc = new Scanner(System.in);
    int frameHeight = 600;
    int frameWidth = 600;
    int gameBoardSize = 560;
    int marginSize = 20;
    Color backgroundColor = new Color(255, 255, 120);


    public JFrame frame;

    GUI() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(frameWidth, 40));

        JLabel gameLabel = new JLabel("2048 Game", SwingConstants.CENTER);
        gameLabel.setFont(new Font("Serif", Font.BOLD, 20));
        northPanel.add(gameLabel);
        northPanel.setBackground(backgroundColor);

        JPanel eastPanel = new JPanel();
        eastPanel.setBackground(backgroundColor);
        eastPanel.setPreferredSize(new Dimension(marginSize, gameBoardSize));

        JPanel westPanel = new JPanel();
        westPanel.setBackground(backgroundColor);
        westPanel.setPreferredSize(new Dimension(marginSize, gameBoardSize));

        JPanel southPanel = new JPanel();
        southPanel.setBackground(backgroundColor);
        southPanel.setPreferredSize(new Dimension(gameBoardSize, marginSize));
        addRandom2or4(field);
        addRandom2or4(field);
        while (!checkIfLost(field) || !checkIfWin(field)) {
            String input = sc.nextLine();
            if (input.equals("new game")) {
                newGame();
            }
            if (input.equals("r")) {
                right(field);
                addRandom2or4(field);
            }
            if (input.equals("u")) {
                up(field);

                addRandom2or4(field);
            }
            if (input.equals("d")) {
                down(field);
                addRandom2or4(field);
            }
            if (input.equals("l")) {
                left(field);
                addRandom2or4(field);
            }
            JLabel label00 = new JLabel(String.valueOf(field[0][0]), SwingConstants.CENTER);
            JLabel label01 = new JLabel(String.valueOf(field[0][1]), SwingConstants.CENTER);
            JLabel label02 = new JLabel(String.valueOf(field[0][2]), SwingConstants.CENTER);
            JLabel label03 = new JLabel(String.valueOf(field[0][3]), SwingConstants.CENTER);
            JLabel label10 = new JLabel(String.valueOf(field[1][0]), SwingConstants.CENTER);
            JLabel label11 = new JLabel(String.valueOf(field[1][1]), SwingConstants.CENTER);
            JLabel label12 = new JLabel(String.valueOf(field[1][2]), SwingConstants.CENTER);
            JLabel label13 = new JLabel(String.valueOf(field[1][3]), SwingConstants.CENTER);
            JLabel label20 = new JLabel(String.valueOf(field[2][0]), SwingConstants.CENTER);
            JLabel label21 = new JLabel(String.valueOf(field[2][1]), SwingConstants.CENTER);
            JLabel label22 = new JLabel(String.valueOf(field[2][2]), SwingConstants.CENTER);
            JLabel label23 = new JLabel(String.valueOf(field[2][3]), SwingConstants.CENTER);
            JLabel label30 = new JLabel(String.valueOf(field[3][0]), SwingConstants.CENTER);
            JLabel label31 = new JLabel(String.valueOf(field[3][1]), SwingConstants.CENTER);
            JLabel label32 = new JLabel(String.valueOf(field[3][2]), SwingConstants.CENTER);
            JLabel label33 = new JLabel(String.valueOf(field[3][3]), SwingConstants.CENTER);

            JPanel gameBoard = new JPanel();
            gameBoard.setLayout(new GridLayout(4, 4));
            gameBoard.add(label00);
            gameBoard.add(label01);
            gameBoard.add(label02);
            gameBoard.add(label03);
            gameBoard.add(label10);
            gameBoard.add(label11);
            gameBoard.add(label12);
            gameBoard.add(label13);
            gameBoard.add(label20);
            gameBoard.add(label21);
            gameBoard.add(label22);
            gameBoard.add(label23);
            gameBoard.add(label30);
            gameBoard.add(label31);
            gameBoard.add(label32);
            gameBoard.add(label33);


            gameBoard.setBackground((Color.GREEN));

            frame.getContentPane().add(gameBoard, BorderLayout.CENTER);
            frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
            frame.getContentPane().add(westPanel, BorderLayout.WEST);
            frame.getContentPane().add(northPanel, BorderLayout.NORTH);
            frame.getContentPane().add(eastPanel, BorderLayout.EAST);
            frame.getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));
            frame.pack();
            frame.setVisible(true);
        }
    }
}