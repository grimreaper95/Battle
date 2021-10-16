package battlegame.player;

import static battlegame.util.Constants.GEAR_EQUIP_COUNT;

import battlegame.ability.AbilityName;
import battlegame.ability.Charisma;
import battlegame.ability.Constitution;
import battlegame.ability.Dexterity;
import battlegame.ability.Strength;
import battlegame.gear.Gear;
import battlegame.gear.GearType;
import battlegame.inventory.Armory;
import battlegame.inventory.EquipmentBag;
import battlegame.random.IRandom;
import battlegame.util.CustomPair;
import battlegame.weapon.Weapon;
import battlegame.weapon.WeaponType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A class to represent a Player to play the Battle.
 */

public class Player implements IPlayer {
  private final String name;
  private final Map<AbilityName, Integer> abilities;
  private final List<Weapon> weaponList;
  private final List<Gear> gearList;
  private final Map<GearType, Integer> gearTypeCount;
  private final IRandom randomGenerator;
  private int health;

  /**
   * Constructs a Player object.
   * @param name the name of the Player.
   * @param randomGenerator random number generator to generate randomness.
   */
  public Player(String name, IRandom randomGenerator) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Name of player cannot be null or empty");
    }
    if (randomGenerator == null) {
      throw new IllegalArgumentException("Random generator cannot be null");
    }

    this.name = name;
    this.randomGenerator = randomGenerator;
    abilities = new EnumMap<>(AbilityName.class);
    gearTypeCount = new HashMap<>();
    abilities.put(AbilityName.STRENGTH, new Strength(this.randomGenerator).getValue());
    abilities.put(AbilityName.CHARISMA, new Charisma(this.randomGenerator).getValue());
    abilities.put(AbilityName.CONSTITUTION, new Constitution(this.randomGenerator).getValue());
    abilities.put(AbilityName.DEXTERITY, new Dexterity(this.randomGenerator).getValue());
    abilities.put(AbilityName.ANY, 0);

    gearTypeCount.put(GearType.HEADGEAR, 0);
    gearTypeCount.put(GearType.POTION, 0);
    gearTypeCount.put(GearType.BELT, 0);
    gearTypeCount.put(GearType.FOOTWEAR, 0);

    weaponList = new ArrayList<>();
    gearList = new ArrayList<>();
    health = 0;
    updateHealth();
  }

  private void updateHealth() {
    health = 0;
    for (EnumMap.Entry<AbilityName, Integer> entry : abilities.entrySet()) {
      health += entry.getValue();
    }
  }

  @Override
  public void regainHealth() {
    updateHealth();
  }

  @Override
  public void decreaseHealth(int damage) {
    this.health = this.health - damage;
  }

  @Override
  public int getHealth() {
    return this.health;
  }

  private boolean isGearCompatible(Gear newGear) {
    if (newGear.getLimit() >= gearTypeCount.get(newGear.getType()) + newGear.getSize()) {
      gearTypeCount.put(newGear.getType(),
              gearTypeCount.get(newGear.getType()) + newGear.getSize());
      return true;
    }
    return false;
  }

  @Override
  public void eqipAllGears(EquipmentBag equipmentBag) {
    if (this.gearList.size() != 0) {
      throw new IllegalStateException("Player has already equipped gears!");
    }
    for (int i = 0; i < GEAR_EQUIP_COUNT; i++) {
      Gear chosenGear = equipmentBag.getGearFromEquipmentBag();
      //System.out.println(chosenGear.getName());
      if (isGearCompatible(chosenGear)) {
        gearList.add(chosenGear);
        equipmentBag.removeGearFromBag(chosenGear);
      }
    }
    affectPlayerAbilities();
    Collections.sort(gearList);
  }

  @Override
  public void eqipWeapon(Armory armory) {
    if (this.weaponList.size() != 0) {
      throw new IllegalStateException("Player has already equipped weapons!");
    }
    Weapon weapon = armory.getWeaponFromArmory();
    if (weapon != null) {
      weaponList.add(weapon);
      armory.removeWeaponFromArmory(weapon);
      if (weapon.getType() == WeaponType.KATANAS) {
        Weapon secondWeapon = armory.getWeaponFromArmory();
        if (secondWeapon.getType() == WeaponType.KATANAS) {
          weaponList.add(secondWeapon);
          armory.removeWeaponFromArmory(secondWeapon);
        }
      }
    }
  }

  private void affectPlayerAbilities() {
    for (Gear currentGear : gearList) {
      List<CustomPair> affectedAbilityList = currentGear.getAffectedAbilityList();
      for (CustomPair affectedAbility : affectedAbilityList) {
        int currentAbilityValue = abilities.get(affectedAbility.getKey());
        abilities.put(affectedAbility.getKey(),
                currentAbilityValue + affectedAbility.getValue());
      }
    }
    updateHealth();
  }

  @Override
  public String toString() {
    StringBuilder description = new StringBuilder();
    description.append("Player Name: " + this.getName());
    description.append("\n");
    description.append("Player ability values are: ");
    description.append("[");
    description.append("Strength: " + abilities.get(AbilityName.STRENGTH));
    description.append(",");
    description.append("Constitution: " + abilities.get(AbilityName.CONSTITUTION));
    description.append(",");
    description.append("Dexterity: " + abilities.get(AbilityName.DEXTERITY));
    description.append(",");
    description.append("Charisma: " + abilities.get(AbilityName.CHARISMA));
    description.append("]");
    description.append("\n");
    description.append("Player is wearing following gears: ");
    description.append("\n");
    description.append("[");
    if (gearList.size() > 0) {
      for (int i = 0; i < gearList.size() - 1; i++) {
        description.append(gearList.get(i).getName());
        description.append(",");
        description.append("\n");
      }
      description.append(gearList.get(gearList.size() - 1).getName());
    }
    description.append("]");
    description.append("\n");
    description.append("Player is using the following weapons: ");
    description.append("\n");
    description.append("[");
    if (weaponList.size() > 0) {
      for (int i = 0; i < weaponList.size() - 1; i++) {
        description.append(weaponList.get(i).getName());
        description.append(",");
      }
      description.append(weaponList.get(weaponList.size() - 1).getName());
    }
    description.append("]");
    return description.toString();
  }

  @Override
  public int getStrikingPower() {
    return abilities.get(AbilityName.STRENGTH)
            + this.randomGenerator.getRandom(1, 11);
  }

  @Override
  public int getAvoidanceAbility() {
    return abilities.get(AbilityName.DEXTERITY)
            + this.randomGenerator.getRandom(1, 7);
  }

  @Override
  public int getPotentialStrikingDamage() {
    if (weaponList.isEmpty()) {
      return abilities.get(AbilityName.STRENGTH);
    }
    int weaponDamage = 0;
    for (Weapon weapon : weaponList) {
      weaponDamage += weapon.getRandomDamage(abilities.get(weapon.getEffectiveWieldAbilityName()));
    }
    return abilities.get(AbilityName.STRENGTH) + weaponDamage;
  }

  @Override
  public int getActualDamage(int opponentConstitution) {
    return getPotentialStrikingDamage() - opponentConstitution;
  }

  @Override
  public boolean canStrike(int opponentAvoidanceAbility) {
    return (this.getStrikingPower() > opponentAvoidanceAbility);
  }

  @Override
  public Map<AbilityName, Integer> getAbilities() {
    return new EnumMap<>(abilities);
  }

  @Override
  public List<Gear> getGearList() {
    return new ArrayList<>(this.gearList);
  }

  @Override
  public List<Weapon> getWeaponList() {
    return new ArrayList<>(this.weaponList);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String getDescription() {
    return this.toString();
  }
}
