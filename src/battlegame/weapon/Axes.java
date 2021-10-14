package battlegame.weapon;

import battlegame.ability.AbilityName;

public class Axes extends AbstractWeapon{
  public Axes() {
    super("Axes", 6, 10,
            1, AbilityName.ANY, Integer.MIN_VALUE);
  }
}
