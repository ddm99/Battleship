package ece651.sp22.nd157.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class BattleShipBoard<T> implements Board<T> {
  private final int width;
  private final int height;
  private final ArrayList<Ship<T>> myShips;
  private final PlacementRuleChecker<T> placementChecker;
  HashMap<Coordinate, T> enemyHits;
  final T missInfo;

  public int getHeight() {
    return this.height;
  }

  public int getWidth() {
    return this.width;
  }

  public BattleShipBoard(int w, int h, PlacementRuleChecker<T> rule, T missInfo) {
    /**
     * Constructs a BattleShipBoard with the specified width and height
     *
     * @param w    is the width of the newly constructed board.
     * @param h    is the height of the newly constructed board.
     * @param rule is the rule we want to test against
     * @throws IllegalArgumentException if width or height is out of bound
     */
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
    placementChecker = rule;
    this.missInfo = missInfo;
    this.enemyHits = new HashMap<Coordinate, T>();
  }

  public BattleShipBoard(int w, int h, T missInfo) {
    /**
     * Constructs the Board with default rule checker
     *
     * @param default rule chekcer check for both in Bound and no collision
     */
    this(w, h, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<T>(null)), missInfo);
  }

  public String tryAddShip(Ship<T> toAdd) {
    /**
     * Check for validity of ship placement, then add to the board
     *
     * @params toAdd is the ship to be added to the array of myShips
     * @return has the shipped being added
     */
    String placementProblem = placementChecker.checkPlacement(toAdd, this);
    if (placementProblem == null) {
      myShips.add(toAdd);
      return null;
    }
    return placementProblem;
  }

  public T whatIsAtForSelf(Coordinate where) {
    return whatIsAt(where, true);

  }

  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }

  protected T whatIsAt(Coordinate where, boolean isSelf) {
    /**
     * Returns the infomation present at input coordinate
     *
     * @params where is the coordinate entered to check whether the ship is present
     *         at that coordinate on the board
     */
    if (isSelf) {
      for (Ship<T> s : myShips) {
        if (s.occupiesCoordinates(where)) {
          return s.getDisplayInfoAt(where, isSelf);
        }
      }
    } else {
      if (enemyHits.containsKey(where)) {
        return enemyHits.get(where);
      }
    }
    return null;
  }

  public Ship<T> fireAt(Coordinate c) {
    /**
     * search for any ship that occupies coordinate c, if one is found, that ship is
     * hit, is not, add to enemy misses
     *
     * @param c is the coordinate we fire at
     */
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(c)) {
        s.recordHitAt(c);
        enemyHits.put(c, s.getDisplayInfoAt(c, false));
        return s;
      }
    }
    enemyHits.put(c, missInfo);
    return null;
  }

  public boolean isLost() {
    /**
     * Check if every ship on this board has been sunk
     */
    for (Ship<T> s : myShips) {
      if (s.isSunk() == false) {
        return false;
      }
    }
    return true;
  }
}
