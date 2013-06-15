package com.github.enderrun.terraingen;

import org.bukkit.Material;
import org.bukkit.util.noise.SimplexOctaveGenerator;

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
		this.zSizeInBlocks = 50;
		
		this.generatedWorld = new byte[xSizeInBlocks][256][zSizeInBlocks];
	}

	@Override
	public void run() {
		generateTerrain();
		mainThreadGenerator.notifyAsyncThreadComplete(generatedWorld,
				xSizeInBlocks, zSizeInBlocks);
	}

	private void generateTerrain() {

		SimplexOctaveGenerator gen1 = new SimplexOctaveGenerator(this.seed, 8);
		gen1.setScale(1 / 32.0); // how "scaled" the noise generator should be.

		for (int x = 0; x < xSizeInBlocks; x++) {
			for (int z = 0; z < zSizeInBlocks; z++) {

				for (int y = 64; y < 128; y++) {
					double density = gen1.noise(x, y, z, 0.5, 0.5);
					if (density > 0.0) {
						this.generatedWorld[x][y][z] = (byte) Material.ENDER_STONE.getId();
					}
				}
			}
		}
	}

}