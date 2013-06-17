package com.github.enderrun.sound;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import com.github.enderrun.EnderRun;

public class CellularAutomataGenerator {
	
	private static final int NUMBER_OF_SEMITONES = 6;
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private int[][] cellPositions; //for detecting inter-cell-collisions
	private int maxX, maxY;

	public CellularAutomataGenerator(long seed, int maxX, int maxY, int initialCells) {
		Random rand = new Random(seed);
		this.maxX = maxX;
		this.maxY = maxY;
		cellPositions = new int[maxX][maxY];
		for (int i = 0; i < initialCells; i++) {
			try {
				cells.add(new Cell(rand.nextInt(maxX), rand.nextInt(maxY), maxX, maxY, Cell.Direction.getRandom(rand), cellPositions));
			} catch (InvalidPositionException e) {
				EnderRun.getInstance().getLogger().log(Level.INFO, "Skipped a cell for music, " + i + " left to go");
				continue;
			}
		}
	}

	public ArrayList<Integer> get() {
		return checkForWallColisons();
	}

	private ArrayList<Integer> checkForWallColisons() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (Cell c : cells) {
			if (c.update(cellPositions)) {
				//if there is a colission on the wall
				int semitone = (c.x + c.y)%NUMBER_OF_SEMITONES;
				/*if (c.x == 0 || c.x == maxX) semitone = c.x%NUMBER_OF_SEMITONES;
				else if (c.y == 0 || c.y == maxY) semitone = c.y%NUMBER_OF_SEMITONES;
				else semitone = (c.y*c.x)%NUMBER_OF_SEMITONES;*/
				list.add(semitone);
			}
		}
		return list;
	}
}
