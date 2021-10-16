import org.junit.Before;
import org.junit.Test;

import battlegame.battle.Battle;
import battlegame.battle.IBattle;
import battlegame.player.IPlayer;
import battlegame.player.Player;
import battlegame.random.IRandom;

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
    battle = new Battle(player1, player2);
  }

  @Test
  public void testStartBattleIfPossible() {
    assertTrue(battle.startBattle());
  }

  @Test
  public void testIsBattleOn() {
    assertFalse(battle.isBattleOn());
    assertTrue(battle.startBattle());
    assertTrue(battle.isBattleOn());
  }

  @Test (expected = IllegalStateException.class)
  public void testStartBattleWhenBattleAlreadyGoingOn() {
    assertTrue(battle.startBattle());
    assertTrue(battle.startBattle());
  }

  @Test (expected = IllegalStateException.class)
  public void testGetWinnerNameBeforeBattleBegins() {
    battle.getWinnerName();
  }

  @Test (expected = IllegalStateException.class)
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
  public void playerOneWins() {
    battle.startBattle();
    while (battle.isBattleOn()) {
      battle.move();
    }
    //assertEquals(battle.getWinnerName());
  }
}
