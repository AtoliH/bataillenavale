package ensta;

public class AbstractShip {

    public enum Orientation {
        NORD, SUD, EST, WEST
    }

    private String label;
    private String nom;
    private int taille;
    private Orientation orientation;

    protected AbstractShip(String nomVoulu, String labelVoulu, int tailleVoulue, Orientation orientationVoulue) {
        this.nom = nomVoulu;
        this.label = labelVoulu;
        this.taille = tailleVoulue;
        this.orientation = orientationVoulue;
    }
}

