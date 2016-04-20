package com.gmail.tth4ik;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.MouseEvent;

import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.ConsoleProgram;
import acm.program.GraphicsProgram;

public class BreakOut extends GraphicsProgram {

	// ������� ����� �������� (������ ����) - RED(2 ����� �����) ORANGE,
	// YELLOW, GREEN, CYAN (2 ���� �����.

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

	private static int bricksQuantity = NBRICK_ROWS*NBRICKS_PER_ROW;

	private static int lifes = 3;

	private static int vx, vy;

	private GRect brick;

	public void run() {
		setup();
		// while (!gameOver()) {
		if (ball == null) {
			createBall();
			throwBall();
			// }
			// checkForCollisions();
			// moveBall();
		}

	}

	/**
	 * ����� ��������� ��� - ������� ���� ���, ��������, �� ��� ���������,
	 * ������� � ���� �������� �� �����
	 */
	private void setup() {
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		createField();
		createRaquet();
		addMouseListeners();

	}

	/** ����� ������� �'���� �� �������� ����������� */
	private void createBall() {
		ball = new GOval(2 * BALL_RADIUS, 2 * BALL_RADIUS);
		ball.setFilled(true);
		ball.setFillColor(Color.RED);
		ball.setColor(Color.RED);
		add(ball, APPLICATION_WIDTH / 2 - BALL_RADIUS, APPLICATION_HEIGHT / 2 - BALL_RADIUS);

	}

	/** ����� ������� ������� �� �������� ����������� */
	private void createRaquet() {
		paddle = new GRect(PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.BLACK);
		add(paddle, APPLICATION_WIDTH / 2 - PADDLE_WIDTH / 2, APPLICATION_HEIGHT - PADDLE_HEIGHT - PADDLE_Y_OFFSET);
	}

	/**
	 * ����� ����� ��'��� �� ���� ������ ���������� (��� ����� ������� ���
	 * ���� �������). ���� �� �� ������ ��'��� - ����� ����� ����� �� ��� ��
	 * ����������� � ����� �� �� ��������� ����������
	 */
	public void mousePressed(MouseEvent e) {
		obj = getElementAt(e.getX(), e.getY());
		if (obj != null) {
			last = new GPoint(e.getPoint());
		}
	}

	/**
	 * ����� ���� ������� �� ��� "������������� �����". ��� ����� �������
	 * ������ ������ ������ ����, ������ �� �� �������. ������ �'����� -
	 * ������� ���������� �� ���. ��� �������� ����� � ��� ����� ���� -
	 * �� ������ ������
	 */
	public void mouseDragged(MouseEvent e) {
		if ((obj == null) || (obj != paddle)) {
			return;
		} else {
			if (obj.getX() < 0) {
				obj.move(5, 0);
				return;
			}
			if (obj.getX() >= APPLICATION_WIDTH - PADDLE_WIDTH) {
				obj.move(-5, 0);
				return;
			}
			obj.move(e.getX() - last.getX(), 0);
			last = new GPoint(e.getPoint());
		}
	}

	private void createField() {
		
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
		if (lifes == 0)
			return true;
		return false;
	}

	private boolean ballOutOfBottomLine() {
		// ����� ��� �������� ��������, ������ �'����� �� ���� ���� ������
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

	private GOval ball;
	private GRect paddle;
	private GObject obj;
	private GPoint last;

}
