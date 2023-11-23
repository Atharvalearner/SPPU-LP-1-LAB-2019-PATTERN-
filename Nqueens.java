public class Nqueens {
	int N = 8;
	int board[][] = { { 0, 0, 0, 0 ,0, 0, 0, 0},{ 0, 0, 0, 0 ,0, 0, 0, 0},{ 0, 0, 0, 0 ,0, 0, 0, 0},{ 0, 0, 0, 0 ,0, 0, 0, 0},{ 0, 0, 0, 0 ,0, 0, 0, 0},{ 0, 0, 0, 0 ,0, 0, 0, 0},{ 0, 0, 0, 0 ,0, 0, 0, 0},{ 0, 0, 0, 0 ,0, 0, 0, 0} };
	public static void main(String args[]){
		Nqueens Queen = new Nqueens();
		Queen.solve();
	}
	void printSolution(int board[][]){
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (board[i][j] == 1)
					System.out.print("Q ");
				else
					System.out.print(". ");
			}
			System.out.println();
		}
	}
	boolean solve(){
		if (solvetill(board, 0) == false) {
			System.out.print("Solution does not exist");
			return false;
		}
		printSolution(board);
		return true;
	}
	boolean solvetill(int board[][], int col){
		if (col >= N)
			return true;
		for (int row = 0; row < N; row++) {
			if (isSafe(board, row, col)) {
				board[row][col] = 1;
				if (solvetill(board, col + 1) == true){
					return true;
				}
				board[row][col] = 0;
			}
		}
		return false;
	}
	boolean isSafe(int board[][], int row, int col){
		int i, j;
		for (i = 0; i < col; i++)
			if (board[row][i] == 1)
				return false;
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
			if (board[i][j] == 1)
				return false;
		for (i = row, j = col; j >= 0 && i < N; i++, j--)
			if (board[i][j] == 1)
				return false;
		return true;
	}
}