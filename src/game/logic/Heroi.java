package game.logic;

public class Heroi extends MazeObj {
	boolean Armado;
	boolean alive;

	Heroi(char tab[][]) {
		super(tab,'H');
		Armado = false;
		alive = true;
	}
	
	public Heroi(int lin, int col)
	{
		super('H',lin,col);
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
	
	
	public void Dead(Drake Drags[]) {
		for (int d = 0; d < Drags.length; d++)
			if (Drags[d].alive() && Drags[d].awake)
				if (Math.abs(Drags[d].coluna - this.coluna) <= 1
						&& Math.abs(Drags[d].linha - this.linha) <= 1)
					alive = false;
	}
	

}