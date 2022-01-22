package ece651.sp22.nd157.battleship;

import java.util.HashMap;
import java.util.Iterator;

public class BasicShip<T> implements Ship<T> {
  HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;

  public String getName(){
    return "NULL";
  }

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
    throw new IllegalArgumentException("The target location is not a part of this ship!");
  }

  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    // TODO Auto-generated method stub
    return myPieces.containsKey(where);
  }

  @Override
  public boolean isSunk() {
    /**
     * Check whether all the coordinates of the ship have been hit
     */
    for (HashMap.Entry<Coordinate, Boolean> entry : myPieces.entrySet()) {
      if (entry.getValue() == false) {
        return false;
      }
    }
    return true;
  }

  @Override
  public void recordHitAt(Coordinate where) {
    /**
     * Record the position of the part of the ship when it is hit
     *
     * @params where is the position the enemy fired at
     */
    checkCoordinateInThisShip(where);
    myPieces.replace(where, true);
  }

  @Override
  public boolean wasHitAt(Coordinate where) {
    /**
     * Check whether the ship at given coordinate has been hit
     *
     * @params where is the coordinate to look at
     */
    checkCoordinateInThisShip(where);
    return myPieces.get(where);
  }

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    /**
     * Obtain the display Info at target coordinate
     *
     * @params where is the coordinate we want to know about
     */
    if (wasHitAt(where)) {
      return myDisplayInfo.getInfo(where, true);
    } else {
      return myDisplayInfo.getInfo(where, false);
    }
  }
}
