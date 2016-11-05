
/**
 * Created by aaron on 11/5/16.
 */
public interface Crossover {
    <T> Chromosome<T>[] apply(Chromosome<T> parentA, Chromosome<T> parentB);
}
