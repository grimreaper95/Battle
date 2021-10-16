package battlegame.gear;

import battlegame.util.CustomPair;

import java.util.List;

/**
 * An interface that represents a gear that can be wielded by a player.
 */

public interface Gear extends Comparable<Gear> {
  /**
   * get name of the gear.
   *
   * @return name of the gear
   */
  String getName();

  /**
   * get limit of the gear.
   *
   * @return limit of the gear
   */
  int getLimit();

  /**
   * get random effect of the gear in the gear effect range.
   *
   * @return random effect of the gear
   */
  int getRandomEffect();

  /**
   * get max effect of the gear.
   *
   * @return max effect of the gear.
   */
  int getMaxGearEffect();

  /**
   * get list of abilites to be affacted by the gear along with the amount by which
   * each ability will be affected.
   *
   * @return name of the gear
   */
  List<CustomPair> getAffectedAbilityList();

  /**
   * get number of units each holding a gear represents.
   * A belt can represent multiple units. For other gears this method returns 1.
   *
   * @return number of units each holding a gear represents.
   */
  int getSize();

  /**
   * get type of a gear.
   *
   * @return type of a gear.
   */
  GearType getType();
}
