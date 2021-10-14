package battlegame.weapon;

import battlegame.ability.AbilityName;

public class Flails extends AbstractWeapon{
  public Flails() {
    super("Flails", 8, 12,
            1, AbilityName.DEXTERITY, 14);
  }
}
