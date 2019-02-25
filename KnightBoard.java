public class KnightBoard{

  private int[][] board;
  private int rows;
  private int cols;
  private int[] moves;

  public KnightBoard(int startingRows,int startingCols){
    if (startingCols <= 0 || startingRows <= 0){
      throw new IllegalArgumentException();
    }
    board = new int[startingRows][startingCols];
    rows = startingRows;
    cols = startingCols;
    moves = new int[] {1, -2,     //an array of pairs of moves that can be made;
                      -1, -2,
                       2, -1,
                       2,  1,
                      -2, -1,
                      -2,  1,
                       1,  2,
                      -1,  2};
    }

    public String toString() {
      String s = "";
      for (int i = 0; i < board.length;i++) {
        for (int j = 0; j < board[i].length;j++) {
          if (board[i][j] == 0) {
            s += " _ ";
          }
          else {
            if (board[i][j] < 10) {
              s += " " + board[i][j] + " ";
            }
            else {
              s += board[i][j] + " ";
            }
          }
        }
        s += "\n";
      }
      return s;
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
      for (int i = 0; i < board.length; i++){  //probably not needed but just in case;
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
      addKnight(r,c,counter);
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

    private int countSolutionsH(int r, int c, int counter){
      int total = 0;
      if (r >= rows || c >= cols || r < 0 || c < 0) {
        return 0;
      }
      if (board[r][c] != 0) {
        return 0;
      }
      if (counter == rows * cols) {
        return 1;
      }
      for (int i = 0; i < moves.length; i += 2) {
        addKnight(r, c, counter);
        total += countSolutionsH(r + moves[i],c +moves[i+1],counter+1);
        removeKnight(r, c);
      }
      return total;
    }

    //testcase must be a valid index of your input/output array
    public static void runTest(int i){

      KnightBoard b;
      int[]m =   {4,5,5,5,5};
      int[]n =   {4,5,4,5,5};
      int[]startx = {0,0,0,1,2};
      int[]starty = {0,0,0,1,2};
      int[]answers = {0,304,32,56,64};
      if(i >= 0 ){
        try{
          int correct = answers[i];
          b = new KnightBoard(m[i%m.length],n[i%m.length]);

          int ans  = b.countSolutions(startx[i],starty[i]);

          if(correct==ans){
            System.out.println("PASS board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans);
          }else{
            System.out.println("FAIL board size: "+m[i%m.length]+"x"+n[i%m.length]+" "+ans+" vs "+correct);
          }
        }catch(Exception e){
          System.out.println("FAIL Exception case: "+i);

        }
      }
    }

    public static void main (String[] args){
      runTest(0);
      runTest(1);
      runTest(2);
      runTest(3);
      runTest(4);
        KnightBoard test = new KnightBoard(5, 5);
      System.out.println(test.solve(0, 0));
      System.out.println(test);
      KnightBoard test2 = new KnightBoard(5, 5);
      System.out.println(test2.countSolutions(0, 0));
      KnightBoard test3 = new KnightBoard(3, 4);
      System.out.println(test3.countSolutions(0, 0));
      
    }



  }
