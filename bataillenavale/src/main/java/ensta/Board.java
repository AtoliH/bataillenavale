package ensta;

public class Board {
    private String nom;
    private Character[][] navires;
    private boolean[][] frappes;

    public Board (String nomVoulu, int taille) {
        this.nom = nomVoulu;
        navires = new Character[taille][taille];
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
}
