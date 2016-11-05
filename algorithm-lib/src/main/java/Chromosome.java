import lombok.Data;

import java.util.List;

@Data
abstract class Chromosome<ContentType> {
  private final List<ContentType> entries;

  public Chromosome(List<ContentType> entries){
    this.entries = entries;
  }

  abstract int getFitness();
}
