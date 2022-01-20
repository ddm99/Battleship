package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }
    @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20));
  }

   @Test
  public void test_ship_placement(){
     Board<Character> testBoard = new BattleShipBoard<Character>(5,5);
     Character [][] expected= new Character[5][5];
     Coordinate c1 = new Coordinate(1,1);
     Coordinate c2 = new Coordinate(2,2);
     Coordinate c3 = new Coordinate(3,3);
     Coordinate c4 = new Coordinate(4,4);
       Ship<Character> s1 = new BasicShip(c1);
       Ship<Character> s2 = new BasicShip(c2);
       Ship<Character> s3 = new BasicShip(c3);
       Ship<Character> s4 = new BasicShip(c4);
       testBoard.tryAddShip(s1);
       expected[1][1]='s';
       checkWhatIsAtBoard(testBoard, expected);
       testBoard.tryAddShip(s2);
       expected[2][2]='s';
       checkWhatIsAtBoard(testBoard, expected);
       testBoard.tryAddShip(s3);
       expected[3][3]='s';
       checkWhatIsAtBoard(testBoard, expected);
       testBoard.tryAddShip(s4);
       expected[4][4]='s';
       checkWhatIsAtBoard(testBoard, expected);
     }
   
private <T> void checkWhatIsAtBoard(Board<T> b, T[][] expected){
    for(int i = 0; i < b.getWidth(); i++){
      for(int j = 0; j< b.getHeight(); j++){
        Coordinate c = new Coordinate(i, j);
        assertEquals(b.whatIsAt(c), expected[i][j]);
      }
    }
  }
}
