package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_makeCoords() {
    Coordinate upperLeft = new Coordinate(1, 2);
    HashSet<Coordinate> testSet = RectangleShip.makeCoords(upperLeft, 1, 3);
    HashSet<Coordinate> expectedSet = new HashSet<Coordinate>();
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(2, 2);
    Coordinate c3 = new Coordinate(3, 2);
    expectedSet.add(c1);
    expectedSet.add(c2);
    expectedSet.add(c3);
    assert (testSet.equals(expectedSet));
  }
  
  @Test
  public void test_super_constructor() {
    Coordinate upperLeft = new Coordinate(1, 2);
    RectangleShip<Character> s = new RectangleShip<Character>("submarine",upperLeft, 1, 3,'s','*');
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(2, 2);
    Coordinate c3 = new Coordinate(3, 2);
    assert(s.occupiesCoordinates(c1));
    assert(s.occupiesCoordinates(c2));
    assert(s.occupiesCoordinates(c3));
    assertEquals("submarine",s.getName());
    }
}
