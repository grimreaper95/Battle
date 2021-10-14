package battlegame.weapon;

import battlegame.ability.AbilityName;

public class Broadswords extends AbstractWeapon {
  public Broadswords() {
    super("Broadswords", 6, 10,
            1, AbilityName.ANY, Integer.MIN_VALUE);
  }
}
