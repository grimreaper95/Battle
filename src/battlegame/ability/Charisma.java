package battlegame.ability;

import battlegame.random.IRandom;

/**
 * A class to represent Charisma of a Player.
 */

public class Charisma extends AbstractAbility {
  /**
   * Constructs a Charisma object.
   * @param randomGenerator random number generator
   */
  public Charisma(IRandom randomGenerator) {
    super(randomGenerator);
  }
}
