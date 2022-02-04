package ece651.sp22.nd157.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

public class TextPlayer {
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;
  final AbstractShipFactory<Character> shipFactory;
  private int MoveCounter;
  private int ScanCounter;
  String name;
  final ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;

  public TextPlayer(String name, Board<Character> theBoard, Reader inputSource, PrintStream out,
      AbstractShipFactory<Character> shipFactory) {
    /**
     * Constructs the class with name of the player
     *
     * @param name        is the name of this player
     * @param theBoard    is the board we want to use for the game
     * @param inputSource is how we read input from user
     * @param out         is where we want to print the output of the game to
     * @params shipsToPlace the type of ship we are going to place
     * @param shipCreationFns the function to generate the given type ship
     */
    this.name = name;
    this.shipFactory = shipFactory;
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = (BufferedReader) inputSource;
    // this.inputReader = new BufferedReader(inputSource);
    this.MoveCounter =3;
    this.ScanCounter =3;
    this.out = out;
    this.shipsToPlace = new ArrayList<String>();
    this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
    setupShipCreationList();
    setupShipCreationMap();
  }

  protected void setupShipCreationMap() {
    /**
     * The Shortcut to setup the given typed ship
     */
    shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
    shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
    shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
  }

  protected void setupShipCreationList() {
    /**
     * set up the list of ship we want to create
     */
    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
  }

