package main.java;

public class Board {
    private int ROWS;
    private int COLS;
    public static int[][] board ;
    public static final int EMPTY = 0;
    public static final int CROSS = 1;
    public static final int NOUGHT = 2;

    public Board(int ROWS, int COLS){
        this.ROWS = ROWS;
        this.COLS = COLS;
        this.board = new int[this.ROWS][this.COLS];

        //initiate empty board
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                this.board[row][col] = EMPTY;  // all cells empty
            }
        }
    }

    public int FillCellContent(int row, int col, int seed){
//        if (seed == EMPTY || seed == CROSS || seed == NOUGHT)
        return this.board[row][col] = seed;
    }

    public int getCellContent(int row, int col){
        return this.board[row][col];
    }

    public boolean isEmptyCell(int row, int col){
        return (this.board[row][col]==EMPTY);
    }

    public int getROWS(){
        return this.ROWS;
    }

    public int getCOLS(){
        return this.COLS;
    }

    public boolean isObstacleStillvalid(){
        boolean valid = false;
        int count = 0;
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if(this.board[row][col] == EMPTY)count++;  // all cells empty
            }
        }
        if (count>3)valid=true;
        return valid;
    }

}
