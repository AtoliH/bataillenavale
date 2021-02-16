package ensta;

public class AbstractShip {

    public enum Orientation {
        NORD, SUD, EST, OUEST
    }

    private char label;
    private String nom;
    private int taille;
    private Orientation orientation;

    private int strikeCount;

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

    public String getName() {
        return nom;
    }

    public int getLength() {
        return taille;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientationVoulue) {
        this.orientation = orientationVoulue;
    }

    public void addStrike() {
        this.strikeCount++;
    }

    public boolean isSunk() {
        return strikeCount == taille;
    }
}

