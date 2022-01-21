package ece651.sp22.nd157.battleship;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {
  private T myData;
  private T onHit;

  public SimpleShipDisplayInfo(T mydata, T onhit) {
    /**
     * Constructs the information to display regarding the ship
     *
     * @params mydata is the info to display when the part is not hit
     * @params onhit is the info to display when the part is hit
     */
    this.myData = mydata;
    this.onHit = onhit;
  }

  @Override
  public T getInfo(Coordinate where, boolean hit) {
    /**
     * Returns the display info of the part of the ship
     *
     * @params where is the coordinate that the info relates to
     * @params hit is the state of whether the coordinate has been hit
     */
    if (hit) {
      return onHit;
    } else {
      return myData;
    }
  }

}
