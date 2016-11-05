import java.util.List;

;

/**
 * Created by aaron on 10/3/16.
 */

public class PersonChromosome extends Chromosome {
    public PersonChromosome(List<Person> people){
        super(people);
    }
    public int getFitness(){
        return 0;
    }
}
