package ece651.sp22.nd157.battleship;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {
  
 public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
   /**
     * Constructs the next placement checker
     */
    super(next);
  }

  
  @Override
  protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    /**
     * Iterates through the ships' location on board, check if there is
     * collision between newly placed ship and existing ships
     */
    for(Coordinate c:theShip.getCoordinates()){
      if(theBoard.whatIsAt(c) != null){
        return false;
      }
    }
    return true;
  }

}
