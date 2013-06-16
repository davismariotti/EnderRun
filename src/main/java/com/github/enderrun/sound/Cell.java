package com.github.enderrun.sound;

import java.util.Random;

/**
 * A cell used in the cellular automata generator
 *
 */
public class Cell {
	
	public Cell(int x, int y, int maxX, int maxY, Direction direction, int[][] cellPositions) throws InvalidPositionException {
		this.x = x;
		this.y = y;
		this.maxX = maxX;
		this.maxY = maxY;
		this.direction = direction;
		if (cellPositions[x][y] != 1) cellPositions[x][y] = 1;
		else {
			throw new InvalidPositionException();	
		}
	}

	public enum Direction {
		NORTH,
		EAST,
		SOUTH,
		WEST;

		public static Direction getRandom(Random rand) {
			int n = rand.nextInt(4);
			if (n == 0) return Direction.NORTH;
			else if (n==1) return Direction.SOUTH;
			else if (n==2) return Direction.EAST;
			else return Direction.WEST;
		}
	}
	
	public int x, y, maxX, maxY;
	public Direction direction;
	
	/**
	 * @param cellPositions 
	 * @return weather or not it hit an edge
	 */
	public boolean update(int[][] cellPositions) {
		int newX = getNewX();
		int newY = getNewY();
		
		if (newX < 0 || newX >= maxX || newY < 0 || newY >= maxY) {
			//we hit a wall;
			reverseDirection();
			return true;
		}
		
		if (cellPositions[newX][newY] == 1) {
			//we hit another cell
			rotateDirection();
			return true;
		}
		
		cellPositions[x][y] = 0;
		x = newX;
		y = newY;
		cellPositions[x][y] = 1;
		return false;
	}
	
	private int getNewY() {
		if (direction == Direction.NORTH) return y+1;
		else if (direction == Direction.SOUTH) return y-1;
		else return y;
	}	
	
	private int getNewX() {
		if (direction == Direction.EAST) return x+1;
		else if (direction == Direction.WEST) return x-1;
		else return x;
	}

	private void reverseDirection() {
		if (direction == Direction.NORTH) direction = Direction.SOUTH;
		else if (direction == Direction.EAST) direction = Direction.WEST;
		else if (direction == Direction.SOUTH) direction = Direction.NORTH;
		else direction = Direction.EAST;		
	}
	
	private void rotateDirection() {
		if (direction == Direction.NORTH) direction = Direction.EAST;
		else if (direction == Direction.EAST) direction = Direction.SOUTH;
		else if (direction == Direction.SOUTH) direction = Direction.WEST;
		else direction = Direction.NORTH;		
	}
	
}
