package ece651.sp22.nd157.battleship;

import java.util.HashMap;

public class BasicShip implements Ship<Character>{
  HashMap<Coordinate, Boolean>  myPieces;
  private final Coordinate myLocation;
  /*
   * Constructor Field
   */
  public BasicShip(Coordinate Input){
    this.myLocation = Input;
  }
  /*
   * Method Overide
   */
  @Override
  public boolean occupiesCoordinates(Coordinate where) {
    // TODO Auto-generated method stub
    return where.equals(myLocation);
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
  public Character getDisplayInfoAt(Coordinate where) {
    // TODO Auto-generated method stub
    return 's';
  }

}
