package com.company;

import java.util.Random;


public class Game2048 {
    static int[][] field = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
    static boolean changed;

    static Random rd = new Random();

    public static void main(String[] args) {
        GUI gui = new GUI();
    }

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
            int a;
            a = rd.nextInt(10);
            if (a == 0) {  // there is 10% chance for a=0,
                // so there is 10% chance for adding 4 and 90% chance for adding 2 after a move
                field[row][column] = 4;
            } else
                field[row][column] = 2;
            printField(field);
        }

    }

    public static void move(int[][] field) {
        changed = false;
        int[][] initialField = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                initialField[i][j] = field[i][j];
            }
        }

        //bringing all numbers to the right in the same order without merging
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

        //merging numbers to the right if needed
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


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(initialField[i][j] != field[i][j])
                changed=true;
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
                        rotate90(field);
                    printField(field);
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
                    printField(field);
                    return true;
                }
            }
        }
        return false;
    }

}

