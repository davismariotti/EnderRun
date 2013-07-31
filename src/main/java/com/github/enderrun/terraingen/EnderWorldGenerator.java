package com.github.enderrun.terraingen;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class EnderWorldGenerator {
	/*
	 * We create an async thread to generate the terrain. 
	 * Then, once it's been generated, we're notified by
	 * the worker thread, then we copy over the generated
	 * portion of the world on the main thread, slowly enough
	 * that we don't over-load the server.
	 * 
	 */
    private final AsyncTerrainGenerator asyncTerrainGenerator;
    private final Thread asyncGeneratorThread;
    public final World world;

    /**
     * 
     * @param IslandSizeX
     * The x size (on average) of each island
     * @param IslandSizeZ
     * The z size (on average) of each island
     * @param IslandDistance
     * The average distance between islands
     * @param chunkXRadiusGenerated
     * The number of chunks outward (along x) the generator will generate from (0,0)
     */
    public EnderWorldGenerator(int islandSizeX, int islandSizeZ,
            int islandDistance, int generatedXSize, int generatedYSize, World world) {
    	
    	this.world = world;
    	
        this.asyncTerrainGenerator = new AsyncTerrainGenerator(islandSizeX, islandSizeZ,
            islandDistance, generatedXSize, generatedYSize, this, this.world.getSeed());
        this.asyncGeneratorThread = new Thread(this.asyncTerrainGenerator);
    }

    public EnderWorldGenerator(World world) {
        this(5, 5, 32, 40, 20, world);
    }
    
    public void generateWorld() {
    	asyncGeneratorThread.start();
    }
    
    public synchronized void notifyAsyncThreadComplete(int[][][] generatedWorld, int xSize, int zSize) {
    	
    	//TODO Set this up as a recuring task, so that it doesn't slow down the server
    	//Also, we may want to use nms code here
    	Bukkit.broadcastMessage("Done generating world. Copying it into the end");
    	
    	for (int x = 0; x < xSize;  x++) {
    		for (int z = 0; z < zSize; z++) {
    			for (int y = 0; y < 256; y++) {
    				try {
    					this.world.getBlockAt(x, y, z).getChunk().load(true);
    					this.world.getBlockAt(x, y, z).setTypeId(generatedWorld[x][y][z]);
    				}
    				catch (Exception e) {
    					e.printStackTrace();
    					continue;
    				}
    			}
    		}
    	}
    	
    	Bukkit.broadcastMessage("Enderrun world is ready");
    }
    
}