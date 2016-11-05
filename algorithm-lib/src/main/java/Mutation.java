
/**
 * Created by aaron on 11/5/16.
 */
public interface Mutation {
    <T> Chromosome<T> apply(Chromosome<T> chromosome);
}
