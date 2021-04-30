package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class GUI extends Game2048 {
    int frameHeight = 600;
    int frameWidth = 600;
    int gameBoardSize = 560;
    int marginSize = 20;
    Color backgroundColor = new Color(0xFAF8EF);


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


        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_UP) {
                    up(field);

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }

                    if(changed){
                    addRandom2or4(field);
                    }
                } else if (key == KeyEvent.VK_LEFT) {
                    left(field);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    if(changed)
                    addRandom2or4(field);
                } else if (key == KeyEvent.VK_DOWN) {
                    down(field);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    if(changed)
                    addRandom2or4(field);
                } else if (key == KeyEvent.VK_RIGHT) {
                    right(field);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    if(changed)
                    addRandom2or4(field);
                }
                if(key==KeyEvent.VK_S){
                    field = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
                    addRandom2or4(field);
                    addRandom2or4(field);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        do {
            JPanel gameBoard = new JPanel();


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
            JLabel[][] labelArray = {{label00, label01, label02, label03}, {label10, label11, label12, label13}
                    , {label20, label21, label22, label23}, {label30, label31, label32, label33}};

            JPanel panel00 = new JPanel();
            JPanel panel01 = new JPanel();
            JPanel panel02 = new JPanel();
            JPanel panel03 = new JPanel();
            JPanel panel10 = new JPanel();
            JPanel panel11 = new JPanel();
            JPanel panel12 = new JPanel();
            JPanel panel13 = new JPanel();
            JPanel panel20 = new JPanel();
            JPanel panel21 = new JPanel();
            JPanel panel22 = new JPanel();
            JPanel panel23 = new JPanel();
            JPanel panel30 = new JPanel();
            JPanel panel31 = new JPanel();
            JPanel panel32 = new JPanel();
            JPanel panel33 = new JPanel();

            JPanel[][] panelArray = {{panel00, panel01, panel02, panel03}, {panel10, panel11, panel12, panel13},
                    {panel20, panel21, panel22, panel23}, {panel30, panel31, panel32, panel33}};

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    switch (field[i][j]) {
                        case 0:
                            panelArray[i][j].setBackground(new Color(204, 192, 179));
                            break;
                        case 2:
                            panelArray[i][j].setBackground(new Color(238, 228, 218));
                            break;
                        case 4:
                            panelArray[i][j].setBackground(new Color(237, 224, 200));
                            break;
                        case 8:
                            panelArray[i][j].setBackground(new Color(242, 177, 121));
                            break;
                        case 16:
                            panelArray[i][j].setBackground(new Color(245, 149, 99));
                            break;
                        case 32:
                            panelArray[i][j].setBackground(new Color(246, 124, 95));
                            break;
                        case 64:
                            panelArray[i][j].setBackground(new Color(246, 94, 59));
                            break;
                        case 128:
                            panelArray[i][j].setBackground(new Color(237, 207, 114));
                            break;
                        case 256:
                            panelArray[i][j].setBackground(new Color(237, 204, 97));
                            break;
                        case 512:
                            panelArray[i][j].setBackground(new Color(237, 200, 80));
                            break;
                        case 1024:
                            panelArray[i][j].setBackground(new Color(237, 197, 63));
                            break;
                        case 2048:
                            panelArray[i][j].setBackground(new Color(237, 194, 46));
                            break;

                    }
                    labelArray[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                    panelArray[i][j].add(labelArray[i][j]);
                    gameBoard.setLayout(new GridLayout(4,4));
                    gameBoard.add(panelArray[i][j]);
                }
            }


            frame.getContentPane().add(gameBoard);
            frame.getContentPane().add(southPanel, BorderLayout.SOUTH);
            frame.getContentPane().add(westPanel, BorderLayout.WEST);
            frame.getContentPane().add(northPanel, BorderLayout.NORTH);
            frame.getContentPane().add(eastPanel, BorderLayout.EAST);
            frame.getContentPane().setPreferredSize(new Dimension(frameWidth, frameHeight));
            frame.pack();
            frame.setVisible(true);
            frame.setFocusable(true);


        }while (!checkIfLost(field) && !checkIfWin(field));

        if(checkIfLost(field)){
            northPanel.removeAll();
            JLabel lostLabel = new JLabel("You lost!! Press 's' to start again", SwingConstants.CENTER);
            lostLabel.setFont(new Font("Serif", Font.BOLD, 20));
            northPanel.add(lostLabel);
            frame.setFocusable(true);
            frame.setVisible(true);
        }

        if(checkIfWin(field)){
            northPanel.removeAll();
            JLabel winLabel = new JLabel("You win!! Press 's' to start again", SwingConstants.CENTER);
            winLabel.setFont(new Font("Serif", Font.BOLD, 20));
            northPanel.add(winLabel);
            frame.setFocusable(true);
            frame.setVisible(true);
        }
    }
}