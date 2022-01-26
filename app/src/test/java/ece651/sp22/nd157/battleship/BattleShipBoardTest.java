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
  public void test_ship_placement() {
    Board<Character> testBoard = new BattleShipBoard<Character>(5, 5);
    Character[][] expected = new Character[5][5];
    Coordinate c1 = new Coordinate(1, 1);
    Coordinate c2 = new Coordinate(2, 2);
    Coordinate c3 = new Coordinate(3, 3);
    Coordinate c4 = new Coordinate(4, 4);
    Ship<Character> s1 = new RectangleShip<Character>(c1, 's', '*');
    Ship<Character> s2 = new RectangleShip<Character>(c2, 's', '*');
    Ship<Character> s3 = new RectangleShip<Character>(c3, 's', '*');
    Ship<Character> s4 = new RectangleShip<Character>(c4, 's', '*');
    assert (testBoard.tryAddShip(s1));
    expected[1][1] = 's';
    checkWhatIsAtBoard(testBoard, expected);
    assert (testBoard.tryAddShip(s2));
    expected[2][2] = 's';
    checkWhatIsAtBoard(testBoard, expected);
    assert (testBoard.tryAddShip(s3));
    expected[3][3] = 's';
    checkWhatIsAtBoard(testBoard, expected);
    assert (testBoard.tryAddShip(s4));
    expected[4][4] = 's';
    checkWhatIsAtBoard(testBoard, expected);
    AbstractShipFactory<Character> shipFactory = new V1ShipFactory();
    Placement p = new Placement(new Coordinate(1, 4), 'H');
    Placement p1 = new Placement(new Coordinate(2, 4), 'V');
    Placement p2 = new Placement(new Coordinate(1, 2), 'H');
    Ship<Character> s5 = shipFactory.makeDestroyer(p);
    Ship<Character> s6 = shipFactory.makeDestroyer(p1);
    Ship<Character> s7 = shipFactory.makeDestroyer(p2);
    assertFalse(testBoard.tryAddShip(s5));
    assertFalse(testBoard.tryAddShip(s6));
    checkWhatIsAtBoard(testBoard, expected);
    assert (testBoard.tryAddShip(s7));
    expected[1][2] = 'd';
    expected[1][3] = 'd';
    expected[1][4] = 'd';
    testBoard.tryAddShip(s5);
    testBoard.tryAddShip(s6);
    checkWhatIsAtBoard(testBoard, expected);
  }

  private <T> void checkWhatIsAtBoard(Board<T> b, T[][] expected) {
    for (int i = 0; i < b.getWidth(); i++) {
      for (int j = 0; j < b.getHeight(); j++) {
        Coordinate c = new Coordinate(i, j);
        assertEquals(b.whatIsAt(c), expected[i][j]);
      }
    }
  }
}
