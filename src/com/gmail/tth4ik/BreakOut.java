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
		// �����, ���� ���� �'���� � ������� ������
		
	}

	private void throwBall() {
		// �����, ������� �'���� � ������� ������ �� ������� ���
		
	}

	/**����� �������, �� ��� �������� - ��� ���������� ������, ��� �������*/
	private boolean gameOver() {
		if (bricksQuantity==0)
			return true;
		if (ballOutOfBottomLine())
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
		// �������� �� �������� � ����������, ������� ��������, ������ �'����
		
	}

	private void collideWithWalls() {
		// �������� � �������, ������� �� �����
		
	}

	private void setup() {
		createField();
		createRaquet();
		createBall();
		//addMouseListener();
		
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
	
	
}
