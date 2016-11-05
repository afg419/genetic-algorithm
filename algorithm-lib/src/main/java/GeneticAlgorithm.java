
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class GeneticAlgorithm {
	private final float sufficientFitness;
	private final int totalBreedings;
	private final Mutation mutation;
	private final Selection selection;
	private final Crossover crossover;

	//breed generations until we find a chromosome of sufficient fitness or we exceed the maximum number of iterations
	<T> GeneticResult<T> train(List<Chromosome<T>> chromosomes){
		GeneticResult<T> bestFit = maxFitness(chromosomes, 0);
		for(int i = 0; i < totalBreedings; i++){
			if(bestFit.getFitness() > sufficientFitness){ break ;}
			chromosomes = breedNewGeneration(chromosomes);
			bestFit = maxFitness(chromosomes, i);
		}
		return bestFit;
	}

	//Select higher fitness chromosomes, cross them over pairwise, mutate them at random
	private <T> List<Chromosome<T>> breedNewGeneration(List<Chromosome<T>> chromosomes){
		List<Chromosome<T>> selected = selection.apply(chromosomes);
		Collections.shuffle(selected);

		List<Chromosome<T>> crossedOver = Lists.newArrayList();
		for(int i = 0; i < selected.size(); i+=2){
			Chromosome<T>[] bred = crossover.apply(selected.get(i), selected.get(i+1));
			crossedOver.add(bred[0]);
			crossedOver.add(bred[1]);
		}

		return crossedOver.stream().map(mutation::apply).collect(Collectors.toList());
	}

	//returns the chromosome with the highest fitness from a list, along with said fitness
	private <T> GeneticResult<T> maxFitness(List<Chromosome<T>> chromosomes, int totalBreedings){
		Chromosome<T> bestFit = fittestChromosome(chromosomes);
		return new GeneticResult<T>(bestFit, bestFit.getFitness(), totalBreedings);
	}

	private <T> Chromosome<T> fittestChromosome(List<Chromosome<T>> chromosomes){
		return chromosomes.stream().max( (c1, c2) -> Integer.compare(c1.getFitness(), c2.getFitness()) ).get();
	}

	//Output data from a genetic algorithm
	@Data
	public class GeneticResult<T> {
		private final Chromosome<T> result;
		private final int fitness;
		private final int totalBreedings;
	}
}
