package battlegame.ability;

/**
 * An Enum to represent name of the Abilities.
 */
public enum AbilityName {
  STRENGTH(0), CONSTITUTION(1), DEXTERITY(2), CHARISMA(3), ANY(4);
  private final int abilityVal;

  AbilityName(int val) {
    abilityVal = val;
  }

  /**
   * Get the value of Ability.
   * @return the value of Ability.
   */
  public int getAbilityVal() {
    return abilityVal;
  }
}
