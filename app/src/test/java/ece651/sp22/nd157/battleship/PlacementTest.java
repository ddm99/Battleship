package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {
   @Test
  public void test_hashCode() {
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(0, 3);
    Coordinate c4 = new Coordinate(2, 1);
    Placement  p1 = new Placement(c1, 'v');
    Placement  p2 = new Placement(c2, 'V');
    Placement  p3 = new Placement(c3, 'h');
    Placement  p4 = new Placement(c4, 'H');
    
    assertEquals(p1.hashCode(), p2.hashCode());
    assertEquals(p1.getOrientation(),'V');
    assertNotEquals(p1.hashCode(), p3.hashCode());
    assertNotEquals(p1.hashCode(), p4.hashCode());
  }

  @Test
  public void test_invalid_constructor(){
    assertThrows(IllegalArgumentException.class,() -> new Placement("A5V0"));
    assertThrows(IllegalArgumentException.class,() -> new Placement("A5"));
  }

  @Test
  public void test_equals(){
    Coordinate c1 = new Coordinate(1, 2);
    Coordinate c2 = new Coordinate(1, 2);
    Coordinate c3 = new Coordinate(0, 3);
    Coordinate c4 = new Coordinate(2, 1);
    Placement  p1 = new Placement(c1, 'v');
    Placement  p2 = new Placement(c2, 'V');
    Placement  p3 = new Placement(c3, 'h');
    Placement  p4 = new Placement(c4, 'H');
    assertEquals(p1,p2);
    assertNotEquals(p2,p3);
    assertNotEquals(p1,p4);
    assertNotEquals(p1,c1);
  }
}
