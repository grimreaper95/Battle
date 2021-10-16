package battlegame.random;

import java.util.Random;

/**
 * A class used to generate random number.
 */
public class RandomGenerator implements IRandom {
  private final Random random;

  /**
   * Constructs a RandomGenerator object. It is used to generate randomness.
   */
  public RandomGenerator() {
    this.random = new Random();
  }

  @Override
  public int getRandom(int lo, int hi) {
    if (hi <= lo) {
      throw new IllegalArgumentException("Higher bound cannot be <= lower bound");
    }
    return random.nextInt(hi - lo) + lo;
  }

  @Override
  public Random getRandomGenerator() {
    return random;
  }
}
