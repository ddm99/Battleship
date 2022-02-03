package ece651.sp22.nd157.battleship;

import java.util.ArrayList;
import java.util.HashMap;

public class TypeCarrier<T> extends BasicShip<T> {
final private String name;
  HashMap<Integer,Coordinate> myBlocks;

  public String getName(){
    return name;
  }

  public Coordinate getBlock(Integer key){
    return myBlocks.get(key);
  }

  public TypeCarrier(String name,Coordinate upperLeft,Character orientation, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemDisplayInfo) {
    /**
     * Constructs the coordinate and displayInfo of the ship using its parent's
     * constructor
     */
    super(new ArrayList<Coordinate>(makeCoords(upperLeft, orientation).values()), myDisplayInfo,enemDisplayInfo);
    this.name = name;
    this.myBlocks = new HashMap<Integer,Coordinate>();
    this.myBlocks = makeCoords(upperLeft, orientation);
  }

  public TypeCarrier(String name, Coordinate upperLeft, Character orientation, T data, T onHit) {
   /**
    * Constrcuts this class from the given info
    */
   this(name, upperLeft,orientation,new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }

  static HashMap<Integer,Coordinate> makeCoords(Coordinate upperLeft, Character orientation) {
    /**
     * Create a Hashmap where each block of the Carrier has a respective label
     *
     *@param upperLeft is the upperleft coordinate to place the ship
     *@param orientation is the orientation of the ship, for Carrier 'U','D','L','R'
     *@return return a hashmap with each block cooresponding to a order
     *@throws thorw illegalArgumentException if invalid orientation is entered
     */
    int top = upperLeft.getRow();
    int left = upperLeft.getColumn();
    HashMap<Integer,Coordinate> newset = new HashMap<Integer,Coordinate>();
    if (orientation=='U'){
      newset.put(1,new Coordinate(top,left));
      newset.put(2,new Coordinate(top+1,left));
      newset.put(3,new Coordinate(top+2,left));
      newset.put(4,new Coordinate(top+3,left));
      newset.put(5,new Coordinate(top+2,left+1));
      newset.put(6,new Coordinate(top+3,left+1));
      newset.put(7,new Coordinate(top+4,left+1));
    }
    else if (orientation=='D'){
      newset.put(1,new Coordinate(top+4,left+1));
      newset.put(2,new Coordinate(top+3,left+1));
      newset.put(3,new Coordinate(top+2,left+1));
      newset.put(4,new Coordinate(top+1,left+1));
      newset.put(5,new Coordinate(top+2,left));
      newset.put(6,new Coordinate(top+1,left));
      newset.put(7,new Coordinate(top,left));
    }
    else if (orientation=='L'){
      newset.put(1,new Coordinate(top+1,left));
      newset.put(2,new Coordinate(top+1,left+1));
      newset.put(3,new Coordinate(top+1,left+2));
      newset.put(4,new Coordinate(top+1,left+3));
      newset.put(5,new Coordinate(top,left+2));
      newset.put(6,new Coordinate(top,left+3));
      newset.put(7,new Coordinate(top,left+4));
    }
    else if (orientation=='R'){
      newset.put(1,new Coordinate(top,left+4));
      newset.put(2,new Coordinate(top,left+3));
      newset.put(3,new Coordinate(top,left+2));
      newset.put(4,new Coordinate(top,left+1));
      newset.put(5,new Coordinate(top+1,left+2));
      newset.put(6,new Coordinate(top+1,left+1));
      newset.put(7,new Coordinate(top+1,left));
    }
    else{
      throw new IllegalArgumentException("Invalid Orientation for placing a Battleship!");
    }
    return newset;
  }
}
