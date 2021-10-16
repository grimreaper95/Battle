package battlegame.gear;

import battlegame.ability.AbilityName;
import battlegame.random.IRandom;
import battlegame.util.CustomPair;

/**
 * A class to represent Potion.
 */

public class Potion extends AbstractGear {
  private IRandom randomGenerator;

  /**
   * Constructs a Potion object and assigns basic attributes to it.
   * @param potionName name of the potion.
   * @param potionLimit limit of the potion which represents the maximum potions
   *                      a player can have.
   * @param maxPotionEffect maximum effect a potion can have on attributes of the player.
   * @param isEffectNegative whether gear affects player abilities positively or negatively.
   * @param gearType type of gear.
   * @param randomGenerator random number generator to generate randomness.
   */
  public Potion(String potionName, int potionLimit, int maxPotionEffect,
                boolean isEffectNegative, GearType gearType, IRandom randomGenerator) {
    super(potionName, potionLimit, maxPotionEffect, isEffectNegative,
            gearType, randomGenerator);
    this.randomGenerator = randomGenerator;

    abilityList.add(new CustomPair(getRandomAbility(), getRandomEffect()));
  }

  private AbilityName getRandomAbility() {
    int abilityVal = randomGenerator.getRandom(0, 4);
    for (AbilityName abilityName : AbilityName.values()) {
      if (abilityName.getAbilityVal() == abilityVal) {
        return abilityName;
      }
    }
    throw new IllegalStateException("No such ability value exists");
  }


  @Override
  protected int compareToBelt(Belt belt) {
    return 1;
  }

  @Override
  protected int compareToPotion(Potion potion) {
    return potion.getName().compareTo(this.getName());
  }

  @Override
  public int compareTo(Gear g) {
    if (g instanceof AbstractGear) {
      AbstractGear thatGear = (AbstractGear) g;
      return thatGear.compareToPotion(this);
    }
    return 1; //default implementation, should never come here as
    // every question will implement AbstractGear
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
