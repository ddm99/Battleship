package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class TypeCarrierTest {
  @Test
  public void test_makeCoords_up() {
    Coordinate upperLeft = new Coordinate(1, 2);
    HashMap<Integer, Coordinate> testup = TypeCarrier.makeCoords(upperLeft, 'U');
    HashMap<Integer, Coordinate> expectup = new HashMap<Integer, Coordinate>();
    expectup.put(1,new Coordinate(1,2));
    expectup.put(2,new Coordinate(2,2));
    expectup.put(3,new Coordinate(3,2));
    expectup.put(4,new Coordinate(4,2));
    expectup.put(5,new Coordinate(3,3));
    expectup.put(6,new Coordinate(4,3));
    expectup.put(7,new Coordinate(5,3));
    assert (testup.equals(expectup));
  }
  
  @Test
  public void test_makeCoords_down() {
    Coordinate upperLeft = new Coordinate(1, 2);
    HashMap<Integer, Coordinate> testdown = TypeCarrier.makeCoords(upperLeft, 'D');
    HashMap<Integer, Coordinate> expectdown = new HashMap<Integer, Coordinate>();
    expectdown.put(1,new Coordinate(5,3));
    expectdown.put(2,new Coordinate(4,3));
    expectdown.put(3,new Coordinate(3,3));
    expectdown.put(4,new Coordinate(2,3));
    expectdown.put(5,new Coordinate(3,2));
    expectdown.put(6,new Coordinate(2,2));
    expectdown.put(7,new Coordinate(1,2));
    assert (testdown.equals(expectdown));
  }
  
  @Test
  public void test_makeCoords_left() {
    Coordinate upperLeft = new Coordinate(1, 2);
    HashMap<Integer, Coordinate> testleft = TypeCarrier.makeCoords(upperLeft, 'L');
    HashMap<Integer, Coordinate> expectleft = new HashMap<Integer, Coordinate>();
    expectleft.put(1,new Coordinate(2,2));
    expectleft.put(2,new Coordinate(2,3));
    expectleft.put(3,new Coordinate(2,4));
    expectleft.put(4,new Coordinate(2,5));
    expectleft.put(5,new Coordinate(1,4));
    expectleft.put(6,new Coordinate(1,5));
    expectleft.put(7,new Coordinate(1,6));
    assert (testleft.equals(expectleft));
  }
  
  @Test
  public void test_makeCoords_right() {
    Coordinate upperLeft = new Coordinate(1, 2);
    HashMap<Integer, Coordinate> testright = TypeCarrier.makeCoords(upperLeft, 'R');
    HashMap<Integer, Coordinate> expectright = new HashMap<Integer, Coordinate>();
    expectright.put(1,new Coordinate(1,6));
    expectright.put(2,new Coordinate(1,5));
    expectright.put(3,new Coordinate(1,4));
    expectright.put(4,new Coordinate(1,3));
    expectright.put(5,new Coordinate(2,4));
    expectright.put(6,new Coordinate(2,3));
    expectright.put(7,new Coordinate(2,2));
    assert (testright.equals(expectright));
  }

  @Test
  public void test_makeCoords_invalid_orientation() {
    Coordinate upperLeft = new Coordinate(1, 2);
    assertThrows(IllegalArgumentException.class, () -> TypeCarrier.makeCoords(upperLeft, 'e'));
  }

  @Test
  public void test_super_constructor() {
    Coordinate upperLeft = new Coordinate(1, 2);
    TypeCarrier<Character> s = new TypeCarrier<Character>("Carrier", upperLeft, 'D', 'c', '*');
    Coordinate c1 = new Coordinate(5, 3);
    Coordinate c2 = new Coordinate(4, 3);
    Coordinate c3 = new Coordinate(3, 3);
    Coordinate c4 = new Coordinate(2, 3);
    Coordinate c5 = new Coordinate(3, 2);
    Coordinate c6 = new Coordinate(2, 2);
    Coordinate c7 = new Coordinate(1, 2);
    assert(s.occupiesCoordinates(c1));
    assert(s.occupiesCoordinates(c2));
    assert(s.occupiesCoordinates(c3));
    assert(s.occupiesCoordinates(c4));
    assert(s.occupiesCoordinates(c5));
    assert(s.occupiesCoordinates(c7));
    assert(s.occupiesCoordinates(c6));
  }

   @Test
  public void test_get(){
  Coordinate upperLeft = new Coordinate(1, 2);
    TypeCarrier<Character> s = new TypeCarrier<Character>("Carrier", upperLeft, 'D', 'c', '*');
    assertEquals("Carrier", s.getName());
    Coordinate c1 = new Coordinate(5, 3);
    Coordinate c2 = new Coordinate(4, 3);
    Coordinate c3 = new Coordinate(3, 3);
    Coordinate c4 = new Coordinate(2, 3);
    Coordinate c5 = new Coordinate(3, 2);
    Coordinate c6 = new Coordinate(2, 2);
    Coordinate c7 = new Coordinate(1, 2);
    assert(c1.equals(s.getBlock(1)));
    assert(c2.equals(s.getBlock(2)));
    assert(c3.equals(s.getBlock(3)));
    assert(c4.equals(s.getBlock(4)));
    assert(c5.equals(s.getBlock(5)));
    assert(c6.equals(s.getBlock(6)));
    assert(c7.equals(s.getBlock(7)));
  }
}
