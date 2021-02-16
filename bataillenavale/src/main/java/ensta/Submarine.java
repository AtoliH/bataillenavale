package ensta;

public class Submarine extends AbstractShip {
    public Submarine(Orientation orientation) {
        super("Sous-marin", 'S', 3, orientation);
    }

    public Submarine() {
        this(Orientation.EST);
    }
}
