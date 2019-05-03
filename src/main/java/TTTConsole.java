package main.java;

import java.util.Random;
import java.util.Scanner;
/**
 * Tic-Tac-Toe: Two-player console, non-graphics, non-OO version.
 * All variables/methods are declared as static (belong to the class)
 *  in the non-OO version.
 */
public class TTTConsole {
 
   // The game board and the player
   public static Board board ; // game board in 2D array
   public static int currentPlayer; // the current player (CROSS or NOUGHT)
   public static int gameMode; // current game mode
   public static int point; // point to win variable
   public static boolean isobstacle; // obstacle flag variable
   public static boolean fix_obstacle; // fix obstacle flag variable
   public static int obstacle_count; // number of obstacle variable
   public static boolean vs_com; // fix obstacle flag variable

   public static Scanner in = new Scanner(System.in); // the input Scanner

 
   /** The entry main method (the program starts here) */
   public static void main(String[] args) {
      //showing title and some interactive features
      Text.welcomeText();
      Text.showTitle();
      Text.border();

      Text.GameModeSelectionText();
      System.out.println("Choose your Game Mode (1 / 2)");
      gameMode = in.nextInt();
      while (gameMode < Constant.CLASSIC || gameMode > Constant.MODERN){
         System.out.println("Please, choose between 1 (Classic) / 2 (Modern) only ");
         gameMode = in.nextInt();
      }
      Text.border();

      Text.DifficultiesSelectionText();
      System.out.println("Choose your difficulty level (1 / 2 / 3 / 4)");
      int difficulty = in.nextInt();
      while (difficulty < Constant.EASY || difficulty > Constant.CUSTOM){
         System.out.println("Please, choose between 1 / 2 / 3 / 4 only ");
         difficulty = in.nextInt();
      }
      Text.border();
      switch (difficulty){
         case 1 :
            System.out.println(Constant.DifficultyMessage.EASY.pesan());
            //initialize Easy mode game
            board = new Board(3,3);
            point = 3;
            isobstacle = false;
            fix_obstacle = false;
            obstacle_count = 0;
            break;
         case 2 :
            System.out.println(Constant.DifficultyMessage.MEDIUM.pesan());
            //initialize Medium mode game
            board = new Board(5,5);
            point = 3;
            isobstacle = true;
            fix_obstacle = true;
            obstacle_count = 1;
            break;
         case 3 :
            System.out.println(Constant.DifficultyMessage.HARD.pesan());
            //initialize Medium mode game
            board = new Board(7,7);
            point = 4;
            isobstacle = true;
            fix_obstacle = false;
            obstacle_count = 3;
            break;
         case 4 :
            System.out.println(Constant.DifficultyMessage.CUSTOM.pesan());
            System.out.println();
            //initialize Medium mode game
            // Initialize the game and it's features
            System.out.println("How many dimensions do you want to play (x y)? ");
            int dimensi_x = in.nextInt();
            int dimensi_y = in.nextInt();
            board = new Board(dimensi_x,dimensi_y);
            Text.border();

            System.out.println("Max point to win ? (the more point needed to win, the more exciting it will be) ");
            point = in.nextInt();
            while (point > Math.min(dimensi_x,dimensi_y) || point<=1){
               if (point>1) {
                  System.out.println("Invalid max point (the value must be less than or equal to dimensions size");
                  point = in.nextInt();
               }
               else {
                  System.out.println("Are you sure? because this means that whoever play first is the winner -_-  .Please Try Again");
                  point = in.nextInt();
               }
            }
            Text.border();

            Text.ObstacleImage();
            System.out.println("Do you wanna add Obstacle in game ?(y/n) ");
            String obstacle = in.next().equalsIgnoreCase("y") ? "y" : "n";
            isobstacle = false;
            fix_obstacle = false;
            obstacle_count = 1;
            if (obstacle.equalsIgnoreCase("y")){
               isobstacle = true;
               System.out.println("\n" +
                       "Activate Fix Obstacle ?(y/n) ");
               if (in.next().equalsIgnoreCase("y")){
                  fix_obstacle = true;
               }
               if (!fix_obstacle){
                  System.out.println("\n" +
                          "Activate Multiple Obstacle ?(y/n)");
                  if (in.next().equalsIgnoreCase("y")) {
                     System.out.println("\n" +
                             "How many Obstacle do you wanna use at a time? (2-3 Obstacle)");
                     int num = in.nextInt();
                     while (num < 2 || num > 3) {
                        System.out.println("Please choose between 2-3 Obstacle");
                        num = in.nextInt();
                     }
                     obstacle_count = num;
                  }
               }
            }
            Text.border();
            break;
      }
      Text.border();

      Text.PlayerComSelectionText();
      System.out.println("Choose your opponent ?(1 / 2) ");
      int com = in.nextInt();
      while (com < Constant.PLAYER || com > Constant.COM){
         System.out.println("Please, choose between 1 / 2 only ");
         com = in.nextInt();
      }
      if (com == Constant.COM) {
         vs_com = true;
         Text.ComImage();
         System.out.println("Computer will play as O player ");
      }
      Text.border();

      System.out.println("Do you wanna shuffle who's gonna play first ?(y/n) ");
      String shuffle = in.next().equalsIgnoreCase("y") ? "y" : "n";
      currentPlayer = Constant.CROSS;
      if (shuffle.equalsIgnoreCase("y")){
         Random random = new Random();
         currentPlayer = random.nextInt(2)+1;
         if (currentPlayer == Constant.CROSS){
            System.out.println("X player gonna play first ");
         } else {
            System.out.println("O player gonna play first ");
         }
      }
      GameController gameCtrl = new GameController(board,Constant.PLAYING,point,isobstacle,fix_obstacle,obstacle_count,gameMode,vs_com);
      Text.border();

      // Play the game once
      do {
         gameCtrl.playerMove(currentPlayer); // update currentRow and currentCol
         gameCtrl.updateGame(currentPlayer); // update currentState
         System.out.println();
         gameCtrl.printBoard();
         // Print message if game-over
         if (gameCtrl.getCurrentState() == Constant.CROSS_WON) {
            System.out.println("'X' won! Bye!");
            Text.Themes();
            if (vs_com) System.out.println(Constant.DefeatResponse.getRandomResponse().pesan());
         } else if (gameCtrl.getCurrentState() == Constant.NOUGHT_WON) {
            System.out.println("'O' won! Bye!");
            Text.Themes();
            if (vs_com) System.out.println(Constant.VictoryResponse.getRandomResponse().pesan());
         } else if (gameCtrl.getCurrentState() == Constant.DRAW) {
            System.out.println("It's a Draw! Bye!");
         }
         // Switch player
         currentPlayer = (currentPlayer == Constant.CROSS) ? Constant.NOUGHT : Constant.CROSS;
      } while (gameCtrl.getCurrentState() == Constant.PLAYING); // repeat if not game-over
   }

}