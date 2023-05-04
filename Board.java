import java.util.ArrayList;

public class Board {

	private final int NUM_OF_COLUMNS = 7;//assuming that the board will always be at least 4 x 4
	private final int NUM_OF_ROW = 6;
	
	/* 
	 * The board object must contain the board state in some manner.
	 * You must decide how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose
	 * how the board is being implemented to other classes. Specifically, the
	 * Player classes.
	 * 
	 */

	private String[][] playerMoves;
	private int num_diag = NUM_OF_COLUMNS + NUM_OF_ROW - 8 + 1;
	private ArrayList<String> playerlist = new ArrayList<String>();
	
	public Board() {
		this.playerMoves = new String[NUM_OF_COLUMNS][NUM_OF_ROW];
		this.reset();
			
	}
	
	public void printBoard() {
		for (int i = 0; i < NUM_OF_ROW; i++ ) {
			for (int j = 0; j < NUM_OF_COLUMNS; j++ ) {
				if ( playerMoves[i][j].equals(" ")) {
					if ((i != NUM_OF_ROW - 1) & (j != NUM_OF_COLUMNS - 1)) {
						System.out.print("| ");						
						
					}
					else if ((i != NUM_OF_ROW - 1) & (j == NUM_OF_COLUMNS - 1)) {
						System.out.print("| |");
					}
					else if ((i == NUM_OF_ROW - 1) & (j != NUM_OF_COLUMNS - 1)) {
						System.out.print("|_");
					}
					else {
						System.out.print("|_|");
					}
					
				}
				else {
					if (j == NUM_OF_COLUMNS - 1) {
						System.out.print("|" + playerMoves[i][j] + "|");
					}
					else {
						System.out.print("|" + playerMoves[i][j]);
					}
			
				}

				
			}
			System.out.println("");
		}

		
	}
	
	// Assumes that the 2 players are not using the same symbol
	//Assumes that the players are not using ' ' as their symbol
	public boolean containsWin() {
		if  (playerlist.size() != 2) {//if one of the players hasn't made a move yet there is no winner
			return false;
		}
        
		boolean isWin = this.checkWin(playerMoves, playerlist.get(0), playerlist.get(1));
		return isWin;
		
	}
	
	public boolean isTie() {
		// check if the board is full
		for (int i = 0; i < NUM_OF_COLUMNS; i++ ) {
			if (playerMoves[0][i].equals(" ")) {
				return false;
				
			}
				
		}
		return true;
		
	}
	
	public void reset() {
		for(int i = 0; i < NUM_OF_ROW; i ++) {
			String row = (" ,").repeat(NUM_OF_COLUMNS - 1) + " ";
			String[] rowList = row.split(",");
			playerMoves[i] = rowList;
			
		}

	}
	
	public boolean checkMove(int move) {
		//checks if there is an open spot in that column
		if (playerMoves[0][move - 1].equals(" ")) {
			return true;
				
		}
		
		return false;
		
	}
	
	public void inputMove(Player player, int move) {
		String symbol = Character.toString(player.symbol);
		if (playerlist.size() < 2) {
			if (!playerlist.contains(symbol)) {
				playerlist.add(symbol);
				
			}
		}
		for (int i = NUM_OF_ROW - 1; i >= 0; i = i - 1) {
			if (playerMoves[i][move - 1].equals(" ")) {
				playerMoves[i][move - 1] = symbol;
				break;
					
				}
					
		}		
		
		
	}
	
	public int inputRangeAI() {
		return NUM_OF_COLUMNS;
	}
	
	public boolean winAI(Character AI, int index) {
		String strAI = Character.toString(AI);
		String[][] row = new String[NUM_OF_ROW][NUM_OF_COLUMNS];
		
		//makes a copy of the board
        for (int i = 0; i < NUM_OF_ROW; i++) {
            System.arraycopy(playerMoves[i], 0, row[i], 0, row[i].length);
       }
	
		if (playerlist.size() != 2) {
			return false;
		}
		
		//inputs the AI's potential move in the copied board
		for (int i = NUM_OF_ROW - 1; i >= 0; i = i - 1) {
			if (row[i][index].equals(" ")) {
				row[i][index] = strAI;
				break;
					
				}
						
		}

		//check if there is a win with the new move in the board
		boolean isWin = this.checkWin(row, strAI, strAI);
		return isWin;
		
	}
	
