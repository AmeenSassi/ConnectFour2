import java.util.Vector;

public class Solver {

    private static final int MININFIN = -1000000;
    private static final int MAXINFIN = 1000000;

    private boolean checkValid(State grid, int x){
        if(grid.grid[x][0] != 0){
            return false;
        }
        return true;
    }

    private Vector<Moves> actions(State grid){
        Vector<Moves> moves = new Vector<>();
        for (int i = 0; i < grid.width; i++){
            Moves m = new Moves();
            if(checkValid(grid, i)){
                for(int j = 0; j < grid.height; j++){
                    if(grid.grid[i][j] != 0){
                        m.x = i;
                        m.y = j - 1;
                        moves.add(m);
                        j = grid.height;
                    }
                    else if(j == (grid.height - 1)){
                        m.x = i;
                        m.y = j;
                        moves.add(m);
                    }
                }
            }
        }
        return moves;
    }

    public State makeState(Board grid){
        State state = new State();
        state.height = grid.height;
        state.width = grid.width;
        int[][] temp = new int[grid.width][grid.height];
        for (int i = 0; i < grid.height; i++) {
            for (int j = 0; j < grid.width; j++) {
                temp[j][i] = grid.grid[j][i];
            }
        }
        state.grid = temp;
        state.player = grid.player;
        state.validMoves = actions(state);
        return state;
    }

    private State doMove(int player, State temp, Moves move ){
        temp.grid[move.x][move.y] = player;
        return temp;
    }

    private State results(State grid, Moves moves){
        State state = new State();
        state.height = grid.height;
        state.width = grid.width;
        int[][] temp = new int[grid.width][grid.height];
        for (int i = 0; i < grid.height; i++) {
            for (int j = 0; j < grid.width; j++) {
                temp[j][i] = grid.grid[j][i];
            }
        }
        state.grid = temp;
        state = doMove(grid.player, state, moves);
        state.validMoves = actions(state);
        return state;
    }

    private int checkLeft(State state, int token, int x, int y){
        int count = 0;
        if(x >= 4) {
            for (int i = 0; i < 4; i++) {
                if (!(((x - i) == 0) || (state.grid[x - i][y] != token))) {
                    count++;
                    return count;
                }
            }
        }
        return count;
    }

    private int checkRight(State state, int token, int x, int y){
        int count = 0;
        if((state.width - x) >= 4) {
            for (int i = 0; i < 4; i++) {
                if (!(((x + i) == (state.width - 1)) || (state.grid[x + i][y] != token))) {
                    count++;
                    return count;
                }
            }
        }
        return count;
    }

    private int checkUp(State state, int token, int x, int y){
        int count = 0;
        for(int i = 0; i < 4; i++) {
            if(((y-i) == 0) || (state.grid[x][y-i] != token)){
                count++;
                return count;
            }
        }
        return count;
    }

    private int checkDown(State state, int token, int x, int y){
        int count = 0;
        for(int i = 0; i < 4; i++) {
            if(((y+i) == (state.height-1)) || (state.grid[x][y+i] != token)){
                count++;
                return count;
            }
        }
        return count;
    }

