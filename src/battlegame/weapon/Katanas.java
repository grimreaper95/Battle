package battlegame.weapon;

import battlegame.ability.AbilityName;
import battlegame.random.IRandom;


/**
 * A class to represent Katanas weapon that can be wielded by a Player.
 */
public class Katanas extends AbstractWeapon {
  /**
   * Constructs an Katanas object and assigns basic attributes to it.
   * @param name the name of the katanas weapon.
   * @param minimumDamage the minimum damage that can be affected by the katanas weapon.
   * @param maximumDamage the maxium damage that can be affected by the katanas weapon.
   * @param carryLimit the maximum carry limit of the katanas weapon.
   * @param effectiveWieldAbilityName the name of the ability required to wield
   *                                  katanas weapon effectively.
   * @param effectiveWieldAbilityValue the value of the ability required to wield
   *                                   katanas weapon effectively.
   * @param weaponType the type of the weapon.
   * @param randomGenerator random number generator to generate randomness.
   */
  public Katanas(String name, int minimumDamage, int maximumDamage,
                 int carryLimit, AbilityName effectiveWieldAbilityName,
                 int effectiveWieldAbilityValue, WeaponType weaponType, IRandom randomGenerator) {
    super(name, minimumDamage, maximumDamage,
            carryLimit, effectiveWieldAbilityName, effectiveWieldAbilityValue,
            weaponType, randomGenerator);
  }
}
