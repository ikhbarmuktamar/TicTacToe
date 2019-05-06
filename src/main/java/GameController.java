package main.java;

import java.util.Random;
import java.util.Scanner;

public class GameController {
    private final Board boardGame;
    private final int rows, cols ;
    public static Scanner in = new Scanner(System.in);
    private int currentState ;
    private int gameMode ;
    private int point_to_win ;
    private boolean add_obstacle ;
    private boolean fix_obstacle ;
    private int obstacle_count;
    private boolean vs_com;

    private int count_round ;

    public GameController(Board board, int CurrentState, int point2win, boolean obstacle, boolean Fix_obstacle, int Obstacle_count, int gameMode, boolean with_com){
        this.boardGame = board;
        this.rows = this.boardGame.getROWS();
        this.cols = this.boardGame.getCOLS();
        this.currentState = CurrentState;
        this.point_to_win = point2win;
        this.add_obstacle = obstacle;
        this.fix_obstacle = Fix_obstacle;
        this.obstacle_count = Obstacle_count;
        this.gameMode = gameMode;
        this.vs_com = with_com;
        this.count_round = 0;
    }

    public int getCurrentState(){
        return this.currentState;
    }

    /** Player with the "theSeed" makes one move, with input validation.*/
    public void playerMove(int theSeed) {
        Random rand = new Random();
        boolean validInput = false;  // for input validation
        do {
            if (theSeed == Constant.CROSS) {
                System.out.print("Player 'X', enter your move (row[1-"+rows+"] column[1-"+cols+"]): ");
            } else {
                System.out.print("Player 'O', enter your move (row[1-"+rows+"] column[1-"+cols+"]): ");
            }

            if (theSeed == Constant.NOUGHT && this.vs_com){
                try {
                    Thread.sleep(1000);
                    System.out.println();
                    String resp_com = this.count_round == 0 ? Constant.StartDiaologCom.getRandomResponse().pesan()
                            : Constant.Response.getRandomResponse().pesan();
                    System.out.println(resp_com);
                    boolean valid_com = false;
                    while (!valid_com) {
                        int row_com = rand.nextInt(rows);
                        int col_com = rand.nextInt(cols);
                        if (boardGame.isEmptyCell(row_com, col_com)) {
                            if (!this.fix_obstacle) {
                                clearObstacle();
                            }
                            boardGame.FillCellContent(row_com, col_com, Constant.NOUGHT);
                            valid_com = true;
                        }
                    }
                    if (add_obstacle) {
                        addObstacle();
                    }
                    Thread.sleep(2000);
                    validInput = true;
                    count_round++;
                } catch (InterruptedException ex){

                }
            } else {
                int row = in.nextInt() - 1;  // array index starts at 0 instead of 1
                int col = in.nextInt() - 1;
                if (row >= 0 && row < rows && col >= 0 && col < cols && boardGame.isEmptyCell(row, col)) {
                    if (!this.fix_obstacle) {
                        clearObstacle();
                    }
                    boardGame.FillCellContent(row, col, theSeed);  // update game-board content
                    if (add_obstacle) {
                        addObstacle();
                    }
                    validInput = true;  // input okay, exit loop
                    count_round++;
                } else {
                    System.out.println();
                    System.out.println("This move at (" + (row + 1) + "," + (col + 1) + ") is not valid. Please Try again...");
                    System.out.println();
                }
            }
        } while (!validInput);  // repeat until input is valid
    }

    public void addObstacle(){
        Random rand = new Random();
        if (boardGame.isObstacleStillvalid()) {
            boolean loopObs = true;
            int count = 0;
            while (loopObs) {
                int row_obs = rand.nextInt(rows);
                int col_obs = rand.nextInt(cols);
                if (boardGame.isEmptyCell(row_obs, col_obs)) {
                    boardGame.FillCellContent(row_obs, col_obs, Constant.OBSTACLE);
                    count++;
                    if (count == this.obstacle_count) loopObs = false;
                }
            }
        }
    }