    private int checkDiagUR(State state, int token, int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if ((((x - i) == 0) || ((y - i) == 0)) || (state.grid[x - i][y - i] != token)) {
                count++;
                return count;
            }
        }
        return count;
    }

    private int checkDiagUL(State state, int token, int x, int y) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if ((((x + i) == (state.width - 1)) || ((y - i) == 0)) || (state.grid[x + i][y - i] != token)) {
                count++;
                return count;
            }
        }
        return count;
    }

    private int checkDiagDL(State state, int token, int x, int y){
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if ((((x + i) == (state.width-1)) || ((y + i) == (state.height-1))) || (state.grid[x + i][y + i] != token)) {
                count++;
                return count;
            }
        }
        return count;
    }

    private int checkDiagDR(State state, int token, int x, int y){
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if ((((x - i) == 0) || ((y - i) == (state.height-1))) || (state.grid[x - i][y + i] != token)) {
                count++;
                return count;
            }
        }
        return count;
    }

    public boolean terminalTest(State state) {
        int x = 0;
        int y = 0;
        int token = 0;
        for (int i = 0; i < state.height; i++){
            for (int j = 0; j < state.width; j++){
                if(state.grid[j][i] != 0){
                    token = state.grid[j][i];
                    x = j;
                    y = i;
                    if (checkLeft(state, token, x, y) == 4){
                        return true;
                    }
                    if (checkRight(state, token, x, y) == 4){
                        return true;
                    }
                    if (checkUp(state, token, x, y) == 4){
                        return true;
                    }
                    if (checkDown(state, token, x, y) == 4){
                        return true;
                    }
                    if (checkDiagUL(state, token, x, y) == 4){
                        return true;
                    }
                    if (checkDiagUR(state, token, x, y) == 4){
                        return true;
                    }
                    if (checkDiagDL(state, token, x, y) == 4){
                        return true;
                    }
                    if (checkDiagDR(state, token, x, y) == 4){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int utility(State state, int player) {
        int token = 0;
        int sum = 0;
        for (int i = 0; i < state.height; i++) {
            for (int j = 0; j < state.width; j++) {
                if (state.grid[j][i] != 0) {
                    token = state.grid[j][i];
                    if (checkLeft(state, token, j, i) == 4 || (checkRight(state, token, j, i) == 4) || (checkUp(state, token, j, i) == 4) || (checkDown(state, token, j, i) == 4) || (checkDiagUL(state, token, j, i) == 4) || (checkDiagUR(state, token, j, i) == 4) || (checkDiagDL(state, token, j, i) == 4) || checkDiagDR(state, token, j, i) == 4) {

                        if (token == state.player) {
                            return 10 * 10 * 10 * 10;
                        } else {
                            return -(10 * 10 * 10 * 10);
                        }
                    }
                    if (checkLeft(state, token, j, i) == 3 || (checkRight(state, token, j, i) == 3) || (checkUp(state, token, j, i) == 3) || (checkDown(state, token, j, i) == 3) || (checkDiagUL(state, token, j, i) == 3) || (checkDiagUR(state, token, j, i) == 3) || (checkDiagDL(state, token, j, i) == 3) || checkDiagDR(state, token, j, i) == 3) {

                        if (token == state.player) {
                            sum += 10 * 10 * 10;
                        } else {
                            sum -= 10 * 10 * 10;
                        }
                    }
                    if (checkLeft(state, token, j, i) == 2 || (checkRight(state, token, j, i) == 2) || (checkUp(state, token, j, i) == 2) || (checkDown(state, token, j, i) == 2) || (checkDiagUL(state, token, j, i) == 2) || (checkDiagUR(state, token, j, i) == 2) || (checkDiagDL(state, token, j, i) == 2) || checkDiagDR(state, token, j, i) == 2) {

                        if (token == state.player) {
                            sum += 10 * 10;
                        } else {
                            sum -= 10 * 10;
                        }
                    }
                }
            }
        }
        return sum;
    }

    private boolean firstMove(State state) {
        for (int i = 0; i < state.height; i++) {
            for (int j = 0; j < state.width; j++) {
                if (state.grid[j][i] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /*private State doMove(int player, State temp, Moves move){
        temp.grid[move.x][move.y] = player;
        return temp;
    }*/

    public State doMove(int player, State temp, int x){
        for (int i = 0; i < temp.height; i++) {
            if(temp.grid[x][i] != 0) {
                temp.grid[x][i] = player;
                return temp;
            }
        }
        temp.grid[x][temp.height-1] = player;
        return temp;
    }

    private int getMove(State state, State temp1){
        int temp = 0;
        for (int i = 0; i < state.height; i++) {
            for (int j = 0; j < state.width; j++) {
                if (state.grid[j][i] != temp1.grid[j][i] && state.grid[j][i] == 0) {
                    temp = j;
                }
            }
        }
        return temp;
    }

    private int maxValue(State state, int alpha, int beta, int depth, int depthMax){
        depth++;
        if (terminalTest(state) || (depth == depthMax)){
            return utility(state, state.player);
        }
        int value = MININFIN;
        for(int i = 0; i < state.validMoves.size(); i++){
            value = Math.max(value, minValue(results(state, state.validMoves.get(i)),alpha, beta, depth, depthMax));
        }
        if(value >= beta){
            return value;
        }
        alpha = Math.max(alpha, value);
        return value;
    }

    private int minValue(State state, int alpha, int beta, int depth, int depthMax){
        depth++;
        if (terminalTest(state) || (depth == depthMax)){
            if (state.player == 1){
                return utility(state, 2);
            }
            else {
                return utility(state, 1);
            }
        }
        int value = MAXINFIN;
        for(int i = 0; i < state.validMoves.size(); i++){
            value = Math.max(value, maxValue(results(state, state.validMoves.get(i)),alpha, beta, depth, depthMax));
        }
        if(value <= alpha){
            return value;
        }
        beta = Math.min(beta, value);
        return value;
    }

}
