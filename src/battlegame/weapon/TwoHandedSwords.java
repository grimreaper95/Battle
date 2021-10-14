package battlegame.weapon;
import battlegame.ability.AbilityName;

public class TwoHandedSwords extends AbstractWeapon{
  public TwoHandedSwords() {
    super("TwoHandedSwords", 8, 12,
            1, AbilityName.DEXTERITY, 14);
  }
}
