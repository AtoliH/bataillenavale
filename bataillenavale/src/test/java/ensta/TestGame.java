package ensta;
import java.util.List;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests unitaires pour Game.
 */
public class TestGame {
    @Test
    public void main() {
        Board grille = new Board("Grille", 10);
        System.out.println(grille.print());

        AbstractShip[] navires = {
            new Destroyer(),
            new Submarine(),
            new Submarine(),
            new Battleship(),
            new Carrier()
        };

        BattleShipsAI ia = new BattleShipsAI(grille, grille);
        ia.putShips(navires);

        int naviresDetruits = 0;
        int[] coords = new int[2];

        while(naviresDetruits < 5) {
            Hit hit = ia.sendHit(coords);

            System.out.println("("+coords[0]+","+coords[1]+")");

            if (hit != Hit.MISS && hit != Hit.STRIKE) {
                naviresDetruits++;
                System.out.println(hit + " coulé");
            } else {
                System.out.println(hit);
            }

            System.out.println(grille.print());
            System.out.println(naviresDetruits);
        }
    }
}
