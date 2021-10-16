import org.junit.Before;
import org.junit.Test;

import battlegame.ability.AbilityName;
import battlegame.random.IRandom;
import battlegame.weapon.Axes;
import battlegame.weapon.BroadSwords;
import battlegame.weapon.Flails;
import battlegame.weapon.Katanas;
import battlegame.weapon.TwoHandedSwords;
import battlegame.weapon.Weapon;
import battlegame.weapon.WeaponType;

import static battlegame.util.Constants.AXES_NAME;
import static battlegame.util.Constants.BROADSWORDS_NAME;
import static battlegame.util.Constants.EFFECTIVE_FLAIL_WEILD_DEXTERITY;
import static battlegame.util.Constants.EFFECTIVE_TWO_HANDED_SWORD_WEILD_STRENGTH;
import static battlegame.util.Constants.FLAILS_NAME;
import static battlegame.util.Constants.KATANAS_NAME;
import static battlegame.util.Constants.TWOHANDEDSWORDS_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test class to test Weapon.
 */

public class WeaponTest {
  private Weapon axes;
  private Weapon broadSwords;
  private Weapon katanas;
  private Weapon twoHandedSwords;
  private Weapon flails;
  private IRandom randomGenerator;

  @Before
  public void setUp() {
    randomGenerator = new RandomGeneratorTest(5);
    axes = new Axes(AXES_NAME, 6, 10, 1,
            AbilityName.ANY, Integer.MIN_VALUE, WeaponType.AXES, randomGenerator);

    broadSwords = new BroadSwords(BROADSWORDS_NAME, 6, 10, 1,
            AbilityName.ANY, Integer.MIN_VALUE, WeaponType.BROADSWORDS, randomGenerator);

    katanas = new Katanas(KATANAS_NAME, 4, 6,
            2, AbilityName.ANY, Integer.MIN_VALUE,
            WeaponType.KATANAS, randomGenerator);

    twoHandedSwords = new TwoHandedSwords(TWOHANDEDSWORDS_NAME, 8, 12,
            1, AbilityName.STRENGTH, EFFECTIVE_TWO_HANDED_SWORD_WEILD_STRENGTH,
            WeaponType.TWOHANDEDSWORDS, randomGenerator);


    flails = new Flails(FLAILS_NAME, 8, 12,
            1, AbilityName.DEXTERITY,
            EFFECTIVE_FLAIL_WEILD_DEXTERITY, WeaponType.FLAILS, randomGenerator);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    new Flails(null, 8, 12,
            1, AbilityName.DEXTERITY,
            EFFECTIVE_FLAIL_WEILD_DEXTERITY, WeaponType.FLAILS, randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    new Flails("", 8, 12,
            1, AbilityName.DEXTERITY,
            EFFECTIVE_FLAIL_WEILD_DEXTERITY, WeaponType.FLAILS, randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testWeaponCarryLimiNegative() {
    new TwoHandedSwords(TWOHANDEDSWORDS_NAME, 8, 12,
            -3, AbilityName.STRENGTH, EFFECTIVE_TWO_HANDED_SWORD_WEILD_STRENGTH,
            WeaponType.TWOHANDEDSWORDS, randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEffectiveWieldAbilityNull() {
    new Katanas(KATANAS_NAME, 4, 6,
            2, null, Integer.MIN_VALUE,
            WeaponType.KATANAS, randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRandomGeneratorNull() {
    new Katanas(KATANAS_NAME, 4, 6,
            2, AbilityName.DEXTERITY, Integer.MIN_VALUE,
            WeaponType.KATANAS, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEffectiveWeaponTypeNull() {
    new Katanas(KATANAS_NAME, 4, 6,
            2, AbilityName.STRENGTH, Integer.MIN_VALUE,
            null, randomGenerator);
  }

  @Test
  public void testGetType() {
    assertEquals("Axes type", WeaponType.AXES, axes.getType());
    assertEquals("Flails type", WeaponType.FLAILS, flails.getType());
    assertEquals("TWOHANDEDSWORDS type", WeaponType.TWOHANDEDSWORDS, twoHandedSwords.getType());
    assertEquals("Katanas type", WeaponType.KATANAS, katanas.getType());
    assertEquals("Broadswords type", WeaponType.BROADSWORDS, broadSwords.getType());
  }

  @Test
  public void testToString() {
    assertEquals("AXES", axes.toString());
    assertEquals("FLAILS", flails.toString());
    assertEquals("TWOHANDEDSWORDS", twoHandedSwords.toString());
    assertEquals("KATANAS", katanas.toString());
    assertEquals("BROADSWORDS", broadSwords.toString());
  }

  @Test
  public void testGetCarryLimit() {
    assertEquals("Axes carry limit", 1, axes.getCarryLimit());
    assertEquals("Broadswords carry limit", 1, broadSwords.getCarryLimit());
    assertEquals("Flails carry limit", 1, flails.getCarryLimit());
    assertEquals("Katanas carry limit", 2, katanas.getCarryLimit());
    assertEquals("Two Handed Swords carry limit", 1, twoHandedSwords.getCarryLimit());
  }

  @Test
  public void testGetEffectiveWieldAbilityName() {
    assertEquals("Two Handed Swords Wield Ability Name",
            AbilityName.STRENGTH, twoHandedSwords.getEffectiveWieldAbilityName());
    assertEquals("Falils Wield Ability Name",
            AbilityName.DEXTERITY, flails.getEffectiveWieldAbilityName());
  }

  @Test
  public void testGetEffectiveWieldAbilityValue() {
    assertEquals("Two Handed Swords Effective Wield Ability Value",
            14, twoHandedSwords.getEffectiveWieldAbilityValue());
    ;
    assertEquals("Falils Effective Wield Ability Value", 14,
            flails.getEffectiveWieldAbilityValue());
    ;
  }

  @Test
  public void testKatanasMaximumDamageRange() {
    int randomDamage = katanas.getRandomDamage(10);
    assertTrue("Katana damage should be between 4 and 6",
            randomDamage >= 4 && randomDamage <= 6);
    randomDamage = katanas.getRandomDamage(-10);
    assertTrue("Katana damage should be between 4 and 6",
            randomDamage >= 4 && randomDamage <= 6);
  }


  @Test
  public void testBroadSwordsMaximumDamageRange() {
    int randomDamage = broadSwords.getRandomDamage(10);
    assertTrue("BroadSwords damage should be between 6 and 10",
            randomDamage >= 6 && randomDamage <= 10);
  }


  @Test
  public void testTwoHandedSwordsDamageWithLessEffectiveAbilityValue() {
    int randomDamage = twoHandedSwords.getRandomDamage(13);
    assertTrue("Two Handed Swords damage should be between 4 and 6",
            randomDamage >= 4 && randomDamage <= 6);
  }

  @Test
  public void testTwoHandedSwordsMaximumDamageRange() {
    int randomDamage = twoHandedSwords.getRandomDamage(15);
    assertTrue("Two Handed Swords damage should be between 4 and 6",
            randomDamage >= 8 && randomDamage <= 12);
  }

  @Test
  public void testAxesMaximumDamageRange() {
    int randomDamage = axes.getRandomDamage(10);
    assertTrue("Axes damage should be between 6 and 10",
            randomDamage >= 6 && randomDamage <= 10);
  }

  @Test
  public void testFalilsMaximumDamageRange() {
    int randomDamage = flails.getRandomDamage(15);
    assertTrue("Katana damage should be between 4 and 6",
            randomDamage >= 8 && randomDamage <= 12);
  }

  @Test
  public void testFlailsDamageWithLessEffectiveAbilityValue() {
    int randomDamage = twoHandedSwords.getRandomDamage(10);
    assertTrue("Two Handed Swords damage should be between 4 and 6",
            randomDamage >= 4 && randomDamage <= 6);
  }
}
