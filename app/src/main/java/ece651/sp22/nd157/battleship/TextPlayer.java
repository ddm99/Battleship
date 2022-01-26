package ece651.sp22.nd157.battleship;

import java.io.BufferedReader;
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
  private String name;
  final ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;

  public TextPlayer(String name, Board<Character> theBoard, Reader inputSource, PrintStream out,
      AbstractShipFactory<Character> shipFactory
      ) {
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
    //this.inputReader = new BufferedReader(inputSource);
    this.out = out;
    this.shipsToPlace = new ArrayList<String>();
    this.shipCreationFns = new HashMap<String, Function<Placement,Ship<Character>>>();
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
    return new Placement(s);
  }

  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    /**
     * This method output a prompt and read input from user, then it uses
     * readPlacement() to place ship accordingly
     *
     * @throw Input or Output may fail
     */
    Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
    Ship<Character> s = createFn.apply(p);
    theBoard.tryAddShip(s);
    out.print(view.displayMyOwnBoard());
  }

  public void doPlacementPhase() throws IOException {
    /**
     * Display the prompt, and ask the user to do placement once
     *
     * @throw Input or Output may fail
     */
    out.print(view.displayMyOwnBoard());
    out.println("Player " + this.name + ":" + "you are going to place the following ships (which are all\n"
        + "rectangular). For each ship, type the coordinate of the upper left\n"
        + "side of the ship, followed by either H (for horizontal) or V (for\n"
        + "vertical).  For example M4H would place a ship horizontally starting\n"
        + "at M4 and going to the right.  You have\n");
    out.println("2 \"Submarines\" ships that are 1x2");
    out.println("3 \"Destroyers\" that are 1x3");
    out.println("3 \"Battleships\" that are 1x4");
    out.println("2 \"Carriers\" that are 1x6\n");
    for (int i = 0; i < shipsToPlace.size(); i++) {
      doOnePlacement(shipsToPlace.get(i), shipCreationFns.get(shipsToPlace.get(i)));
    }
  }
}
