package game.logic;

import java.util.Scanner;
import java.util.Vector;

public class Eagle extends MazeObj {
	Boolean flying;
	Cell start;
	Cell end;
	Boolean alive;
	Vector<Cell> path = new Vector<Cell>();
	Boolean Sword;

	public Eagle(Heroi Dono) {
		super();
		this.coluna = Dono.coluna;
		this.linha = Dono.linha;
		this.symbol = 'B';
		alive = true;
		flying = false;
		Sword = false;
	}

	public void Dead(Drake Drags[]) {
		for (int d = 0; d < Drags.length; d++)
			if (Drags[d].alive() && Drags[d].awake)
				if ((Drags[d].coluna - this.coluna) <= 1
						&& (Drags[d].linha - this.linha) <= 1)
					alive = false;
	}

	public void DrawPath(Sword E, Heroi Dono) {
		flying = true;
		start = new Cell(Dono.linha, Dono.coluna);
		end = new Cell(E.linha, E.coluna);
		path.add(start);
		double dcol = 1.0;
		double dlin = 1.0;

		if (Math.abs(Dono.coluna - E.coluna) < Math.abs(Dono.linha - E.linha))
			dcol = 1.0 * (Dono.coluna - E.coluna) / (Dono.linha - E.linha);
		else if (Math.abs(Dono.coluna - E.coluna) > Math.abs(Dono.linha	- E.linha)) 
			dlin = 1.0 * (Dono.linha - E.linha) / (Dono.coluna - E.coluna);
	

		if (Dono.coluna - E.coluna < 0 && dcol < 0)
			dcol = -dcol;
		else if (Dono.coluna - E.coluna > 0 && dcol > 0)
			dcol = -dcol;

		if (Dono.linha - E.linha < 0 && dlin < 0)
			dlin = -dlin;
		else if (Dono.linha - E.linha > 0 && dlin > 0)
			dlin = -dlin;

		double col = 1.0 * Dono.coluna;
		double lin = 1.0 * Dono.linha;

		while (!end.samePosition(path.lastElement())) {

			//System.out.printf("\nDestino %s ", end.dy);
			//System.out.printf("\nDestino %s ", end.dx);

			//System.out.printf("\nesta %s ", col);
		    //System.out.printf("\nesta %s ", lin);

			// //////SYSTEMPAUSE
			//System.out.println("Press Any Key To Continue...");
			//new java.util.Scanner(System.in).nextLine();
			// ///////SYSTEMPAUSE

			if (col == (double) end.line)
				dcol = 0;

			if (lin == (double) end.colune)
				dlin = 0;

			col = col + dcol;
			lin = lin + dlin;

			Cell toadd = new Cell((int) Math.round(lin), (int) Math.round(col));
			if(!path.contains(toadd))
			path.add(toadd);

		}

		for (int i = path.size() - 1; i >= 0; i--) {
			path.add(path.get(i));
		}
		path.remove(0);
		path.remove((int) Math.round(path.size()/2));
		
		for (int i = 0; i < path.size(); i++) {
			System.out.printf("\nesta %s ", path.elementAt(i).colune);
		    System.out.printf("\nesta %s ", path.elementAt(i).line);
		}
		
	}

	public void removeFirst(){
		if(path.size() > 0)
			path.remove(0);
	}

	
	public Cell nextPosition(){
		return path.elementAt(0);
	}


	public boolean alive(){
		return alive;
	}
	
	public boolean flying(){
		return flying;
	}

}