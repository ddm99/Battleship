package ece651.sp22.nd157.battleship;

/**
 * This class is used to check if the coordinate of the newly added ship may
 * collides with an existing ship
 */
public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {

  public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
    /**
     * Constructs the next placement checker
     */
    super(next);
  }

  @Override
  protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    /**
     * Iterates through the ships' location on board, check if there is collision
     * between newly placed ship and existing ships
     */
    for (Coordinate c : theShip.getCoordinates()) {
      if (theBoard.whatIsAtForSelf(c) != null) {
        return "That placement is invalid: the ship overlaps another ship.";
      }
    }
    return null;
  }

}
