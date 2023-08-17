import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;

public class Move implements Serializable{
	enum MoveType{
		ROCK,
		PAPER,
		SCISSORS
	}
	private MoveType moveType;
	
	public Move(MoveType moveType){
		this.moveType= moveType;
		
	}
	public Move() {
		this.moveType=null;
	}
	public static Move PVCChooseMove() {
		Random random = new Random();
		MoveType[] values = MoveType.values();
		MoveType randomMoveType = values[random.nextInt(values.length)];
		return new Move(randomMoveType);
		
		
		
	}
	
	public static Move P1ChooseMove(){
		System.out.println("Choose your move");
		MoveType P1Move;
		Scanner a = new Scanner(System.in);
		
		int chosen = a.nextInt();
		try {
		switch(chosen) {
		case 1:
		P1Move = MoveType.ROCK;
		break;
		case 2:
		P1Move = MoveType.PAPER;
		break;
		case 3:
		P1Move = MoveType.SCISSORS;
		break;
		default:
			throw new InvalidGameTypeException();
			}
		}catch(InvalidGameTypeException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		
		}
		return new Move(P1Move);
		
	}
	
	public MoveType getMoveType() {
		return moveType;
	}
	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}
	
	

}