  public Placement readPlacement(String prompt) throws IOException {
    /**
     * Read from Input and parse it to the placement of the ship
     *
     * @params prompt is the input string we read from user
     */
    out.println(prompt);
    String s = inputReader.readLine();
    if (s == null) {
      throw new EOFException();
    }
    return new Placement(s);
  }

  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    /**
     * This method output a prompt and read input from user, then it uses
     * readPlacement() to place ship accordingly
     *
     * @throw Input or Output may fail
     */
    try {
      Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
      Ship<Character> s = createFn.apply(p);
      String placementProblem = theBoard.tryAddShip(s);
      if (placementProblem != null) {
        out.println(placementProblem);
        doOnePlacement(shipName, createFn);
      } else {
        printDivider();
        out.print(view.displayMyOwnBoard());
        printDivider();
      }
    } catch (IllegalArgumentException e) {
      out.println(e.getMessage());
      doOnePlacement(shipName, createFn);
    } catch (EOFException eof) {
      out.println("placement phase ended!");
      throw new EOFException();
    }
  }
  
  public void printDivider(){
    out.println("---------------------------------------------------------------------------");
  }

  public void doPlacementPhase() throws IOException {
    /**
     * Display the prompt, and ask the user to do placement once
     *
     * @throw Input or Output may fail
     */
    out.print(view.displayMyOwnBoard());
    printDivider();
    out.println("Player " + this.name + ":" + "you are going to place the following ships (which are all\n"
        + "rectangular). For each ship, type the coordinate of the upper left\n"
        + "side of the ship, followed by either H (for horizontal) or V (for\n"
        + "vertical).  For example M4H would place a ship horizontally starting\n"
        + "at M4 and going to the right.  You have\n");
    out.println("2 \"Submarines\" ships that are 1x2");
    out.println("3 \"Destroyers\" that are 1x3");
    out.println("3 \"Battleships\" that are 1x4");
    out.println("2 \"Carriers\" that are 1x6\n");
    printDivider();
    for (int i = 0; i < shipsToPlace.size(); i++) {
      doOnePlacement(shipsToPlace.get(i), shipCreationFns.get(shipsToPlace.get(i)));
    }
  }

  public Coordinate readCoords(String prompt) throws IOException {
    /**
     * Prompt the user to input a coordinate and try to convert the input to
     * Coordinate
     */
    out.println(prompt);
    String s = inputReader.readLine();
    if (s == null) {
      throw new EOFException("No more input to read!");
    }
    Coordinate c = new Coordinate(s);
    if ((c.getRow() >= theBoard.getHeight()) || (c.getRow() < 0) || (c.getColumn() < 0)
        || (c.getColumn() >= theBoard.getWidth())) {
      throw new IllegalArgumentException("The Coordinate you fire at is off the board!");
    }
    return c;
  }

  public void playOneTurn(Board<Character> enemyBoard, BoardTextView enemyBoardView) throws IOException {
    try {
      Coordinate c = readCoords("Attacking Phase");
      enemyBoard.fireAt(c);
      if (enemyBoard.whatIsAtForEnemy(c) == 'b') {
        out.println("You hit a battleship!");
      } else if (enemyBoard.whatIsAtForEnemy(c) == 's') {
        out.println("You hit a submarine!");
      } else if (enemyBoard.whatIsAtForEnemy(c) == 'c') {
        out.println("You hit a carrier!");
      } else if (enemyBoard.whatIsAtForEnemy(c) == 'd') {
        out.println("You hit a destroyer!");
      } else {
        out.println("You Missed!");
      }
      printDivider();
    } catch (IllegalArgumentException e) {
      out.println(e.getMessage());
      playOneTurn(enemyBoard, enemyBoardView);
    } catch (EOFException e) {
      out.println(e.getMessage());
      throw new EOFException();
    }
  }

  public void playwithChoice(Board<Character> enemyBoard, BoardTextView enemyBoardView)throws IOException{
    out.println("Possible actions for Player "+name+":");
    out.println();
    out.println("F Fire at a square");
    out.println("M Move a ship to another square ("+MoveCounter+" remaining)");
    out.println("S Sonar scan ("+ScanCounter+" remaining)");
    out.println();
    out.println("Player "+name+", what would you like to do?");
    printDivider();
    try{
    String s = inputReader.readLine();
    if (s == null) {
      throw new EOFException();
    }
    if(s.equals("F")){
      playOneTurn(enemyBoard, enemyBoardView);
    }else if(s.equals("M")&&(MoveCounter>0)){
      moveTargetShip();
    }else if(s.equals("S")&&(ScanCounter>0)){
      useSonar(enemyBoard);
    }else{
      throw new IllegalArgumentException("Incorrect input for choices, please select again");
    }
    }catch(IllegalArgumentException e){
      out.println(e.getMessage());
      playwithChoice(enemyBoard, enemyBoardView);
    }
  }

  public void moveTargetShip() throws IOException {
    Ship<Character> s=null;
    try {
      Coordinate c = readCoords("Player " + name +" Please enter the location of the ship you want to move");
      s =theBoard.findMoveShip(c);
      Placement p = readPlacement("Player " + name + " where do you want to move "+s.getName()+" to?");
      Function<Placement, Ship<Character>> createFn = shipCreationFns.get(s.getName());
      Ship<Character> s1 = createFn.apply(p);
      s1.updateHitInfo(s);
      String placementProblem = theBoard.tryAddShip(s1);
      if (placementProblem != null) {
        throw new IllegalArgumentException("placementProblem");
      }
      MoveCounter--;
    } catch (IllegalArgumentException e) {
      if(s!=null){
        theBoard.tryAddShip(s);
      }
      throw e;
    } catch (EOFException e) {
      out.println(e.getMessage());
      throw e;
    }
  }

  public void useSonar(Board<Character> enemyBoard)throws IOException{
    try{
    Coordinate c = readCoords("Player " + name +" Please enter the location of your sonar scan");
    ArrayList<Integer> result=enemyBoard.sonarScan(c);
    out.println("Submarines occupy "+result.get(0)+" squares");
    out.println("Destroyers occupy "+result.get(1)+" squares");
    out.println("BattleShips occupy "+result.get(2)+" squares");
    out.println("Carriers occupy "+result.get(3)+" squares");
    printDivider();
    ScanCounter--;
    }catch(IllegalCallerException e){
      throw e;
    }catch(EOFException e){
      out.println(e.getMessage());
      throw e;
    }
  }

}
