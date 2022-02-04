/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ece651.sp22.nd157.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
  private TextPlayer player1;
  private TextPlayer player2;

  public App(TextPlayer p1, TextPlayer p2) {
    this.player1 = p1;
    this.player2 = p2;
  }

  public static void main(String[] args) throws IOException {
    /**
     * The execution file for the game, it creates a board, initialize two players
     * and ask them where to place the ship
     *
     * @params args is the argument that can be used to manipulate the program
     */
    try{
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    TextPlayer p1 = makePlayer(input,"A");
    TextPlayer p2 = makePlayer(input,"B");
    App app = new App(p1, p2);
    app.doPlacementPhase();
    app.doAttackingPhase();
    }catch(EOFException e){
      return;
    }
  }

  public void doPlacementPhase() throws IOException {
    /**
     * use the textplayer's method to prompt both users to place a ship
     */
    player1.doPlacementPhase();
    player2.doPlacementPhase();
  }

  protected void doRound(TextPlayer me,TextPlayer enemy) throws IOException {
    me.out.println("Player " + me.name + "'s turn:");
    if(me instanceof ComPlayer){
    }else{
    me.out.println(me.view.displayMyBoardWithEnemyNextToIt(enemy.view, "Your Ocean", "Player" + enemy.name+"'s Ocean"));
    }
    me.playwithChoice(enemy.theBoard, enemy.view);
    me.printDivider();
  }

  static public TextPlayer makePlayer(BufferedReader inputReader,String name) throws IOException{
    V2ShipFactory factory = new V2ShipFactory();
    Board<Character> b = new BattleShipBoard<Character>(10, 20, 'X');
    System.out.println("Player "+name+": enter 'C' for compueter, enter'P' to play yourself");
    while(true){
    String s = inputReader.readLine();
    if(s==null){
      throw new EOFException();
    }
    if(s.equals("P")){
    return new TextPlayer(name, b, inputReader, System.out, factory);
    }else if(s.equals("C")){
    return new ComPlayer(name, b, inputReader, System.out, factory);
    }else{
      System.out.println("Wrong input, please enter 'P' for human player, 'C' for computer player:");
    }
    }
  }

  public void doAttackingPhase() throws IOException {
    while (true) {
      doRound(player1,player2);
      if (player2.theBoard.isLost()) {
        player2.out.println("Player "+player1.name+" Win!");
        player1.out.println("Player "+player2.name+" Lost!");
        return;
      }
      doRound(player2,player1);
      if (player1.theBoard.isLost()) {
        player1.out.println("Player "+player2.name+" Win!");
        player2.out.println("Player "+player1.name+" Lost!");
        return;
      }
    }
  }
}
