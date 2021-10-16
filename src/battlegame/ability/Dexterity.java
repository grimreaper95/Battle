package battlegame.ability;

import battlegame.random.IRandom;

/**
 * A class to represent Dexterity of a Player.
 */
public class Dexterity extends AbstractAbility {
  /**
   * Constructs a Dexterity object.
   * @param randomGenerator random number generator
   */
  public Dexterity(IRandom randomGenerator) {
    super(randomGenerator);
  }
}
