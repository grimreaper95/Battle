package battlegame.gear;

import java.util.List;

import battlegame.ability.AbilityName;
import battlegame.util.CustomPair;


public interface Gear extends Comparable{
  String getName();

  int	getLimit();

  int getMaxGearEffect();

  List<CustomPair> getAffectedAbilityList();

  void updateGear();

  @Override
  int compareTo(Object o);

}
