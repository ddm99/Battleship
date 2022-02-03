package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_makeCoords() {
    Coordinate upperLeft = new Coordinate(1, 2);
    HashMap<Integer,Coordinate> testSet = RectangleShip.makeCoords(upperLeft, 1, 3);
    HashMap<Integer,Coordinate> expectedSet = new HashMap<Integer,Coordinate>();
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(2, 2);
    Coordinate c3 = new Coordinate(3, 2);
    expectedSet.put(1,c1);
    expectedSet.put(2,c2);
    expectedSet.put(3,c3);
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

  @Test
  public void test_get(){
  Coordinate upperLeft = new Coordinate(1, 2);
  RectangleShip<Character> s = new RectangleShip<Character>("Destroyer", upperLeft, 1,3, 'b', '*');
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(2, 2);
    Coordinate c3 = new Coordinate(3, 2);
    assertEquals("Destroyer", s.getName());
    assert(c1.equals(s.getBlock(1)));
    assert(c2.equals(s.getBlock(2)));
    assert(c3.equals(s.getBlock(3)));
  }
}
