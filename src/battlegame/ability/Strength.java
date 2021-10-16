package battlegame.ability;

import battlegame.random.IRandom;

/**
 * A class to represent Strength of a Player.
 */
public class Strength extends AbstractAbility {
  /**
   * Constructs a Strength object.
   * @param randomGenerator random number generator
   */
  public Strength(IRandom randomGenerator) {
    super(randomGenerator);
  }
}
