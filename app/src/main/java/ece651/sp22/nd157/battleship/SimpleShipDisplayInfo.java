package ece651.sp22.nd157.battleship;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {
  private T myData;
  private T onHit;

  /*
   * Constructor Field
   */
  public SimpleShipDisplayInfo(T mydata, T onhit) {
    this.myData = mydata;
    this.onHit = onhit;
  }

  @Override
  public T getInfo(Coordinate where, boolean hit) {
    if (hit) {
      return onHit;
    } else {
      return myData;
    }
  }

}
