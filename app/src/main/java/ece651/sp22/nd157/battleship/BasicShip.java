package ece651.sp22.nd157.battleship;

import java.util.HashMap;

public class BasicShip<T> implements Ship<T> {
  HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;

  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo) {
    /**
     * Constructs a BasicShip type with the specific set of coordinates
     * 
     * @param where         is the set of coordinates that the ship exists
     * @param myDisplayInfo is what information is present(with type T) in that
     *                      coordinate
     */
    this.myDisplayInfo = myDisplayInfo;
    myPieces = new HashMap<Coordinate, Boolean>();
    for (Coordinate c : where) {
      myPieces.put(c, false);
    }
  }

  protected void checkCoordinateInThisShip(Coordinate c) {
    /**
     * Helper Function that check if the coordinate entered correspond to a part of
     * this ship
     * 
     * @throws IllegalArgumentException if not present
     */
    if (myPieces.containsKey(c)) {
      return;
    }
    throw new IllegalArgumentException("You missed!");
  }

  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    // TODO Auto-generated method stub
    return myPieces.containsKey(where);
  }

  @Override
  public boolean isSunk() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    // TODO this is not right. We need to
    // look up the hit status of this coordinate
    return myDisplayInfo.getInfo(where, false);
  }

}
