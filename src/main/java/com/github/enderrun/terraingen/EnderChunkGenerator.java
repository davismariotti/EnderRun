package com.github.enderrun.terraingen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.World.Environment;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.PerlinOctaveGenerator;

import com.github.enderrun.LocalizedStrings;

public class EnderChunkGenerator extends ChunkGenerator {

	public final int islandSizeX, islandSizeZ, IslandDistance,
			chunkRadiusGenerated;

	/**
	 * 
	 * @param IslandSize
	 *            The size (on average) of each island
	 * @param IslandDistance
	 *            The average distance between islands
	 * @param chunkRadiusGenerated
	 *            The number of chunks outward the generator will generate from
	 *            (0,0)
	 */
	public EnderChunkGenerator(int IslandSizeX, int islandSizeZ,
			int IslandDistance, int chunkRadiusGenerated) {
		this.islandSizeX = IslandSizeX;
		this.islandSizeZ = islandSizeZ;
		this.IslandDistance = IslandDistance;
		this.chunkRadiusGenerated = chunkRadiusGenerated;
	}

	public EnderChunkGenerator() {
		this(20, 20, 20, 4);
	}

	public static void createNewEnderRunWorld() {

		// TODO generate all of the chunks in the islands

		WorldCreator worldOptions = new WorldCreator(LocalizedStrings.GENERATED_WORLD_NAME);
		worldOptions.generator(new EnderChunkGenerator());
		worldOptions.environment(Environment.THE_END);

		Bukkit.createWorld(worldOptions);
	}

	@Override
	public byte[][] generateBlockSections(World world, Random rand, int chunkX,
			int chunkZ, BiomeGrid biome) {

		if (chunkX > islandSizeX || chunkX < -islandSizeX
				|| chunkZ > islandSizeZ || chunkZ > -islandSizeZ)
			return null;

		PerlinOctaveGenerator blockDensityGenerator = new PerlinOctaveGenerator(
				world, 8);
		blockDensityGenerator.setScale(1 / 32.0);

		byte[][] chunk = new byte[world.getMaxHeight() / 16][];

		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int worldX = x + chunkX * 16;
				int worldZ = z + chunkZ * 16;

				for (int y = 64; y < 128; y++) {
					double density = blockDensityGenerator.noise(worldX, y,
							worldZ, 1, 1);
					double threshold = 0.0;

					if (density > threshold) {
						setBlock(x, y, z, chunk, Material.ENDER_STONE);
					}
				}
			}
		}
		return chunk;
	}

	/**
	 * Returns a list of all of the block populators (they make "little"
	 * features) to be called after the chunk generator runs on this chunk
	 */
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world) {
		ArrayList<BlockPopulator> pops = new ArrayList<BlockPopulator>();
		pops.add(new BeaconPopulator());
		return pops;
	}

	/**
	 * Takes the in-chunk coordinates of a block, and the byte[][] representing
	 * each of the sections of the chunk, and sets it to the specified material
	 */
	private void setBlock(int x, int y, int z, byte[][] chunk, Material material) {

		// if the Block section the block is in hasn't been used yet, allocate
		// it
		if (chunk[y >> 4] == null)
			chunk[y >> 4] = new byte[16 * 16 * 16];

		if (y < 256 && y >= 0 && x < 16 && x >= 0 && z < 16 && z >= 0) {
			chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (byte) material.getId();
		}
	}
}