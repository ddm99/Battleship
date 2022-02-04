package ece651.sp22.nd157.battleship;

import java.util.ArrayList;

public interface Board<T> {
  public int getWidth();
  public int getHeight();
  public String tryAddShip(Ship<T> toAdd);
  public T whatIsAtForSelf(Coordinate where);
  public T whatIsAtForEnemy(Coordinate where);
  public Ship<T> fireAt(Coordinate c);
  public boolean isLost();
  public Ship<T> findMoveShip(Coordinate where);
  public ArrayList<Integer> sonarScan(Coordinate where);
}
