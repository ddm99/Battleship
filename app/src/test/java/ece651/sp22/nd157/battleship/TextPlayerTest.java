package ece651.sp22.nd157.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {
  @Test
  public void test_sonar_throws()throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "z4h", bytes);
    assertThrows(IllegalArgumentException.class,()->player.moveTargetShip());
    assertThrows(EOFException.class,()->player.moveTargetShip());
  }

  @Test
  public void test_play_with_choice_eof()throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "", bytes);
    assertThrows(EOFException.class,()->player.playwithChoice(player.theBoard,player.view));
  }
  
  @Test
  public void test_move_ship() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B4h\nb4\na4v\na2v", bytes);
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output8.txt");
    V2ShipFactory shipFactory = new V2ShipFactory();
    player.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    player.theBoard.fireAt(new Coordinate("B5"));
    player.moveTargetShip();
    player.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    String expected = new String(expectedStream.readAllBytes());
    String actual = bytes.toString();
    assertEquals(expected, actual);
    bytes.reset(); // clear out bytes for next time around
  }

  @Test
  public void test_errors() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B4h\nb8", bytes);
    V2ShipFactory shipFactory = new V2ShipFactory();
    player.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    assertThrows(IllegalArgumentException.class,()->player.moveTargetShip());
  }
  
  @Test
  public void test_play_one_turn_eof(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player1 = createTextPlayer(10, 20, "", bytes);
    Board<Character> b =new BattleShipBoard<Character>(10,20,'X');
    BoardTextView view = new BoardTextView(b);
    assertThrows(EOFException.class,() -> player1.playOneTurn(b, view));
  }
  
  @Test
  public void test_move_eof(){
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "", bytes);
    assertThrows(EOFException.class,()->player.moveTargetShip());
  }

  @Test
  public void test_move_placement_error() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "A0H\nA0\nA9H", bytes);
    V2ShipFactory shipFactory = new V2ShipFactory();
    player.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    assertThrows(IllegalArgumentException.class,()->player.moveTargetShip());
  }
  
  @Test
  public void test_play_one_turn()throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    ByteArrayOutputStream bytes2 = new ByteArrayOutputStream();
    TextPlayer player1 = createTextPlayer(10, 20, "A0H\nB0\nC0\nD0\nE0\nH0\nJ0\nZ0\nS0", bytes);
    TextPlayer player2 = createTextPlayer(10, 20, "A0H\nB0H\nC0H\nD0H\nE0H\nF0H\nG0H\nH0H\nI0H\nJ0H\n", bytes2);
    player2.doPlacementPhase();
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output3.txt");
    assertNotNull(expectedStream);
    player1.playOneTurn(player2.theBoard, player2.view);
    player1.playOneTurn(player2.theBoard, player2.view);
    player1.playOneTurn(player2.theBoard, player2.view);
    player1.playOneTurn(player2.theBoard, player2.view);
    player1.playOneTurn(player2.theBoard, player2.view);
    player1.playOneTurn(player2.theBoard, player2.view);
    player1.playOneTurn(player2.theBoard, player2.view);
    String expected = new String(expectedStream.readAllBytes());
    String actual = bytes.toString();
    assertEquals(expected, actual);
    bytes.reset(); // clear out bytes for next time around
  }
  
  @Test
  void test_do_Placement_phase() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "A0H\nB0H\nC0H\nD0H\nE0H\nF0H\nG0H\nH0H\nI0H\nJ0H\n", bytes);
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output1.txt");
    assertNotNull(expectedStream);
    player.doPlacementPhase();
    String expected = new String(expectedStream.readAllBytes());
    String actual = bytes.toString();
    assertEquals(expected, actual);
    bytes.reset(); // clear out bytes for next time around
  }
  
  @Test
  void test_read_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);
    String prompt = "Please enter a location for a ship:";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');
    for (int i = 0; i < expected.length; i++) {
      Placement p = player.readPlacement(prompt);
      assertEquals(p, expected[i]); // did we get the right Placement back
      assertEquals(prompt + "\n", bytes.toString()); // should have printed prompt and newline
      bytes.reset(); // clear out bytes for next time around
    }
  }

  @Test
  public void test_do_one_placement() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B8H\na4v\n", bytes);
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output2.txt");
    assertNotNull(expectedStream);
    V1ShipFactory shipFactory = new V1ShipFactory();
    player.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p));
    String expected = new String(expectedStream.readAllBytes());
    String actual = bytes.toString();
    assertEquals(expected, actual);
    bytes.reset(); // clear out bytes for next time around
  }

  @Test
  public void test_read_eof() throws IOException {
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "", bytes);
    InputStream expectedStream = getClass().getClassLoader().getResourceAsStream("output4.txt");
    assertNotNull(expectedStream);
    V1ShipFactory shipFactory = new V1ShipFactory();
    assertThrows(EOFException.class,() -> player.doOnePlacement("Destroyer", (p) -> shipFactory.makeDestroyer(p)));
  }

  private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h,'X');
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("A", board, input, output, shipFactory);
  }
}
