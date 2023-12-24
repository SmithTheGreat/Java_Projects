public class Minesweeper {
    Space[][] board;
    int [][] coord = {{1,1},{1,0},{1,-1},{0,1},{0,0},{0,1},{-1,1},{-1,0},{-1,-1}};

  // clears space
  // if space value(number of mines) is 0 then it clears all surrounding spaces if they are valid squares
    public void clearSpace(int i, int j) {
        if(isValidSquare(i,j)){
            board[i][j].clearSpace();
            if (board[i][j].getValue() == 0) {
                for (int[] yes : coord) {
                    if(isValidSquare(i+yes[0],j+yes[1])) {
                        if (!board[i+yes[0]][j+yes[1]].isCleared()) {
                            clearSpace(i+yes[0],j+yes[1]);
                        }
                    }
                }
            }
        }
    }
    
    public void toggleFlag(int i, int j) {
        board[i][j].toggleFlag();
    }
    //checks if player wins
    public boolean didPlayerWin() {
        int mines =0;
        int mins = 0;
        int bob=0;
        for(Space[] yes : board) {
            for (Space s : yes) {
                if (s.getValue() == -1 && !s.isCleared()) {
                    mins ++;
                }
                if (s.getValue() == -1) {
                    mines ++;
                }
                else if (s.isCleared()) {
                    bob ++;
                }
            }
        }
        return mines==mins && mins+bob == (board.length*board[0].length);
    }
    //checks if player lost
    public boolean didPlayerLose() {
        for(Space[] yes : board) {
            for (Space s : yes) {
                if (s.getValue() == -1 && s.isCleared()) {
                    return true;
                }
            }
        }
        return false;
    }
    //returns a string of the board
    public String toString() {
        String str = "";
        for(Space[] row : board) {
            for ( Space s : row) {
                str += s.toString();
            }
            str += "\n";
        }
        return str;
    }
    public boolean isValidSquare (int i, int j) {
        return (i >= 0 && i < board.length && j >= 0 && j < board[i].length);
    }

  //creates a minesweeper board
    public Minesweeper(int[][] values) {
        board = new Space[values.length][values[0].length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                board[i][j] = new Space(values[i][j]);
            }
        }
    }
    
    public Minesweeper(int rows, int cols, int numMines) {
        generateRandomBoard(rows, cols, numMines);
    }
    // makes a random minesweeper board
    public void generateRandomBoard(int rows, int cols, int numMines) {
        board = new Space[rows][cols];
        
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = new Space(0);
        
        int row = (int) (Math.random() * rows);
        int col = (int) (Math.random() * cols);
        while (numMines > 0) {
            if (board[row][col].getValue() != -1) {
                board[row][col] = new Space(-1);
                numMines--;
            }
            row = (int) (Math.random() * rows);
            col = (int) (Math.random() * cols);
        }
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Space(countMinesSurroundingSpace(i, j));
            }
        }
    }
    
    public int countMinesSurroundingSpace(int i, int j) {
        if (board[i][j].getValue() == -1)
            return -1;
        
        int res = 0;
        int[][] vectors = {{1, 1}, {1, -1}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};
        for (int[] vector : vectors) {
            if (i + vector[0] < board.length && i + vector[0] >= 0 && j + vector[1] < board[i].length && j + vector[1] >= 0 && board[i + vector[0]][j + vector[1]].getValue() == -1)
                res++;
        }
        return res;
    }
    
    public boolean isValidStartingBoard(int row, int col) {
        return board[row][col].getValue() == 0;
    }
}
