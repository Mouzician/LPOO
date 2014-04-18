package game.logic;

/**
 * Classe referente ao elemento "Dragão" do labirinto
 */
public class Drake extends MazeObj {
	private static final long serialVersionUID = 1L;
	boolean vivo;
	boolean awake;

	DragonChoice choice = DragonChoice.MOV;
	
	public enum DragonChoice {
		NOTMOV, MOV, MOVSLEEP
	}
	
	public Drake(char tab[][],DragonChoice c) {
		super(tab,'D');
		vivo = true;
		awake = true;
		choice = c;
	}
	
	public Drake(int lin, int col,DragonChoice c)
	{
		super('D',lin,col);
		vivo = true;
		awake = true;
		choice = c;
	}
	

	public void Dead() {
		vivo = false;
	}
	
	public void sleep(){
		awake = false;
	}
	
	public void wakeup(){
		awake = true;
	}
	
	public boolean alive(){
		return vivo;
	}
	
	public boolean sleeping(){
		return !awake;
	}
	
	

}