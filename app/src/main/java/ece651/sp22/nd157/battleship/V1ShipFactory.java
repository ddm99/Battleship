package ece651.sp22.nd157.battleship;

/**
 * This class is used to create ships appear in Version 1 of this game
 */
public class V1ShipFactory implements AbstractShipFactory<Character> {

  protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name) {
    /**
     * Helper function to create ship from input info If the orientation of the ship
     * is horizontal, exchange w and h
     *
     * @params where coordinate of the ship
     * @params w width of the ship
     * @params h height of the ship
     * @params letter is the symbol represents the ship on board
     * @params name is the name of the type of ship
     * @throw throw exception if orientation entered is nither h or v
     */
    if (where.getOrientation() == 'H') {
      int temp = w;
      w = h;
      h = temp;
    } else if (where.getOrientation() != 'V') {
      throw new IllegalArgumentException("Incorrect orientation!");
    }
    RectangleShip<Character> s = new RectangleShip<Character>(name, where.getCoordinate(), w, h, letter, '*');
    return s;
  }

  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    // create a submarine
    return createShip(where, 1, 2, 's', "Submarine");
  }

  @Override
  public Ship<Character> makeBattleship(Placement where) {
    // create a battleship
    return createShip(where, 1, 4, 'b', "Battleship");
  }

  @Override
  public Ship<Character> makeCarrier(Placement where) {
    // create a carrier
    return createShip(where, 1, 6, 'c', "Carrier");
  }

  @Override
  public Ship<Character> makeDestroyer(Placement where) {
    // create a destroyer
    return createShip(where, 1, 3, 'd', "Destroyer");
  }

}
