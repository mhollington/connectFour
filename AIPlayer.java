import java.util.Random;

public class AIPlayer extends Player {

	public AIPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
		// TODO Auto-generated constructor stub
		
	}


	@Override
	public void makeMove(Board board) {
	
		boolean makeMoveAI = false;
		int highInput = board.inputRangeAI();
		
		for (int i = 0; i < highInput; i++) {
			boolean isWin = board.winAI(this.symbol, i);
			if (isWin) {
				board.inputMove(this, i + 1);
				makeMoveAI = true;
				break;
			}
		}
		
		if (!makeMoveAI) {
			for (int i = 0; i < highInput; i++) {
				boolean isBlock = board.blockAI(this.symbol, i);
				if (isBlock) {
					board.inputMove(this, i + 1);
					makeMoveAI = true;
					break;
				}
			}
			
			while (makeMoveAI == false) {
				int move = new Random().nextInt((highInput - 1) + 1)  + 1;
			    boolean isGood = board.checkMove(move);
							    
			    if (isGood) {
			    	board.inputMove(this, move);
			    	makeMoveAI = true;
			    	}
								
							}
			}
		}
		

			
		

		
		
	

}
