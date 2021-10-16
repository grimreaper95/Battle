package battlegame.gear;

import battlegame.ability.AbilityName;
import battlegame.random.IRandom;
import battlegame.util.CustomPair;

/**
 * A class to represent the Headgear.
 */
public class Headgear extends AbstractGear {
  /**
   * Constructs a Headgear object and assigns basic attributes to it.
   * @param headgearName name of the headgear.
   * @param headGearLimit limit of the headgear which represents the maximum headgear
   *                      a player can wear.
   * @param maxHeadGearEffect maximum effect a headgear can have on  attributes of the player.
   * @param isEffectNegative whether gear affects player abilities positively or negatively.
   * @param gearType type of gear.
   * @param randomGenerator random number generator to generate randomness.
   */
  public Headgear(String headgearName, int headGearLimit,
                  int maxHeadGearEffect, boolean isEffectNegative,
                  GearType gearType, IRandom randomGenerator) {
    super(headgearName, headGearLimit, maxHeadGearEffect,
            isEffectNegative, gearType, randomGenerator);
    abilityList.add(new CustomPair(AbilityName.CONSTITUTION, getRandomEffect()));
  }

  @Override
  protected int compareToBelt(Belt belt) {
    return 1;
  }

  @Override
  protected int compareToPotion(Potion potion) {
    return 1;
  }

  @Override
  protected int compareToHeadgear(Headgear headgear) {
    return headgear.getName().compareTo(this.getName());
  }

  @Override
  public int compareTo(Gear g) {
    if (g instanceof AbstractGear) {
      AbstractGear thatGear = (AbstractGear) g;
      return thatGear.compareToHeadgear(this);
    }
    return 1; //default implementation, should never come here as
    // every Gear will implement AbstractGear.
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
