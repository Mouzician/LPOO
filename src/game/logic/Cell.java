package game.logic;

public class Cell {
	int line, colune;

	public Cell() {
	    line = 0;
		colune = 0;
	}

	public Cell(int y, int x) {
		line = y;
		colune = x;
	}

	public void setLine(int lin) {
		line = lin;
	}

	public void setColune(int col) {
		 colune = col;
	}
	
	public Boolean samePosition(Cell comp)
	{
		return(colune == comp.colune && line == comp.line);
	}
	
	public Cell soma(int lin, int col){
		Cell temp = new Cell(this.colune+col,this.line + lin);
		return temp;
	}
	
	public int getLine(){
		return line;
	}
	
	public int getCol(){
		return colune;
	}
}
