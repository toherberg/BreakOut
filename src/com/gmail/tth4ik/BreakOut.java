package com.gmail.tth4ik;

/**����'������ ��� - BreakOut. ��� ������� �'���� ������� ������� �� �����. 
 * ����������� �� �������, ������� ������ ���� �� �������, ��� ����� ��� ��� �������� ������ � ���� ������� �� ������� �'�����
 * ��������� - �������������� ���� - ������� ������ ������ ���� �� ������� � ����, ��������� ������ ����, ����� �� ������. 
 * �������� ����� ������, ��������� ����� DELAY (��������). ����� ����� �������� ������� �����, ������� �������� �������� ����� lifes
 * � ��� ������ ����, �� ���������� ���� ������� �������� � ��������/������/���������. 
 * � ������� ������ - 3 �����. ���� �� ����� �� ��� ������ �'�� �������� - ��� ���������� � ������ ��� �� ���������. 
 * ��� ������� - ����� ����� �� ��������. ��� ��� ����������� � ������� ���������� ���������
 * */

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Event;
import java.awt.event.MouseEvent;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GPoint;
import acm.graphics.GRect;
import acm.program.ConsoleProgram;
import acm.program.GraphicsProgram;
import acm.util.MediaTools;
import acm.util.RandomGenerator;
import acm.util.SoundClip;

/**
 * �� ������ ������ ��������, ��� ���� ���������� � �����������, �� �'����
 * ���������� �� ���� � ������� � ������ �������� ������. ��� ���� ���
 * ������� ��� �������������� �� ���� �������� � ������ ����, � ������ �������
 */
