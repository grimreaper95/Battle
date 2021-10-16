import battlegame.random.IRandom;

import java.util.Random;
/**
 * Test class to generate random number with seed.
 */

public class RandomGeneratorTest implements IRandom {
  private final Random random;

  public RandomGeneratorTest(int seed) {
    this.random = new Random(seed);
  }

  @Override
  public int getRandom(int lo, int hi) {
    return random.nextInt(hi - lo) + lo;
  }

  @Override
  public Random getRandomGenerator() {
    return random;
  }
}
