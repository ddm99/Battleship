package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
  }

  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5, 'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20, 'X'));
  }

  @Test
  public void test_ship_placement() {
    Board<Character> testBoard = new BattleShipBoard<Character>(5, 5, 'X');
    Character[][] expected = new Character[5][5];
    Coordinate c1 = new Coordinate(1, 1);
    Coordinate c2 = new Coordinate(2, 2);
    Coordinate c3 = new Coordinate(3, 3);
    Coordinate c4 = new Coordinate(4, 4);
    Ship<Character> s1 = new RectangleShip<Character>(c1, 's', '*');
    Ship<Character> s2 = new RectangleShip<Character>(c2, 's', '*');
    Ship<Character> s3 = new RectangleShip<Character>(c3, 's', '*');
    Ship<Character> s4 = new RectangleShip<Character>(c4, 's', '*');
    assertEquals(null, testBoard.tryAddShip(s1));
    expected[1][1] = 's';
    checkWhatIsAtBoard(testBoard, expected, true);
    assertEquals(null, testBoard.tryAddShip(s2));
    expected[2][2] = 's';
    checkWhatIsAtBoard(testBoard, expected, true);
    assertEquals(null, testBoard.tryAddShip(s3));
    expected[3][3] = 's';
    checkWhatIsAtBoard(testBoard, expected, true);
    assertEquals(null, testBoard.tryAddShip(s4));
    expected[4][4] = 's';
    checkWhatIsAtBoard(testBoard, expected, true);
    AbstractShipFactory<Character> shipFactory = new V1ShipFactory();
    Placement p = new Placement(new Coordinate(1, 4), 'H');
    Placement p1 = new Placement(new Coordinate(2, 4), 'V');
    Placement p2 = new Placement(new Coordinate(1, 2), 'H');
    Ship<Character> s5 = shipFactory.makeDestroyer(p);
    Ship<Character> s6 = shipFactory.makeDestroyer(p1);
    Ship<Character> s7 = shipFactory.makeDestroyer(p2);
    assertEquals("That placement is invalid: the ship goes off the right of the board.", testBoard.tryAddShip(s5));
    assertEquals("That placement is invalid: the ship overlaps another ship.", testBoard.tryAddShip(s6));
    checkWhatIsAtBoard(testBoard, expected, true);
    assertEquals(null, testBoard.tryAddShip(s7));
    expected[1][2] = 'd';
    expected[1][3] = 'd';
    expected[1][4] = 'd';
    testBoard.tryAddShip(s5);
    testBoard.tryAddShip(s6);
    checkWhatIsAtBoard(testBoard, expected, true);
  }

  private <T> void checkWhatIsAtBoard(Board<T> b, T[][] expected, boolean isSelf) {
    for (int i = 0; i < b.getWidth(); i++) {
      for (int j = 0; j < b.getHeight(); j++) {
        Coordinate c = new Coordinate(i, j);
        if (isSelf) {
          assertEquals(b.whatIsAtForSelf(c), expected[i][j]);
        } else {
          assertEquals(b.whatIsAtForEnemy(c), expected[i][j]);
        }
      }
    }
  }

  @Test
  public void test_fireat() {
    Board<Character> testBoard = new BattleShipBoard<Character>(10, 20, 'X');
    AbstractShipFactory<Character> shipFactory = new V1ShipFactory();
    Placement p = new Placement(new Coordinate(0, 4), 'H');
    Placement p1 = new Placement(new Coordinate(2, 5), 'V');
    Placement p2 = new Placement(new Coordinate(6, 6), 'H');
    Ship<Character> s = shipFactory.makeDestroyer(p);
    Ship<Character> s1 = shipFactory.makeDestroyer(p1);
    Ship<Character> s2 = shipFactory.makeDestroyer(p2);
    testBoard.tryAddShip(s);
    testBoard.tryAddShip(s1);
    testBoard.tryAddShip(s2);
    assertSame(s, testBoard.fireAt(new Coordinate(0, 5)));
    assertSame(s, testBoard.fireAt(new Coordinate(0, 4)));
    assertSame(s, testBoard.fireAt(new Coordinate(0, 6)));
    assertSame(s1, testBoard.fireAt(new Coordinate(4, 5)));
    assertSame(s2, testBoard.fireAt(new Coordinate(6, 6)));
    assertEquals(null, testBoard.fireAt(new Coordinate(0, 0)));
    assert (s.isSunk());
    assertFalse(s1.isSunk());
    assertFalse(s2.isSunk());
  }

  @Test
  public void test_whatisat_enemy() {
    Board<Character> testBoard = new BattleShipBoard<Character>(5, 5, 'X');
    Character[][] expected = new Character[5][5];
    Coordinate c1 = new Coordinate(1, 1);
    Coordinate c2 = new Coordinate(2, 2);
    Coordinate c3 = new Coordinate(3, 3);
    Coordinate c4 = new Coordinate(4, 4);
    Ship<Character> s1 = new RectangleShip<Character>(c1, 's', '*');
    Ship<Character> s2 = new RectangleShip<Character>(c2, 's', '*');
    Ship<Character> s3 = new RectangleShip<Character>(c3, 's', '*');
    Ship<Character> s4 = new RectangleShip<Character>(c4, 's', '*');
    testBoard.tryAddShip(s1);
    testBoard.tryAddShip(s2);
    testBoard.tryAddShip(s3);
    testBoard.tryAddShip(s4);
    testBoard.fireAt(new Coordinate(1, 1));
    testBoard.fireAt(new Coordinate(1, 2));
    testBoard.fireAt(new Coordinate(1, 3));
    testBoard.fireAt(new Coordinate(1, 4));
    expected[1][1] = 's';
    expected[1][2] = 'X';
    expected[1][3] = 'X';
    expected[1][4] = 'X';
    checkWhatIsAtBoard(testBoard, expected, false);
  }

  @Test
  public void test_is_lost() {
    Board<Character> testBoard = new BattleShipBoard<Character>(5, 5, 'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    Ship<Character> s = shipFactory.makeSubmarine(new Placement(new Coordinate(0, 0), 'V'));
    Ship<Character> s1 = shipFactory.makeSubmarine(new Placement(new Coordinate(0, 1), 'V'));
    testBoard.tryAddShip(s);
    testBoard.tryAddShip(s1);
    testBoard.fireAt(new Coordinate(0, 0));
    assertFalse(testBoard.isLost());
    testBoard.fireAt(new Coordinate(0, 1));
    assertFalse(testBoard.isLost());
    testBoard.fireAt(new Coordinate(1, 0));
    testBoard.fireAt(new Coordinate(2, 2));
    assertFalse(testBoard.isLost());
    testBoard.fireAt(new Coordinate(1, 1));
    assert (testBoard.isLost());
  }
}
