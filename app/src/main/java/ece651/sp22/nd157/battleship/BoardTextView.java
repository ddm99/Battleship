package ece651.sp22.nd157.battleship;

import java.util.function.Function;

/**
 * This class handles textual display of a Board (i.e., converting it to a
 * string to show to the user). It supports two ways to display the Board: one
 * for the player's own board, and one for the enemy's board.
 */
public class BoardTextView {
  /**
   * The Board to display
   */
  private final Board<Character> toDisplay;

  public BoardTextView(Board<Character> toDisplay) {
    /**
     * Constructs a BoardView, given the board it will display.
     * 
     * @param toDisplay is the Board to display
     * @throws IllegalArgumentException if the board is larger than 10x26.
     */
    this.toDisplay = toDisplay;
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException(
          "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }
  }
  
  public String displayMyOwnBoard() {
    return displayAnyBoard((c)->toDisplay.whatIsAtForSelf(c));
  }

  public String displayEnemyBoard(){
    return displayAnyBoard((c)->toDisplay.whatIsAtForEnemy(c));
  }

  protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn){
    /**
     * Display the board according to the rules, including axis labels and ship info
     */
    final char base = 'A';
    StringBuilder ans = new StringBuilder("");
    ans.append(makeHeader());
    for (int i = 0; i < toDisplay.getHeight(); i++) {
      String sep = "";
      char col_num = (char) (base + i);
      StringBuilder line = new StringBuilder("");
      line.append(col_num);
      line.append(" ");
      for (int j = 0; j < toDisplay.getWidth(); j++) {
        line.append(sep);
        if (getSquareFn.apply(new Coordinate(i, j)) != null) {
          line.append(getSquareFn.apply(new Coordinate(i,j)));
        } else {
          line.append(" ");
        }
        sep = "|";
      }
      line.append(" ");
      line.append(col_num);
      line.append("\n");
      ans.append(line);
    }
    ans.append(makeHeader());
    return ans.toString();
  }

  public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {
    String myBoard = this.displayMyOwnBoard();
    String enemyBoard = enemyView.displayEnemyBoard();
    String [] mylines = myBoard.split("\n");
    String [] enemylines = enemyBoard.split("\n");
    StringBuilder ans = new StringBuilder("");
    ans.append(makeLabel(myHeader, enemyHeader));
    for (int i = 0; i<mylines.length;i++){
      ans.append(mylines[i]);
      if((i == 0)||(i==mylines.length-1)){
        ans.append("  ");
      }
      ans.append("                ");
      ans.append(enemylines[i]);
      ans.append("\n");
    }
    return ans.toString();
  }

  String makeLabel(String myHeader, String enemyHeader){
    StringBuilder ans = new StringBuilder("");
    ans.append("     ");
    ans.append(myHeader);
    int spacebtw = 2 * toDisplay.getWidth() + 22 -5 -myHeader.length();
    for(int i =0;i<spacebtw;i++){
      ans.append(" ");
    }
    ans.append(enemyHeader);
    ans.append("\n");
    return ans.toString();
  }
  
  String makeHeader() {
    /**
     * This makes the header line, e.g. 0|1|2|3|4\n
     * 
     * @return the String that is the header line for the given board
     */
    StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
    String sep = ""; // start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }
}
