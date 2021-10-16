package battlegame.weapon;

import battlegame.ability.AbilityName;
import battlegame.random.IRandom;

/**
 * A class to represent TwoHandedSwords weapon that can be wielded by a Player.
 */

public class TwoHandedSwords extends AbstractWeapon {
  /**
   * Constructs an TwoHandedSwords object and assigns basic attributes to it.
   * @param name the name of the twoHandedSwords weapon.
   * @param minimumDamage the minimum damage that can be affected by the twoHandedSwords weapon.
   * @param maximumDamage the maxium damage that can be affected by the twoHandedSwords weapon.
   * @param carryLimit the maximum carry limit of the twoHandedSwords weapon.
   * @param effectiveWieldAbilityName the name of the ability required to wield
   *                                  twoHandedSwords weapon effectively.
   * @param effectiveWieldAbilityValue the value of the ability required to wield
   *                                   twoHandedSwords weapon effectively.
   * @param weaponType the type of the weapon.
   * @param randomGenerator random number generator to generate randomness.
   */
  public TwoHandedSwords(String name, int minimumDamage, int maximumDamage,
                         int carryLimit, AbilityName effectiveWieldAbilityName,
                         int effectiveWieldAbilityValue,
                         WeaponType weaponType, IRandom randomGenerator) {
    super(name, minimumDamage, maximumDamage,
            carryLimit, effectiveWieldAbilityName, effectiveWieldAbilityValue,
            weaponType, randomGenerator);
  }
}
