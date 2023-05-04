import java.util.Scanner; 

public class HumanPlayer extends Player {
	private char symbol;
	private Board board;
	private String name; 


	public HumanPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeMove(Board board) {
		boolean moveMade = false;
		
		while (moveMade == false) {
		    Scanner human_move = new Scanner(System.in); 
		    int newMove;
		    System.out.print("Please input your move:");
		    newMove = human_move.nextInt();
		    
		    boolean isGood = board.checkMove(newMove);
		    
		    if (isGood) {
		    	board.inputMove(this, newMove);
		    	moveMade = true;
		    	
		    }
		    else {
		    	System.out.println("invalid move");
		    }

		}
	    
	    
	    

		// TODO Auto-generated method stub
		
	}


}
