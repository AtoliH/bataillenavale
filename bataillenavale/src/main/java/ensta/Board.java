package ensta;

public class Board implements IBoard {
    private String nom;
    private char[][] navires;
    private boolean[][] frappes;

    public Board (String nomVoulu, int taille) {
        this.nom = nomVoulu;
        navires = new char[taille][taille];
        frappes = new boolean[taille][taille];
    }

    public Board (String nomVoulu) {
        this(nomVoulu, 10);
    }

    public String print () {
        String affichage = "Navires :" + " ".repeat(14) + "Frappes:\n";
        affichage += "  " + "A B C D E F G H I J" + "    " + "A B C D E F G H I J\n";
        
        for (int i = 1; i < 10; i++)
            affichage += i+" " + ". ".repeat(10) + " "+ i+" " + ". ".repeat(10) + "\n";

        for (int i = 10; i <= frappes.length ; i++)
            affichage += i + ". ".repeat(10) + " "+ i + ". ".repeat(10) + "\n";

        return affichage;
    }

    public int getSize() {
        return frappes.length;
    }

    public void putShip(AbstractShip ship, int x, int y) {
        navires[x-1][y-1] = ship.getLabel();
    }

    public boolean hasShip(int x, int y) {
        return navires[x-1][y-1] != 0;
    }

    public void setHit(boolean hit, int x, int y) {
        frappes[x-1][y-1] = hit;
    }

    public Boolean getHit(int x, int y) {
        return frappes[x-1][y-1];   
    }
}