    /** Update the "currentState" after the player with "theSeed" has placed on
     (currentRow, currentCol). */
    public void updateGame(int theSeed) {
        if (gameMode == Constant.CLASSIC) {
            if (hasWon(theSeed)) {//, currentRow, currentCol)) {  // check if winning move
                this.currentState = (theSeed == Constant.CROSS) ? Constant.CROSS_WON : Constant.NOUGHT_WON;
            } else if (isDraw()) {  // check for draw
                this.currentState = Constant.DRAW;
            }
        } else {
            if (isDraw()){
                if (calculateScore(Constant.CROSS) > calculateScore(Constant.NOUGHT)){
                    this.currentState = Constant.CROSS_WON;
                } else if (calculateScore(Constant.CROSS) < calculateScore(Constant.NOUGHT)){
                    this.currentState = Constant.NOUGHT_WON;
                } else {
                    this.currentState = Constant.DRAW;
                }

            }
        }
        // Otherwise, no change to currentState (still PLAYING).
    }

    /** Return true if it is a draw (no more empty cell) */
    // TODO: Shall declare draw if no player can "possibly" win
    public boolean isDraw() {
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (boardGame.isEmptyCell(row,col)) {
                    return false;  // an empty cell found, not draw, exit
                }
            }
        }
        return true;  // no empty cell, it's a draw
    }

    /** Return true if the player with "theSeed" has won after placing at*/
    public boolean hasWon(int theSeed){
        return (VerticalCheck(theSeed) || HorizontalCheck(theSeed) || DiagonalCheck(theSeed) || OpDiagonalCheck(theSeed));
    }

    public boolean HorizontalCheck(int theSeed) {
        int point = 0;
        //horizontal checking
        for (int i = 0; i<rows ; i++){
            for (int j = 0; j<(cols-point_to_win)+1; j++) {
                for (int k = j; k < j + point_to_win; k++) {
                    if(boardGame.getCellContent(i,k)==theSeed)point++;
                    else if (boardGame.getCellContent(i,k)==Constant.EMPTY)break;
                }
                if (point==point_to_win)return true;
                point = 0;
            }
        }
        return false;
    }

    public boolean VerticalCheck(int theSeed) {
        int point = 0;
        //vertical checking
        for (int i = 0; i<cols ; i++){
            for (int j = 0; j<(rows-point_to_win)+1; j++) {
                for (int k = j; k < j + point_to_win; k++) {
                    if(boardGame.getCellContent(k,i)==theSeed)point++;
                    else if (boardGame.getCellContent(k,i)==Constant.EMPTY)break;
                }
                if (point==point_to_win)return true;
                point = 0;
            }
        }
        return false;
    }

    public boolean DiagonalCheck(int theSeed) {
        int point = 0;
        //diagonal checking
        for (int i = 0; i<(rows-point_to_win)+1 ; i++){
            for (int j = 0; j<(cols-point_to_win)+1 ; j++) {
                for (int k = 0; k < point_to_win ; k++) {
                    if(boardGame.getCellContent(i+k,j+k)==theSeed)point++;
                    else if (boardGame.getCellContent(i+k,j+k)==Constant.EMPTY)break;
                }
                if (point==point_to_win)return true;
                point = 0;
            }
        }
        return false;
    }

    public boolean OpDiagonalCheck(int theSeed) {
        int point = 0;
        //Opdiagonal checking
        // there still miss calculate for this method in 3x3, in order to keep game moving in 3x3 frame, in need to do this
//        if (rows == 3 && cols == 3){
//            return (boardGame.getCellContent(2,0) == theSeed) && (boardGame.getCellContent(1,1) == theSeed) &&
//                    (boardGame.getCellContent(0,2) == theSeed);
//        }
        for (int i = rows-1; i>=rows-(rows-point_to_win)-1 ; i--){
            for (int j = 0; j<(cols-point_to_win)+1 ; j++) {
                for (int k = 0; k < point_to_win ; k++) {
                    if(boardGame.getCellContent(i-k,j+k)==theSeed)point++;
                    else if (boardGame.getCellContent(i-k,j+k)==Constant.EMPTY) break;
                }
                if (point==point_to_win)return true;
                point = 0;
            }
        }
        return false;
    }

    public int calculateScore(int theSeed){
        return HorizontalCount(theSeed)+VerticalCount(theSeed)+DiagonalCount(theSeed)+OpDiagonalCount(theSeed);
    }

    public int HorizontalCount(int theSeed) {
        int point = 0;
        int score = 0;

        //horizontal checking
        for (int i = 0; i<rows ; i++){
            for (int j = 0; j<(cols-point_to_win)+1; j++) {
                for (int k = j; k < j + point_to_win; k++) {
                    if(boardGame.getCellContent(i,k)==theSeed)point++;
                    else if (boardGame.getCellContent(i,k)==Constant.EMPTY)break;
                }
                if (point==point_to_win)score++;
                point = 0;
            }
        }
        return score;
    }

    public int VerticalCount(int theSeed) {
        int point = 0;
        int score = 0;
        //vertical checking
        for (int i = 0; i<cols ; i++){
            for (int j = 0; j<(rows-point_to_win)+1; j++) {
                for (int k = j; k < j + point_to_win; k++) {
                    if(boardGame.getCellContent(k,i)==theSeed)point++;
                    else if (boardGame.getCellContent(k,i)==Constant.EMPTY)break;
                }
                if (point==point_to_win)score++;
                point = 0;
            }
        }
        return score;
    }

    public int DiagonalCount(int theSeed) {
        int point = 0;
        int score = 0;
        //diagonal checking
        for (int i = 0; i<(rows-point_to_win)+1 ; i++){
            for (int j = 0; j<(cols-point_to_win)+1 ; j++) {
                for (int k = 0; k < point_to_win ; k++) {
                    if(boardGame.getCellContent(i+k,j+k)==theSeed)point++;
                    else if (boardGame.getCellContent(i+k,j+k)==Constant.EMPTY)break;
                }
                if (point==point_to_win)score++;
                point = 0;
            }
        }
        return score;
    }

    public int OpDiagonalCount(int theSeed) {
        int point = 0;
        int score = 0;
        //Opdiagonal checking
        for (int i = rows-1; i>=rows-(rows-point_to_win)-1 ; i--){
            for (int j = 0; j<(cols-point_to_win)+1 ; j++) {
                for (int k = 0; k < point_to_win ; k++) {
                    if(boardGame.getCellContent(i-k,j+k)==theSeed)point++;
                    else if (boardGame.getCellContent(i-k,j+k)==Constant.EMPTY) break;
                }
                if (point==point_to_win)score++;
                point = 0;
            }
        }
        return score;
    }


    /** Print the game board */
    public void printBoard() {
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                printCell(boardGame.getCellContent(row,col)); // print each of the cells
                if (col != cols - 1) {
                    System.out.print("|");   // print vertical partition
                }
            }
            System.out.println();
            if (row != rows - 1) {
                for (int i = 0 ; i<cols ;i++) {
                    System.out.print("===="); // print horizontal partition
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    public void clearObstacle(){
        for (int row = 0; row < rows; ++row) {
            for (int col = 0; col < cols; ++col) {
                if (boardGame.getCellContent(row,col) == Constant.OBSTACLE)boardGame.FillCellContent(row,col,Constant.EMPTY);
            }
        }
    }

    /** Print a cell with the specified "content" */
    public void printCell(int content) {
        switch (content) {
            case Constant.EMPTY:        System.out.print("   "); break;
            case Constant.NOUGHT:       System.out.print(" O "); break;
            case Constant.CROSS:        System.out.print(" X "); break;
            case Constant.OBSTACLE:     System.out.print(" # "); break;
        }
    }
}
