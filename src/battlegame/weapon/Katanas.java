package battlegame.weapon;

import battlegame.ability.AbilityName;

public class Katanas extends AbstractWeapon{
  public Katanas() {
    super("Katanas", 4, 6,
            2, AbilityName.STRENGTH, 14);
  }
}
