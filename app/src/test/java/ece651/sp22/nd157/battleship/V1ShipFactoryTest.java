package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V1ShipFactoryTest {
  @Test
  public void test_V1ShipFactory_wrong_orientation(){
    Placement V1 = new Placement(new Coordinate(1,1),'A');
    V1ShipFactory f = new V1ShipFactory();
    assertThrows(IllegalArgumentException.class,() -> f.makeSubmarine(V1));
  }
  @Test
  public void test_V1ShipFactory_CreateShip() {
    V1ShipFactory f = new V1ShipFactory();
    Placement v1_1 = new Placement(new Coordinate(1, 1), 'V');
    Placement v1_2 = new Placement(new Coordinate(1, 1), 'h');
    Coordinate c1 = new Coordinate(1,1);
    Coordinate c2 = new Coordinate(1,2);
    Coordinate c3 = new Coordinate(1,3);
    Coordinate c4 = new Coordinate(1,4);
    Coordinate c5 = new Coordinate(1,5);
    Coordinate c6 = new Coordinate(1,6);
    Coordinate c7 = new Coordinate(2,1);
    Coordinate c8 = new Coordinate(3,1);
    Ship<Character> dst = f.makeDestroyer(v1_1);
    Ship<Character> car = f.makeCarrier(v1_2);
    Ship<Character> bat = f.makeBattleship(v1_2);
    Ship<Character> sub = f.makeSubmarine(v1_1);
    checkShip(dst,"Destroyer",'d',c1,c7,c8);
    checkShip(car,"Carrier",'c',c1,c2,c3,c4,c5,c6);
    checkShip(bat,"Battleship",'b',c1,c2,c3,c4);
    checkShip(sub,"Submarine",'s',c1,c7);
  }
  
  private void checkShip(Ship<Character> testShip, String expectedName,
                         char expectedLetter, Coordinate... expectedLocs){
    for(int i =0; i < expectedLocs.length; i++){
      assertEquals(expectedLetter, testShip.getDisplayInfoAt(expectedLocs[i]));
            assertEquals(expectedName, testShip.getName());
        }
  }

}
