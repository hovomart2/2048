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

    //returns true if the field is full
    public static boolean fieldIsFull(int[][] field) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (field[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void addRandom2or4(int[][] field) {
        int row;
        int column;
        if (!fieldIsFull(field)) {
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

    public static void rightMove(int[][] field) {
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
            //when the row is in {a,a,b,b} form it becomes {0,0,2a,2b} by this if statement
            if (field[j][3] == field[j][2] && field[j][3] != 0 && field[j][0] == field[j][1]) {
                field[j][3] *= 2;
                field[j][2] = 2 * field[j][0];
                field[j][1] = 0;
                field[j][0] = 0;
            //when the row is in {a,b,c,c} form it becomes {0,a,b,2c} by this if statement
            } else if (field[j][3] == field[j][2] && field[j][3] != 0 && field[j][0] != field[j][1]) {
                field[j][3] *= 2;
                field[j][2] = field[j][1];
                field[j][1] = field[j][0];
                field[j][0] = 0;
            //when the row is in {a,b,b,c} form it becomes {0,a,2b,c} by this if statement
            } else if (field[j][2] == field[j][1]) {
                field[j][2] *= 2;
                field[j][1] = field[j][0];
                field[j][0] = 0;
            //when the row is in {a,a,b,c} form it becomes {0,2a,b,c} by this if statement
            } else if (field[j][0] == field[j][1]) {
                field[j][1] *= 2;
                field[j][0] = 0;
            }
        }


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (initialField[i][j] != field[i][j])
                    changed = true;
            }
        }
    }

    public static void rotate(int[][] field) {
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

    public static void upMove(int[][] field) {

        rotate(field);
        rightMove(field);
        rotate(field);
        rotate(field);
        rotate(field);

    }

    public static void downMove(int[][] field) {
        rotate(field);
        rotate(field);
        rotate(field);
        rightMove(field);
        rotate(field);
    }

    public static void leftMove(int[][] field) {
        rotate(field);
        rotate(field);
        rightMove(field);
        rotate(field);
        rotate(field);
    }

    //checks if the matrix has moves to the right
    public static boolean hasMovesToTheRight(int[][] field) {
        boolean breakloops = false;
        for (int i = 0; i < 4 && !breakloops; i++) {
            for (int j = 1; j < 4 && !breakloops; j++) {
                if (i == 3 && j == 3 && field[3][3] != field[3][2]) {
                    return false;
                }
                if (field[i][j] == field[i][j - 1] || field[i][j - 1] == 0) {
                    breakloops = true;
                }

            }
        }
        return true;
    }

    //checks all directions and returns true if the player lost, returns false if the game is not over
    public static boolean shouldLost(int[][] field) {
        int[][] initialField = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                initialField[i][j] = field[i][j];
            }
        }
        //checks whether the matrix has move to the right
        if (!hasMovesToTheRight(field)) {
            rotate(field);
            //checks whether the matrix can move up
            if (!hasMovesToTheRight(field)) {
                rotate(field);
                //checks whether the matrix has move to the left
                if (!hasMovesToTheRight(field)) {
                    rotate(field);
                    //checks whether the matrix can move down
                    if (!hasMovesToTheRight(field))
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

    //returns true if the player wins, returns false if the game is continuing
    public static boolean shouldWin(int[][] field) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (field[i][j] == 128) {
                    printField(field);
                    return true;
                }
            }
        }
        return false;
    }

}

