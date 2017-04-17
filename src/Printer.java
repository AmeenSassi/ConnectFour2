public class Printer {
    public void printGrid(State grid) {
        for (int i = 0; i < grid.height; i++) {
            for (int j = 0; j < grid.width; j++) {
                if (grid.grid[j][i] != 0) {
                    System.out.print(grid.grid[j][i]);
                    System.out.print(" ");
                } else {
                    System.out.print(" ");
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println("---------------------------------------");
    }
}
