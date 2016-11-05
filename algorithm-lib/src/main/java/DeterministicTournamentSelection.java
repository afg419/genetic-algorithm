
import lombok.Data;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by aaron on 11/5/16.
 */
@Data
public class DeterministicTournamentSelection implements Selection {
    private final int TOURNAMENT_RATIO;

    public <T> List<Chromosome<T>> apply(List<Chromosome<T>> chromosomes){
        List<Chromosome<T>> selected = newArrayList();
        int tournament_size = chromosomes.size()/TOURNAMENT_RATIO + 1;
        for(int i =0 ; i < chromosomes.size(); i++){
            Collections.shuffle(chromosomes);
            selected.add(fittestChromosome(chromosomes.subList(0, tournament_size)));
        }
        return selected;
    }

    private <T> Chromosome<T> fittestChromosome(List<Chromosome<T>> chromosomes){
        return chromosomes.stream().max( (c1, c2) -> Integer.compare(c1.getFitness(), c2.getFitness()) ).get();
    }
}
