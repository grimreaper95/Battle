import org.junit.Before;
import org.junit.Test;

import java.util.List;

import battlegame.inventory.EquipmentBag;
import battlegame.gear.Gear;
import battlegame.gear.GearType;
import battlegame.random.IRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Test class to test Equipment Bag.
 */

public class EquipmentBagTest {

  private EquipmentBag equipmentBag;
  IRandom randomGenerator;

  @Before
  public void setUp() {
    randomGenerator = new RandomGeneratorTest(5);
    equipmentBag = new EquipmentBag(randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEquipmentBagWithNullRandomGenerator() {
    new EquipmentBag(null);
  }

  @Test
  public void testOneFourthGearsInBagAreNegative() {
    List<Gear> gearsInBag = equipmentBag.getAllGearsInBag();
    assertTrue("Number of items in bag should " +
            "be more than 40", (gearsInBag.size() >= 40));
    int negatives = 0;
    for (Gear gear : gearsInBag) {
      if (gear.getRandomEffect() < 0) {
        negatives++;
      }
    }
    assertTrue("At least one fourth items in equipment bag are negative",
            negatives >= gearsInBag.size() / 4);
  }

  @Test
  public void testEquipmentBagSize() {
    assertTrue("Size of equipment bag when created " +
            "with at least 40 gears", (equipmentBag.getEquipmentBagSize() >= 40));
  }


  @Test
  public void testGetGearFromEquipmentBag() {
    Gear gearFromBox = equipmentBag.getGearFromEquipmentBag();
    assertTrue("Gear in gear box should be one of the four types",
            gearFromBox.getType() == GearType.BELT ||
                    gearFromBox.getType() == GearType.POTION ||
                    gearFromBox.getType() == GearType.HEADGEAR ||
                    gearFromBox.getType() == GearType.FOOTWEAR);
  }

  @Test
  public void testBeltsInEquipmentBag() {
    int belts = 0;
    List<Gear> gearList = equipmentBag.getAllGearsInBag();
    for (Gear gear : gearList) {
      if (gear.getType() == GearType.BELT) belts++;
    }
    assertTrue("Minimum 15 belts should be present", (belts >= 15));
  }

  @Test
  public void testHeadGearsInEquipmentBag() {
    int headgears = 0;
    List<Gear> gearList = equipmentBag.getAllGearsInBag();
    for (Gear gear : gearList) {
      if (gear.getType() == GearType.HEADGEAR) headgears++;
    }
    assertTrue("Minimum 5 headgears should be present", (headgears >= 5));
  }

  @Test
  public void testFootwearsInEquipmentBag() {
    int footwears = 0;
    List<Gear> gearList = equipmentBag.getAllGearsInBag();
    for (Gear gear : gearList) {
      if (gear.getType() == GearType.FOOTWEAR) footwears++;
    }
    assertTrue("Minimum 5 footwears should be present", (footwears >= 5));
  }

  @Test
  public void testPotionsInEquipmentBag() {
    int potions = 0;
    List<Gear> gearList = equipmentBag.getAllGearsInBag();
    for (Gear gear : gearList) {
      if (gear.getType() == GearType.POTION) potions++;
    }
    assertTrue("Minimum 15 potions should be present", (potions >= 15));
  }


  @Test
  public void testRemoveFromEquipmentBag() {
    int sizeBefore = equipmentBag.getEquipmentBagSize();
    assertEquals("Before removing gear", sizeBefore, equipmentBag.getEquipmentBagSize());

    Gear gearFromBag = equipmentBag.getGearFromEquipmentBag();
    assertNotNull("Gear from equipment bag", gearFromBag);
    equipmentBag.removeGearFromBag(gearFromBag);

    assertEquals("Size after removing gear",
            sizeBefore - 1, equipmentBag.getEquipmentBagSize());
  }
}
