package ece651.sp22.nd157.battleship;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.function.Function;

public class ComPlayer extends TextPlayer {
  private ArrayList<Placement> placementList;
  private ArrayList<Coordinate> fireList;

  public ComPlayer(String name, Board<Character> theBoard, Reader inputSource, PrintStream out,
      AbstractShipFactory<Character> shipFactory) {
    super("Com " + name, theBoard, inputSource, out, shipFactory);
    this.placementList = new ArrayList<Placement>();
    createPlacementList();
    this.fireList = new ArrayList<Coordinate>();
    createFireList();
  }

  @Override
  public void doPlacementPhase() throws IOException {
    for (int i = 0; i < shipsToPlace.size(); i++) {
      Function<Placement, Ship<Character>> createFn = shipCreationFns.get(shipsToPlace.get(i));
      Ship<Character> s = createFn.apply(placementList.get(i));
      theBoard.tryAddShip(s);
    }
    out.println(name+" finished placement");
    printDivider();
  }

  @Override
  public void playwithChoice(Board<Character> enemyBoard, BoardTextView enemyBoardView) throws IOException{
    out.println(name+"'s attack");
    Coordinate c = fireList.get(0);
    fireList.remove(0);
    enemyBoard.fireAt(c);
    if (enemyBoard.whatIsAtForEnemy(c) == 'b') {
        out.println(name+" hit a battleship!");
      } else if (enemyBoard.whatIsAtForEnemy(c) == 's') {
        out.println(name+" hit a submarine!");
      } else if (enemyBoard.whatIsAtForEnemy(c) == 'c') {
        out.println(name+" hit a carrier!");
      } else if (enemyBoard.whatIsAtForEnemy(c) == 'd') {
        out.println(name+" hit a destroyer!");
      } else {
        out.println(name+" Missed!");
      }
    fireList.add(c);
  }

  private void createPlacementList() {
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
    for(int i=0;i<theBoard.getHeight();i++){
      for(int j=0;j<theBoard.getWidth();j++){
        fireList.add(new Coordinate(i,j));
      }
    }
  }
}
