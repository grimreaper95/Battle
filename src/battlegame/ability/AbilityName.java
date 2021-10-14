package battlegame.ability;

public enum AbilityName {
  STRENGTH(0), CONSTIUTION(1), DEXTERITY(2), CHARISMA(3), ANY(4);
  public final int abilityVal;
  AbilityName(int val) {
    abilityVal = val;
  }
}
