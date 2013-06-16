package com.github.enderrun.sound;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import com.github.enderrun.EnderRun;

public class CellularAutomataGenerator {
	
	private ArrayList<Cell> cells = new ArrayList<Cell>();
	private int[][] cellPositions; //for detecting inter-cell-collisions

	public CellularAutomataGenerator(long seed, int maxX, int maxY, int initialCells) {
		Random rand = new Random(seed);
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
				list.add((c.x + c.y)%8);
			}
		}
		return list;
	}
}
