import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import battlegame.ability.AbilityName;
import battlegame.gear.Belt;
import battlegame.gear.BeltSize;
import battlegame.gear.Footwear;
import battlegame.gear.Gear;
import battlegame.gear.GearType;
import battlegame.gear.Headgear;
import battlegame.gear.Potion;
import battlegame.random.IRandom;


import static battlegame.util.Constants.BELT_LIMIT;
import static battlegame.util.Constants.BELT_NAME;
import static battlegame.util.Constants.FLAILS_NAME;
import static battlegame.util.Constants.FOOTWEAR_LIMIT;
import static battlegame.util.Constants.FOOTWEAR_NAME;
import static battlegame.util.Constants.HEADGEAR_LIMIT;
import static battlegame.util.Constants.HEADGEAR_NAME;
import static battlegame.util.Constants.MAX_BELT_EFFECT;
import static battlegame.util.Constants.MAX_FOOTWEAR_EFFECT;
import static battlegame.util.Constants.MAX_HEADGEAR_EFFECT;
import static battlegame.util.Constants.MAX_POTION_EFFECT;
import static battlegame.util.Constants.POTION_LIMIT;
import static battlegame.util.Constants.POTION_NAME;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Test class to test Gear.
 */

public class GearTest {
  private Gear headgearPositive;
  private Gear headgearNegative;
  private Gear potionPositive;
  private Gear potionNegative;

  private Gear beltPositive;
  private Gear beltNegative;
  private Gear footwearPositive;
  private Gear footwearNegative;
  IRandom randomGenerator;

