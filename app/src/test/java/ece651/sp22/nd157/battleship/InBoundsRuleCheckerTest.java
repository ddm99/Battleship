package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_check_placement() {
    PlacementRuleChecker<Character> c = new InBoundsRuleChecker<Character>(null);
    Board<Character> board = new BattleShipBoard<>(4, 4);
    Placement p = new Placement(new Coordinate(1, 3), 'H');
    Placement p1 = new Placement(new Coordinate(1, 1), 'V');
    AbstractShipFactory<Character> shipFactory = new V1ShipFactory();
    Ship<Character> s = shipFactory.makeDestroyer(p);
    Ship<Character> s1 = shipFactory.makeDestroyer(p1);
    assertFalse(c.checkPlacement(s,board));
    assert(c.checkPlacement(s1,board));
  }
}
