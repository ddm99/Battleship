package ece651.sp22.nd157.battleship;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents the battleship in version2 of the game. It creates a
 * hashmap to relates the coordinate with respective block of the ship
 */
public class TypeBattleShip<T> extends BasicShip<T> {
  final private String name;
  HashMap<Integer, Coordinate> myBlocks;

  public String getName() {
    return name;
  }

  public Coordinate getBlock(Integer key) {
    return myBlocks.get(key);
  }

  public TypeBattleShip(String name, Coordinate upperLeft, Character orientation, ShipDisplayInfo<T> myDisplayInfo,
      ShipDisplayInfo<T> enemDisplayInfo) {
    /**
     * Constructs the coordinate and displayInfo of the ship using its parent's
     * constructor
     */
    super(new ArrayList<Coordinate>(makeCoords(upperLeft, orientation).values()), myDisplayInfo, enemDisplayInfo,
        makeCoords(upperLeft, orientation));
    this.name = name;
    this.myBlocks = new HashMap<Integer, Coordinate>();
    this.myBlocks = makeCoords(upperLeft, orientation);
  }

  public TypeBattleShip(String name, Coordinate upperLeft, Character orientation, T data, T onHit) {
    /**
     * Constrcuts this class from the given info
     */
    this(name, upperLeft, orientation, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }

  public void updateHitInfo(Ship<T> s) {
    /**
     * Update the moved ship's status based on the status of the ship before
     * movement
     *
     * @param s is the ship before movement
     */
    for (int i = 1; i <= 4; i++) {
      Boolean isHit = s.getIsHit(i);
      this.myPieces.put(this.getBlock(i), isHit);
    }
  }

  static HashMap<Integer, Coordinate> makeCoords(Coordinate upperLeft, Character orientation) {
    /**
     * Create a Hashmap where each block of the Battleship has a respective label
     *
     * @param upperLeft   is the upperleft coordinate to place the ship
     * @param orientation is the orientation of the ship, for
     *                    BattleShip'U','D','L','R'
     * @return return a hashmap with each block cooresponding to a order
     * @throws thorw illegalArgumentException if invalid orientation is entered
     */
    int top = upperLeft.getRow();
    int left = upperLeft.getColumn();
    HashMap<Integer, Coordinate> newset = new HashMap<Integer, Coordinate>();
    if (orientation == 'U') {
      newset.put(1, new Coordinate(top, left + 1));
      newset.put(2, new Coordinate(top + 1, left));
      newset.put(3, new Coordinate(top + 1, left + 1));
      newset.put(4, new Coordinate(top + 1, left + 2));
    } else if (orientation == 'D') {
      newset.put(1, new Coordinate(top + 1, left + 1));
      newset.put(2, new Coordinate(top, left + 2));
      newset.put(3, new Coordinate(top, left + 1));
      newset.put(4, new Coordinate(top, left));
    } else if (orientation == 'L') {
      newset.put(1, new Coordinate(top + 1, left));
      newset.put(2, new Coordinate(top + 2, left + 1));
      newset.put(3, new Coordinate(top + 1, left + 1));
      newset.put(4, new Coordinate(top, left + 1));
    } else if (orientation == 'R') {
      newset.put(1, new Coordinate(top + 1, left + 1));
      newset.put(2, new Coordinate(top, left));
      newset.put(3, new Coordinate(top + 1, left));
      newset.put(4, new Coordinate(top + 2, left));
    } else {
      throw new IllegalArgumentException("Invalid Orientation for placing a Battleship!");
    }
    return newset;
  }
}
