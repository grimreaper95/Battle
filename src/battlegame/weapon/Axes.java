package battlegame.weapon;

import battlegame.ability.AbilityName;
import battlegame.random.IRandom;

/**
 * A class to represent Axes weapon that can be wielded by a Player.
 */
public class Axes extends AbstractWeapon {
  /**
   * Constructs an Axes object and assigns basic attributes to it.
   * @param name the name of the axes weapon.
   * @param minimumWeaponDamage the minimum damage that can be affected by the axes weapon.
   * @param maximumWeaponDamage the maxium damage that can be affected by the axes weapon.
   * @param carryLimit the maximum carry limit of the axes weapon.
   * @param effectiveWieldAbilityName the name of the ability required to wield
   *                                  weapon effectively.
   * @param effectiveWieldAbilityValue the value of the ability required to wield
   *                                   weapon effectively.
   * @param weaponType the type of the weapon.
   * @param randomGenerator random number generator to generate randomness.
   */
  public Axes(String name, int minimumWeaponDamage, int maximumWeaponDamage,
              int carryLimit, AbilityName effectiveWieldAbilityName,
              int effectiveWieldAbilityValue, WeaponType weaponType, IRandom randomGenerator) {
    super(name, minimumWeaponDamage, maximumWeaponDamage,
            carryLimit, effectiveWieldAbilityName,
            effectiveWieldAbilityValue, weaponType, randomGenerator);
  }
}
