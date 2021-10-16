package battlegame.gear;


import battlegame.ability.AbilityName;
import battlegame.random.IRandom;
import battlegame.util.CustomPair;

/**
 * A class to represent Belt gear.
 */
public class Belt extends AbstractGear {

  private final BeltSize beltSize;
  private final IRandom randomGenerator;

  /**
   * Constructs a Belt gear object and assigns basic attributes to it.
   * @param beltName name of belt gear.
   * @param beltLimit limit of belt gear. It represents the maxium unit of this gear
   *                  player can wear.
   * @param maxBeltEffect maximum of of belt gear possible.
   * @param isEffectNegative x
   * @param beltSize represents the size of belt.
   * @param gearType represents the type of this belt gear.
   * @param randomGenerator random number generator used to generate random values.
   */
  public Belt(String beltName, int beltLimit, int maxBeltEffect,
              boolean isEffectNegative, BeltSize beltSize, GearType gearType,
              IRandom randomGenerator) {
    super(beltName, beltLimit, maxBeltEffect, isEffectNegative,
            gearType, randomGenerator);
    this.beltSize = beltSize;
    this.randomGenerator = randomGenerator;
    //adding two random abilities that will be affected by the belt
    int abilitiesAffectedCount = getAbilitiesAffectedCount();
    for (int i = 0; i < abilitiesAffectedCount; i++) {
      abilityList.add(new CustomPair(getRandomAbility(), getRandomEffect()));
    }
  }

  private int getAbilitiesAffectedCount() {
    return randomGenerator.getRandom(0, 3); // gives 0 or 1 or 2
  }

  private AbilityName getRandomAbility() {
    int abilityVal = randomGenerator.getRandom(0, 4);
    for (AbilityName abilityName : AbilityName.values()) {
      if (abilityName.getAbilityVal() == abilityVal) {
        return abilityName;
      }
    }
    throw new IllegalStateException("Ability not present in ability list");
  }

  @Override
  public int getSize() {
    return beltSize.getBetltSize();
  }

  @Override
  protected int compareToBelt(Belt belt) {
    return belt.getName().compareTo(this.getName());
  }

  @Override
  protected int compareToPotion(Potion potion) {
    return -1;
  }

  @Override
  public int compareTo(Gear g) {
    if (g instanceof AbstractGear) {
      AbstractGear thatGear = (AbstractGear) g;
      return thatGear.compareToBelt(this);
    }
    return 1; //default implementation, should never come here as
    // every question will implement AbstractGear.
  }

  @Override
  public String toString() {
    return this.getName();
  }
}
