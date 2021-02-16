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
            String affichageNavires = "";
            for (int j = 0; j < taille; j++) {
                // Navires
                String shipLabel = ".";
                ShipState ss = navires[j][i-1];
                if (ss != null) {
                    shipLabel = ""+ss.getShip().getLabel();
                    if (ss.isStruck()) {
                        shipLabel = ColorUtil.colorize(shipLabel, ColorUtil.Color.RED);
                    }
                }

                // Frappes
                String label = ".";
                Boolean frappe = frappes[j][i-1];
                if (frappe != null) {
                    label = frappe? ColorUtil.colorize("X", ColorUtil.Color.RED) : "X";
                }
                affichageNavires += shipLabel + " ";
                affichageFrappes += label + " ";
            }

            if (i == 10)
                space = "";

            affichage += i+ space + affichageNavires + " "+ i+ space + affichageFrappes + "\n";
        }

        return affichage;
    }

    public int getSize() {
        return frappes.length;
    }

    public void putShip(AbstractShip ship, int x, int y) throws Exception {
        if (!canPutShip(ship, x, y)) {
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
    // Place le bateau dans le cas où il peut être placé
    private boolean canPutShip(AbstractShip ship, int x, int y) {
        // Si hors limite le bateau ne peut être placé
        if (x < 1 || y < 1 || x > taille || y > taille) {
            return false;
        }

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

        // On place le bateau maintenant que l'on est sûr que l'on peut le faire
        ix = x;
        iy = y;
        for (int i = 0; i < ship.getLength(); ++i) {
            navires[ix-1][iy-1] = new ShipState(ship);
            ix += dx;
            iy += dy;
        }

        return true;
    }

    public Hit sendHit(int x, int y) {
        System.out.println(x+","+y);
        if (navires[x-1][y-1] != null) {
            // Dans le cas où le navire aurait déjà été coulé ou touché on agit comme si cela n'avait pas encore été le cas, le joueur aura de nouveau l'information correspondant à l'état du navire (touché ou coulé)
            navires[x-1][y-1].addStrike();
            ShipState ss = navires[x-1][y-1];
            if (ss.isSunk()) {
                System.out.println(ss.getShip().getLabel() + " coulé");
                return Hit.fromInt(ss.getShip().getLength());
            }
            return Hit.STRIKE;
        }
        return Hit.MISS;    
    }
}