public class BreakOut extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 500;
	public static final int APPLICATION_HEIGHT = 800;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 75;
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
	private static final int BRICK_HEIGHT = 20;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	private int bricksQuantity = NBRICK_ROWS * NBRICKS_PER_ROW;

	private static int lifes = 3;

	/** ball speed */
	private double vx, vy;

	private RandomGenerator rgen = RandomGenerator.getInstance();

	/** ��������, �� ����� ������� ������, ��� ��� ���� ������� */
	private static final int DELAY = 10;

	public void run() {
		setup();
		while (!gameOver()) {
			if (ball == null) {
				waitForClick(); // ��� � ������ � ����� �������� ����� �����
								// �������� �'����� ������������� ���� ����
								// �����
				createBall();
				throwBall();
			}
		}
	}

	/**
	 * ����� ��������� ��� - ������� ���� ���, ��������, �� ��� ���������,
	 * ������� � ���� �������� �� �����
	 */
	private void setup() {
		bounceClip = new SoundClip("bounce.au");
		bounceClip.setVolume(0.5);
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
		ball.sendToBack();

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
	 * ������� ���������� �� ���. ��� ��������� ����� � ��� ����� ���� -
	 * �� ������ ������
	 */
	public void mouseDragged(MouseEvent e) {
		if ((e.getX() < 0) || (e.getX() > APPLICATION_WIDTH))
			return;
		if ((obj == null) || (obj != paddle)) {
			return;
		} else {
			if (paddle.getX() < 0) {
				paddle.move(5, 0);
				return;
			}
			if (paddle.getX() >= APPLICATION_WIDTH - PADDLE_WIDTH) {
				paddle.move(-5, 0);
				return;
			}
			paddle.move(e.getX() - last.getX(), 0);
			last = new GPoint(e.getPoint());
		}
	}

	/*** ������� ���� � �������� */
	private void createField() {
		int x = BRICK_SEP / 2;
		int y = BRICK_Y_OFFSET;
		for (int row = 0; row < NBRICK_ROWS; row++) {
			for (int column = 0; column < NBRICKS_PER_ROW; column++) {
				brick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
				add(brick);
				brick.setFilled(true);
				if (row < 2)
					brick.setColor(Color.RED);
				if (row == 2 || row == 3)
					brick.setColor(Color.ORANGE);
				if (row == 4 || row == 5)
					brick.setColor(Color.YELLOW);
				if (row == 6 || row == 7)
					brick.setColor(Color.GREEN);
				if (row == 8 || row == 9)
					brick.setColor(Color.CYAN);
				x += BRICK_WIDTH + BRICK_SEP;
			}
			y += (BRICK_HEIGHT + BRICK_SEP);
			x = BRICK_SEP / 2;
		}
	}

	/**
	 * �������� ����� ��� - ������� �'���� � ���� ���� �� �������� ����,
	 * ���������� ���糿
	 */
	private void throwBall() {
		vy = 3.0;
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5))
			vx = -vx;
		while (!gameOver()) {
			ball.move(vx, vy);
			pause(DELAY);
			checkForCollisions();
			if (ballOutOfBottomLine()) {
				return;
			}
		}

	}

	/**
	 * ����� �������, �� ��� �������� - ��� ���������� ������, ��� �������.
	 * �������� �������, ���� ���������� ���� �� ��������, ������� - ����
	 * �������� �� �����. � ������� �������, �� �������� - �������� ��������
	 * ������ �'���� � ������ � �������� � ����� �������� ���� ���������
	 * ����� - ��� ���������� ��� �������, ��� ��������� � ���������
	 */
	private boolean gameOver() {
		if (bricksQuantity == 0) {
			remove(ball);
			overLine = new GLabel("CONGRATULATIONS,YOU WIN!");
			overLine.setFont("Arial-Bold-18");
			add(overLine, APPLICATION_WIDTH / 2 - overLine.getWidth() / 2,
					APPLICATION_HEIGHT / 2 - overLine.getHeight() / 2);
			return true;
		}
		if (lifes == 0) {
			overLine = new GLabel("GAME OVER");
			overLine.setFont("Arial-Bold-18");
			add(overLine, APPLICATION_WIDTH / 2 - overLine.getWidth() / 2,
					APPLICATION_HEIGHT / 2 - overLine.getHeight() / 2);
			return true;
		}
		return false;
	}

	/**
	 * �����-�������� �������� �'����� � ������� ������. ������� �'��,
	 * ������ ������� �����, ����� ����� ����� ������� ��������, �� ������
	 * ���� �����, �� ���������� ��� ���������
	 */
	private boolean ballOutOfBottomLine() {
		if ((ball.getY() + 2 * BALL_RADIUS) > APPLICATION_HEIGHT) {
			remove(ball);
			ball = null;
			lifes--;
			return true;
		}
		return false;
	}

	/**
	 * ����� �������� ���糿 �'����� � ���, �� ���� �� ���� �������� -
	 * ����� � ������ �����, �������, ��������
	 */
	private void checkForCollisions() {
		if (gameOver())
			return;
		collideWithWalls();
		collideWithBricks();
		collideWithRaquet();

	}

	/**
	 * �����, ���� �������� �� �������� �'���� � ��������, ���� ��� - ������
	 * ����. ���������� 2 ������� ������� �'���.
	 * 
	 * ��в���, ���� �������������� ������� ���� if (vy > 0) ����������
	 * �������� � ����������� �'���, ��� �������� ����� ��, �� �'�� �����������
	 * �� ���� ������� (�������, �� ���������� 4 ����� �� ����� ��������, �
	 * ���� ������� ��� �'����) ��������� � ������� ������� � ����� ���������
	 * �� �� � ���������� �������, ����� ����� �������� �����, ���� �'��
	 * ���������� �����-���� ��������� �������. ����� ������ ����� ����. �
	 * ����� ������ � ���� ��������� ������� - ������ �'�� ���� ����
	 * "��������" � �������, ����� �� �� ������� ������ ������� � �����
	 * ��������. ���� �������� ������ ����� ��, �� �� ���������� � �����
	 * ���糿 �� ������ �'�����, � ����� �� �������� ��������, � ���� ��������
	 * ��� �'���� ( �� �� ���� ������� � �������������� ������� ��
	 * �����������)
	 * 
	 * ��в���, ���� �� ��������������: ���� �'���� ������� ����������� ������
	 * ��������� �� ������ ������� ������� - �� ����������, ������� ��
	 * ���������� �������� vy, ����� ����� �� ����������� ������ ���� �� ��
	 * �. ���� � �'�� ���������� �� ����� ����� �������� �������� �������, ��
	 * ������ ����, �� �� ����� ���������� � ������������ �������� �� �� �
	 * � ���� ����. ��� ������� �� ���� ������� ����������, ���� �'����
	 * ������ � �������� �������, �� ��� ���� ��������: ����� �� ���
	 * ������� ������� �� ���� ��������, ���� ������ � ����� ����, ��
	 * ����� �������, ���� ������ � ������� ����, ���� � �� ���� ��
	 * ������ � ������� ����, �� � ����� ���� �� ������ ��, �� �������,
	 * �������� �� ��������� � �'���� ���� ����. ����� ������ ������� ��������.
	 */
	private void collideWithRaquet() {
		/*
		if (getElementAt(ball.getX(), ball.getY() + 2 * BALL_RADIUS) == paddle) {
			if (vy > 0)
				vy = -vy;
			bounceClip.play();
			return;
		} else if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY()) == paddle) {
			if (vy > 0)
				vy = -vy;
			bounceClip.play();
			return;
		}
		*/
		if (getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) == paddle) {
			if (ball.getY() + 2 * BALL_RADIUS >= paddle.getY()) {
				bounceClip.play();
				if (vy > 0)
					vy = -vy;
				return;
			}
		}
		if (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + BALL_RADIUS) == paddle) {
			if ((ball.getX() + 2 * BALL_RADIUS >= paddle.getX())) {
				if (vx > 0){
					vx = -vx;
					bounceClip.play();
				}
				return;
			}
		}
		if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS) == paddle) {
			if (ball.getX() <= paddle.getX() + PADDLE_WIDTH) {
				if (vx < 0){
					vx = -vx;
					bounceClip.play();
				}
				return;
			}

		}

	}

	/**
	 * �������� �������� � ����������, � �������, ���� ���� �������� -
	 * ������� ��������, ������ �� ������� � ��������� ������. �����
	 * ���������, �� ����� �������� ���� ������� �������� - ���� ���� ������
	 * �� ����� ��� ������ �������� - ��������� �� ���������� �������� �� ��
	 * y. ���� �'���� �������� �� ���� � ���� �������� - ��������� ��
	 * ���������� �������� �� �� x. ��� ����, ��� �� ���j ����� �����
	 * (������� �� ����� ������ ��������, ����� ������� ������ ��������
	 * ������, ��� ���� ���� ��� � ���� "starter", ��� � �������� ��������
	 * �'��� (� ������� �� ������ ������ ��������). ���������, �� �����
	 * �������� �������� �� � �������, �� � �� ������ ���� (������� ��������
	 * � ���� �������� ��� �'����), �� �� ���� ���� � �������������� �������
	 * �� �����������, � �������� ������������� ������ ����� �� ���. ������
	 * �������� ����� � ������� �� ��� ������� ������� ������� ����, �� ���
	 * ��������� �'���� ����������� �� ������ ����. ����� �����, ���� ��
	 * ������ ������� � �����, �� �������� ������� ��� �� �'���� (�� ���� �,
	 * ���� �'�� ��� �� ���������� �����), � ��������, ���� �� �� ����
	 * ��������, �� ������ ���� �� ��� ��� ����� �������� ����� �
	 * ���������� ������
	 */
	private void collideWithBricks() {
		if ((getElementAt(ball.getX() + BALL_RADIUS, ball.getY()) != null)
				&& (getElementAt(ball.getX() + BALL_RADIUS, ball.getY()) != paddle)
				&& (getElementAt(ball.getX() + BALL_RADIUS, ball.getY()) != ball)) {
			collider = getElementAt(ball.getX() + BALL_RADIUS, ball.getY());
			remove(collider);
			collider = null;
			bricksQuantity--;
			bounceClip.play();
			vy = -vy;
			return;
		}
		if ((getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + BALL_RADIUS) != null)
				&& (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + BALL_RADIUS) != paddle)
				&& (getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + BALL_RADIUS) != ball)) {
			collider = getElementAt(ball.getX() + 2 * BALL_RADIUS, ball.getY() + BALL_RADIUS);
			remove(collider);
			collider = null;
			bricksQuantity--;
			bounceClip.play();
			vx = -vx;
			return;
		}
		if ((getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != null)
				&& (getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != paddle)
				&& (getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS) != ball)) {
			collider = getElementAt(ball.getX() + BALL_RADIUS, ball.getY() + 2 * BALL_RADIUS);
			remove(collider);
			collider = null;
			bricksQuantity--;
			bounceClip.play();
			vy = -vy;
			return;
		}
		if ((getElementAt(ball.getX(), ball.getY() + BALL_RADIUS) != null)
				&& (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS) != paddle)
				&& (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS) != ball)) {
			collider = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS);
			remove(collider);
			collider = null;
			bricksQuantity--;
			bounceClip.play();
			vx = -vx;
			return;
		}

	}

	/**
	 * ����� �������� �� � �������� �'����� � ������, ���� ��� - ������
	 * ����. � ����� ������ �������� �� �������� ���������� �'����� ��������
	 * �� ��� �� ������� ��������� ����������� ���� �������� ����.
	 */
	private void collideWithWalls() {
		if ((ball.getX() + 2 * BALL_RADIUS) >= APPLICATION_WIDTH) {
			bounceClip.play();
			vx = -vx;
		}
		if ((ball.getX()) <= 0) {
			bounceClip.play();
			vx = -vx;
		}
		if (ball.getY() <= 0) {
			bounceClip.play();
			vy = -vy;
		}
	}

	private SoundClip bounceClip;
	private GOval ball;
	private GRect brick;
	private GRect paddle;
	private GObject obj;
	private GPoint last;
	private GLabel overLine;
	private GObject collider;
}
