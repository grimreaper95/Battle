package battlegame.util;

import battlegame.ability.AbilityName;

public class CustomPair {
  private AbilityName abilityName;
  private int value;
  public CustomPair(AbilityName abilityName, int value) {
    this.abilityName = abilityName;
    this.value = value;
  }
  // standard getters and setters

  public void setKey(AbilityName abilityName) {
    this.abilityName = abilityName;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public AbilityName getKey() {
    return abilityName;
  }
  public int getValue() {
    return value;
  }
}