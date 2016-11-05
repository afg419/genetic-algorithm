/**
 * Created by aaron on 11/5/16.
 */
public class SwapMutation implements Mutation {
    public <T> Chromosome<T> apply(Chromosome<T> chromosome){
        return chromosome;
    }
}
