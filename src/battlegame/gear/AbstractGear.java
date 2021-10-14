package battlegame.gear;

import java.util.ArrayList;
import java.util.List;

import battlegame.RandomGenerator;
import battlegame.util.CustomPair;

public class AbstractGear implements Gear{
  private final String gearName;
  private final int maxGearEffect, gearLimit;
  protected List<CustomPair> abilityList = new ArrayList<>();
  boolean isEffectNegative;

  protected int getRandomEffect() {
    return RandomGenerator.getRandom(0, maxGearEffect) * (((isEffectNegative) ? 1 : -1));
  }

  AbstractGear(String gearName, int maxGearEffect, int gearLimit, boolean isEffectNegative) {
    this.gearName = gearName;
    this.maxGearEffect = maxGearEffect;
    this.gearLimit = gearLimit;
    this.isEffectNegative = isEffectNegative;
  }

  @Override
  public String getName() {
    return this.gearName;
  }
  @Override
  public int getLimit() {
    return this.gearLimit;
  }

  @Override
  public int getMaxGearEffect() {
    return this.maxGearEffect;
  }


  @Override
  public List<CustomPair> getAffectedAbilityList() {
    return this.abilityList;
  }

  @Override
  public void updateGear() {

  }

  @Override
  public int compareTo(Object o) {
    return 0;
  }
}
