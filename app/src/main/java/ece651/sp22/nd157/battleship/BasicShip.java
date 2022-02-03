package ece651.sp22.nd157.battleship;

import java.util.HashMap;
import java.util.Iterator;

public abstract class BasicShip<T> implements Ship<T> {
  HashMap<Coordinate, Boolean> myPieces;
  HashMap<Integer,Coordinate> myBlock;
  protected ShipDisplayInfo<T> myDisplayInfo;
  protected ShipDisplayInfo<T> enemyDisplayInfo;


  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo,HashMap<Integer,Coordinate> myBlock) {
    /**
     * Constructs a BasicShip type with the specific set of coordinates
     * 
     * @param where         is the set of coordinates that the ship exists
     * @param myDisplayInfo is what information is present(with type T) in that
     *                      coordinate
     */
    this.myDisplayInfo = myDisplayInfo;
    this.enemyDisplayInfo = enemyDisplayInfo;
    myBlock = new HashMap<Integer,Coordinate>();
    this.myBlock = myBlock;
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

  public Iterable<Coordinate> getCoordinates() {
    /**
     * Get all of the Coordinates that this Ship occupies.
     * 
     * @return An Iterable with the coordinates that this Ship occupies
     */
    return myPieces.keySet();
  }

  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    /**
     * Check if the target coordinate occupied by a ship
     */
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
  public T getDisplayInfoAt(Coordinate where, boolean myShip) {
    /**
     * Obtain the display Info at target coordinate
     *
     * @param myShip is used to detemine which set of info to display
     * @params where is the coordinate we want to know about
     */
    if (wasHitAt(where)) {
      if (myShip) {
        return myDisplayInfo.getInfo(where, true);
      } else {
        return enemyDisplayInfo.getInfo(where, true);
      }
    } else {
      if (myShip) {
        return myDisplayInfo.getInfo(where, false);
      } else {
        return enemyDisplayInfo.getInfo(where, false);
      }
    }
  }
  public Boolean getIsHit(Integer i){
    return this.myPieces.get(this.myBlock.get(i));
  }

  public void updateHitInfo(Ship<T> s){
    for(int i =1; i<=myBlock.size();i++){
      Boolean isHit = s.getIsHit(i);
      this.myPieces.put(myBlock.get(i), isHit);
    }
  }
}
