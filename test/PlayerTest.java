import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import battlegame.inventory.Armory;
import battlegame.inventory.EquipmentBag;
import battlegame.ability.AbilityName;
import battlegame.gear.Gear;
import battlegame.gear.GearType;
import battlegame.player.IPlayer;
import battlegame.player.Player;
import battlegame.random.IRandom;
import battlegame.weapon.Weapon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class to test Player.
 */

public class PlayerTest {
  private IPlayer player;
  private IRandom randomGenerator;
  private EquipmentBag equipmentBag;
  private Armory armory;

  @Before
  public void setUp() {
    randomGenerator = new RandomGeneratorTest(5);
    equipmentBag = new EquipmentBag(randomGenerator);
    armory = new Armory(randomGenerator);
    player = new Player("Alex", randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRandomGeneratorNull() {
    new Player("Joey", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullName() {
    new Player(null, randomGenerator);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyName() {
    new Player("", randomGenerator);
  }

  @Test
  public void testNewPlayerHasNoGear() {
    assertEquals("Gear with new player", 0, player.getGearList().size());
  }

  @Test
  public void testNewPlayerHasNoWeapons() {
    assertEquals("Weapons with new player", 0, player.getWeaponList().size());
  }

  @Test
  public void testGetAbilitiesBeforeGearEquip() {
    Map<AbilityName, Integer> abilities = player.getAbilities();
    assertEquals(15, abilities.get(AbilityName.STRENGTH).intValue());
    assertEquals(14, abilities.get(AbilityName.CONSTITUTION).intValue());
    assertEquals(12, abilities.get(AbilityName.DEXTERITY).intValue());
    assertEquals(14, abilities.get(AbilityName.CHARISMA).intValue());
  }

  @Test
  public void testGetAbilitiesAfterGearEquip() {
    player.eqipAllGears(equipmentBag);
    Map<AbilityName, Integer> abilities = player.getAbilities();
    assertEquals(17, abilities.get(AbilityName.STRENGTH).intValue());
    assertEquals(18, abilities.get(AbilityName.CONSTITUTION).intValue());
    assertEquals(16, abilities.get(AbilityName.DEXTERITY).intValue());
    assertEquals(21, abilities.get(AbilityName.CHARISMA).intValue());
  }

  @Test
  public void testEquipWeapon() {
    assertEquals(0, player.getWeaponList().size());
    player.eqipWeapon(armory);
    assertEquals(1, player.getWeaponList().size());
    assertEquals("AXES", player.getWeaponList().get(0).getName());
  }

  @Test
  public void testHealthOfNewPlayer() {
    Map<AbilityName, Integer> abilities = player.getAbilities();
    int abilitySum = 0;
    for (Integer abilityVal : abilities.values())
      abilitySum += abilityVal;
    assertEquals("Health of new player", abilitySum, player.getHealth());
    assertEquals("Value of health of player "
            + "with random seed", 55, player.getHealth());
  }


  @Test
  public void testHealthUpdatedAfterGearEquip() {
    player.eqipAllGears(equipmentBag);
    Map<AbilityName, Integer> abilitiesAfter = player.getAbilities();
    int newAbilitySum = 0;
    for (Integer abilityVal : abilitiesAfter.values())
      newAbilitySum += abilityVal;
    assertEquals(newAbilitySum, player.getHealth());
  }


  @Test
  public void testPlayerCanHaveOneHeadGearAfterGearEquip() {
    player.eqipAllGears(equipmentBag);
    List<Gear> gearList = player.getGearList();
    int cnt = 0;
    for (Gear gear : gearList) {
      if (gear.getType() == GearType.HEADGEAR) {
        cnt++;
      }
    }
    assertEquals("Player has 1 headgear", 1, cnt);
  }

  @Test
  public void testPlayerCanHaveOneFootwearAfterGearEquip() {
    player.eqipAllGears(equipmentBag);
    List<Gear> gearList = player.getGearList();
    int cnt = 0;
    for (Gear gear : gearList) {
      if (gear.getType() == GearType.FOOTWEAR) {
        cnt++;
      }
    }
    assertTrue("Player has 1 footwear atmost", (cnt <= 1));
    assertEquals(cnt, 1);
  }

  @Test
  public void testPlayerCanHaveTenUnitsOfBeltAfterGearEquip() {
    player.eqipAllGears(equipmentBag);
    List<Gear> gearList = player.getGearList();
    int cnt = 0;
    for (Gear gear : gearList) {
      if (gear.getType() == GearType.BELT) {
        cnt += gear.getSize();
      }
    }
    assertTrue("PPlayer has 10 units of Belt atmost", (cnt <= 10));
    assertEquals(10, cnt);
  }

  @Test
  public void testMaxWeaponCountIfNotKatana() {
    player.eqipAllGears(equipmentBag);
    player.eqipWeapon(armory);
    assertEquals("Player current weapon list size", 1, player.getWeaponList().size());
    assertEquals("Player current weapon", "BROADSWORDS", player.getWeaponList().get(0).getName());
  }

  @Test
  public void testMaxWeaponCountIfFirstKatanaSecondNonKatana() {
    IRandom generator = new RandomGeneratorTest(33);
    IPlayer testPlayer = new Player("testPlayer", generator);
    testPlayer.eqipAllGears(new EquipmentBag(generator));
    testPlayer.eqipWeapon(new Armory(generator));
    assertEquals("Player current weapon list size", 1, testPlayer.getWeaponList().size());
    assertEquals("Player current weapon", "KATANAS", testPlayer.getWeaponList().get(0).getName());
  }

  @Test
  public void testMaxWeaponCountIfFirstKatanaSecondKatana() {
    IRandom generator = new RandomGeneratorTest(36);
    IPlayer testPlayer = new Player("testPlayer", generator);
    testPlayer.eqipWeapon(new Armory(generator));
    assertEquals("Player current weapon list size", 2, testPlayer.getWeaponList().size());
    assertEquals("Player current weapon", "KATANAS", testPlayer.getWeaponList().get(0).getName());
  }

  @Test
  public void testToStringWithSingleWeapon() {
    player.eqipAllGears(equipmentBag);
    player.eqipWeapon(armory);
    String expectedDescription = "Player Name: Alex\n"
            +
            "Player ability values are: [Strength: 17,Constitution: 18,Dexterity: 16,Charisma: 21]\n"
            +
            "Player is wearing following gears: \n"
            +
            "[HEADGEAR_1,\n"
            +
            "POTION_13,\n"
            +
            "POTION_14,\n"
            +
            "POTION_15,\n"
            +
            "POTION_3,\n"
            +
            "POTION_4,\n"
            +
            "POTION_9,\n"
            +
            "BELT_1,\n"
            +
            "BELT_12,\n"
            +
            "BELT_13,\n"
            +
            "BELT_16,\n"
            +
            "FOOTWEAR_0]\n"
            +
            "Player is using the following weapons: \n"
            +
            "[BROADSWORDS]";
    String actualDescription = player.toString();
    assertEquals(expectedDescription, actualDescription);
  }

  @Test
  public void testGetDescriptionWithMultipleWeapons() {
    IRandom generator = new RandomGeneratorTest(36);
    IPlayer testPlayer = new Player("testPlayer", generator);
    testPlayer.eqipWeapon(new Armory(generator));

    String expectedDescription = "Player Name: testPlayer\n"
            + "Player ability values are: [Strength: 12,Constitution: 16,Dexterity: 13,Charisma: 15]\n" +
            "Player is wearing following gears: \n"
            + "[]\n" +
            "Player is using the following weapons: \n"
            + "[KATANAS,KATANAS]";
    String actualDescription = testPlayer.getDescription();
    assertEquals(expectedDescription, actualDescription);
  }


  @Test(expected = IllegalStateException.class)
  public void testEquipWeaponMultipleTimes() {
    player.eqipWeapon(armory);
    player.eqipWeapon(armory);
  }

  @Test(expected = IllegalStateException.class)
  public void testEquipGearMultipleTimes() {
    player.eqipAllGears(equipmentBag);
    player.eqipAllGears(equipmentBag);
  }

  @Test
  public void testGetStrikingPower() {
    assertTrue(player.getStrikingPower() >= player.getAbilities().get(AbilityName.STRENGTH) + 1);
    assertTrue(player.getStrikingPower() <= player.getAbilities().get(AbilityName.STRENGTH) + 10);
    assertEquals(17, player.getStrikingPower());
  }

  @Test
  public void testAvoidanceAbility() {
    assertTrue(player.getAvoidanceAbility()
            >= player.getAbilities().get(AbilityName.DEXTERITY) + 1);
    assertTrue(player.getAvoidanceAbility()
            >= player.getAbilities().get(AbilityName.DEXTERITY) + 6);
    assertEquals(18, player.getAvoidanceAbility());
  }

  @Test
  public void testGetPotentialStrikingDamagePlayerAbilityMoreThanEffectiveAbility() {
    player.eqipAllGears(equipmentBag);
    player.eqipWeapon(armory);
    assertEquals(1, player.getWeaponList().size());
    assertEquals("BROADSWORDS", player.getWeaponList().get(0).getName());
    List<Weapon> weaponList = player.getWeaponList();
    int weaponDamage = 0;
    for (Weapon weapon : weaponList) {
      weaponDamage += weapon.getMaximumDamage();
    }

    assertTrue(player.getPotentialStrikingDamage()
            <= player.getAbilities().get(AbilityName.STRENGTH) + weaponDamage);

    assertEquals(25, player.getPotentialStrikingDamage());
  }


  @Test
  public void testGetPotentialStrikingDamage() {
    player.eqipAllGears(equipmentBag);
    player.eqipWeapon(armory);
    List<Weapon> weaponList = player.getWeaponList();
    int maxWeaponDamage = 0;
    for (Weapon weapon : weaponList) {
      //if weapon is present, potential damage has to be <= than sum of strength
      // and maximum damage made by weapon
      maxWeaponDamage += weapon.getMaximumDamage();
    }
    assertTrue(player.getPotentialStrikingDamage()
            > player.getAbilities().get(AbilityName.STRENGTH));
    assertTrue(player.getPotentialStrikingDamage()
            <= player.getAbilities().get(AbilityName.STRENGTH) + maxWeaponDamage);
    assertEquals(24, player.getPotentialStrikingDamage());
  }


  @Test
  public void testGetActualDamageGreaterThanZero() {
    final int testOpponentDamage1 = 5;
    final int testOpponentDamage2 = 25;
    player.eqipAllGears(equipmentBag);
    player.eqipWeapon(armory);
    assertTrue(player.getActualDamage(testOpponentDamage1) > 0);
    assertEquals(0, player.getActualDamage(testOpponentDamage2));
  }

  @Test
  public void testGetActualDamageLessThanZero() {
    int testOpponentDamage = 100;
    assertTrue(player.getActualDamage(testOpponentDamage) < 0);
  }

  @Test
  public void testDecreaseHealth() {
    player.eqipAllGears(equipmentBag);
    player.eqipWeapon(armory);
    assertEquals(72, player.getHealth());
    player.decreaseHealth(10);
    assertEquals(62, player.getHealth());
  }

  @Test
  public void testRegainHealth() {
    player.eqipAllGears(equipmentBag);
    player.eqipWeapon(armory);
    assertEquals(72, player.getHealth());
    player.decreaseHealth(10);
    assertEquals(62, player.getHealth());
    player.regainHealth();
    assertEquals(72, player.getHealth());
  }

  @Test
  public void testGetName() {
    assertEquals("Player name: ", "Alex", player.getName());
  }

}
