public class KnightBoard{

  private int[][]board;
  private int rows;
  private int cols;

  public KnightBoard(int startingRows,int startingCols){
    if (startingCols == 0 || startingRows == 0){
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
    rows = startingRows;
    cols = startingCols;
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
  }

  private boolean addKnight(int r, int c){


  }


}
