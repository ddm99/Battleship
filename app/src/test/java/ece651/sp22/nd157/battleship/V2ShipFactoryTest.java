package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest {
  @Test
  public void test_V2ShipFactory_wrong_orientation() {
    Placement V2 = new Placement(new Coordinate(1, 1), 'A');
    V2ShipFactory f = new V2ShipFactory();
    assertThrows(IllegalArgumentException.class, () -> f.makeCarrier(V2));
    assertThrows(IllegalArgumentException.class, () -> f.makeSubmarine(V2));
  }

  @Test
  public void test_V2ShipFactory_CreateShip() {
    V2ShipFactory f = new V2ShipFactory();
    Placement v1_1 = new Placement(new Coordinate(1, 1), 'V');
    Placement v1 = new Placement(new Coordinate(1, 1), 'H');
    Placement v1_2 = new Placement(new Coordinate(1, 1), 'u');
    Coordinate c1 = new Coordinate(1,1);
    Coordinate c2 = new Coordinate(1,2);
    Coordinate c3 = new Coordinate(4,1);
    Coordinate c4 = new Coordinate(3,2);
    Coordinate c5 = new Coordinate(4,2);
    Coordinate c6 = new Coordinate(5,2);
    Coordinate c7 = new Coordinate(2,1);
    Coordinate c8 = new Coordinate(3,1);
    Coordinate c9 = new Coordinate(2,2);
    Coordinate c0 = new Coordinate(2,3);
    Ship<Character> dst = f.makeDestroyer(v1_1);
    Ship<Character> car = f.makeCarrier(v1_2);
    Ship<Character> bat = f.makeBattleship(v1_2);
    Ship<Character> sub = f.makeSubmarine(v1);
    checkShip(dst,"Destroyer",'d',c1,c7,c8);
    checkShip(car,"Carrier",'c',c1,c7,c8,c3,c4,c6,c5);
    checkShip(bat,"Battleship",'b',c2,c7,c9,c0);
    checkShip(sub,"Submarine",'s',c1,c2);
  }
  
  private void checkShip(Ship<Character> testShip, String expectedName,
                         char expectedLetter, Coordinate... expectedLocs){
    for(int i =0; i < expectedLocs.length; i++){
      assertEquals(expectedLetter, testShip.getDisplayInfoAt(expectedLocs[i],true));
            assertEquals(expectedName, testShip.getName());
        }
  }
}