	private boolean checkWin(String[][] array, String player1, String player2) {
		//make each row into a string and checks if an element is repeated 4 times in the string
		//check rows for win
		for (int i = 0; i < NUM_OF_ROW; i++ ) {
			String rowCheck = String.join("", array[i]);
			boolean rowWin = (rowCheck.contains(player1.repeat(4)) || rowCheck.contains(player2.repeat(4)));
			if (rowWin) {
				return true;
				}
				
			}

		//checks columns for win
		String[][] col = this.columns(array);
		for (int i = 0; i < NUM_OF_COLUMNS; i++ ) {
			String colCheck = String.join("",  col[i]);
			boolean colWin = (colCheck.contains(player1.repeat(4)) || colCheck.contains(player2.repeat(4)));
			if (colWin) {
				return true;
				}
			}
			
		//check right diagonal for win 
		String[][] rightD = this.rDiagonal(array);
		for (int k = 0; k < num_diag; k++ ) {
			String r_diag = String.join("", rightD[k]);
			boolean diag_r_win = (r_diag.contains(player1.repeat(4)) || r_diag.contains(player2.repeat(4)));
			if (diag_r_win) {
				return true;
			}
		}
		
		//check left diagonal for win
		String[][] leftD = this.lDiagonal(array);
		for (int k = 0; k < num_diag; k++ ) {
			String l_diag = String.join("", leftD[k]);
			boolean left_r_win = (l_diag.contains(player1.repeat(4)) || l_diag.contains(player2.repeat(4)));
			if (left_r_win) {
				return true;
			}
		}
	
		
		return false;
	}
	
	public boolean blockAI(Character AI, int index) {
		String strAI = Character.toString(AI);
		Character gamer = AI;
		
		if (playerlist.size() != 2) {
			return false;
		}
		
		for (int i = 0; i < 2; i++) {
			String one_player = playerlist.get(i);
			if (!one_player.equals(strAI)) {
				gamer = one_player.charAt(0);
				
			}
		}
		
		boolean moveWorks = this.winAI(gamer, index);
		
		return moveWorks;
		
	}
	
	
	private String[][] columns(String[][] array) {
		String[][] columnarray = new String[NUM_OF_COLUMNS][NUM_OF_ROW];
		
		for (int i = 0; i < NUM_OF_COLUMNS; i++) {
			String[] temp_array = new String[NUM_OF_ROW];
			for (int j = 0; j < NUM_OF_ROW; j ++) {
				temp_array[j] = array[j][i];
			}
			columnarray[i] = temp_array;
		}
		
		return columnarray;
	}
	
	private String[][] rDiagonal(String[][] array) {
		String[][] diag = new String[num_diag][];
		int length = 4;
		int upto_maxlength = Math.min(NUM_OF_ROW, NUM_OF_COLUMNS) - 4;
		int num_maxlength = num_diag - (upto_maxlength * 2);
		int index = 0;
		
		for (int i = 0; i < num_diag; i ++) {
			diag[i] = new String[length];
			if (i < upto_maxlength) {
				length += 1;
			}
			if (i >= (upto_maxlength + num_maxlength - 1)) {
				length -= 1;
			}
			
		}
		
		for (int i = 3; i < NUM_OF_ROW; i ++) { 
			String[] temp_row = new String[diag[index].length];
			int col = 0;
			int row = i;
			while (col < diag[index].length) {// rows go down by 1, columns go up by 1
				temp_row[col] = array[row][col];
				row -= 1;
				col += 1;
				
			}
			
			diag[index] = temp_row;	
			index += 1;
		}
		
		for (int j = 1; j < (NUM_OF_COLUMNS - 3); j++ ) {
			String[] temp_row = new String[diag[index].length];
			int col = j;
			int temp_index = 0;
			int row = NUM_OF_ROW - 1;
			while (temp_index < diag[index].length) {
				temp_row[temp_index] = array[row][col];
				col += 1;
				row -= 1;
				temp_index += 1;
			
			}
			
			diag[index] = temp_row;
			index += 1;
	
		}
		
		return diag;
		
	}
	
	private String[][] lDiagonal(String[][] array) { 
		String[][] diag = new String[num_diag][];
		int length = 4;
		int upto_maxlength = Math.min(NUM_OF_ROW, NUM_OF_COLUMNS) - 4;
		int num_maxlength = num_diag - (upto_maxlength * 2);
		int index = 0;
		
		for (int i = 0; i < num_diag; i ++) {
			diag[i] = new String[length];
			if (i < upto_maxlength) {
				length += 1;
			}
			if (i >= (upto_maxlength + num_maxlength - 1)) {
				length -= 1;
			}
			
		}
		
		for (int i = NUM_OF_COLUMNS - 4; i >= 0; i = i - 1) {
			int col = i;
			int row = 0;
			String[] temp_array = new String[diag[index].length];
			while ((row < NUM_OF_ROW) & (col < NUM_OF_COLUMNS)) {
				temp_array[row] = array[row][col];
				row += 1;
				col += 1;
				
				
			}
			diag[index] = temp_array;
			index += 1;
			
		}
	
		for (int i = 1; i < NUM_OF_ROW - 3; i++) {
			int row = i;
			int col = 0;
			String[] temp_array = new String[diag[index].length];
			while ((row < NUM_OF_ROW) & (col < NUM_OF_COLUMNS)) {
				temp_array[col] = array[row][col];
				row += 1;
				col += 1;
				
				
			}
			diag[index] = temp_array;
			index += 1;
		}
		
		
		return diag;
		
	}
		
	
}

