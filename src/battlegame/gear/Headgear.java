package battlegame.gear;

import battlegame.ability.AbilityName;
import battlegame.util.CustomPair;

import static battlegame.util.Constants.HEADGEAR_LIMIT;
import static battlegame.util.Constants.HEADGEAR_NAME;
import static battlegame.util.Constants.MAX_HEADGEAR_EFFECT;

public class Headgear extends AbstractGear{
  public Headgear(boolean isEffectNegative) {
    super(HEADGEAR_NAME, HEADGEAR_LIMIT,  MAX_HEADGEAR_EFFECT, isEffectNegative);
    abilityList.add(new CustomPair(AbilityName.CONSTIUTION, getRandomEffect()));
  }

  @Override
  public int compareTo(Object o) {
    return -1;
  }

  @Override
  public void updateGear() {

  }
}
