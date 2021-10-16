package battlegame.ability;

import battlegame.random.IRandom;

/**
 * A class to represent Constitution of a Player.
 */
public class Constitution extends AbstractAbility {
  /**
   * Constructs a Constitution object.
   * @param randomGenerator random number generator
   */
  public Constitution(IRandom randomGenerator) {
    super(randomGenerator);
  }
}
