package ensta;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class TestBoard {
    @Test
    public void main() {
        String affichage = "Navires :" + " ".repeat(14) + "Frappes:\n";
        affichage += "  " + "A B C D E F G H I J" + "    " + "A B C D E F G H I J\n";

        for (int i = 1; i < 10; i++)
            affichage += i+" " + ". ".repeat(10) + " "+ i+" " + ". ".repeat(10) + "\n";

        for (int i = 10; i <= 10 ; i++)
            affichage += i + ". ".repeat(10) + " "+ i + ". ".repeat(10) + "\n";

        Board grille = new Board("Grille", 10);
        assertTrue(affichage.equals(grille.print()));
    }

    @Test
    public void testSendHit() throws Exception {
        Board grille = new Board("Grille", 10);
        Destroyer navire = new Destroyer(AbstractShip.Orientation.SUD);
        grille.putShip(navire, 1, 1);

        String affichage;
        
        // un destroyer non touché
        affichage = "Navires :" + " ".repeat(14) + "Frappes:\n";
        affichage += "  " + "A B C D E F G H I J" + "    " + "A B C D E F G H I J\n";

        affichage += 1+" " + "D " + ". ".repeat(9) + " "+ 1+" "  + ". ".repeat(10)  + "\n";
        affichage += 2+" " + "D " + ". ".repeat(9) + " "+ 2+" " + ". ".repeat(10)  + "\n";
        for (int i = 3; i < 10; i++)
            affichage += i+" " + ". ".repeat(10) + " "+ i+" " + ". ".repeat(10) + "\n";

        for (int i = 10; i <= 10 ; i++)
            affichage += i + ". ".repeat(10) + " "+ i + ". ".repeat(10) + "\n";

        assertTrue(affichage.equals(grille.print()));
        
        // un destroyer touché une fois
        affichage = "Navires :" + " ".repeat(14) + "Frappes:\n";
        affichage += "  " + "A B C D E F G H I J" + "    " + "A B C D E F G H I J\n";

        affichage += 1+" " + ColorUtil.colorize("D", ColorUtil.Color.RED) + " " + ". ".repeat(9) + " "+ 1+" " + ColorUtil.colorize("X", ColorUtil.Color.RED) + " " + ". ".repeat(9)  + "\n";
        affichage += 2+" " + "D " + ". ".repeat(9) + " "+ 2+" " + ". ".repeat(10)  + "\n";
        for (int i = 3; i < 10; i++)
            affichage += i+" " + ". ".repeat(10) + " "+ i+" " + ". ".repeat(10) + "\n";

        for (int i = 10; i <= 10 ; i++)
            affichage += i + ". ".repeat(10) + " "+ i + ". ".repeat(10) + "\n";

        assertTrue(grille.sendHit(1,1) == Hit.STRIKE);
        assertTrue(affichage.equals(grille.print()));
        
        // un destroyer coulé
        affichage = "Navires :" + " ".repeat(14) + "Frappes:\n";
        affichage += "  " + "A B C D E F G H I J" + "    " + "A B C D E F G H I J\n";


        affichage += 1+" " + ColorUtil.colorize("D", ColorUtil.Color.RED) + " " + ". ".repeat(9) + " "+ 1+" " + ColorUtil.colorize("X", ColorUtil.Color.RED) + " " + ". ".repeat(9)  + "\n";
        affichage += 2+" " + ColorUtil.colorize("D", ColorUtil.Color.RED) + " " + ". ".repeat(9) + " "+ 2+" " + ColorUtil.colorize("X", ColorUtil.Color.RED) + " " + ". ".repeat(9)  + "\n";
        for (int i = 3; i < 10; i++)
            affichage += i+" " + ". ".repeat(10) + " "+ i+" " + ". ".repeat(10) + "\n";

        for (int i = 10; i <= 10 ; i++)
            affichage += i + ". ".repeat(10) + " "+ i + ". ".repeat(10) + "\n";

        assertTrue(grille.sendHit(1,2) == Hit.DESTROYER);
        assertTrue(affichage.equals(grille.print()));

        // un destroyer coulé et un coup dans l'eau
        affichage = "Navires :" + " ".repeat(14) + "Frappes:\n";
        affichage += "  " + "A B C D E F G H I J" + "    " + "A B C D E F G H I J\n";


        affichage += 1+" " + ColorUtil.colorize("D", ColorUtil.Color.RED) + " " + ColorUtil.colorize(".", ColorUtil.Color.RED) + " " + ". ".repeat(8) + " "+ 1+" " + ColorUtil.colorize("X", ColorUtil.Color.RED) + " " + "X " + ". ".repeat(8)  + "\n";
        affichage += 2+" " + ColorUtil.colorize("D", ColorUtil.Color.RED) + " " + ". ".repeat(9) + " "+ 2+" " + ColorUtil.colorize("X", ColorUtil.Color.RED) + " " + ". ".repeat(9)  + "\n";
        for (int i = 3; i < 10; i++)
            affichage += i+" " + ". ".repeat(10) + " "+ i+" " + ". ".repeat(10) + "\n";

        for (int i = 10; i <= 10 ; i++)
            affichage += i + ". ".repeat(10) + " "+ i + ". ".repeat(10) + "\n";

        assertTrue(grille.sendHit(2,1) == Hit.MISS);
        assertTrue(affichage.equals(grille.print()));
    }
}
