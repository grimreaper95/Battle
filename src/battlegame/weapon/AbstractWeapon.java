package battlegame.weapon;

import battlegame.RandomGenerator;
import battlegame.ability.AbilityName;

public class AbstractWeapon implements Weapon{

  private final String name;
  private final int minimumWeaponDamage;
  private final int maximumWeaponDamage;
  private final int weaponCarryLimit;
  private final AbilityName effectiveWieldAbilityName;
  private final int effectiveWieldAbilityValue;

  protected AbstractWeapon(String name, int minimumWeaponDamage, int maximumWeaponDamage,
                           int weaponCarryLimit, AbilityName effectiveWieldAbilityName,
                           int effectiveWieldAbilityValue) {
    this.name = name;
    this.minimumWeaponDamage = minimumWeaponDamage;
    this.maximumWeaponDamage = maximumWeaponDamage;
    this.weaponCarryLimit = weaponCarryLimit;
    this.effectiveWieldAbilityName = effectiveWieldAbilityName;
    this.effectiveWieldAbilityValue = effectiveWieldAbilityValue;
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
  public int getRandomDamage() {
    return RandomGenerator.getRandom(getMinimumDamage(), getMaximumDamage());
  }
}
