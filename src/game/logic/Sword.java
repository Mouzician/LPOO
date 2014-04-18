package game.logic;

/**
 * Classe referente ao elemento "Espada" do labirinto
 */
public class Sword extends MazeObj {

	private static final long serialVersionUID = 1L;
	Sword(char tab[][]) {
		super(tab,'E');

	}
	public Sword(int lin, int col)
	{
		super('E',lin,col);
	}

}