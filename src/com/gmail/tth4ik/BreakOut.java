package com.gmail.tth4ik;

import java.awt.Event;
import java.awt.event.MouseEvent;

import acm.program.ConsoleProgram;

public class BreakOut extends ConsoleProgram {
	
	int bricksQuantity;
	
	
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
