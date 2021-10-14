package battlegame;

import java.util.Random;

public class RandomGenerator {
  //https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values
  public static int getRandom(int lo, int hi) {
    return new Random().nextInt(hi - lo) + lo;
  }
}
