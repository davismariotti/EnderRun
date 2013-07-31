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

	private volatile byte[][][] generatedWorld;

	private final int islandSizeX, islandSizeZ, islandDistance, xSizeInBlocks, zSizeInBlocks;
	private final long seed;

	private EnderWorldGenerator mainThreadGenerator;

	public AsyncTerrainGenerator(int islandSizeX, int islandSizeZ,
			int islandDistance, int chunkXRadiusGenerated,
			EnderWorldGenerator generator, long seed) {
		this.islandSizeX = islandSizeX;
		this.islandSizeZ = islandSizeZ;
		this.islandDistance = islandDistance;
		this.mainThreadGenerator = generator;
		this.seed = seed;

		this.xSizeInBlocks = 16 * chunkXRadiusGenerated;
		this.zSizeInBlocks = 100;
		
		this.generatedWorld = new byte[xSizeInBlocks][256][zSizeInBlocks];
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
				
				if (density > 0.0) {
					this.generatedWorld[x][64][z] = (byte) Material.ENDER_STONE.getId();
				}
				
				else {
					this.generatedWorld[x][64][z] = (byte) Material.LAVA.getId();
				}
				
			}
		}
	}

}
