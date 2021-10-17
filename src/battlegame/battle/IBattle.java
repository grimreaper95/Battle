package battlegame.battle;

/**
* An interface that represents a Battle to be fought in the game.
*/
public interface IBattle {


  /**
   * A method to start a battle.
   */
  boolean startBattle();

  /**
   * A method to test battle status.
   * It returns true if the battle is On and false, otherwise.
   */
  boolean isBattleOn();

  /**
   * A method that allows to play rematch.
   */
  void rematch();

  /**
   * A method to make a move in the battle.
   */
  void move();

  /**
   * Get the name of the winner of the battle.
   * @return the name of the winner of the battle.
   */
  String getWinnerName();

  /**
   * Get the name of the current player who is to move.
   * @return name of the current player who is to move.
   */
  String getCurrentPlayerToMoveName();

  /**
  * Get the name of the player who is going to move first.
  * @return the name of the player who is going to move first.
  */
  String getPlayerToMoveFirstName();

  /**
   * Get the actual damage done in a turn.
   * @return the actual damage done in a turn.
   */
  int getActualDamage();

  /**
   * Get if current player to move can strike in this move.
   * @return true if player strikes in this move, else returns false.
   */
  boolean canPlayerStrike();
}
