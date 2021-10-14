package battlegame.gear;


import battlegame.ability.AbilityName;
import battlegame.util.CustomPair;

import static battlegame.util.Constants.FOOTWEAR_LIMIT;
import static battlegame.util.Constants.FOOTWEAR_NAME;
import static battlegame.util.Constants.MAX_FOOTWEAR_EFFECT;

public class Footwear extends AbstractGear{
  public Footwear(boolean isEffectNegative) {
    super(FOOTWEAR_NAME, FOOTWEAR_LIMIT, MAX_FOOTWEAR_EFFECT, isEffectNegative);
    abilityList.add(new CustomPair(AbilityName.DEXTERITY, getRandomEffect()));
  }

  @Override
  public int compareTo(Object o) {
    return 1;
  }
}
