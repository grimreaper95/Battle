package battlegame.player;

import battlegame.ability.AbilityName;
import battlegame.gear.Gear;
import battlegame.inventory.Armory;
import battlegame.inventory.EquipmentBag;
import battlegame.weapon.Weapon;

import java.util.List;
import java.util.Map;

/**
 * An interface to represent a Player.
 */

public interface IPlayer {
  /**
   * Regain health of Player to sum of its abilities with gear effect.
   */
  void regainHealth();

  /**
   * decrease health of Player by the damage amount.
   * @param damage the amount of damage a Player got hurt.
   */
  void decreaseHealth(int damage);

  /**
   * get the health of a Player.
   * @return the health of a Player.
   */
  int getHealth();

  /**
   * equip all the random gears that are possible for the Player from the equipment bag.
   * @param equipmentBag the equipment bag to choose gears from.
   */
  void eqipAllGears(EquipmentBag equipmentBag);

  /**
   * equip all the random weapons that are possible for the Player from the weapon armory.
   * @param armory he weapon armory to choose weapons from.
   */
  void eqipWeapon(Armory armory);

  /**
   * Get potential striking damage of the Player.
   * @return the potential striking damage of the Player.
   */
  int getPotentialStrikingDamage();

  /**
   * Get the striking power of a Player.
   * @return the striking power of a Player.
   */
  int getStrikingPower();

  /**
   * Get the avoidance ability of a Player.
   * @return the avoidance ability of a Player.
   */
  int getAvoidanceAbility();

  /**
   * Get the actual damage a player can make given the opponent's constitution.
   * @param opponentConstitution the constitution of the opponent.
   * @return the actual damage caused by a Player.
   */
  int getActualDamage(int opponentConstitution);

  /**
   * Checks if player can successfully strike another Player given the opponent's avoidance.
   * @param opponentAvoidance the avoidance of the opponent.
   * @return true if the player can strike the opponent, false otherwise.
   */
  boolean canStrike(int opponentAvoidance);

  /**
   * The ability map of the Player. It has the value of each ability of a Player.
   * @return ability map of the Player.
   */
  Map<AbilityName, Integer> getAbilities();

  /**
   * get the list of gears with the Player.
   * @return the list of gears with the Player.
   */
  List<Gear> getGearList();

  /**
   * get the list of weapons with the Player.
   * @return the list of weapons with the Player.
   */
  List<Weapon> getWeaponList();

  /**
   * Get the name of the Player.
   * @return the name of the Player.
   */
  String getName();


  /**
   * Get the complete description of the Player.
   */
  String getDescription();
}
