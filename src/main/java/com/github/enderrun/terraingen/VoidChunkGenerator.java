package com.github.enderrun.terraingen;

import java.util.Random;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

/**
 * A bukkit-style void chunk generator. We use this
 * to stop regular terrain from generating around 
 * the islands in the end *
 */
public class VoidChunkGenerator extends ChunkGenerator {

	public byte[][] generateBlockSections(World world, Random rand, int ChunkX,
			int ChunkZ, BiomeGrid biome) {

		return null;
	}
}
