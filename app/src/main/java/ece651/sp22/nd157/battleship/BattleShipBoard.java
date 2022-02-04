package ece651.sp22.nd157.battleship;

import java.util.ArrayList;
import java.util.HashMap;

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

  public Ship<T> findMoveShip(Coordinate where) {
    /**
     * find the ship to be moved located at the specified coordinate, remove it from
     * list of myShip
     *
     * @param where is the location to find the ship
     * @return the ship found at target location
     */
    for (Ship<T> s : myShips) {
      if (s.occupiesCoordinates(where)) {
        myShips.remove(s);
        return s;
      }
    }
    throw new IllegalArgumentException("No ship exists on selected grid!");
  }

  public ArrayList<Integer> sonarScan(Coordinate where){
    /**
     * returns the result of sonar scan
     *
     *@return array of result, In the Order of submarine,destroyer,battleship,carrier
     */
    if((where.getRow()>=height)||(where.getRow()<0)||(where.getColumn()>=width)||(where.getColumn()<0)){
      throw new IllegalArgumentException("The Coordinate for sonar scan is out of board");
    }
    int subCounter=0;
    int desCounter=0;
    int batCounter=0;
    int carCounter=0;
    for(int i=where.getRow()-3;i<=where.getRow()+3;i++){
      for(int j=where.getColumn()-3;j<=where.getColumn()+3;j++){
        int diff = Math.abs(i-where.getRow())+Math.abs(j-where.getColumn());
        if(diff<=3){
          T info =whatIsAtForSelf(new Coordinate(i,j));
          if(info!=null){
          if(info.equals('s')){
            subCounter++;
          }else if(info.equals('d')){
            desCounter++;
          }else if(info.equals('b')){
            batCounter++;
          }else if(info.equals('c')){
            carCounter++;
          }
          }
        }
      }
    }
    ArrayList<Integer> ans = new ArrayList<Integer>();
    ans.add(subCounter);
    ans.add(desCounter);
    ans.add(batCounter);
    ans.add(carCounter);
    return ans;
  }
}
