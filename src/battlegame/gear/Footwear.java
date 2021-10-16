package battlegame.gear;

import battlegame.ability.AbilityName;
import battlegame.random.IRandom;
import battlegame.util.CustomPair;

/**
 * A class to represent Footwear gear.
 */

public class Footwear extends AbstractGear {
  /**
   * Constructs a Footwear gear object and assigns basic attributes to it.
   * @param footwearName name of the footwear.
   * @param footwearLimit limit of the footwear which represents the number of footwear a player
   *                      can wear.
   * @param maxFootwearEffect maximum effect of footwear.
   * @param isEffectNegative  represents if this gear will affect player attributes positively
   *                          or negatively.
   * @param gearType the type of gear.
   * @param generator random number generator to generate randomness.
   */
  public Footwear(String footwearName, int footwearLimit, int maxFootwearEffect,
                  boolean isEffectNegative, GearType gearType, IRandom generator) {
    super(footwearName, footwearLimit, maxFootwearEffect,
            isEffectNegative, gearType, generator);
    abilityList.add(new CustomPair(AbilityName.DEXTERITY, getRandomEffect()));
  }

  @Override
  protected int compareToBelt(Belt belt) {
    return -1;
  }

  @Override
  protected int compareToPotion(Potion potion) {
    return -1;
  }

  @Override
  protected int compareToFootwear(Footwear footwear) {
    return footwear.getName().compareTo(this.getName());
  }

  @Override
  public int compareTo(Gear g) {
    if (g instanceof AbstractGear) {
      AbstractGear thatGear = (AbstractGear) g;
      return thatGear.compareToFootwear(this);
    }
    return 1; //default implementation, should never come here as
    // every question will implement AbstractGear.
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
