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
			    returner += " _ ";
			}
			else {
			    if (board[i][j] < 10) {
			    	returner += " " + board[i][j] + " ";
			    }
			    else {
			    	returner += board[i][j] + " ";
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
  //  System.out.println(toString());
    if (counter == rows * cols) {
      return true;
    }
    for (int i = 0; i < moves.length; i += 2) {
    		if (solveH(r+moves[i],c+moves[i+1],counter+1)){
          return true;
        }
      }
    removeKnight(r,c);
    return false;
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


  public int countSolutions(int startingRow, int startingCol){
    checkBoard();
    if (startingRow >= rows || startingCol >= cols || startingRow < 0 || startingCol < 0){
      throw new IllegalArgumentException();
    }
    return countSolutionsH(startingRow, startingCol, 1);
  }

  private int countSolutionsH(int row, int col, int counter){
    int total = 0;
    if (row >= rows || col >= cols || row < 0 || col < 0) {
      		return 0;
    }
    if (board[row][col] != 0) {
      return 0;
    }
    if (counter == rows * cols) {
      return 1;
    }
    for (int i = 0; i < moves.length; i += 2) {
      board[row][col] = counter;
      total += countSolutionsH(row + moves[i],col+moves[i+1],counter+1);
    	board[row][col] = 0;
    }
    return total;
  }


  public static void main (String[] args){
    KnightBoard test = new KnightBoard(5, 5);
    System.out.println(test.solve(0, 0));
    System.out.println(test);
    KnightBoard test2 = new KnightBoard(5, 5);
    System.out.println(test2.countSolutions(0, 0));
  }


}
