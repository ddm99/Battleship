package ece651.sp22.nd157.battleship;

/**
 * This class represents the coordinate of the board, it can parse either two
 * ints or a string of 2 characters into relevent coordinate
 */
public class Coordinate {
  private final int row;
  private final int column;

  /*
   * This method return the coordinate's row
   */
  public int getRow() {
    return this.row;
  }

  /*
   * This method return the coordinate's column
   */
  public int getColumn() {
    return this.column;
  }

  /*
   * Constructors: from value and from String
   */
  public Coordinate(int r, int c) {
    this.row = r;
    this.column = c;
  }

  public Coordinate(String descr) {
    if (descr.length() != 2) {
      throw new IllegalArgumentException("Invalid Size of Input!");
    }
    char strR = descr.charAt(0);
    char strC = descr.charAt(1);

    if (strR >= 'a' && strR <= 'z') {
      strR = Character.toUpperCase(strR);
      this.row = (int) strR - 65;
    } else if (strR >= 'A' && strR <= 'Z') {
      this.row = (int) strR - 65;
    } else {
      throw new IllegalArgumentException("Invalid Input row!");
    }
    if (strC < '0' || strC > '9') {
      throw new IllegalArgumentException("Invalid Input column!");
    }
    this.column = (int) strC - 48;
  }

  /*
   * This Section Overides the methods that will be used on this class
   */
  @Override
  public boolean equals(Object o) {
    if (o.getClass().equals(getClass())) {
      Coordinate c = (Coordinate) o;
      return row == c.row && column == c.column;
    }
    return false;
  }

  @Override
  public String toString() {
    return "(" + row + ", " + column + ")";
  }

  @Override
  public int hashCode() {
    return toString().hashCode();
  }
}
