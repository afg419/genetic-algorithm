
import java.util.List;

/**
 * Created by aaron on 11/5/16.
 */
public interface Selection {
    <T> List<Chromosome<T>> apply(List<Chromosome<T>> chromosomes);
}
