package ece651.sp22.nd157.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {
  final private String name;
  HashMap<Integer, Coordinate> myBlocks;

  public String getName() {
    return name;
  }

  public Coordinate getBlock(Integer key) {
    return myBlocks.get(key);
  }

  public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo,
      ShipDisplayInfo<T> enemDisplayInfo) {
    /**
     * Constructs the coordinate and displayInfo of the ship using its parent's
     * constructor
     */
    super(new ArrayList<Coordinate>(makeCoords(upperLeft, width, height).values()), myDisplayInfo, enemDisplayInfo);
    this.name = name;
    this.myBlocks = new HashMap<Integer, Coordinate>();
    this.myBlocks = makeCoords(upperLeft, width, height);
  }

  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    /**
     * Constrcuts this class from the given info
     */
    this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    /**
     * Default helper constructor that initialize a 1x1 ship
     */
    this("testship", upperLeft, 1, 1, data, onHit);
  }

  public void updateHitInfo(RectangleShip<T> s) {
    /**
     * Update the moved ship's status based on the status of the ship before
     * movement
     *
     * @param s is the ship before movement
     */
    for (int i = 1; i <= myBlocks.size(); i++) {
      Boolean isHit = s.myPieces.get(s.getBlock(i));
      this.myPieces.put(this.getBlock(i), isHit);
    }
  }

  static HashMap<Integer, Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
    /**
     * Generates a set of coordinates according to the ship's dimension and position
     *
     * @params upperLeft is the upper left position of the ship
     * @params width is the horizontal length of the ship
     * @params height is the verticle length of the ship
     */
    int top = upperLeft.getRow();
    int left = upperLeft.getColumn();
    int k = 1;
    HashMap<Integer, Coordinate> newset = new HashMap<Integer, Coordinate>();
    for (int j = 0; j < width; j++) {
      for (int i = 0; i < height; i++) {
        Coordinate c = new Coordinate((top + i), (left + j));
        newset.put(k, c);
        k++;
      }
    }
    return newset;
  }
}
