package ensta;
import java.io.Serializable;
import java.util.List;

public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coordinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;

        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getLength());
            System.out.println(msg);

            boolean wrongInput;
            do {
                wrongInput = false;
                try {
                    InputHelper.ShipInput res = InputHelper.readShipInput();
                    
                    // set ship orientation
                    AbstractShip.Orientation orientation;
                    switch (res.orientation) {
                        case "n":
                            orientation = AbstractShip.Orientation.NORD;
                            break;
                        case "s":
                            orientation = AbstractShip.Orientation.SUD;
                            break;
                        case "e":
                            orientation = AbstractShip.Orientation.EST;
                            break;
                        case "w":
                            orientation = AbstractShip.Orientation.OUEST;
                        default:
                            throw new Exception("Unknown orientation");

                    }
                    s.setOrientation(orientation);

                    // put ship at given position
                    board.putShip(s, res.x, res.y);
                } catch (Exception e) {
                    wrongInput = true;
                }
            } while (wrongInput);

            // TODO when ship placement successful
            ++i;
            done = i == 5;

            // Affichage de la grille
            System.out.println(board.print());
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done = false;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // On redemande les coordonnées tant qu'elles ne sont pas valides
            if (hitInput.x > 0 && hitInput.y > 0 
                    && hitInput.x <= opponentBoard.getSize() 
                    && hitInput.y <= opponentBoard.getSize()) {
                // call sendHit on this.opponentBoard
                hit = this.opponentBoard.sendHit(hitInput.x,hitInput.y);
                
                // On retourne les coordonnées dans le tableau coords de telle façon que coords[0] contienne la valeur de x et coords[1] la valeur de y
                coords[0] = hitInput.x;
                coords[1] = hitInput.y;
 
                done = true;
            }
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
}
