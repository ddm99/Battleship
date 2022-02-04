package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;

public class ComPlayerTest {
  @Test
  public void test_Com_placement()throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    V2ShipFactory factory = new V2ShipFactory();
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    TextPlayer p1 = new ComPlayer("A", b1, input, System.out, factory);
    assert(p1.name.equals("Com A"));
    p1.doPlacementPhase();
    assertEquals('s', b1.whatIsAtForSelf(new Coordinate("A0")));
    assertEquals('s', b1.whatIsAtForSelf(new Coordinate("A1")));
    assertEquals('d', b1.whatIsAtForSelf(new Coordinate("A2")));
    assertEquals('d', b1.whatIsAtForSelf(new Coordinate("A4")));
    assertEquals('b', b1.whatIsAtForSelf(new Coordinate("A6")));
    assertEquals('b', b1.whatIsAtForSelf(new Coordinate("A9")));
    assertEquals('c', b1.whatIsAtForSelf(new Coordinate("D3")));
    assertEquals('c', b1.whatIsAtForSelf(new Coordinate("E6")));
  }

  @Test
  public void test_play_with_choice()throws IOException{
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    V2ShipFactory factory = new V2ShipFactory();
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
    TextPlayer p1 = new ComPlayer("A", b1, input, System.out, factory);
    p1.doPlacementPhase();
    for(int i=0;i<100;i++){
    p1.playwithChoice(b1, null);
    }
    assertEquals('*', b1.whatIsAtForSelf(new Coordinate("A0")));
    assertEquals('*', b1.whatIsAtForSelf(new Coordinate("A1")));
    assertEquals('*', b1.whatIsAtForSelf(new Coordinate("A2")));
    assertEquals('*', b1.whatIsAtForSelf(new Coordinate("A4")));
  }

}
