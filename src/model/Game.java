package model;

public class Game {
	int[][] grid = new int[3][3];
	private boolean XTurn = true;
	private boolean gameOver = false;
	private int XWins = 0;
	private int OWins = 0;

	public Game() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				grid[i][j] = 0;
			}
		}
	}

	public int getTile(int X, int Y) {
		return grid[X][Y];
	}
	
	public boolean isXTurn(){
		return XTurn;
	}

	public void clickedTile(int x, int y) {
		if (!gameOver) {
			if (grid[x][y] != 0) {
				return;
			}

			if (XTurn) {
				grid[x][y] = 1;
			} else {
				grid[x][y] = 2;
			}
			XTurn = !XTurn;

		}
		if (checkWhoIsWinner() || checkIfGameIsOver()) {
			System.out.println("There is winner or draw");
			resetBoard();
			gameOver = false;
		}

	}

	private boolean checkWhoIsWinner() {
		boolean XWin = false;
		boolean OWin = false;
		for (int i = 0; i < 3; i++) {
			if (grid[0][i] == 1 && grid[1][i] == 1 && grid[2][i] == 1) {
				System.out.println("1");
				XWin = true;
			}
			if (grid[i][0] == 1 && grid[i][1] == 1 && grid[i][2] == 1) {
				System.out.println("2");
				XWin = true;
			}
			if (grid[0][0] == 1 && grid[1][1] == 1 && grid[2][2] == 1) {
				System.out.println("3");
				XWin = true;
			}
			if (grid[0][2] == 1 && grid[1][1] == 1 && grid[2][0] == 1) {
				System.out.println("4");
				XWin = true;
			}
			if (grid[0][i] == 2 && grid[1][i] == 2 && grid[2][i] == 2) {
				System.out.println("5");
				OWin = true;
			}
			if (grid[i][0] == 2 && grid[i][1] == 2 && grid[i][2] == 2) {
				System.out.println("6");
				OWin = true;
			}
			if (grid[0][0] == 2 && grid[1][1] == 2 && grid[2][2] == 2) {
				System.out.println("7");
				OWin = true;
			}
			if (grid[0][2] == 2 && grid[1][1] == 2 && grid[2][0] == 2) {
				System.out.println("8");
				OWin = true;
			}
		}
		if (XWin) {
			XWins += 1;
			System.out.println("X Wins");
			return true;
		} else if (OWin) {
			System.out.println("O Wins");
			OWins += 1;
			return true;
		} else {
			return false;
		}
	}

	private void resetBoard() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j] = 0;
			}
		}
	}

	private boolean checkIfGameIsOver() {
		boolean over = true;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 0) {
					over = false;
				}
			}
		}
		gameOver = over;
		return over;
	}

	/**
	 * @return the xWins
	 */
	public int getXWins() {
		return XWins;
	}

	/**
	 * @return the oWins
	 */
	public int getOWins() {
		return OWins;
	}

}
