package battlegame.inventory;

import static battlegame.util.Constants.AXES_NAME;
import static battlegame.util.Constants.BROADSWORDS_NAME;
import static battlegame.util.Constants.EFFECTIVE_FLAIL_WEILD_DEXTERITY;
import static battlegame.util.Constants.EFFECTIVE_TWO_HANDED_SWORD_WEILD_STRENGTH;
import static battlegame.util.Constants.FLAILS_NAME;
import static battlegame.util.Constants.KATANAS_NAME;
import static battlegame.util.Constants.MAXIMUM_WEAPON_COUNT;
import static battlegame.util.Constants.MINIMUM_WEAPON_COUNT;
import static battlegame.util.Constants.TWOHANDEDSWORDS_NAME;

import battlegame.ability.AbilityName;
import battlegame.random.IRandom;
import battlegame.weapon.Axes;
import battlegame.weapon.BroadSwords;
import battlegame.weapon.Flails;
import battlegame.weapon.Katanas;
import battlegame.weapon.TwoHandedSwords;
import battlegame.weapon.Weapon;
import battlegame.weapon.WeaponType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class to represent the Weapon Armory which will contain all the weapons to choose from.
 */

public class Armory {
  List<Weapon> weaponList;
  IRandom randomGenerator;

  /**
   * Constructs an Armory object and fills it with weapons using the random number generator.
   * @param randomGenerator random number generator to generate randomness.
   */
  public Armory(IRandom randomGenerator) {
    weaponList = new ArrayList<>();
    if (randomGenerator == null) {
      throw new IllegalArgumentException("Random generator cannot be null");
    }
    this.randomGenerator = randomGenerator;
    //add 1 weapon of each type
    fillArmoryWithRandomWeapons();
  }

  private void fillArmoryWithRandomWeapons() {
    final int katanasCount = randomGenerator.getRandom(
            MINIMUM_WEAPON_COUNT, MAXIMUM_WEAPON_COUNT + 1);
    final int broadSwordsCount = randomGenerator.getRandom(
            MINIMUM_WEAPON_COUNT, MAXIMUM_WEAPON_COUNT + 1);
    final int twoHandedSwordsCount = randomGenerator.getRandom(
            MINIMUM_WEAPON_COUNT, MAXIMUM_WEAPON_COUNT + 1);
    final int axesCount = randomGenerator.getRandom(
            MINIMUM_WEAPON_COUNT, MAXIMUM_WEAPON_COUNT + 1);
    final int flailsCount = randomGenerator.getRandom(
            MINIMUM_WEAPON_COUNT, MAXIMUM_WEAPON_COUNT + 1);

    for (int i = 0; i < katanasCount; i++) {
      weaponList.add(new Katanas(KATANAS_NAME, 4, 6,
              2, AbilityName.STRENGTH, Integer.MIN_VALUE,
              WeaponType.KATANAS, randomGenerator));
    }

    for (int i = 0; i < broadSwordsCount; i++) {
      weaponList.add(new BroadSwords(BROADSWORDS_NAME, 6, 10, 1,
              AbilityName.ANY, Integer.MIN_VALUE, WeaponType.BROADSWORDS, randomGenerator));
    }

    for (int i = 0; i < twoHandedSwordsCount; i++) {
      weaponList.add(new TwoHandedSwords(TWOHANDEDSWORDS_NAME, 8, 12,
              1, AbilityName.STRENGTH, EFFECTIVE_TWO_HANDED_SWORD_WEILD_STRENGTH,
              WeaponType.TWOHANDEDSWORDS, randomGenerator));
    }

    for (int i = 0; i < axesCount; i++) {
      weaponList.add(new Axes(AXES_NAME, 6,
              10, 1,
              AbilityName.ANY, Integer.MIN_VALUE, WeaponType.AXES, randomGenerator));
    }

    for (int i = 0; i < flailsCount; i++) {
      weaponList.add(new Flails(FLAILS_NAME, 8, 12,
              1, AbilityName.DEXTERITY,
              EFFECTIVE_FLAIL_WEILD_DEXTERITY, WeaponType.FLAILS, randomGenerator));
    }

    Collections.shuffle(weaponList, randomGenerator.getRandomGenerator());
  }

  /**
   * Get a random weapon from armory.
   * @return a random weapon from armory.
   */
  public Weapon getWeaponFromArmory() {
    int getRandomWeaponIdx = randomGenerator.getRandom(0, weaponList.size());
    return weaponList.get(getRandomWeaponIdx);
  }

  /**
   * Remove weapon from armory.
   * @param weapon the weapon to be removed from the armory.
   */
  public void removeWeaponFromArmory(Weapon weapon) {
    weaponList.remove(weapon);
  }

  /**
   * Get the list of weapons present in the armory.
   * @return list of weapons present in the armory.
   */
  public List<Weapon> getWeaponList() {
    return new ArrayList<>(weaponList);
  }

  /**
   * Get the size of armory.
   * @return the size of armory.
   */
  public int getArmorySize() {
    return weaponList.size();
  }
}
