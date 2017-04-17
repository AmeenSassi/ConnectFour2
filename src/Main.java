public class Main {

    public static void main(String[] args) {
        Solver solve = new Solver();
        Printer print = new Printer();
        StateBridge bridge = new StateBridge();
        Board grid = bridge.parse();
        State state = solve.makeState(grid);
        while(true){
            
        }
    }
}
