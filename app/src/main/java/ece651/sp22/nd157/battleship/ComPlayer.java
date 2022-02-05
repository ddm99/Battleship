package ece651.sp22.nd157.battleship;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.function.Function;

/**
 * This class extends from textplayer and represent a computer player, it
 * overwrites some behavior of human player with sets of predefined actions
 */
public class ComPlayer extends TextPlayer {
  private ArrayList<Placement> placementList;
  private ArrayList<Coordinate> fireList;

  public ComPlayer(String name, Board<Character> theBoard, Reader inputSource, PrintStream out,
      AbstractShipFactory<Character> shipFactory) {
    /**
     * calls the textplayer's constructor to construct a player creates empty lists
     * for placementlist and firelist
     */
    super("Com " + name, theBoard, inputSource, out, shipFactory);
    this.placementList = new ArrayList<Placement>();
    createPlacementList();
    this.fireList = new ArrayList<Coordinate>();
    createFireList();
  }

  @Override
  public void doPlacementPhase() throws IOException {
    /**
     * Override the Textplayer's method, the com player will place ships according
     * to the placement on placementlist
     */
    for (int i = 0; i < shipsToPlace.size(); i++) {
      Function<Placement, Ship<Character>> createFn = shipCreationFns.get(shipsToPlace.get(i));
      Ship<Character> s = createFn.apply(placementList.get(i));
      theBoard.tryAddShip(s);
    }
    out.println(name + " finished placement");
    printDivider();
  }

  @Override
  public void playwithChoice(Board<Character> enemyBoard, BoardTextView enemyBoardView) throws IOException {
    /**
     * Override the Textplayer's method, the com player will only fire sequentially
     * according to fire list, it will fire at first coordinate on the fire list,
     * and then remove that coordinate and place it at the back of the list
     *
     * @param enemyBoard is the board this player will fire at
     */
    out.println(name + "'s attack");
    Coordinate c = fireList.get(0);
    fireList.remove(0);
    enemyBoard.fireAt(c);
    if (enemyBoard.whatIsAtForEnemy(c) == 'b') {
      out.println(name + " hit a battleship!");
    } else if (enemyBoard.whatIsAtForEnemy(c) == 's') {
      out.println(name + " hit a submarine!");
    } else if (enemyBoard.whatIsAtForEnemy(c) == 'c') {
      out.println(name + " hit a carrier!");
    } else if (enemyBoard.whatIsAtForEnemy(c) == 'd') {
      out.println(name + " hit a destroyer!");
    } else {
      out.println(name + " Missed!");
    }
    fireList.add(c);
  }

  private void createPlacementList() {
    // fill the placementlist for the placement used to construct ships
    placementList.add(new Placement("A0V"));
    placementList.add(new Placement("A1V"));
    placementList.add(new Placement("A2V"));
    placementList.add(new Placement("A3V"));
    placementList.add(new Placement("A4V"));
    placementList.add(new Placement("A5R"));
    placementList.add(new Placement("A6D"));
    placementList.add(new Placement("A8L"));
    placementList.add(new Placement("D0R"));
    placementList.add(new Placement("D5L"));
  }

  private void createFireList() {
    // fill the firelist for the coordinate the com player fire at
    for (int i = 0; i < theBoard.getHeight(); i++) {
      for (int j = 0; j < theBoard.getWidth(); j++) {
        fireList.add(new Coordinate(i, j));
      }
    }
  }
}
