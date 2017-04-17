import java.util.Vector;

public class State {
    int[][] grid;
    int height;
    int player;
    int width;
    Vector<Moves> validMoves = new Vector<>();
    int value;
}
