package game.logic;

import java.util.Random;

public class Drake extends MazeObj {
	boolean vivo;
	boolean awake;

	DragonChoice choice = DragonChoice.MOV;
	
	public enum DragonChoice {
		NOTMOV, MOV, MOVSLEEP
	}
	
	Drake(char tab[][],DragonChoice c) {
		super(tab,'D');
		vivo = true;
		awake = true;
		choice = c;
	}
	
	Drake(int lin, int col,DragonChoice c)
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
	
	
	
	

}