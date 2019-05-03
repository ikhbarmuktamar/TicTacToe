READ ME PLEASE

Hello, My name is Ikhbar
i am the creator / modifier of this game

I create this program to fulfill recruitment requirment process

in here, i build TIC TAC TOE game
I build and modify it into become OOP Structure java program.
instead focusing only in one java class, i break it down into several class which has it's own role.

in this program consist of :
- TTTConsole class
- GameController class
- Text class
- Board class
- Constants class

in the main (TTTConsole.java) class, i only fill it with flow of the game,
activate some features in game. here's what I add into game to make it more attractive :

- Game Mode
	this features allows us to choose game mode
	- Classic Mode
		in this mode, whoever make score first is the winner
	- Modern Mode
		in this mode, we can play the game until no space left in board, 
		and whoever makes more score in the end of the game is the winner.
		
- Difficulty Level
	this features allows us to choose the difficulties of the game
	- EASY Mode
		in this mode, we can only play in 3x3 board, with no Obstacle appear in the game, 
		and the number of point in order to win/add score is 3 (Only for Noob...hehehe)
	- MEDIUM Mode
		in this mode, we play in 5x5 board with fix Obstacle will appear in the game, 
		and the number of point in order to win/add score is 3 
	- HARD Mode
		in this mode, we play in 7x7 board with 3 Obstacles will appear at a time in board and will disappear in the next round
		and be replaced with another 3 new Obstacles. and in order to win the game or add score , player need to make 4 point.
	- CUSTOM Mode
		in this mode, you can set all features in the game to your interest.
		
- Obstacle in game 
	this Obstacle will appear randomly in board to interfere player movement.
	we configure how the obstacle will appear in board and it's behaviour.
	- We can set fix_Obstacle to make obstacle appear permanently in board or just appear in that round.  (In Custom Mode Only)
    - We can set how much Obstacles that will appear in board at a time (In Custom Mode Only)

- Dynamic dimensions of Board
	this features allows us to set up the size of Game Board, ( 3x3 , 6x4, 7x10, or whatever you want). (In Custom Mode Only)
	
- Max Point to win
	we can set up max point in order to win, (In Custom Mode Only)

- Player vs Player  ///  Player vs COM
	we can choose wheter we play against our friend or against COM 
	(with current condition COM movement will base on random number, I had a plan to implement unsupervised machine learning)
	
	we add some interactive dialog/ response COM to make the game more attractive.
	
	
*Some Bug
- in Modern Mode, the scoring still need to be fix,
in case of this situation 
--------------
X | X | X | X
--------------
O | X | O | 

in current program
score for X is 2, but it should score 1 



