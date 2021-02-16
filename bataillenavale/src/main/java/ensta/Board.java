package ensta;

public class Board implements IBoard {
    private String nom;
    private ShipState[][] navires;
    private Boolean[][] frappes;
    private int taille;

    public Board (String nomVoulu, int tailleVoulue) {
        this.nom = nomVoulu;
        this.taille = tailleVoulue;
        navires = new ShipState[taille][taille];
        frappes = new Boolean[taille][taille];
    }

    public Board (String nomVoulu) {
        this(nomVoulu, 10);
    }

    public String print () {
        String affichage = "Navires :" + " ".repeat(14) + "Frappes:\n";
        affichage += "  " + "A B C D E F G H I J" + "    " + "A B C D E F G H I J\n";
        
        for (int i = 1; i <= taille; i++) {
            String space = " "; // L'espace entre les numéros de ligne et la grille affichée
            String affichageFrappes = "";
            for (int j = 0; j < taille; j++) {
                String label = ".";
                Boolean frappe = frappes[i-1][j];
                if (frappe != null) {
                    label = frappe? ColorUtil.colorize("X", ColorUtil.Color.RED) : "X";
                }
                affichageFrappes += label + " ";
            }

            if (i == 10)
                space = "";

            affichage += i+ space + ". ".repeat(10) + " "+ i+ space + affichageFrappes + "\n";
        }

        return affichage;
    }

    public int getSize() {
        return frappes.length;
    }

    public void putShip(AbstractShip ship, int x, int y) throws Exception {
        if (canPutShip(ship, x, y)) {
            navires[x-1][y-1] = new ShipState(ship);
        } else {
            // Si le bateau ne peut pas être placé on lance un exception qui devra être récupérée plus haut.
            throw new Exception("Le bateau ne peut pas être placé ici.");
        }
    }

    public boolean hasShip(int x, int y) {
        return navires[x-1][y-1] != null;
    }

    public void setHit(boolean hit, int x, int y) {
        frappes[x-1][y-1] = hit;
    }

    public Boolean getHit(int x, int y) {
        return frappes[x-1][y-1];   
    }

    // Pour vérifier que le bateau peut en effet être placé à un certain emplacement compte tenu des limites de la grille et des potentielles collisions avec des bateaux déjà présents.
    private boolean canPutShip(AbstractShip ship, int x, int y) {
        AbstractShip.Orientation o = ship.getOrientation();
        int dx = 0, dy = 0;
        if (o == AbstractShip.Orientation.EST) {
            if (x + ship.getLength() >= this.taille) {
                return false;
            }
            dx = 1;
        } else if (o == AbstractShip.Orientation.SUD) {
            if (y + ship.getLength() >= this.taille) {
                return false;
            }
            dy = 1;
        } else if (o == AbstractShip.Orientation.NORD) {
            if (y + 1 - ship.getLength() < 0) {
                return false;
            }
            dy = -1;
        } else if (o == AbstractShip.Orientation.OUEST) {
            if (x + 1 - ship.getLength() < 0) {
                return false;
            }
            dx = -1;
        }

        int ix = x;
        int iy = y;

        for (int i = 0; i < ship.getLength(); ++i) {
            if (this.hasShip(ix, iy)) {
                return false;
            }
            ix += dx;
            iy += dy;
        }

        return true;
    }
}
