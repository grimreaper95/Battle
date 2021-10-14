package battlegame.gear;

import battlegame.RandomGenerator;
import battlegame.ability.AbilityName;
import battlegame.util.CustomPair;

import static battlegame.util.Constants.MAX_POTION_EFFECT;
import static battlegame.util.Constants.POTION_LIMIT;
import static battlegame.util.Constants.POTION_NAME;

public class Potion extends AbstractGear{

  private AbilityName getRandomAbility() {
    int abilityVal = RandomGenerator.getRandom(0,3);
    for(AbilityName abilityName: AbilityName.values()) {
      if(abilityName.abilityVal == abilityVal) {
        return abilityName;
      }
    }
    return null; // throw exception? should never come here - value does not exist in enum.
  }

  public Potion(boolean isEffectNegative) {
    super(POTION_NAME, POTION_LIMIT, MAX_POTION_EFFECT, isEffectNegative);
    abilityList.add(new CustomPair(getRandomAbility(), getRandomEffect()));
  }

}
