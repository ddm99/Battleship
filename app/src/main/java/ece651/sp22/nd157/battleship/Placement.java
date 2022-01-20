package ece651.sp22.nd157.battleship;

public class Placement {
  private final Coordinate where;
  private final char orientation;
  /*
   *Getters
   */
  public Coordinate getCoordinate(){
    return this.where;
  }
  public char getOrientation(){
    return this.orientation;
  }
  /*
   *Constructor Field
   */
  public Placement(Coordinate c, char ori) {
    this.where = c;
    this.orientation = Character.toUpperCase(ori);
  }
  public Placement(String descr){
    if (descr.length() != 3) {
      throw new IllegalArgumentException("Invalid Size of Input!");
    }
    this.where = new Coordinate(descr.substring(0,2));
    this.orientation = Character.toUpperCase(descr.charAt(2));
  }
  /*
   * Overiding method for comparison
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Placement c = (Placement) o;
      return where.equals(c.where) && orientation == c.orientation;
    }
    return false;
  }

  @Override
  public String toString() {
    StringBuilder line = new StringBuilder("");
    line.append(where.toString());
    line.append(Character.toString(orientation));
    return line.toString();
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
