package ensta;

public class AbstractShip {

    public enum Orientation {
        NORD, SUD, EST, WEST
    }

    private char label;
    private String nom;
    private int taille;
    private Orientation orientation;

    protected AbstractShip(String nomVoulu, char labelVoulu, int tailleVoulue, Orientation orientationVoulue) {

        if (labelVoulu == 0)
            throw new IllegalArgumentException("Caractère null non autorisé comme label");

        this.nom = nomVoulu;
        this.label = labelVoulu;
        this.taille = tailleVoulue;
        this.orientation = orientationVoulue;
    }

    public char getLabel() {
        return label;
    }
}

