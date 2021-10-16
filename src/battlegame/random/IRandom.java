package battlegame.random;

import java.util.Random;

/**
 * An interface used to generate a random number to generate randomness.
 */
public interface IRandom {
  /**
   * Get random number within a range.
   * @param low lower bound(inclusive) within which random number is generated.
   * @param high upper bound(exclusive) within which random number is generated.
   * @return an integral random number between the range [low, high)
   */
  //https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values
  int getRandom(int low, int high);

  /**
   * Get the random number generator.
   * @return the random number generator
   */
  Random getRandomGenerator();
}
