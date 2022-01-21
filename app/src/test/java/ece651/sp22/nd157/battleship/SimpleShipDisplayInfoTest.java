package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {
  @Test
    public void test_getInfo_onHit(){
        SimpleShipDisplayInfo<Integer> s = new SimpleShipDisplayInfo<Integer>(10,20);
        Coordinate c = new Coordinate(10,20);
        int result = s.getInfo(c, true);
        assertEquals(20,result);
    }
  @Test
  public void test_getInfo_myData(){
        SimpleShipDisplayInfo<Integer> s = new SimpleShipDisplayInfo<Integer>(10,20);
        Coordinate c = new Coordinate(10,20);
        int result = s.getInfo(c, false);
        assertEquals(10,result);
    }

}
