package ensta;

public class ShipState {
    private AbstractShip navire;
    private boolean struck;

    public ShipState(AbstractShip navire) {
        this.navire = navire;
    }

    public void addStrike() {
        // Le navire ne peut pas être touché deux fois
        // Dans le cas ou deux coups seraient joués au même endroit, le deuxième coup n'a pas d'effet.
        if (!struck) {
            navire.addStrike();
            struck = true;
        }
    }

    public boolean isStruck() {
        return struck;
    }

    public String toString() {
        return ColorUtil.colorize(navire.getLabel(), ColorUtil.Color.RED);
    }

    public boolean isSunk() {
        return navire.isSunk();
    }

    public AbstractShip getShip() {
        return navire;
    }
}
