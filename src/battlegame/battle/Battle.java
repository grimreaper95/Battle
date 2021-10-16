package battlegame.battle;

import static battlegame.util.Constants.MAX_BATTLE_MOVES;

import battlegame.ability.AbilityName;
import battlegame.player.IPlayer;
import battlegame.player.Player;
import battlegame.random.IRandom;
import battlegame.random.RandomGenerator;

/**
 * A class to represent a Battle.
 */
public class Battle implements IBattle {

  private IPlayer playerToMoveNow;
  private IPlayer playerToMoveNext;
  private IPlayer winner;
  private final IPlayer dummyPlayerDraw;
  private boolean battleOn;
  private int battleMoves;
  private IRandom randomGenerator;

  /**
   * Constructs a Battle object.
   *
   * @param player1 player 1 to play the battle.
   * @param player2 player 2 to play the battle.
   */

  public Battle(IPlayer player1, IPlayer player2) {
    if (player1 == null) {
      throw new IllegalArgumentException("Player 1 cannot be null");
    }
    if (player2 == null) {
      throw new IllegalArgumentException("Player 2 cannot be null");
    }
    battleOn = false;
    battleMoves = 0;
    playerToMoveNow = player1;
    playerToMoveNext = player2;
    randomGenerator = new RandomGenerator();
    dummyPlayerDraw = new Player("No player", randomGenerator);
    winner = player1;
  }

  @Override
  public void move() {
    battleMoves++;
    if (battleMoves >= MAX_BATTLE_MOVES) {
      battleOn = false;
      winner = dummyPlayerDraw;
    }
    if (playerToMoveNow.canStrike(playerToMoveNext.getAvoidanceAbility())) {
      int actualDamage = getActualDamage();
      if (actualDamage > 0) {
        playerToMoveNext.decreaseHealth(actualDamage);
        if (playerToMoveNext.getHealth() <= 0) {
          winner = playerToMoveNow;
          battleOn = false;
        }
      }
    }
    IPlayer temp = playerToMoveNow;
    playerToMoveNow = playerToMoveNext;
    playerToMoveNext = temp;
  }

  private int getActualDamage() {
    return playerToMoveNow.getActualDamage(
            playerToMoveNext.getAbilities().get(AbilityName.CONSTITUTION));
  }

  @Override
  public String getPlayerToMoveFirstName() {
    if (playerToMoveNow.getAbilities().get(AbilityName.CHARISMA)
            < playerToMoveNext.getAbilities().get(AbilityName.CHARISMA)) {
      IPlayer temp = playerToMoveNow;
      playerToMoveNow = playerToMoveNext;
      playerToMoveNext = temp;
    }
    return playerToMoveNow.getName();
  }

  @Override
  public boolean startBattle() {
    if (this.battleOn) {
      throw new IllegalStateException("Battle is already going on!");
    }
    battleMoves = 0;
    this.battleOn = true;
    return true;
  }

  @Override
  public String getCurrentPlayerToMoveName() {
    return playerToMoveNow.getName();
  }

  @Override
  public String getWinnerName() {
    if (isBattleOn()) {
      throw new IllegalStateException("Winner not decided yet. Battle is on!");
    }
    if (battleMoves < MAX_BATTLE_MOVES) {
      if (playerToMoveNow.getHealth() > 0 && playerToMoveNext.getHealth() > 0) {
        throw new IllegalStateException("No winner yet. Both players have positive health!");
      }
    }
    battleOn = false;
    return winner.getName();
  }

  @Override
  public void rematch() {
    playerToMoveNow.regainHealth();
    playerToMoveNext.regainHealth();
  }

  @Override
  public boolean isBattleOn() {
    return this.battleOn;
  }
}
