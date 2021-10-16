import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import battlegame.random.IRandom;
import battlegame.random.RandomGenerator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class to test randomness of RandomGeneratorTest class
 */

public class RandomTest {
  IRandom randomGenerator;
  IRandom randomGeneratorWithSeed;

  @Before
  public void setUp() {
    randomGenerator = new RandomGenerator();
    randomGeneratorWithSeed = new RandomGeneratorTest(10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRandomHigherBoundLesserThanEqualToLowerBound() {
    new RandomGenerator().getRandom(10, 6);
  }

  @Test
  public void testGetRandom() {
    Set<Integer> uniqueRandomValues = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      int randomNumber = randomGenerator.getRandom(10, 51);
      assertTrue(randomNumber >= 10 && randomNumber <= 50);
      uniqueRandomValues.add(randomNumber);
    }
    assertTrue(uniqueRandomValues.size() >= 1);
  }

  @Test
  public void testRandomGeneratorWithSeed() {
    assertEquals(8, randomGeneratorWithSeed.getRandom(5, 20));
    assertEquals(5, randomGeneratorWithSeed.getRandom(5, 20));
    assertEquals(48, randomGeneratorWithSeed.getRandom(18, 151));
    assertEquals(13, randomGeneratorWithSeed.getRandom(13, 43));
    assertEquals(84, randomGeneratorWithSeed.getRandom(63, 88));
    assertEquals(40, randomGeneratorWithSeed.getRandom(12, 66));
  }

  @Test
  public void testGetRandomGenerator() {
    assertNotNull(randomGenerator.getRandomGenerator());
    assertNotNull(randomGeneratorWithSeed.getRandomGenerator());
  }
}
