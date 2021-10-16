package battlegame.weapon;

import battlegame.ability.AbilityName;
import battlegame.random.IRandom;

/**
 * An abstract class to that contains common code shared by all Weapons.
 */

public class AbstractWeapon implements Weapon {

  private final String name;
  private final int minimumWeaponDamage;
  private final int maximumWeaponDamage;
  private final int weaponCarryLimit;
  private final AbilityName effectiveWieldAbilityName;
  private final int effectiveWieldAbilityValue;
  private WeaponType weaponType;
  IRandom randomGenerator;

  protected AbstractWeapon(String name, int minimumWeaponDamage, int maximumWeaponDamage,
                           int weaponCarryLimit, AbilityName effectiveWieldAbilityName,
                           int effectiveWieldAbilityValue,
                           WeaponType weaponType, IRandom randomGenerator) {

    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (weaponCarryLimit < 0) {
      throw new IllegalArgumentException("Weapon carrying limit cannot be negative");
    }
    if (effectiveWieldAbilityName == null) {
      throw new IllegalArgumentException("Effective Wield Ability cannot be null");
    }
    if (weaponType == null) {
      throw new IllegalArgumentException("Weapon type cannot be null");
    }

    if (randomGenerator == null) {
      throw new IllegalArgumentException("Random generator cannot be null");
    }

    this.name = name;
    this.minimumWeaponDamage = minimumWeaponDamage;
    this.maximumWeaponDamage = maximumWeaponDamage;
    this.weaponCarryLimit = weaponCarryLimit;
    this.effectiveWieldAbilityName = effectiveWieldAbilityName;
    this.effectiveWieldAbilityValue = effectiveWieldAbilityValue;
    this.weaponType = weaponType;
    this.randomGenerator = randomGenerator;
  }

  @Override
  public int getMinimumDamage() {
    return minimumWeaponDamage;
  }

  @Override
  public int getMaximumDamage() {
    return maximumWeaponDamage;
  }

  @Override
  public int getCarryLimit() {
    return weaponCarryLimit;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public AbilityName getEffectiveWieldAbilityName() {
    return effectiveWieldAbilityName;
  }

  @Override
  public int getEffectiveWieldAbilityValue() {
    return effectiveWieldAbilityValue;
  }

  @Override
  public int getRandomDamage(int effectiveAbilityCurrentValue) {
    int minWeaponDamage = this.getMinimumDamage();
    int maxWeaponDamage = this.getMaximumDamage();
    if (effectiveAbilityCurrentValue <= effectiveWieldAbilityValue) {
      minWeaponDamage /= 2;
      maxWeaponDamage /= 2;
    }
    return randomGenerator.getRandom(minWeaponDamage, maxWeaponDamage);
  }

  public WeaponType getType() {
    return this.weaponType;
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
