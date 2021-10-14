package battlegame;

import java.util.EnumMap;
import java.util.Map;

import battlegame.ability.Ability;
import battlegame.ability.AbilityName;
import battlegame.ability.Charisma;
import battlegame.ability.Constitution;
import battlegame.ability.Dexterity;
import battlegame.ability.Strength;

public class Player {
  String name;
  Map<AbilityName, Ability> abilityMap;
  public Player(String name) {
    this.name = name;
    abilityMap = new EnumMap<>(AbilityName.class);
    abilityMap.put(AbilityName.STRENGTH, new Strength());
    abilityMap.put(AbilityName.CHARISMA, new Charisma());
    abilityMap.put(AbilityName.CONSTIUTION, new Constitution());
    abilityMap.put(AbilityName.DEXTERITY, new Dexterity());
  }
}
