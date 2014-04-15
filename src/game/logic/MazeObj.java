package game.logic;
import java.util.Random;


public abstract class MazeObj {
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
		
		
		
		//esta funçao vai servir de base para o mover de derivadas desta funçao
		public boolean MoveObj(char tab[][], int dx, int dy){
			
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
