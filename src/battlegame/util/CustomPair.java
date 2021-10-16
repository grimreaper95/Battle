package battlegame.util;

import battlegame.ability.AbilityName;

/**
 * A class to represent a Pair of the Ability name and its value.
 */
public class CustomPair {
  private AbilityName abilityName;
  private int value;

  /**
   * Constructs a CustomPair object and initializes it to the given threshold values.
   * @param abilityName the name of the ability.
   * @param value the value of the ability.
   */
  public CustomPair(AbilityName abilityName, int value) {
    this.abilityName = abilityName;
    this.value = value;
  }

  public AbilityName getKey() {
    return abilityName;
  }

  public int getValue() {
    return value;
  }
}