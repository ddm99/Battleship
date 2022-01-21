package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BasicShipTest {
  @Test
  public void test_record_hit() {
    Coordinate c1 = new Coordinate(1, 1);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(1, 3);
    Coordinate c4 = new Coordinate(1, 4);
    RectangleShip<Character> s = new RectangleShip<Character>(c1, 3, 1, 's', '*');
    s.recordHitAt(c3);
    assertThrows(IllegalArgumentException.class, () -> s.recordHitAt(c4));
    assert (s.wasHitAt(c3));
    assertThrows(IllegalArgumentException.class, () -> s.wasHitAt(c4));
    assertFalse(s.wasHitAt(c2));
  }

  @Test
  public void test_is_sunk() {
    Coordinate c1 = new Coordinate(1, 1);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(1, 3);
    RectangleShip<Character> s = new RectangleShip<Character>(c1, 3, 1, 's', '*');
    s.recordHitAt(c3);
    s.recordHitAt(c2);
    assertFalse(s.isSunk());
    s.recordHitAt(c1);
    assert (s.isSunk());
  }

  @Test
  public void test_get_DisplayInfo_at() {
    Coordinate c1 = new Coordinate(1, 1);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(1, 3);
    RectangleShip<Character> s = new RectangleShip<Character>(c1, 3, 1, 's', '*');
    s.recordHitAt(c2);
    assertEquals('*', s.getDisplayInfoAt(c2));
    assertEquals('s', s.getDisplayInfoAt(c3));
  }
}
