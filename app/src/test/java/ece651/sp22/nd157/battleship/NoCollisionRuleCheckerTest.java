package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_rules() {
    PlacementRuleChecker<Character> c = new NoCollisionRuleChecker<Character>(null);
    Board<Character> board = new BattleShipBoard<>(5, 5,'X');
    Placement p = new Placement(new Coordinate(1, 3), 'V');// initial ship
    Placement p1 = new Placement(new Coordinate(1, 1), 'H');// collision
    Placement p2 = new Placement(new Coordinate(4, 4), 'H');// out of board right
    Placement p3 = new Placement(new Coordinate(-1, 1), 'H');// out of board top
    Placement p4 = new Placement(new Coordinate(4, 1), 'V');// out of board bottom
    Placement p5 = new Placement(new Coordinate(2, -1), 'H');// out of board left
    AbstractShipFactory<Character> shipFactory = new V1ShipFactory();
    Ship<Character> s = shipFactory.makeDestroyer(p);
    assertEquals(null, board.tryAddShip(s));
    Ship<Character> s1 = shipFactory.makeDestroyer(p1);
    Ship<Character> s2 = shipFactory.makeDestroyer(p2);
    Ship<Character> s3 = shipFactory.makeDestroyer(p3);
    Ship<Character> s4 = shipFactory.makeDestroyer(p4);
    Ship<Character> s5 = shipFactory.makeDestroyer(p5);
    assertEquals("That placement is invalid: the ship overlaps another ship.", board.tryAddShip(s1));
    assertEquals("That placement is invalid: the ship goes off the right of the board.", board.tryAddShip(s2));
    assertEquals("That placement is invalid: the ship goes off the top of the board.", board.tryAddShip(s3));
    assertEquals("That placement is invalid: the ship goes off the bottom of the board.", board.tryAddShip(s4));
    assertEquals("That placement is invalid: the ship goes off the left of the board.", board.tryAddShip(s5));
  }
}
