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
}
