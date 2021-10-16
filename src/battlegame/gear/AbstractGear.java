package battlegame.gear;

import battlegame.random.IRandom;
import battlegame.util.CustomPair;

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class to that contains common code shared by all Gears.
 */

public abstract class AbstractGear implements Gear {
  private final String gearName;
  private final int maxGearEffect;
  private final int gearLimit;
  private final boolean isEffectNegative;
  private final GearType gearType;
  private final IRandom generator;
  protected final List<CustomPair> abilityList;

  protected AbstractGear(String gearName, int gearLimit,
                         int maxGearEffect, boolean isEffectNegative, GearType gearType,
                         IRandom generator) {
    if (gearName == null || gearName.equals("")) {
      throw new IllegalArgumentException("Gear Name cannot be null or empty");
    }
    if (gearType == null) {
      throw new IllegalArgumentException("Gear type cannot be null");
    }
    if (generator == null) {
      throw new IllegalArgumentException("Random Number Generator cannot be null");
    }

    this.gearName = gearName;
    this.gearLimit = gearLimit;
    this.maxGearEffect = maxGearEffect;
    this.isEffectNegative = isEffectNegative;
    this.gearType = gearType;
    this.generator = generator;
    abilityList = new ArrayList<>();
  }

  @Override
  public int getRandomEffect() {
    return generator.getRandom(1, maxGearEffect) * (((isEffectNegative) ? -1 : 1));
  }

  @Override
  public String getName() {
    return this.gearName;
  }

  @Override
  public GearType getType() {
    return this.gearType;
  }

  @Override
  public int getLimit() {
    return this.gearLimit;
  }

  @Override
  public List<CustomPair> getAffectedAbilityList() {
    return new ArrayList<>(this.abilityList);
  }

  @Override
  public int getMaxGearEffect() {
    return maxGearEffect;
  }

  @Override
  public int getSize() {
    return 1;
  }

  protected int compareToHeadgear(Headgear headgear) {
    return -1;
  }

  protected int compareToFootwear(Footwear footwear) {
    return 1;
  }

  protected abstract int compareToPotion(Potion potion);

  protected abstract int compareToBelt(Belt belt);
}
