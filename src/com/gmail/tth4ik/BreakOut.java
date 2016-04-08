package com.gmail.tth4ik;

import java.awt.Event;
import java.awt.event.MouseEvent;

import acm.program.ConsoleProgram;

public class BreakOut extends ConsoleProgram {
	
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	
/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;
	
	private static int bricksQuantity = 100;
	
	
	
	public void run(){
		setup();
		throwBall();
		while (!gameOver()){
			checkForCollisions();
			moveBall();
		}
	}

	private void moveBall() {
		// метод, €кий рухаЇ м'€чик у певному напр€м≥
		
	}

	private void throwBall() {
		// метод, жбурл€Ї м'€чик у певному напр€м≥ на початку гри
		
	}

	/**ћетод визначаЇ, чи гра зак≥нчена - або користувач виграв, або програв*/
	private boolean gameOver() {
		if (bricksQuantity==0)
			return true;
		if (ballOutOfBottomLine())
			return true;
		return false;
	}

	private boolean ballOutOfBottomLine() {
		// метод дл€ перев≥рки програшу, пад≥нн€ м'€чика за нижнЇ поле екрану
		return false;
	}

	private void checkForCollisions() {
		collideWithWalls();
		collideWithBricks();
		collideWithRaquet();
		
	}

	private void collideWithRaquet() {
		// перев≥р€Ї м'€ч на з≥ткненн€ з ракеткою, в≥дбиваЇ м'€чик
		
	}

	private void collideWithBricks() {
		// перев≥р€Ї на з≥ткненн€ з цеглинками, видал€Ї цеглинки, в≥дбиваЇ м'€чик
		
	}

	private void collideWithWalls() {
		// з≥ткненн€ з≥ ст≥нками, в≥дбитт€ в≥д ст≥нок
		
	}

	private void setup() {
		createField();
		createRaquet();
		createBall();
		//addMouseListener();
		
	}

	private void createBall() {
		// створюЇ м'€чик, ставить його у певну позиц≥ю
		
	}

	private void createRaquet() {
		// створюЇ ракетку, ставить њњ у певну позиц≥ю
		
	}

	private void createField() {
		// створюЇ поле - наб≥р цеглинок
		
	}
	
	
}
