package com.github.enderrun.genetic;

import java.util.List;

public interface Genome {

	/**
	 * Make random changes (mutations), as part of 
	 * natural selection
	 */
	public void mutate();
	
	/**
	 * Combines randomly selected parts of this genome
	 * with that of the other. Both must be mutated
	 * first, by calling {@code blueprint}
	 */
	public void mate(Genome other);
	
	/**
	 * Gets some of this genome's genes
	 * @return A list of genes
	 */
	public List<Gene> getGenes();
	
}
