package battlegame.weapon;

import battlegame.ability.AbilityName;
import battlegame.random.IRandom;

/**
 * A class to represent Flails weapon that can be wielded by a Player.
 */
public class Flails extends AbstractWeapon {
  /**
   * Constructs an Flails object and assigns basic attributes to it.
   * @param name the name of the flails weapon.
   * @param minimumDamage the minimum damage that can be affected by the flails weapon.
   * @param maximumDamage the maxium damage that can be affected by the flails weapon.
   * @param carryLimit the maximum carry limit of the flails weapon.
   * @param effectiveWieldAbilityName the name of the ability required to wield
   *                                  flails weapon effectively.
   * @param effectiveWieldAbilityValue the value of the ability required to wield
   *                                   flails weapon effectively.
   * @param weaponType the type of the weapon.
   * @param randomGenerator random number generator to generate randomness.
   */
  public Flails(String name, int minimumDamage, int maximumDamage,
                int carryLimit, AbilityName effectiveWieldAbilityName,
                int effectiveWieldAbilityValue, WeaponType weaponType, IRandom randomGenerator) {
    super(name, minimumDamage, maximumDamage,
            carryLimit, effectiveWieldAbilityName,
            effectiveWieldAbilityValue, weaponType, randomGenerator);
  }
}
