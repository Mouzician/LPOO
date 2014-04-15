package game.logic;

public class Sword extends MazeObj {

	Sword(char tab[][]) {
		super(tab,'E');

	}
	public Sword(int lin, int col)
	{
		super('E',lin,col);
	}

}