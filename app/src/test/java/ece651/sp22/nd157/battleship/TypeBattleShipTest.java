package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class TypeBattleShipTest {
  @Test
  public void test_update_hit_info(){
    V2ShipFactory f = new V2ShipFactory();
    TypeBattleShip<Character> s = f.makeBattleship(new Placement("A4D"));
    s.recordHitAt(new Coordinate("A4"));
    s.recordHitAt(new Coordinate("B5"));
    TypeBattleShip<Character> s1 = f.makeBattleship(new Placement("A0U"));
    s1.updateHitInfo(s);
    assertFalse(s1.myPieces.get(new Coordinate("B0")));
    assertFalse(s1.myPieces.get(new Coordinate("B1")));
    assert(s1.myPieces.get(new Coordinate("A1")));
    assert(s1.myPieces.get(new Coordinate("B2")));
  }
  
  @Test
  public void test_makeCoords_up() {
    Coordinate upperLeft = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 3);
    Coordinate c4 = new Coordinate(2, 2);
    Coordinate c6 = new Coordinate(2, 3);
    Coordinate c7 = new Coordinate(2, 4);
    HashMap<Integer, Coordinate> testup = TypeBattleShip.makeCoords(upperLeft, 'U');
    HashMap<Integer, Coordinate> expectup = new HashMap<Integer, Coordinate>();
    expectup.put(1, c2);
    expectup.put(2, c4);
    expectup.put(3, c6);
    expectup.put(4, c7);
    assert (testup.equals(expectup));
  }

  @Test
  public void test_makeCoords_down() {
    Coordinate upperLeft = new Coordinate(1, 2);
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 3);
    Coordinate c3 = new Coordinate(1, 4);
    Coordinate c6 = new Coordinate(2, 3);
    HashMap<Integer, Coordinate> testdown = TypeBattleShip.makeCoords(upperLeft, 'D');
    HashMap<Integer, Coordinate> expectdown = new HashMap<Integer, Coordinate>();
    expectdown.put(1, c6);
    expectdown.put(2, c3);
    expectdown.put(3, c2);
    expectdown.put(4, c1);
    assert (testdown.equals(expectdown));
  }

  @Test
  public void test_makeCoords_left() {
    Coordinate upperLeft = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 3);
    Coordinate c4 = new Coordinate(2, 2);
    Coordinate c6 = new Coordinate(2, 3);
    Coordinate c8 = new Coordinate(3, 3);
    HashMap<Integer, Coordinate> testleft = TypeBattleShip.makeCoords(upperLeft, 'L');
    HashMap<Integer, Coordinate> expectleft = new HashMap<Integer, Coordinate>();
    expectleft.put(1, c4);
    expectleft.put(2, c8);
    expectleft.put(3, c6);
    expectleft.put(4, c2);
    assert (testleft.equals(expectleft));
  }

  @Test
  public void test_makeCoords_right() {
    Coordinate upperLeft = new Coordinate(1, 2);
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c4 = new Coordinate(2, 2);
    Coordinate c5 = new Coordinate(3, 2);
    Coordinate c6 = new Coordinate(2, 3);
    HashMap<Integer, Coordinate> testright = TypeBattleShip.makeCoords(upperLeft, 'R');
    HashMap<Integer, Coordinate> expectright = new HashMap<Integer, Coordinate>();
    expectright.put(1, c6);
    expectright.put(2, c1);
    expectright.put(3, c4);
    expectright.put(4, c5);
    assert (expectright.equals(testright));
  }

  @Test
  public void test_makeCoords_invalid_orientation() {
    Coordinate upperLeft = new Coordinate(1, 2);
    assertThrows(IllegalArgumentException.class, () -> TypeBattleShip.makeCoords(upperLeft, 'e'));
  }

  @Test
  public void test_super_constructor() {
    Coordinate upperLeft = new Coordinate(1, 2);
    TypeBattleShip<Character> s = new TypeBattleShip<Character>("Battleship", upperLeft, 'D', 'b', '*');
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 3);
    Coordinate c3 = new Coordinate(1, 4);
    Coordinate c6 = new Coordinate(2, 3);
    assert(s.occupiesCoordinates(c1));
    assert(s.occupiesCoordinates(c2));
    assert(s.occupiesCoordinates(c3));
    assert(s.occupiesCoordinates(c6));
  }

  @Test
  public void test_get(){
  Coordinate upperLeft = new Coordinate(1, 2);
    TypeBattleShip<Character> s = new TypeBattleShip<Character>("Battleship", upperLeft, 'D', 'b', '*');
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 3);
    Coordinate c3 = new Coordinate(1, 4);
    Coordinate c6 = new Coordinate(2, 3);
    assertEquals("Battleship", s.getName());
    assert(c6.equals(s.getBlock(1)));
    assert(c3.equals(s.getBlock(2)));
    assert(c2.equals(s.getBlock(3)));
    assert(c1.equals(s.getBlock(4)));
  }
}
