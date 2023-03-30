//package com.appliedcoding.video2;
//import com.appliedcoding.video1.Console;
import java.io.Console;
import java.io.IOException;

//import com.appliedcoding.video1.Console;

public class SnakeGame {
	private boolean isRunning;
	private String keyPressed;
	private Console console;
	private Snake snake;
	private Enviroment enviroment;
	
	
	
	public SnakeGame() throws IOException, InterruptedException  {
		try {
			
			console = new Console();
			console.enterCharacterMode();
			console.hiderCursor();
			console.clear();
			
			isRunning = true;
			console = new Console();	
			snake = new Snake(new Position (40,12));
			snake.grow(20);
			enviroment = new Enviroment(new Position (0,0), new Position (79, 23));
			
			while (isRunning) {
				doLoop();
				pause(100);
			}
			
			Position maxScreen = console.detectScreenSize();
			console.gotoXY(1, maxScreen.getY());
			console.showCursor();
			
		} finally {
			console.enterLineMode();
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new SnakeGame();
	}
	
	private void doLoop() throws IOException{
		readKeyPress();
		handleKeyPress();
		
		console.putStringAt(keyPressed + "                   ", 1, -1);
		
		paintRemove();
		
		
		calculateNextState();
		checkCollision();
		paint();
		
	}
	
	private void checkCollision() {
		if (enviroment.isOutOfBounds(snake.getHead()) || snake.isEatingItself()) {
			console.printAt("GAME OVER",2,2);
			isRunning = false;
		}
	}
	
	private void paintRemove() {
		Position tail = snake.getTail();
		console.putCharAt(' ', tail.getY(), tail.getX());
		
	}
	private void paint() {
		Position head = snake.getHead();
		console.putCharAt('*', head.getY(), head.getX());
		console.printScreen();
		
	}

	private void calculateNextState() {
		snake.move();
	}
	
	
	private void handleKeyPress() {
		switch (keyPressed) {
		case "27":
			isRunning = false;
			break;
			
		case "279165": //up
			snake.setDirection(Direction.Up);
			break;
		
		case "279166": //down
			snake.setDirection(Direction.Down);
			break;
			
		case "279168": //left
			snake.setDirection(Direction.Left);
			break;
			
		case "27916": //right
			snake.setDirection(Direction.Right);
			break;
		
		}
	}	
	
	private void pause(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void readKeyPress() throws IOException {
		keyPressed = "";
		while (System.in.available() > 0) {
			keyPressed += System.in.read();
		}
	}
		public static void enterCharacterMode() throws IOException, InterruptedException {
				String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
				Runtime.getRuntime().exec(cmd).waitFor();
			}
			public static void enterLineMode() throws IOException, InterruptedException {
				String[] cmd = {"/bin/sh", "-c", "stty cooked</dev/tty"};
				Runtime.getRuntime().exec(cmd).waitFor();
			}

	}