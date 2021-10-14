package battlegame.gear;

import java.util.List;

import battlegame.RandomGenerator;
import battlegame.ability.AbilityName;
import battlegame.util.CustomPair;

import static battlegame.util.Constants.BELT_LIMIT;
import static battlegame.util.Constants.BELT_NAME;
import static battlegame.util.Constants.MAX_BELT_EFFECT;

public class Belt extends AbstractGear {

  private AbilityName getRandomAbility() {
    int abilityVal = RandomGenerator.getRandom(0,3);
    for(AbilityName abilityName: AbilityName.values()) {
      if(abilityName.abilityVal == abilityVal) {
        return abilityName;
      }
    }
    return null; // throw exception? should never come here - value does not exist in enum.
  }

  public Belt(boolean isEffectNegative) {
    super(BELT_NAME, BELT_LIMIT,  MAX_BELT_EFFECT, isEffectNegative);

    //adding two random abilities that will be affected by the belt
    abilityList.add(new CustomPair(getRandomAbility(), getRandomEffect()));
    abilityList.add(new CustomPair(getRandomAbility(), getRandomEffect()));
  }
}
