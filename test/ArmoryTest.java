import org.junit.Before;
import org.junit.Test;

import java.util.List;

import battlegame.inventory.Armory;
import battlegame.random.IRandom;
import battlegame.weapon.Weapon;
import battlegame.weapon.WeaponType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class to test Armory.
 */

public class ArmoryTest {

  // test null - illArg
  // add weapon to armory
  // delete weapon from armory
  // armory has at least 1 type of weap

  private Armory armory;
  IRandom randomGenerator;

  @Before
  public void setUp() {
    randomGenerator = new RandomGeneratorTest(5);
    armory = new Armory(randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testArmoryNullRandomGenerator() {
    new Armory(null);
  }

  @Test
  public void testGetAllWeaponsFromArmory() {
    List<Weapon> weaponList = armory.getWeaponList();
    assertTrue("Number of weapons in armory should " +
            "be more than 5", (weaponList.size() >= 5));
  }

  @Test
  public void testGetArmorySize() {
    assertTrue("Size of equipment bag when created " +
            "with at least 40 gears", (armory.getArmorySize() >= 5));
  }

  @Test
  public void testEachWeaponPresentInArmory() {
    List<Weapon> weaponList = armory.getWeaponList();
    int katanas = 0, axes = 0, twoHandedSwords = 0, broadSwords = 0, flails = 0;
    for (Weapon weapon : weaponList) {
      if (weapon.getType() == WeaponType.KATANAS) katanas++;
      if (weapon.getType() == WeaponType.AXES) axes++;
      if (weapon.getType() == WeaponType.TWOHANDEDSWORDS) twoHandedSwords++;
      if (weapon.getType() == WeaponType.BROADSWORDS) broadSwords++;
      if (weapon.getType() == WeaponType.FLAILS) flails++;
    }

    assertTrue("Each equipment should be present in armory",
            (katanas > 0 && axes > 0 && twoHandedSwords > 0 && broadSwords > 0 && flails > 0));
  }


  @Test
  public void testGetWeaponFromArmory() {
    Weapon weapon = armory.getWeaponFromArmory();
    assertTrue("Gear in gear box should be one of the four types",
            weapon.getType() == WeaponType.KATANAS
                    || weapon.getType() == WeaponType.AXES
                    || weapon.getType() == WeaponType.BROADSWORDS
                    || weapon.getType() == WeaponType.TWOHANDEDSWORDS
                    || weapon.getType() == WeaponType.FLAILS);
  }


  @Test
  public void testRemoveFromArmory() {
    int sizeBefore = armory.getArmorySize();
    assertEquals("Before removing gear", sizeBefore, armory.getArmorySize());

    Weapon weaponFromArmory = armory.getWeaponFromArmory();
    assertNotNull("Weapon from armory", armory);
    armory.removeWeaponFromArmory(weaponFromArmory);

    assertEquals("Size after removing gear",
            sizeBefore - 1, armory.getArmorySize());
  }
}
