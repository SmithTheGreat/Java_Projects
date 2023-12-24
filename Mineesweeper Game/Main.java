import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Welcome to MineSweeper!\n");
        
        System.out.print("How many rows would you like on your board (at least 5)? ");
        int rows = sc.nextInt();
        while (rows < 5) {
            System.out.println("That's less than 5!");
            System.out.print("How many rows would you like on your board (at least 5)? ");
            rows = sc.nextInt();
        }
        
        System.out.print("How many columns would you like on your board (at least 5)? ");
        int cols = sc.nextInt();
        while (cols < 5) {
            System.out.println("That's less than 5!");
            System.out.print("How many columns would you like on your board (at least 5)? ");
            cols = sc.nextInt();
        }
        
        System.out.print("How many mines would you like to place on the board (no more than a fifth of the spaces can be filled with mines)? ");
        int mines = sc.nextInt();
        while (mines > rows * cols / 5) {
            System.out.println("More than a fifth of the spaces will have mines!");
            System.out.print("How many mines would you like to place on the board (no more than half the spaces can be filled with mines)? ");
            mines = sc.nextInt();
        }
        
        System.out.println("\nLet's clear your first space!");
        System.out.print("What row would you like to clear? ");
        int row = sc.nextInt()-1;
        System.out.print("What column would you like to clear? ");
        int col = sc.nextInt()-1;
        
        Minesweeper game = new Minesweeper(rows, cols, mines);
        while (!game.isValidStartingBoard(row, col)) {
            game = new Minesweeper(rows, cols, mines);
        }
        game.clearSpace(row, col);
        
        System.out.println("\n" + game + "\n");
        
        while (!game.didPlayerLose() && !game.didPlayerWin()) {
            System.out.print("Would you like to toggle a flag or clear a space? (Enter 'flag' or 'clear') ");
            sc.nextLine();
            String action = sc.nextLine();
            while (!action.equals("flag") && !action.equals("clear")) {
                System.out.println("Please enter either 'flag' or 'clear'!");
                System.out.print("Would you like to toggle a flag or clear a space? (Enter 'flag' or 'clear') ");
                action = sc.nextLine();
            }
            
            System.out.print("What row would you like to select? ");
            row = sc.nextInt()-1;
            System.out.print("What column would you like to select? ");
            col = sc.nextInt()-1;
            
            if (action.equals("flag")) {
                game.toggleFlag(row, col);
            } else {
                game.clearSpace(row, col);
            }
            
            System.out.println("\n" + game + "\n");
        }
        
        System.out.println();
        if (game.didPlayerLose()) {
            System.out.println("You lost!");
        } else if (game.didPlayerWin()) {
            System.out.println("You won!");
        }
    }
}
