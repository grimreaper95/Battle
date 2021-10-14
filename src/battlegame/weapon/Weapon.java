package battlegame.weapon;


import battlegame.ability.AbilityName;

public interface Weapon {

  int getMinimumDamage();

  int getMaximumDamage();

  int getCarryLimit();

  String getName();

  AbilityName getEffectiveWieldAbilityName();

  int getEffectiveWieldAbilityValue();

  int getRandomDamage();
}
