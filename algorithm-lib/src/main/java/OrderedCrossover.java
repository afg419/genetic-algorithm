
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by aaron on 11/5/16.
 */
public class OrderedCrossover implements Crossover {
    private final Random rand = new Random();

    public <T> Chromosome<T>[] apply(Chromosome<T> parentA, Chromosome<T> parentB){
        confirmSameElements(parentA, parentB);

        List childAEntries = Lists.newArrayList();
        List childBEntries = Lists.newArrayList();

        int totalEntries = parentA.getEntries().size();

        int bookEnd1 = rand.nextInt(totalEntries - 1); //returns a valid index of entry list
        int bookEnd2 = rand.nextInt(totalEntries - 1); //returns a length s.t start + length <= totalEntries
        int start = Math.min(bookEnd1, bookEnd2);
        int end = Math.max(bookEnd1, bookEnd2);

        List<T> parentAContribution = parentA.getEntries().subList(start, end + 1); //sublist excludes target, add one to get it
        List<T> remainderB = removeContribution(parentAContribution, parentB.getEntries());

        Stream.concat(Stream.concat(
                remainderB.subList(0, start).stream(),
                parentAContribution.stream()),
                remainderB.subList(start, remainderB.size()).stream())
                .collect(Collectors.toList());

        return new Chromosome[]{parentA, parentB};
    }

    private <T> void confirmSameElements(Chromosome<T> a, Chromosome<T> b){
        if(!Sets.immutableEnumSet((List) a.getEntries()).equals(Sets.immutableEnumSet((List) b.getEntries()))){
            throw new RuntimeException("Chromosomes must have same entry set for ordered crossover function." + a.getEntries() + b.getEntries());
        }
    }

    private <T> List<T> removeContribution(List<T> contribution, List<T> fromSource){
        List<T> result = Lists.newArrayList();
        for(T elt : fromSource){
            if(contribution.contains(elt)){
                continue;
            }
            result.add(elt);
        }
        return result;
    }

}
