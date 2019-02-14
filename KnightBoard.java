public class KnightBoard{

  private int[][]board;
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
    moves = new int[] {1, -2,
                      -1, -2,
                       2, -1,
                       2, 1,
                      -2, -1,
                      -2, 1,
                       1, 2,
                      -1, 2}
  }

  public String toString(){
    String s = "";
    for (int i = 0; i < board.length; i++){
      for (int j = 0; j <board[i].length; j++){
        if (board[i][j] < 10){
          s = s + " " + board[i][j];
        }
        s += board[i][j];
      }
    }
    return s;
  }

  private boolean addKnight(int r, int c){


  }


}
