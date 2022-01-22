package ece651.sp22.nd157.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {
  final private String name;

  public String getName(){
    return name;
  }
  
  public RectangleShip(String name,Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo) {
    /**
     * Constructs the coordinate and displayInfo of the ship using its parent's
     * constructor
     */
    super(makeCoords(upperLeft, width, height), myDisplayInfo);
    this.name = name;
  }

  public RectangleShip(String name,Coordinate upperLeft, int width, int height, T data, T onHit) {
    /**
     * Helper constructor to simply the input of the main constructor
     */
    this(name,upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    /**
     * Default helper constructor that initialize a 1x1 ship
     */
    this("testship",upperLeft, 1, 1, data, onHit);
  }

  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
    /**
     * Generates a set of coordinates according to the ship's dimension and position
     *
     * @params upperLeft is the upper left position of the ship
     * @params width is the horizontal length of the ship
     * @params height is the verticle length of the ship
     */
    int top = upperLeft.getRow();
    int left = upperLeft.getColumn();
    HashSet<Coordinate> newset = new HashSet<Coordinate>();
    for (int j = 0; j < width; j++) {
      for (int i = 0; i < height; i++) {
        Coordinate c = new Coordinate((top + i), (left + j));
        newset.add(c);
      }
    }
    return newset;
  }
}
