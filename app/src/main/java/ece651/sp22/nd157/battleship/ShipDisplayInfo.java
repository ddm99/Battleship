package ece651.sp22.nd157.battleship;

public interface ShipDisplayInfo<T> {
public T getInfo(Coordinate where, boolean hit);
}
