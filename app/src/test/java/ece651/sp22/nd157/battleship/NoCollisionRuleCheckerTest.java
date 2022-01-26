package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_check_collision() {
    PlacementRuleChecker<Character> c = new NoCollisionRuleChecker<Character>(null);
    Board<Character> board = new BattleShipBoard<>(4, 4);
    Placement p = new Placement(new Coordinate(1, 3), 'V');
    Placement p1 = new Placement(new Coordinate(1, 1), 'H');
    Placement p2 = new Placement(new Coordinate(4, 1), 'H');
    AbstractShipFactory<Character> shipFactory = new V1ShipFactory();
    Ship<Character> s = shipFactory.makeDestroyer(p);
    board.tryAddShip(s);
    Ship<Character> s1 = shipFactory.makeDestroyer(p1);
    Ship<Character> s2 = shipFactory.makeDestroyer(p2);
    assertFalse(c.checkPlacement(s1,board));
    assert(c.checkPlacement(s2,board));
  }
@Test
public void both_rules() {
    PlacementRuleChecker<Character> c = new NoCollisionRuleChecker<Character>(null);
    PlacementRuleChecker<Character> c1 = new InBoundsRuleChecker<Character>(c);
    Board<Character> board = new BattleShipBoard<>(5, 5);
    Placement p = new Placement(new Coordinate(1, 3), 'V');
    Placement p1 = new Placement(new Coordinate(1, 1), 'H');
    Placement p2 = new Placement(new Coordinate(4, 1), 'H');
     Placement p3 = new Placement(new Coordinate(4, 4), 'V');
    AbstractShipFactory<Character> shipFactory = new V1ShipFactory();
    Ship<Character> s = shipFactory.makeDestroyer(p);
    board.tryAddShip(s);
    Ship<Character> s1 = shipFactory.makeDestroyer(p1);
    Ship<Character> s2 = shipFactory.makeDestroyer(p2);
    Ship<Character> s3 = shipFactory.makeDestroyer(p3);
    assertFalse(c1.checkPlacement(s1,board));
    assert(c1.checkPlacement(s2,board));
    assertFalse(c1.checkPlacement(s3,board));
  }
}
