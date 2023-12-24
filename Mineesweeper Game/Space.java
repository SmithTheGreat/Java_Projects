public class Space {
    private int val;
    private boolean isFlagged;
    private boolean isCleared;
    
    // when we create a space, we'll give it an initial value
    public Space(int value) {
        val = value;
    }
    
    public void toggleFlag() {
        isFlagged = !isFlagged;
    }
    
    public void clearSpace() {
        isCleared = true;
    }
    
    public int getValue() {
        return val;
    }

    public boolean isFlagged() {
        return isFlagged;
    }
    
    public boolean isCleared() {
        return isCleared;
    }
    
    public String toString() {
        if (!isCleared && isFlagged) {
            return "?";
        }
        else if (!isCleared && !isFlagged) {
            return "X";
        }
        else if(isCleared && val == -1) {
            return "!";
        }
        return Integer.toString(val);
        
    }
}
