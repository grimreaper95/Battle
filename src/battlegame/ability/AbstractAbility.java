package battlegame.ability;
import java.util.Arrays;

import battlegame.RandomGenerator;

public class AbstractAbility implements Ability {
  int abilityValue;
  protected AbstractAbility() {
    abilityValue = calculateAbility();
  }

  protected int calculateAbility() {
    int dice[] = new int[4];
    for(int i = 0; i < 4;i++) {
      do {
        dice[i] = RandomGenerator.getRandom(0, 6);
      }while(dice[i] == 1);
    }
    Arrays.sort(dice);
    return dice[1] + dice[2] + dice[3];
  }

  @Override
  public int getValue() {
    return this.abilityValue;
  }
}
