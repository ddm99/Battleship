package ece651.sp22.nd157.battleship;

import java.util.HashSet;

public class RectangleShip {
  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height){
    int top = upperLeft.getRow();
    int left = upperLeft.getColumn();
    HashSet<Coordinate> newset = new HashSet<Coordinate>();
    for(int j=0;j<width;j++){
      for(int i=0;i<height;i++){
        Coordinate c = new Coordinate((top+i),(left+j));
        newset.add(c);
      }
    }
    return newset;
  }
}

