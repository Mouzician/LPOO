package game.logic;
import java.io.Serializable;
import java.util.Random;

/**
 * Classe abstracta de todos os elementos do labirinto
 */
public abstract class MazeObj implements Serializable{
	
	private static final long serialVersionUID = 1L;
		int coluna;
		int linha;
		char symbol;

		MazeObj(char tab[][], char s) {

			while (tab[linha][coluna] != ' ') {
				Random r = new Random();

				linha = r.nextInt(tab.length - 1);

				coluna = r.nextInt(tab.length - 1);
			}
			
			
			
			symbol = s;
		}
		MazeObj(){}

		public MazeObj(char s, int lin, int col)
		{
			symbol = s;
			linha = lin;
			coluna = col;
		}
		
		
		
		/**
		 * M�todo que move os objetos no labirinto
		 */
		public boolean MoveObj(char tab[][], int dx, int dy){
			
			if(coluna + dx >= tab.length || linha + dy >= tab.length || coluna + dx < 0 || linha + dy < 0)
				return false;
			
			
			if(tab[linha + dy][coluna + dx] != 'X')
			{
				coluna = coluna + dx;
				linha = linha + dy;
				
				return true;
				
			}
			else	
				return false;
			
		}

		
		public boolean samePosition(MazeObj comp)
		{
			return (this.coluna == comp.coluna && this.linha == comp.linha);
		}
		
		public boolean samePosition(Cell c)
		{
			return (this.coluna== c.getCol() && this.linha == c.getLine());
		}
		
		
		public char symbol(){
			return symbol;
		}
		
		public int col(){
			return coluna;
		}
		
		public int lin(){
			return linha;
		}
		
		public void setlin(int l){
			linha = l;
		}
		public void setcol(int col){
			coluna = col;
		}
	}
