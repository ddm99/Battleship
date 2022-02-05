package ece651.sp22.nd157.battleship;

/**
 * This class used to represent the placement info used to place ships, it
 * contains coordinate as well as the orientation of the ship
 */
public class Placement {
  private final Coordinate where;
  private final char orientation;

  /*
   * Getters
   */
  public Coordinate getCoordinate() {
    return this.where;
  }

  public char getOrientation() {
    return this.orientation;
  }

  /*
   * Constructor Field
   */
  public Placement(Coordinate c, char ori) {
    /**
     * Constructs the placement of the ship with regards to the coordinate and
     * orientation entered
     *
     * @params c is the coordinate to place the ship
     * @params ori is the orientation of the ship
     */
    this.where = c;
    this.orientation = Character.toUpperCase(ori);
  }

  public Placement(String descr) {
    /**
     * Constructs the placement of the ship from the string
     *
     * @params descr is the string that describes the placement of ship
     */
    if (descr.length() != 3) {
      throw new IllegalArgumentException("Invalid Size of Input!");
    }
    this.where = new Coordinate(descr.substring(0, 2));
    this.orientation = Character.toUpperCase(descr.charAt(2));
  }

  @Override
  public boolean equals(Object o) {
    /**
     * Overides the default method to compare the placement
     *
     * @params o is another placement to compare to
     */
    if (o.getClass().equals(getClass())) {
      Placement c = (Placement) o;
      return where.equals(c.where) && orientation == c.orientation;
    }
    return false;
  }

  @Override
  public String toString() {
    /**
     * Converts the placement to string in the format of (row, col, ori)
     */
    StringBuilder line = new StringBuilder("");
    line.append(where.toString());
    line.append(Character.toString(orientation));
    return line.toString();
  }

  @Override
  public int hashCode() {
    /**
     * Hash the components of the placement
     */
    return toString().hashCode();
  }
}
