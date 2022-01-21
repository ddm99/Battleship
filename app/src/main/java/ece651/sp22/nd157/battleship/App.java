/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ece651.sp22.nd157.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;

public class App {

  public static void main(String[] args) throws IOException {
    /**
     * The execution file for the game, it creates a board, then ask user to input
     * where to place the ship
     *
     * @params args is the argument that can be used to manipulate the program
     */
    Board<Character> board = new BattleShipBoard<>(10, 20);
    App app = new App(board, new InputStreamReader(System.in), System.out);
    app.doOnePlacement();
  }

  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;

  public App(Board<Character> theBoard, Reader inputSource, PrintStream out) {
    /**
     * Constructs the variable required to play the game
     *
     * @params theBoard is the board we want to use for the game
     * @params inputSource is how we read input from user
     * @params out is where we want to print the output of the game to
     */
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = new BufferedReader(inputSource);
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
     */
    Placement p = readPlacement("Where would you like to put your ship?");
    RectangleShip<Character> ship = new RectangleShip<Character>(p.getCoordinate(), 's', '*');
    theBoard.tryAddShip(ship);
    out.print(view.displayMyOwnBoard());
  }

}
