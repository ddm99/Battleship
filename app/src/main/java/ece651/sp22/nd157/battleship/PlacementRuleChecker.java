package ece651.sp22.nd157.battleship;

public abstract class PlacementRuleChecker<T> {
  private final PlacementRuleChecker<T> next;

  public PlacementRuleChecker(PlacementRuleChecker<T> next) {
    /**
     * Constructs the pointer to next rule check we use to check the rules
     *
     * @params next points to the next placement rule checker to be used
     */
    this.next = next;
  }

    protected abstract boolean checkMyRule(Ship<T> theShip, Board<T> theBoard);
    
    public boolean checkPlacement (Ship<T> theShip, Board<T> theBoard) {
    //if we fail our own rule: stop the placement is not legal
    if (!checkMyRule(theShip, theBoard)) {
      return false;
    }
    //other wise, ask the rest of the chain.
    if (next != null) {
      return next.checkPlacement(theShip, theBoard); 
    }
    //if there are no more rules, then the placement is legal
    return true;
  }
 }

