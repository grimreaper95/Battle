package battlegame.gear;

/**
 * An enum to represent size of Belt gear.
 */
public enum BeltSize {
  SMALL(1), MEDIUM(3), LARGE(4);
  private final int beltIdx;
  private final int betltSize;

  BeltSize(int size) {
    if (size == 1) {
      this.betltSize = size;
      this.beltIdx = 0;
    } else if (size == 3) {
      this.betltSize = size;
      this.beltIdx = 1;
    } else {
      this.betltSize = size;
      this.beltIdx = 2;
    }
  }

  /**
   * Get index of belt gear. Represents index associated with belt gear.
   * Index associated to small belt is 0.
   * Index associated to medium belt is 1.
   * Index associated to large belt is 2.
   * @return index of belt gear.
   */
  public int getBeltIdx() {
    return this.beltIdx;
  }

  /**
   * Get size of a belt gear which is the number of units a belt represents.
   * @return the size of a belt gear which is the number of units a belt represents.
   */
  public int getBetltSize() {
    return this.betltSize;
  }
}
