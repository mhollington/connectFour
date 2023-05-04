
public class Runner {

	public static void main(String[] args) {
	
		for (int i = 0; i < 6; i ++) {
			Board board = new Board();
			ConnectFour game = new ConnectFour(board);
			game.setPlayer1(new AIPlayer('R', board, "Esther"));
			game.setPlayer2(new AIPlayer('B', board, "Marie"));
			game.playGame();
			System.out.println("");	
			
		}
				
		
		/*
		Board board = new Board();
		ConnectFour game = new ConnectFour(board);
		game.setPlayer1(new HumanPlayer('R', board, "Alice"));
		game.setPlayer2(new AIPlayer('B', board, "Bob"));
		game.playGame();
		*/
	
		
	}

	}


