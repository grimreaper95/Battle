package battlegame.weapon;

import battlegame.ability.AbilityName;

/**
 * An interface that represents the weapons that can be wielded by a Player.
 */

public interface Weapon {

  /**
   * get minimum damage done by a weapon.
   *
   * @return minimum damage done by a weapon.
   */
  int getMinimumDamage();

  /**
   * get maximum damage done by a weapon.
   *
   * @return maximum damage done by a weapon.
   */
  int getMaximumDamage();

  /**
   * Get the maximum number of units of a weapon allowed to carry simultaneously.
   * @return the maximum number of units of a weapon allowed to carry simultaneously.
   */
  int getCarryLimit();

  /**
   * get name of a weapon.
   *
   * @return name of a weapon.
   */
  String getName();

  /**
   * get the name of ability for a weapon which is required for effective use of the weapon.
   *
   * @return the name of ability for a weapon which is required for effective use of the weapon.
   */
  AbilityName getEffectiveWieldAbilityName();

  /**
   * get the value of ability for a weapon which is required for effective use of the weapon.
   * @return the value of ability for a weapon which is required for effective use of the weapon.
   */
  int getEffectiveWieldAbilityValue();

  /**
   * get random damage done by the weapon.
   *
   * @return random damage done by the weapon.
   */
  int getRandomDamage(int effectiveAbilityValue);

  /**
   * get type of the weapon.
   *
   * @return type of the weapon.
   */
  WeaponType getType();
}
