package game.logic;

public class Heroi extends MazeObj {
	boolean Armado;
	boolean alive;

	Heroi(char tab[][]) {
		super(tab,'H');
		Armado = false;
		alive = true;
	}
	
	public void armado(){
		Armado = true;
		symbol = 'A';
	}
	
	
	public void Dead(){
		alive = false;
	}
	
	public boolean arma(){
		return Armado;
	}
	
	public boolean alive(){
		return alive;
	}
	
	
	
	

}