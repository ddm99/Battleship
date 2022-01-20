package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    Board b1 = new BattleShipBoard(2, 2);
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
    Board wideBoard = new BattleShipBoard(11,20);
    Board tallBoard = new BattleShipBoard(10,27);
    //you should write two assertThrows here
   assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));
  }
 @Test
  public void test_display_empty_3by2() {
    Board b1 = new BattleShipBoard(3, 2);
    BoardTextView view = new BoardTextView(b1);
    String expectedHeader = "  0|1|2\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected =
      expectedHeader+
      "A  | |  A\n"+
      "B  | |  B\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }
  @Test
  public void test_display_empty_3by5() {
    Board b1 = new BattleShipBoard(3, 5);
    BoardTextView view = new BoardTextView(b1);
    String expectedHeader = "  0|1|2\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected =
      expectedHeader+
      "A  | |  A\n"+
      "B  | |  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }
}
