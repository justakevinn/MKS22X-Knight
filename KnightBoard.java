public class KnightBoard{

  private int[][] board;
  private int rows;
  private int cols;
  private int[] moves;

  public KnightBoard(int startingRows,int startingCols){
    if (startingCols == 0 || startingRows == 0){
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
    rows = startingRows;
    cols = startingCols;
    moves = new int[] {1, -2,     //an array of pairs of moves that can be made;
                      -1, -2,
                       2, -1,
                       2, 1,
                      -2, -1,
                      -2, 1,
                       1, 2,
                      -1, 2};
  }

  public String toString() {
		String returner = "";
		for (int i = 0; i < board.length;i++) {
		    for (int j = 0; j < board[i].length;j++) {
			if (board[i][j] == 0) {
			    returner += "_ ";
			}
			else {
			    String s = board[i][j] + "";
			    if (s.length() == 1) {
			    	returner += " " + s + " ";
			    }
			    else {
			    	returner += s + " ";
			    }
			}
		    }
		    returner += "\n";
		}
		return returner;
  }

  private boolean addKnight(int r, int c, int counter){
    if (r >= rows || c >= cols || r < 0 || c < 0){
      return false;
    }
    if (board[r][c] != 0){
      return false;
    }
    else{
      board[r][c] = counter;
      return true;
    }
  }
  private boolean removeKnight(int r, int c){  //intakes location of the knight;
    if(board[r][c] != 0){
      board[r][c] = 0;
      return true;
    }
    return false;
  }

  private boolean removeKnight(int counter){ //intakes counter of the knight;
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j < board[i].length; j++){
        if (board[i][j] == counter){
          board[i][j] = 0;
          return true;
        }
      }
    }
    return false;
  }

  public boolean solve(int startingRow, int startingCol){
    checkBoard();
    if (startingRow >= rows || startingCol >= cols || startingRow < 0 || startingCol < 0){
      throw new IllegalArgumentException();
    }
    return solveH(startingRow, startingCol, 1);
  }

  private boolean solveH(int r, int c, int counter){
    if (r >= rows || c >= cols || r < 0 || c < 0) {
      		return false;
        }
    if (board[r][c] != 0) {
      return false;
    }
    board[r][c] = counter;
    if (counter == rows * cols) {
      return true;
    }
    boolean works = false;
    for (int i = 0; i < moves.length; i += 2) {
    		works = solveH(r+moves[i],c+moves[i+1],counter+1);
    	}
    if (!works) {
    	board[r][c] = 0;
    }
    return works;
  }


  private boolean checkBoard() {
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j ++) {
    			if (board[i][j] != 0) {
    				throw new IllegalStateException();
    			}
    		}
    	}
    	return true;
    }


  public static void main (String[] args){
    KnightBoard test = new KnightBoard(5, 5);
    test.solve(4, 0);
    System.out.println(test);
  }


}
