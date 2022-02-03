package ece651.sp22.nd157.battleship;

public class V2ShipFactory implements AbstractShipFactory<Character> {
   protected RectangleShip<Character> createShip(Placement where, int w, int h, char letter, String name) {
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
  public RectangleShip<Character> makeSubmarine(Placement where) {
    // Create a Submarine
    return createShip(where, 1, 2, 's', "Submarine");
  }

  @Override
  public TypeBattleShip<Character> makeBattleship(Placement where) {
    // create a battleship
    return new TypeBattleShip<Character>("Battleship", where.getCoordinate(), where.getOrientation(),'b', '*');
  }

  @Override
  public TypeCarrier<Character> makeCarrier(Placement where) {
    // create a carrier
    return new TypeCarrier<Character>("Carrier", where.getCoordinate(), where.getOrientation(), 'c', '*');
  }

  @Override
  public RectangleShip<Character> makeDestroyer(Placement where) {
    // Create a Destroyer
    return createShip(where, 1, 3, 'd', "Destroyer");
  }

}
