package com.gmail.tth4ik;

import java.awt.Event;
import java.awt.event.MouseEvent;

import acm.graphics.GOval;
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
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	private static int bricksQuantity = 100;
	
	private static int lifes = 3;

	public void run() {
		setup();
		while (!gameOver()) {
			if (ball==null){
			createBall();
			throwBall();
			}
			checkForCollisions();
			moveBall();
		}
	}

	private void moveBall() {
		// �����, ���� ���� �'���� � ������� ������

	}

	private void throwBall() {
		// �����, ������� �'���� � ������� ������ �� ������� ���

	}

	/** ����� �������, �� ��� �������� - ��� ���������� ������, ��� ������� */
	private boolean gameOver() {
		if (bricksQuantity == 0)
			return true;
		if (lifes==0)
			return true;
		return false;
	}

	private boolean ballOutOfBottomLine() {
		// ����� ��� �������� ��������, ������ �'����� �� ����� ���� ������
		return false;
	}

	private void checkForCollisions() {
		collideWithWalls();
		collideWithBricks();
		collideWithRaquet();

	}

	private void collideWithRaquet() {
		// �������� �'�� �� �������� � ��������, ������ �'����

	}

	private void collideWithBricks() {
		// �������� �� �������� � ����������, ������� ��������, ������
		// �'����

	}

	private void collideWithWalls() {
		// �������� � �������, ������� �� �����

	}

	private void setup() {
		createField();
		createRaquet();
		// addMouseListener();

	}

	private void createBall() {
		// ������� �'����, ������� ���� � ����� �������

	}

	private void createRaquet() {
		// ������� �������, ������� �� � ����� �������

	}

	private void createField() {
		// ������� ���� - ���� ��������

	}
	
	GOval ball;

}
