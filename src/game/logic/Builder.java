package game.logic;

import java.io.Serializable;
import java.util.Random;
import java.util.Stack;

/**
 * Classe construtora do labririnto de forma alietoria
 */
public class Builder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	Cell Exit = new Cell();
	Cell Guide = new Cell();
	char[][] lab;
	boolean[][] CellsVis;
	Stack<Cell> Pathing = new Stack<Cell>();

	public char[][] RandomBuilder(int size) {
		lab = new char[size][size];

		// preencher como o caso base
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (i % 2 != 0 && j % 2 != 0)
					lab[i][j] = ' ';
				else
					lab[i][j] = 'X';
			}
		}

		// reset as celulas visitadas
		CellsVis = new boolean[size][size];
		for (int i = 1; i < CellsVis.length; i++, i++)
			for (int j = 1; j < CellsVis[i].length; j++, j++)
				CellsVis[i][j] = false;

		// atribuir saida random

		Random nr = new Random();

		int dist;
		do {
			dist = nr.nextInt(lab.length - 1);
		} while (dist % 2 == 0);
		

		switch (nr.nextInt(4)) {
		// top
		case 0:
			Exit.setColune(dist);
			break;
		// right
		case 1:
			Exit.setColune(lab.length - 1);
			Exit.setLine(dist);
			break;
		// bottom
		case 2:
			Exit.setColune(dist);
			Exit.setLine(lab.length - 1);
			break;
		// left
		case 3:
			Exit.setLine(dist);
			break;
		}

		lab[Exit.line][Exit.colune] = 'S';
		

		// colocar celula guia ao lado da saida, marcar como visitada e
		// acrescentar ao "path"

		Guide.setColune(Exit.colune);
		Guide.setLine(Exit.line);
		
		if (Exit.colune == 0)
			Guide.setColune(Exit.colune + 1);
		else if (Exit.colune == lab.length - 1)
			Guide.setColune(Exit.colune - 1);
		else if (Exit.line == 0)
			Guide.setLine(Exit.line + 1);
		else
			Guide.setLine(Exit.line - 1);

		CellsVis[Guide.line][Guide.colune] = true;

		Pathing.push(new Cell(Guide.line, Guide.colune));
        
		// ciclo do algoritmo

		while (!Pathing.empty()) {
			while (!(PosMove(1) || PosMove(2) || PosMove(3) || PosMove(4))) {
				Pathing.pop();

				if (Pathing.empty()){
					lab[Exit.line][Exit.colune] = 'S';
					return lab;}
				else{
					Guide = Pathing.peek();}
			}
			int dir;
			do {
				dir = nr.nextInt(4) + 1;
			} while (!PosMove(dir));

			// limpar paredes entre a movimentação
			switch (dir) {
			//Esquerda
			case 1:
				lab[Guide.line][Guide.colune - 1] = ' ';
				Guide.setColune(Guide.colune - 2);
				break;
				//Cima
			case 2:
				lab[Guide.line - 1][Guide.colune] = ' ';
				Guide.setLine(Guide.line - 2);
				break;
				//Direita
			case 3:
				lab[Guide.line][Guide.colune + 1] = ' ';
				Guide.setColune(Guide.colune + 2);
				break;
				//Baixo
			case 4:
				lab[Guide.line + 1][Guide.colune] = ' ';
				Guide.setLine(Guide.line + 2);
				break;
			}
			CellsVis[Guide.line][Guide.colune] = true;

			Pathing.push(new Cell(Guide.line, Guide.colune));
		}


		lab[Exit.line][Exit.colune] = 'S';
		return lab;
	}

	/**
	 * Calcula se é possivel a movimentaçao para certa direção
	 */
	public boolean PosMove(int switcher) {
		switch (switcher) {
		// Esquerda
		case 1:
			if (Guide.colune - 1 <= 0)
				return false;
			return !CellsVis[Guide.line][Guide.colune - 2];
			// Cima
		case 2:
			if (Guide.line - 1 <= 0)
				return false;
			return !CellsVis[Guide.line - 2][Guide.colune];
			// Direita
		case 3:
			if (Guide.colune + 1 >= lab.length - 1)
				return false;
			return !CellsVis[Guide.line][Guide.colune + 2];
			// Baixo
		case 4:
			if (Guide.line + 1 >= lab.length - 1)
				return false;
			return !CellsVis[Guide.line + 2][Guide.colune];
		}
		return false;

	}
}