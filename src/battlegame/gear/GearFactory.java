package battlegame.gear;

import battlegame.util.Constants;

public class GearFactory {
  public Gear getGear(String gearName, boolean isEffectNegative) {
    if(gearName.equals(Constants.HEADGEAR_NAME)) {
      return new Headgear(isEffectNegative);
    }
    else if(gearName.equals(Constants.POTION_NAME)) {
      return new Potion(isEffectNegative);
    }
    else if(gearName.equals(Constants.BELT_NAME)) {
      return new Belt(isEffectNegative);
    }
    else if(gearName.equals(Constants.FOOTWEAR_NAME)) {
      return new Footwear(isEffectNegative);
    }
    else {
      return null;
    }
  }
}
