package ensta;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests unitaires pour Game.
 */
public class TestGame {
    @Test
    public void main() {
        Board grille = new Board(10);
        System.out.println(board.print());

        AbstractShip[] navires = {
            new Destroyer(),
            new Submarine(),
            new Submarine(),
            new Battleship(),
            new Carrier()
        };

        BattleShipsAI ia = new BattleShipsAI(grille, grille);

        int naviresDetruits = 0;
        int coords[];

        while(naviresDetruits < 5) {
            Hit hit = ia.sendHit(coords);

            System.out.println("("+coords[0]+","+coords[1]+")");

            if (hit != Hit.MISS && hit != Hit.STRIKE)
                System.out.println(hit + " coulé");
            else
                System.out.println(hit);

            System.out.println(grille.print());
        }
    }
}
