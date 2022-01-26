package ece651.sp22.nd157.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;

public class TextPlayer {
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;
  final AbstractShipFactory<Character> shipFactory;
  private String name;

  public TextPlayer(String name, Board<Character> theBoard, Reader inputSource, PrintStream out,
      AbstractShipFactory<Character> shipFactory) {
    /**
     * Constructs the class with name of the player
     *
     * @param name        is the name of this player
     * @param theBoard    is the board we want to use for the game
     * @param inputSource is how we read input from user
     * @param out         is where we want to print the output of the game to
     */
    this.name = name;
    this.shipFactory = shipFactory;
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = (BufferedReader) inputSource;
    this.out = out;
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

  public void doOnePlacement() throws IOException {
    /**
     * This method output a prompt and read input from user, then it uses
     * readPlacement() to place ship accordingly
     *
     * @throw Input or Output may fail
     */
    StringBuilder prompt = new StringBuilder("");
    prompt.append("Player ");
    prompt.append(name);
    prompt.append(" where do you want to place a Destroyer?");
    Placement p = readPlacement(prompt.toString());
    Ship<Character> s = shipFactory.makeDestroyer(p);
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
    doOnePlacement();
  }
}
