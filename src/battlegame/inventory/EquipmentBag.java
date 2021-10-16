package battlegame.inventory;

import static battlegame.util.Constants.BELT_LIMIT;
import static battlegame.util.Constants.BELT_NAME;
import static battlegame.util.Constants.EQUIPMENT_BAG_MAX_SIZE;
import static battlegame.util.Constants.FOOTWEAR_LIMIT;
import static battlegame.util.Constants.FOOTWEAR_NAME;
import static battlegame.util.Constants.HEADGEAR_LIMIT;
import static battlegame.util.Constants.HEADGEAR_NAME;
import static battlegame.util.Constants.MAX_BELT_EFFECT;
import static battlegame.util.Constants.MAX_FOOTWEAR_EFFECT;
import static battlegame.util.Constants.MAX_HEADGEAR_EFFECT;
import static battlegame.util.Constants.MAX_POTION_EFFECT;
import static battlegame.util.Constants.MIN_BELT_COUNT_EQUIPMENT_BAG;
import static battlegame.util.Constants.MIN_EQUIPMENT_BAG_SIZE;
import static battlegame.util.Constants.MIN_FOOTWEAR_COUNT_EQUIPMENT_BAG;
import static battlegame.util.Constants.MIN_HEADGEAR_COUNT_IN_EQUIPMENT_BAG;
import static battlegame.util.Constants.MIN_POTION_COUNT_IN_EQUIPMENT_BAG;
import static battlegame.util.Constants.POTION_LIMIT;
import static battlegame.util.Constants.POTION_NAME;

import battlegame.gear.Belt;
import battlegame.gear.BeltSize;
import battlegame.gear.Footwear;
import battlegame.gear.Gear;
import battlegame.gear.GearType;
import battlegame.gear.Headgear;
import battlegame.gear.Potion;
import battlegame.random.IRandom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class to represent the equipment bag consisting of gears to choose from.
 */
public class EquipmentBag {
  private final int bagSize;
  List<Gear> gearList;
  private final int headGearCount;
  private final int potionCount;
  private final int beltCount;
  private final int footwearCount;
  IRandom randomGenerator;

  /**
   * Constructs an eqipment bag and fills it with gears random chosen using the
   * random number generator.
   * @param randomGenerator the random number generator used to generate randomness.
   */
  public EquipmentBag(IRandom randomGenerator) {
    if (randomGenerator == null) {
      throw new IllegalArgumentException("Random generator cannot be null");
    }
    this.randomGenerator = randomGenerator;
    bagSize = this.randomGenerator.getRandom(40, EQUIPMENT_BAG_MAX_SIZE);
    gearList = new ArrayList<>(bagSize);

    headGearCount = (MIN_HEADGEAR_COUNT_IN_EQUIPMENT_BAG * bagSize) / MIN_EQUIPMENT_BAG_SIZE;
    potionCount = (MIN_POTION_COUNT_IN_EQUIPMENT_BAG * bagSize) / MIN_EQUIPMENT_BAG_SIZE;
    beltCount = (MIN_BELT_COUNT_EQUIPMENT_BAG * bagSize) / MIN_EQUIPMENT_BAG_SIZE;
    footwearCount = Integer.max(MIN_FOOTWEAR_COUNT_EQUIPMENT_BAG,
            bagSize - (headGearCount + potionCount + beltCount));
    addGearsToEquipmentBag();
  }

  private BeltSize getRandomBeltType() {
    int randomBeltIdx = randomGenerator.getRandom(0, 3);
    for (BeltSize beltSize : BeltSize.values()) {
      if (beltSize.getBeltIdx() == randomBeltIdx) {
        return beltSize;
      }
    }
    throw new IllegalStateException("No such belt type");
  }

  private void addGearsToEquipmentBag() {
    for (int i = 0; i < headGearCount; i++) {
      gearList.add(new Headgear(HEADGEAR_NAME + "_" + i,
              HEADGEAR_LIMIT, MAX_HEADGEAR_EFFECT,
              (gearList.size() % 4 == 0), GearType.HEADGEAR, randomGenerator));
    }
    for (int i = 0; i < potionCount; i++) {
      gearList.add(new Potion(POTION_NAME + "_" + i, POTION_LIMIT,
              MAX_POTION_EFFECT,
              (gearList.size() % 4 == 0), GearType.POTION, randomGenerator));
    }
    for (int i = 0; i < beltCount; i++) {
      gearList.add(new Belt(BELT_NAME + "_" + i, BELT_LIMIT, MAX_BELT_EFFECT,
              (gearList.size() % 4 == 0), getRandomBeltType(), GearType.BELT, randomGenerator));
    }
    for (int i = 0; i < footwearCount; i++) {
      gearList.add(new Footwear(FOOTWEAR_NAME + "_" + i,
              FOOTWEAR_LIMIT, MAX_FOOTWEAR_EFFECT,
              (gearList.size() % 4 == 0), GearType.FOOTWEAR, randomGenerator));
    }
    Collections.shuffle(gearList, randomGenerator.getRandomGenerator());
  }

  public List<Gear> getAllGearsInBag() {
    return new ArrayList<>(gearList);
  }

  public Gear getGearFromEquipmentBag() {

    return gearList.get(randomGenerator.getRandom(0, gearList.size()));
  }

  /**
   * Remove gear from equipment bag. A gear assigned to a Player will be removed from the equipment
   * bag.
   * @param gear the gear to be removed from the equipment bag.
   */
  public void removeGearFromBag(Gear gear) {
    gearList.remove(gear);
  }

  public int getEquipmentBagSize() {
    return gearList.size();
  }
}
