package ece651.sp22.nd157.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {
  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    /**
     * Check if the coordinate of the ship is within bounds
     *
     * @return false if any coordinate is out of bounds
     */
    Iterable<Coordinate> set = theShip.getCoordinates();
    for (Coordinate current : set) {
      if ((current.getRow() < 0) || (current.getRow() > theBoard.getHeight()) || (current.getColumn() < 0)
          || (current.getColumn() > theBoard.getWidth())) {
        return false;
      }
    }
    return true;
  }
}
