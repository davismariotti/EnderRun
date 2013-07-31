package com.github.enderrun.terraingen;

import java.util.concurrent.Callable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.github.enderrun.EnderRun;

/**
 * An async thread that generates the terrain, then notifies the main thread, in
 * order to reduce load on the server
 * 
 */
public class AsyncTerrainGenerator implements Runnable {

	private volatile int[][][] generatedWorld;

	private final int islandSizeX, islandSizeZ, islandDistance, xSizeInBlocks, zSizeInBlocks;
	private final long seed;

	private EnderWorldGenerator mainThreadGenerator;

	public AsyncTerrainGenerator(int islandSizeX, int islandSizeZ,
			int islandDistance, int generatedXSize, int generatedZSize,
			EnderWorldGenerator generator, long seed) {
		this.islandSizeX = islandSizeX;
		this.islandSizeZ = islandSizeZ;
		this.islandDistance = islandDistance;
		this.mainThreadGenerator = generator;
		this.seed = seed;

		this.xSizeInBlocks = generatedXSize;
		this.zSizeInBlocks = generatedXSize;
		
		this.generatedWorld = new int[xSizeInBlocks][256][zSizeInBlocks];
	}

	@Override
	public void run() {
		generateTerrain();
		Bukkit.getScheduler().callSyncMethod(EnderRun.getInstance(), new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				mainThreadGenerator.notifyAsyncThreadComplete(generatedWorld,
						xSizeInBlocks, zSizeInBlocks);
				return null;
			}
			
		});
	}

	private void generateTerrain() {

		SimplexOctaveGenerator gen1 = new SimplexOctaveGenerator(this.seed, 8);
		gen1.setScale(1 / (float)islandDistance);

		for (int x = 0; x < xSizeInBlocks; x++) {
			for (int z = 0; z < zSizeInBlocks; z++) {
				double density = gen1.noise(x, z, 0.5, 0.5);
				
				if (density > -0.2) {
					this.generatedWorld[x][64][z] = Material.ENDER_STONE.getId();
				}
				
				else {
					this.generatedWorld[x][64][z] = Material.AIR.getId();
				}
				
			}
		}
		
		this.generatedWorld[0][64][0] = Material.ENDER_STONE.getId();
		this.generatedWorld[1][64][0] = Material.ENDER_STONE.getId();
		this.generatedWorld[0][64][1] = Material.ENDER_STONE.getId();
		this.generatedWorld[1][64][1] = Material.ENDER_STONE.getId();
		
		this.generatedWorld[xSizeInBlocks-1][64][zSizeInBlocks-1] = Material.ENDER_STONE.getId();
		this.generatedWorld[xSizeInBlocks-1][65][zSizeInBlocks-1] = Material.BEACON.getId();
	}

}
