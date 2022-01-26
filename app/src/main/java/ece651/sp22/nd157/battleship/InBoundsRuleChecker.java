package ece651.sp22.nd157.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    /**
     * Constructs the next placement checker
     */
    super(next);
  }

  @Override
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    /**
     * Check if the coordinate of the ship is within bounds
     *
     * @return string of error message if false
     */
    Iterable<Coordinate> set = theShip.getCoordinates();
    for (Coordinate current : set) {
      /*
       * if ((current.getRow() < 0) || (current.getRow() >= theBoard.getHeight()) ||
       * (current.getColumn() < 0) || (current.getColumn() >= theBoard.getWidth())) {
       * return false; }
       */
      if (current.getRow() < 0) {
        return "That placement is invalid: the ship goes off the top of the board.";
      }
      if (current.getRow() >= theBoard.getHeight()) {
        return "That placement is invalid: the ship goes off the bottom of the board.";
      }
      if (current.getColumn() < 0) {
        return "That placement is invalid: the ship goes off the left of the board.";
      }
      if (current.getColumn() >= theBoard.getWidth()) {
        return "That placement is invalid: the ship goes off the right of the board.";
      }
    }
    return null;
  }
}
