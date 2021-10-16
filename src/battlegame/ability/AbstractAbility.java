package battlegame.ability;

import battlegame.random.IRandom;

import java.util.Arrays;

/**
 * An Abstract class that contains code shared by all Abilities.
 */

public class AbstractAbility implements Ability {
  private final int abilityValue;
  private final IRandom randomGenerator;

  protected AbstractAbility(IRandom randomGenerator) {
    if (randomGenerator == null) {
      throw new IllegalArgumentException("Random generator cannot be null");
    }
    this.randomGenerator = randomGenerator;
    abilityValue = calculateAbility();
  }

  private int calculateAbility() {
    int []dice = new int[4];
    for (int i = 0; i < 4; i++) {
      do {
        dice[i] = randomGenerator.getRandom(1, 7);
      }
      while (dice[i] == 1);
    }
    Arrays.sort(dice);
    return dice[1] + dice[2] + dice[3];
  }

  @Override
  public int getValue() {
    return this.abilityValue;
  }
}
