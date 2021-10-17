import org.junit.Before;
import org.junit.Test;

import battlegame.ability.AbilityName;
import battlegame.battle.Battle;
import battlegame.battle.IBattle;
import battlegame.inventory.Armory;
import battlegame.inventory.EquipmentBag;
import battlegame.player.IPlayer;
import battlegame.player.Player;
import battlegame.random.IRandom;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Test class to test Battle.
 */
public class BattleTest {
  private IRandom randomGenerator;
  private IPlayer player1;
  private IPlayer player2;
  private IBattle battle;
  private EquipmentBag equipmentBag;
  private Armory armory;

  // player 1 wins
  // player 2 wins
  // match draw
  // player strikes
  // player fails to strike
  // get player to move first
  // get winner
  // regainHealth
  // isBattleOn
  // player enters arena bare handed
  // battle executes correctly - turn by turn
  // health update correctly after damage >= 0
  // health update correctly after damage < 0


  @Before
  public void setUp() {
    randomGenerator = new RandomGeneratorTest(5);
    player1 = new Player("Superman", randomGenerator);
    player2 = new Player("Batman", randomGenerator);
    equipmentBag = new EquipmentBag(randomGenerator);
    armory = new Armory(randomGenerator);
    player1.eqipAllGears(equipmentBag);
    player2.eqipAllGears(equipmentBag);

    player1.eqipWeapon(armory);
    player2.eqipWeapon(armory);

    battle = new Battle(player1, player2);
  }

  @Test
  public void testStartBattleIfPossible() {
    assertTrue(battle.startBattle());
  }


  @Test(expected = IllegalStateException.class)
  public void testStartBattleWhenBattleAlreadyGoingOn() {
    assertTrue(battle.startBattle());
    assertTrue(battle.startBattle());
  }

  @Test
  public void testIsBattleOn() {
    assertFalse(battle.isBattleOn());
    assertTrue(battle.startBattle());
    assertTrue(battle.isBattleOn());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWinnerNameBeforeBattleBegins() {
    battle.getWinnerName();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetWinnerNameWhenThereIsNoWinner() {
    assertTrue(battle.startBattle());
    battle.move();
    battle.move();
    battle.move();
    assertTrue(player1.getHealth() > 0);
    assertTrue(player2.getHealth() > 0);
    battle.getWinnerName();
  }

  @Test
  public void testGetPlayerToMoveFirstName() {
    assertEquals(23, player1.getAbilities().get(AbilityName.CHARISMA).intValue());
    assertEquals(18, player2.getAbilities().get(AbilityName.CHARISMA).intValue());
    assertEquals(player1.getName(), battle.getPlayerToMoveFirstName());
  }


  @Test
  public void testGetCurrentPlayerToMoveName() {
    assertEquals(23, player1.getAbilities().get(AbilityName.CHARISMA).intValue());
    assertEquals(18, player2.getAbilities().get(AbilityName.CHARISMA).intValue());
    assertEquals(player1.getName(), battle.getPlayerToMoveFirstName());

    battle.startBattle();
    assertEquals(player1.getName(), battle.getCurrentPlayerToMoveName());
    battle.move();
    assertEquals(player2.getName(), battle.getCurrentPlayerToMoveName());
    battle.move();
    assertEquals(player1.getName(), battle.getCurrentPlayerToMoveName());
    battle.move();
    assertEquals(player2.getName(), battle.getCurrentPlayerToMoveName());
    battle.move();
    assertEquals(player1.getName(), battle.getCurrentPlayerToMoveName());
  }


  @Test
  public void testGetWinnerNamePlayer1() {
    IRandom generator = new RandomGeneratorTest(13);
    player1 = new Player("Player 1", generator);
    player2 = new Player("Player 2", generator);
    IBattle battleTest = new Battle(player1, player2);
    assertTrue(battleTest.startBattle());
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    battleTest.move();
    assertEquals("Player 1", battleTest.getWinnerName());
  }

  @Test
  public void testGetWinnerNamePlayer2() {
    IRandom generator = new RandomGeneratorTest(15);
    player1 = new Player("Player 1", generator);
    player2 = new Player("Player 2", generator);
    IBattle battleTest = new Battle(player1, player2);
    assertTrue(battleTest.startBattle());
    while (battleTest.isBattleOn()) {
      battleTest.move();
    }
    assertEquals("Player 2", battleTest.getWinnerName());
  }

  @Test
  public void testGetWinnerDraw() {
    IRandom generator = new RandomGeneratorTest(12);
    player1 = new Player("Player 1", generator);
    player2 = new Player("Player 2", generator);
    IBattle battleTest = new Battle(player1, player2);
    assertTrue(battleTest.startBattle());
    while (battleTest.isBattleOn()) {
      battleTest.move();
    }
    assertEquals("No player", battleTest.getWinnerName());
  }


  @Test
  public void testPlayerEnterBattleWithNoGearsAndNoWeapons() {
    IRandom generator = new RandomGeneratorTest(12);
    player1 = new Player("Player 1", generator);
    player2 = new Player("Player 2", generator);
    IBattle battleTest = new Battle(player1, player2);
    assertEquals(0, player1.getWeaponList().size());
    assertEquals(0, player2.getWeaponList().size());

    assertEquals(0, player1.getGearList().size());
    assertEquals(0, player2.getGearList().size());
  }

  @Test
  public void testHealthWhenGetActualDamageLessThanEqualToZero() {
    IRandom generator = new RandomGeneratorTest(33);
    player1 = new Player("Player 1", generator);
    player2 = new Player("Player 2", generator);
    EquipmentBag equipmentBagTest = new EquipmentBag(generator);
    Armory armoryTest = new Armory(generator);
    player1.eqipAllGears(equipmentBagTest);
    player2.eqipAllGears(equipmentBagTest);

    player1.eqipWeapon(armoryTest);
    player2.eqipWeapon(armoryTest);
    IBattle battleTest = new Battle(player1, player2);
    assertTrue(battleTest.startBattle());
    while (battleTest.isBattleOn()) {
      //System.out.println(1);
      battleTest.move();
      if (battleTest.getActualDamage() == 0) {
        assertEquals(69, player1.getHealth());
        assertEquals(70, player2.getHealth());
        assertEquals(0, battleTest.getActualDamage());
        assertEquals(69, player1.getHealth());
        assertEquals(70, player2.getHealth());
      }
    }
  }


  @Test
  public void testHealthWhenGetActualDamageGreaterThanZero() {
    IRandom generator = new RandomGeneratorTest(1);
    player1 = new Player("Player 1", generator);
    player2 = new Player("Player 2", generator);
    EquipmentBag equipmentBagTest = new EquipmentBag(generator);
    Armory armoryTest = new Armory(generator);
    player1.eqipAllGears(equipmentBagTest);
    player2.eqipAllGears(equipmentBagTest);

    player1.eqipWeapon(armoryTest);
    player2.eqipWeapon(armoryTest);

    IBattle battleTest = new Battle(player1, player2);
    assertTrue(battleTest.startBattle());

    assertEquals(65, player1.getHealth());
    assertEquals(64, player2.getHealth());
    battleTest.move();
    assertTrue(battleTest.getActualDamage() > 0);
    assertEquals(10, battleTest.getActualDamage());
    assertEquals(65, player1.getHealth());
    assertEquals(54, player2.getHealth());
  }
}


