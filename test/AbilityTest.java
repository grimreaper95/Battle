import org.junit.Before;
import org.junit.Test;

import battlegame.ability.Ability;
import battlegame.ability.Charisma;
import battlegame.ability.Constitution;
import battlegame.ability.Dexterity;
import battlegame.ability.Strength;
import battlegame.random.IRandom;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class to test Ability.
 */

public class AbilityTest {
  private Ability charisma;
  private Ability strength;
  private Ability constitution;
  private Ability dexterity;
  IRandom randomGenerator;

  @Before
  public void setUp() {
    randomGenerator = new RandomGeneratorTest(44);
    charisma = new Charisma(randomGenerator);
    strength = new Strength(randomGenerator);
    constitution = new Constitution(randomGenerator);
    dexterity = new Dexterity(randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullRandomGenerator() {
    new Charisma(null);
  }

  @Test
  public void testCharismaRange() {
    assertTrue("Charisma should be in the range 6 to 18",
            (charisma.getValue() >= 6 && charisma.getValue() <= 18));
    assertEquals(14, charisma.getValue());
  }

  @Test
  public void testDexterityRange() {
    assertTrue("Charisma should be in the range 6 to 18",
            (dexterity.getValue() >= 6 && dexterity.getValue() <= 18));
    assertEquals(13, dexterity.getValue());
  }

  @Test
  public void testConstitutionRange() {
    assertTrue("Charisma should be in the range 6 to 18",
            (constitution.getValue() >= 6 && constitution.getValue() <= 18));
    assertEquals(13, constitution.getValue());
  }

  @Test
  public void testStrengthRange() {
    assertTrue("Charisma should be in the range 6 to 18",
            (strength.getValue() >= 6 && strength.getValue() <= 18));
    assertEquals(10, strength.getValue());
  }

  @Test
  public void testGetValue() {
    assertEquals(10, strength.getValue());
    assertEquals(13, constitution.getValue());
    assertEquals(14, charisma.getValue());
    assertEquals(13, dexterity.getValue());
  }
}
