package ensta;

public class Battleship extends AbstractShip {
    public Battleship(Orientation orientation) {
        super("Battleship", "B", 4, orientation);
    }

    public Battleship() {
        this(Orientation.EST);
    }
}
