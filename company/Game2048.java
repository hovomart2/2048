package com.company;

import java.util.Random;
import java.util.Scanner;


public class Game2048 {
    static int[][] field = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};

    static Random rd = new Random();

    public static void main(String[] args) {
        GUI gui = new GUI();
        /*addRandom2or4(field);
        addRandom2or4(field);
        Scanner sc = new Scanner(System.in);
        while (!checkIfLost(field) || !checkIfWin(field)) {
            System.out.println("Write r for right, l for left, u for up, d for down, new game for a new game");
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

        }
*/    }


    public static void printField(int[][] field) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(field[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void addRandom2or4(int[][] field) {
        int row;
        int column;
        if (!checkIfFieldIsFull(field)) {
            do {
                row = rd.nextInt(4);
                column = rd.nextInt(4);
            }
            while (field[row][column] != 0);
            field[row][column] = 2;
            printField(field);
        }
    }

    public static void movee(int[][] field) {


        for (int j = 0; j < 4; j++) {
            int count = 0;
            for (int i = 1; i < 4; i++) {
                if (field[j][0] == field[j][1] && field[j][2] == field[j][3] && field[j][2] != 0 && field[j][0] != 0) {
                    field[j][2] = field[j][0] * 2;
                    field[j][3] = field[j][3] * 2;
                    field[j][0] = 0;
                    field[j][1] = 0;
                } else if (field[j][i] == field[j][i - 1]) {
                    field[j][i] *= 2;
                    field[j][i - 1] = 0;
                } else if (i == 3 && field[j][i] == field[j][i - 2] && field[j][i - 1] == 0) {
                    field[j][i] *= 2;
                    field[j][i - 2] = 0;
                } else if (i == 3 && field[j][i] == field[j][i - 3] && field[j][i - 1] == 0 && field[j][i - 2] == 0) {
                    field[j][i] *= 2;
                    field[j][i - 3] = 0;
                } else if (i == 2 && field[j][i] == field[j][i - 2] && field[j][i - 1] == 0) {
                    field[j][i] *= 2;
                    field[j][i - 2] = 0;
                }

            }
        }


        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 1; i--) {
                if (field[j][i] == 0) {
                    field[j][i] = field[j][i - 1];
                    field[j][i - 1] = 0;
                }
            }
            for (int i = 3; i >= 1; i--) {
                if (field[j][i] == 0) {
                    field[j][i] = field[j][i - 1];
                    field[j][i - 1] = 0;
                }
            }
            for (int i = 3; i >= 1; i--) {
                if (field[j][i] == 0) {
                    field[j][i] = field[j][i - 1];
                    field[j][i - 1] = 0;
                }
            }
        }


    }

    public static void move(int[][] field) {

        /*for (int j = 0; j < 4; j++) {

            if (field[j][3] == field[j][2] && field[j][3] != 0) {
                field[j][3] *= 2;
                if (field[j][0] == field[j][1]) {
                    field[j][2] = field[j][0] * 2;
                    field[j][0] = 0;
                    field[j][1] = 0;
                }
                else {
                    field[j][2] = field[j][1];
                    field[j][1] = field[j][0];
                    field[j][0] = 0;
                }
            } else if (field[j][3] == field[j][1] && field[j][2] == 0) {
                field[j][3] *= 2;
                field[j][2] = field[j][0];
                field[j][1] = 0;
                field[j][0] = 0;
            } else if (field[j][3] == field[j][0] && field[j][1] == 0 && field[j][2] == 0) {
                field[j][3] *= 2;
                field[j][0] = 0;
            } else if (field[j][2] == field[j][1]) {
                if (field[j][3] != 0) {
                    field[j][2] *= 2;
                    field[j][1] = field[j][0];
                    field[j][0] = 0;
                } else {
                    field[j][3] = field[j][2] * 2;
                    field[j][2] = field[j][0];
                    field[j][1] = 0;
                    field[j][0] = 0;
                }

            }
            if (field[j][0] == field[j][2] && field[j][1] == 0) {
                if (field[j][3] == 0) {
                    field[j][3] = 2 * field[j][0];
                    field[j][0] = 0;
                    field[j][2] = 0;
                } else {
                    field[j][2] *= 2;
                    field[j][0] = 0;
                }
            } else if (field[j][0] == field[j][1] && field[j][2] == 0 && field[j][3] == 0) {
                field[j][3] = 2 * field[j][0];
                field[j][1] = 0;
                field[j][0] = 0;
            } else if (field[j][0] == field[j][1] && field[j][2] == 0 && field[j][3] != 0) {
                field[j][2] = 2 * field[j][0];
                field[j][0] = 0;
                field[j][1] = 0;
            } else if (field[j][0] == field[j][1] && field[j][2] != 0 && field[j][3] == 0) {
                field[j][3] = field[j][2];
                field[j][2] = field[j][0] * 2;
                field[j][1] = 0;
                field[j][0] = 0;
            } else if (field[j][0] == field[j][1] && field[j][2] != 0 && field[j][3] != 0) {
                field[j][1] *= 2;
                field[j][0] = 0;
            }
        }*/

        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 1; i--) {
                if (field[j][i] == 0) {
                    field[j][i] = field[j][i - 1];
                    field[j][i - 1] = 0;
                }
            }
            for (int i = 3; i >= 1; i--) {
                if (field[j][i] == 0) {
                    field[j][i] = field[j][i - 1];
                    field[j][i - 1] = 0;
                }
            }
            for (int i = 3; i >= 1; i--) {
                if (field[j][i] == 0) {
                    field[j][i] = field[j][i - 1];
                    field[j][i - 1] = 0;
                }
            }
        }

        for (int j = 0; j < 4; j++) {
            if (field[j][3] == field[j][2] && field[j][3] != 0 && field[j][0] == field[j][1]) {
                field[j][3] *= 2;
                field[j][2] = 2 * field[j][0];
                field[j][1] = 0;
                field[j][0] = 0;
            } else if (field[j][3] == field[j][2] && field[j][3] != 0 && field[j][0] != field[j][1]) {
                field[j][3] *= 2;
                field[j][2] = field[j][1];
                field[j][1] = field[j][0];
                field[j][0] = 0;
            } else if (field[j][2] == field[j][1]) {
                field[j][2] *= 2;
                field[j][1] = field[j][0];
                field[j][0] = 0;
            } else if (field[j][0] == field[j][1]) {
                field[j][1] *= 2;
                field[j][0] = 0;
            }
        }
    }

    public static boolean checkIfThereAreAnyRightMoves(int[][] field) {
        boolean breakloops = false;
        for (int i = 0; i < 4 && !breakloops; i++) {
            for (int j = 1; j < 4 && !breakloops; j++) {
                if (i == 3 && j == 3 && field[3][3] != field[3][2]) {
                    return true;
                }
                if (field[i][j] == field[i][j - 1] || field[i][j - 1] == 0) {
                    breakloops = true;
                }

            }
        }
        return false;

    }

    public static boolean checkIfLost(int[][] field) {
        int[][] initialField = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                initialField[i][j] = field[i][j];
            }
        }
        if (checkIfThereAreAnyRightMoves(field)) {
            rotate90(field);
            if (checkIfThereAreAnyRightMoves(field)) {
                rotate90(field);
                if (checkIfThereAreAnyRightMoves(field)) {
                    rotate90(field);
                    if (checkIfThereAreAnyRightMoves(field))
                        System.out.println("Yoy lost!!");
                    return true;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                field[i][j] = initialField[i][j];
            }
        }
        return false;
    }

    public static boolean checkIfFieldIsFull(int[][] field) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void rotate90(int[][] field) {
        int[][] newField = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newField[i][j] = field[3 - j][i];
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                field[i][j] = newField[i][j];
            }
        }
    }

    public static void rotate180(int[][] field) {
        int[][] newField = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newField[i][j] = field[i][3 - j];
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                field[i][j] = newField[i][j];
            }
        }

    }

    public static void rotate270(int[][] field) {
        int[][] newField = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                newField[i][j] = field[j][3 - i];
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                field[i][j] = newField[i][j];
            }
        }
    }

    public static void right(int[][] field) {
        move(field);
    }

    public static void up(int[][] field) {

        rotate90(field);
        move(field);
        rotate270(field);

    }

    public static void down(int[][] field) {

        rotate270(field);
        move(field);
        rotate90(field);

    }

    public static void left(int[][] field) {

        rotate180(field);
        move(field);
        rotate180(field);

    }

    public static boolean checkIfWin(int[][] field) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (field[i][j] == 2048) {
                    System.out.println("Congratulations, yopu win!!");
                    return true;
                }
            }
        }
        return false;
    }

    public static void newGame() {
        int[][] field = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        addRandom2or4(field);
        addRandom2or4(field);
        Scanner sc = new Scanner(System.in);
        while (!checkIfLost(field) || !checkIfWin(field)) {

            String input = sc.nextLine();
            if (input.equals("r")) {
                right(field);
                addRandom2or4(field);
            }
            if (input.equals("up")) {
                up(field);
                addRandom2or4(field);
            }
            if (input.equals("down")) {
                down(field);
                addRandom2or4(field);
            }
            if (input.equals("left")) {
                left(field);
                addRandom2or4(field);
            }
        }
    }
}

