package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_display_myboard_and_enemys(){
    Board<Character> b = new BattleShipBoard<Character>(4, 2,'X');
    Board<Character> b1 = new BattleShipBoard<Character>(4, 2,'X');
    BoardTextView myView =new BoardTextView(b);
    BoardTextView enemyView =new BoardTextView(b1);
    V1ShipFactory f = new V1ShipFactory();
    Ship<Character> myShip = f.makeSubmarine(new Placement(new Coordinate(0,0),'h'));
    Ship<Character> myShip2 = f.makeDestroyer(new Placement(new Coordinate(1,1),'h'));
    Ship<Character> enemyShip = f.makeSubmarine(new Placement(new Coordinate(0,0),'v'));
    Ship<Character> enemyShip2 = f.makeDestroyer(new Placement(new Coordinate(0,1),'h'));
    b.tryAddShip(myShip);
    b.tryAddShip(myShip2);
    b1.tryAddShip(enemyShip);
    b1.tryAddShip(enemyShip2);
    b1.fireAt(new Coordinate(0,0));
    b1.fireAt(new Coordinate(1,1));
    String myString =
      "     My Ocean                 Enemy Ocean\n"+
      "  0|1|2|3                    0|1|2|3\n" + 
      "A s|s| |  A                A s| | |  A\n"+
      "B  |d|d|d B                B  |X| |  B\n"+
      "  0|1|2|3                    0|1|2|3\n";
    assertEquals(myString,myView.displayMyBoardWithEnemyNextToIt(enemyView, "My Ocean", "Enemy Ocean"));
  }
  
  @Test
  public void test_display_enemy_board(){
    Board<Character> b = new BattleShipBoard<Character>(4, 3,'X');
    V1ShipFactory f = new V1ShipFactory();
    //Ship<Character> s = f.makeSubmarine(new Placement(new Coordinate(0,1),'h'));
    Ship<Character> s1 = f.makeDestroyer(new Placement(new Coordinate(0,3),'V'));
    //b.tryAddShip(s);
    b.tryAddShip(s1);
    BoardTextView view =new BoardTextView(b);
    b.fireAt(new Coordinate(0,0));
    b.fireAt(new Coordinate(0,3));
    b.fireAt(new Coordinate(1,3));
    b.fireAt(new Coordinate(2,3));
    String myView =
      "  0|1|2|3\n" +
      "A X| | |d A\n" +
      "B  | | |d B\n" +
      "C  | | |d C\n" +
      "  0|1|2|3\n";
    //make sure we laid things out the way we think we did.
    assertEquals(myView, view.displayEnemyBoard());
  }

   @Test
  public void test_display_empty() {
    Board<Character> b1 = new BattleShipBoard<Character>(2, 2,'X');
    BoardTextView view = new BoardTextView(b1);
    String expectedHeader= "  0|1\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected=
      expectedHeader+
      "A  |  A\n"+ "B  |  B\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }
  @Test
  public void test_invalid_board_size() {
    Board<Character> wideBoard = new BattleShipBoard<Character>(11,20,'X');
    Board<Character> tallBoard = new BattleShipBoard<Character>(10,27,'X');
    //you should write two assertThrows here
   assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
  }
}
