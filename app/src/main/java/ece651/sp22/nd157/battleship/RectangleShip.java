package ece651.sp22.nd157.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {
  /*
   * Constructor Field
   */
  public RectangleShip(Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo) {
    super(makeCoords(upperLeft, width, height), myDisplayInfo);
  }

  public RectangleShip(Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this(upperLeft, 1, 1, data, onHit);
  }

  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
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