  @Before
  public void setUp() {
    randomGenerator = new RandomGeneratorTest(5);
    headgearPositive = new Headgear(HEADGEAR_NAME,
            HEADGEAR_LIMIT, MAX_HEADGEAR_EFFECT, false, GearType.HEADGEAR, randomGenerator);

    potionPositive = new Potion(POTION_NAME, POTION_LIMIT,
            MAX_POTION_EFFECT, false, GearType.POTION, randomGenerator);

    beltPositive = new Belt(BELT_NAME, BELT_LIMIT, MAX_BELT_EFFECT,
            false, BeltSize.MEDIUM, GearType.BELT, randomGenerator);

    footwearPositive = new Footwear(FOOTWEAR_NAME, FOOTWEAR_LIMIT,
            MAX_FOOTWEAR_EFFECT, false, GearType.FOOTWEAR, randomGenerator);

    headgearNegative = new Headgear(HEADGEAR_NAME, HEADGEAR_LIMIT,
            MAX_HEADGEAR_EFFECT, true, GearType.HEADGEAR, randomGenerator);
    potionNegative = new Potion(POTION_NAME, POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);
    beltNegative = new Belt(BELT_NAME, BELT_LIMIT, MAX_BELT_EFFECT,
            true, BeltSize.LARGE, GearType.BELT, randomGenerator);
    footwearNegative = new Footwear(FOOTWEAR_NAME, FOOTWEAR_LIMIT,
            MAX_FOOTWEAR_EFFECT, true, GearType.FOOTWEAR, randomGenerator);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testGearNameNull() {
    new Headgear(null, HEADGEAR_LIMIT, MAX_HEADGEAR_EFFECT,
            false, GearType.HEADGEAR, randomGenerator);
    new Potion("", POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGearEmptyName() {
    new Potion("", POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRandomGeneratorNull() {
    new Headgear(HEADGEAR_NAME, HEADGEAR_LIMIT,
            MAX_HEADGEAR_EFFECT, false, GearType.HEADGEAR, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGearTypeNull() {
    new Headgear(FLAILS_NAME, FOOTWEAR_LIMIT, MAX_FOOTWEAR_EFFECT,
            false, null, randomGenerator);
  }

  @Test
  public void testGearPositiveEffect() {
    assertNotNull(headgearNegative.getAffectedAbilityList());
    assertFalse(headgearNegative.getAffectedAbilityList().isEmpty());
    assertTrue("Headgear has positive effect",
            headgearPositive.getAffectedAbilityList().get(0).getValue() >= 0);
    assertTrue("Potion has positive effect",
            potionPositive.getAffectedAbilityList().get(0).getValue() >= 0);
    assertTrue("Belt has positive effect",
            beltPositive.getAffectedAbilityList().get(0).getValue() >= 0);
    assertTrue("Footwear has positive effect",
            footwearPositive.getAffectedAbilityList().get(0).getValue() >= 0);
  }

  @Test
  public void testToString() {
    assertEquals("HEADGEAR", headgearPositive.toString());
    assertEquals("BELT", beltNegative.toString());
    assertEquals("POTION", potionPositive.toString());
    assertEquals("FOOTWEAR", footwearPositive.toString());
  }

  @Test
  public void testGearNegativeEffect() {
    assertNotNull(headgearNegative.getAffectedAbilityList());
    assertFalse(headgearNegative.getAffectedAbilityList().isEmpty());
    assertTrue("Headgear has negative effect",
            headgearNegative.getAffectedAbilityList().get(0).getValue() <= 0);
    assertTrue("Potion has negative effect",
            potionNegative.getAffectedAbilityList().get(0).getValue() <= 0);
    assertTrue("Belt has negative effect",
            beltNegative.getAffectedAbilityList().get(0).getValue() <= 0);
    assertTrue("Footwear has negative effect",
            footwearNegative.getAffectedAbilityList().get(0).getValue() <= 0);
  }

  @Test
  public void testHeadgearAffectsOneAbility() {
    assertNotNull(headgearNegative.getAffectedAbilityList());
    assertEquals("Headgear affects strength", 1,
            headgearPositive.getAffectedAbilityList().size());
    assertEquals("Headgear affects strength", 1,
            headgearNegative.getAffectedAbilityList().size());
  }

  @Test
  public void testHeadgearAffectsStrength() {
    assertEquals("Headgear affects strength", AbilityName.CONSTITUTION,
            headgearPositive.getAffectedAbilityList().get(0).getKey());
    assertEquals("Headgear affects strength", AbilityName.CONSTITUTION,
            headgearNegative.getAffectedAbilityList().get(0).getKey());
  }

  @Test
  public void testFootwearAffectsOneAbility() {
    assertNotNull(headgearNegative.getAffectedAbilityList());
    assertEquals("Footwear affects strength", 1,
            footwearPositive.getAffectedAbilityList().size());
    assertEquals("Footwear affects strength", 1,
            footwearNegative.getAffectedAbilityList().size());
  }

  @Test
  public void testFootwearAffectsDexterity() {
    assertEquals("Footwear affects dexterity", AbilityName.DEXTERITY,
            footwearPositive.getAffectedAbilityList().get(0).getKey());
    assertEquals("Footwear affects dexterity", AbilityName.DEXTERITY,
            footwearNegative.getAffectedAbilityList().get(0).getKey());
  }


  @Test
  public void testPotionAffectsOneAbility() {
    assertNotNull(potionPositive.getAffectedAbilityList());
    assertEquals("Footwear affects strength", 1,
            potionPositive.getAffectedAbilityList().size());
    assertEquals("Footwear affects strength", 1,
            potionPositive.getAffectedAbilityList().size());
  }

  @Test
  public void testPotionAffectsAllFourAbilities() {
    randomGenerator = new RandomGeneratorTest(4194);
    Gear potion1 = new Potion(POTION_NAME + "1", POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);
    Gear potion2 = new Potion(POTION_NAME + "2", POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);
    Gear potion3 = new Potion(POTION_NAME + "3", POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);
    Gear potion4 = new Potion(POTION_NAME + "4", POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);

    assertEquals("Potion affects Strength", AbilityName.STRENGTH,
            potion1.getAffectedAbilityList().get(0).getKey());

    assertEquals("Potion affects Charisma", AbilityName.DEXTERITY,
            potion2.getAffectedAbilityList().get(0).getKey());

    assertEquals("Potion affects dexterity", AbilityName.CONSTITUTION,
            potion3.getAffectedAbilityList().get(0).getKey());

    assertEquals("Potion affects Constitution", AbilityName.CHARISMA,
            potion4.getAffectedAbilityList().get(0).getKey());
  }

  @Test
  public void testBeltAffectsUptoTwoAbilities() {
    /*for(int i = 0; i < 5000;i++) {
    randomGenerator = new RandomGeneratorTest(i);
    //Potion temp = new Potion(false, randomGenerator);
    if((new Belt(false, BeltSize.SMALL,randomGenerator)).getAffectedAbilityList().size() == 0){
      if((new Belt(false, BeltSize.MEDIUM,randomGenerator)).getAffectedAbilityList().size() == 1){
        if((new Belt(false, BeltSize.LARGE,randomGenerator)).getAffectedAbilityList().size() == 2){
            System.out.println(i);
          }
        }
      }
    }*/
    randomGenerator = new RandomGeneratorTest(60);
    Gear betl1 = new Belt(BELT_NAME, BELT_LIMIT, MAX_BELT_EFFECT,
            false, BeltSize.SMALL, GearType.BELT, randomGenerator);
    Gear betl2 = new Belt(BELT_NAME, BELT_LIMIT, MAX_BELT_EFFECT,
            true, BeltSize.MEDIUM, GearType.BELT, randomGenerator);
    Gear betl3 = new Belt(BELT_NAME, BELT_LIMIT, MAX_BELT_EFFECT,
            true, BeltSize.LARGE, GearType.BELT, randomGenerator);
    assertNotNull(betl1.getAffectedAbilityList());
    assertNotNull(betl2.getAffectedAbilityList());
    assertEquals("Belt affects strength", 0,
            betl1.getAffectedAbilityList().size());
    assertEquals("Belt affects strength", 1,
            betl2.getAffectedAbilityList().size());
    assertEquals("Belt affects strength", 2,
            betl3.getAffectedAbilityList().size());
  }

  @Test
  public void testGetLimit() {
    assertEquals("Headgear limit ", 1, headgearNegative.getLimit());
    assertEquals("Potion limit ", Integer.MAX_VALUE, potionNegative.getLimit());
    assertEquals("Belt limit ", 10, beltPositive.getLimit());
    assertEquals("Footwear limit ", 1, footwearNegative.getLimit());
  }

  @Test
  public void testGetSizeForDifferentTypesOfGears() {
    assertEquals("Size of headgear", 1, headgearPositive.getSize());
    assertEquals("Size of potion", 1, potionNegative.getSize());
    assertEquals("Size of small belt", 1, new Belt(BELT_NAME, BELT_LIMIT, MAX_BELT_EFFECT,
            false, BeltSize.SMALL, GearType.BELT, randomGenerator).getSize());
    assertEquals("Size of small belt", 3, new Belt(BELT_NAME, BELT_LIMIT, MAX_BELT_EFFECT,
            false, BeltSize.MEDIUM, GearType.BELT, randomGenerator).getSize());
    assertEquals("Size of small belt", 4, new Belt(BELT_NAME, BELT_LIMIT, MAX_BELT_EFFECT,
            false, BeltSize.LARGE, GearType.BELT, randomGenerator).getSize());
    assertEquals("Size of footwear", 1, footwearNegative.getSize());
  }

  @Test
  public void testGearSort() {
    Gear headGear1 = new Headgear("HEADGEAR_1", HEADGEAR_LIMIT,
            MAX_HEADGEAR_EFFECT, true, GearType.HEADGEAR, randomGenerator);
    Gear headGear2 = new Headgear("HEADGEAR_2", HEADGEAR_LIMIT,
            MAX_HEADGEAR_EFFECT, true, GearType.HEADGEAR, randomGenerator);
    Gear headGear3 = new Headgear("HEADGEAR_3", HEADGEAR_LIMIT,
            MAX_HEADGEAR_EFFECT, true, GearType.HEADGEAR, randomGenerator);
    Gear potion1 = new Potion("POTION_0", POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);
    Gear potion2 = new Potion("POTION_10", POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);
    Gear potion3 = new Potion("POTION_11", POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);
    Gear potion4 = new Potion("POTION_12", POTION_LIMIT,
            MAX_POTION_EFFECT, true, GearType.POTION, randomGenerator);
    Gear belt1 = new Belt("BELT_10", BELT_LIMIT, MAX_BELT_EFFECT,
            true, BeltSize.LARGE, GearType.BELT, randomGenerator);
    Gear belt2 = new Belt("BELT_11", BELT_LIMIT, MAX_BELT_EFFECT,
            true, BeltSize.LARGE, GearType.BELT, randomGenerator);
    Gear belt3 = new Belt("BELT_3", BELT_LIMIT, MAX_BELT_EFFECT,
            true, BeltSize.LARGE, GearType.BELT, randomGenerator);
    Gear belt4 = new Belt("BELT_4", BELT_LIMIT, MAX_BELT_EFFECT,
            true, BeltSize.MEDIUM, GearType.BELT, randomGenerator);
    Gear footwear1 = new Footwear("FOOTWEAR_3", FOOTWEAR_LIMIT,
            MAX_FOOTWEAR_EFFECT, true, GearType.FOOTWEAR, randomGenerator);
    Gear footwear2 = new Footwear("FOOTWEAR_33", FOOTWEAR_LIMIT,
            MAX_FOOTWEAR_EFFECT, true, GearType.FOOTWEAR, randomGenerator);
    Gear footwear3 = new Footwear("FOOTWEAR_7", FOOTWEAR_LIMIT,
            MAX_FOOTWEAR_EFFECT, true, GearType.FOOTWEAR, randomGenerator);


    List<Gear> gearList = new ArrayList<>();

    gearList.add(footwear1);
    gearList.add(headGear3);
    gearList.add(belt4);
    gearList.add(headGear2);
    gearList.add(potion2);
    gearList.add(footwear3);
    gearList.add(potion3);
    gearList.add(headGear1);
    gearList.add(potion4);
    gearList.add(belt3);
    gearList.add(belt1);
    gearList.add(footwear2);
    gearList.add(potion1);
    gearList.add(belt2);

    Gear[] gearArr = gearList.toArray(new Gear[0]);

    Arrays.sort(gearArr);

    List<Gear> expectedGearList = new ArrayList();
    expectedGearList.add(headGear1);
    expectedGearList.add(headGear2);
    expectedGearList.add(headGear3);
    expectedGearList.add(potion1);
    expectedGearList.add(potion2);
    expectedGearList.add(potion3);
    expectedGearList.add(potion4);
    expectedGearList.add(belt1);
    expectedGearList.add(belt2);
    expectedGearList.add(belt3);
    expectedGearList.add(belt4);
    expectedGearList.add(footwear1);
    expectedGearList.add(footwear2);
    expectedGearList.add(footwear3);

    Gear[] expectedGearListArr = expectedGearList.toArray(new Gear[0]);

    assertArrayEquals(expectedGearListArr, gearArr);
  }

  @Test
  public void testGetType() {
    assertEquals("Headgear type ", GearType.HEADGEAR, headgearPositive.getType());
    assertEquals("Potion type ", GearType.POTION, potionNegative.getType());
    assertEquals("Belt type ", GearType.BELT, beltPositive.getType());
    assertEquals("Footwear type ", GearType.FOOTWEAR, footwearNegative.getType());
  }

  @Test
  public void testGetName() {
    assertEquals("Headgear name ", "Helmet1",
            new Headgear("Helmet1", HEADGEAR_LIMIT, MAX_HEADGEAR_EFFECT, false,
                    GearType.HEADGEAR, randomGenerator).getName());

    assertEquals("Potion name ", "Honey",
            new Potion("Honey", POTION_LIMIT,
                    MAX_POTION_EFFECT, false, GearType.POTION, randomGenerator).getName());
    assertEquals("Belt name ", "BlackBelt",
            new Belt("BlackBelt", BELT_LIMIT, MAX_BELT_EFFECT,
                    true, BeltSize.LARGE, GearType.BELT, randomGenerator).getName());
    assertEquals("Footwear name", "Nike",
            new Footwear("Nike", FOOTWEAR_LIMIT,
                    MAX_FOOTWEAR_EFFECT, true, GearType.FOOTWEAR, randomGenerator).getName());
  }

  @Test
  public void testGetRandomEffect() {
    assertEquals(1, headgearPositive.getRandomEffect());
    assertEquals(-2, potionNegative.getRandomEffect());
    assertEquals(4, beltPositive.getRandomEffect());
    assertEquals(-3, footwearNegative.getRandomEffect());

    assertTrue(headgearPositive.getRandomEffect() >= 1 &&
            headgearPositive.getRandomEffect() <= headgearPositive.getMaxGearEffect());

    assertTrue(potionNegative.getRandomEffect() <= -1 &&
            potionNegative.getRandomEffect() >= potionNegative.getMaxGearEffect() * -1);

    assertTrue(beltPositive.getRandomEffect() >= 1 &&
            beltPositive.getRandomEffect() <= beltPositive.getMaxGearEffect());

    assertTrue(footwearNegative.getRandomEffect() <= -1 &&
            footwearNegative.getRandomEffect() >= footwearNegative.getMaxGearEffect() * -1);
  }
}