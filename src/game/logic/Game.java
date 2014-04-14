package game.logic;

import game.logic.Drake.DragonChoice;

import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Game {
	Heroi Hero;
	Drake Drags[];
	Sword Espada;
	char tab[][];
	Builder Build = new Builder();
	Eagle Ei;

	public Game(int nr, int nrDrags, DragonChoice c) {
		if (nr != 0)
			tab = Build.RandomBuilder(nr);
		else {
			char temp[][] = {
					{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
					{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
					{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
					{ 'X', ' ', ' ', ' ', ' ', ' ', ' ', 'X', ' ', 'S' },
					{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
					{ 'X', ' ', 'X', 'X', ' ', 'X', ' ', 'X', ' ', 'X' },
					{ 'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', ' ', 'X' },
					{ 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X' } };

			tab = temp;
		}

		Hero = new Heroi(tab);

		Drags = new Drake[nrDrags];
		for (int d = 0; d < Drags.length; d++)
			Drags[d] = new Drake(tab, c);
		
		Ei = new Eagle(Hero);

		Espada = new Sword(tab);

	}

	public void movHero(int dx, int dy) {

		if (Hero.MoveObj(tab, dx, dy)) {

			for (int d = 0; d < Drags.length; d++)
				if (Math.abs(Drags[d].coluna - Hero.coluna) <= 1
						&& Math.abs(Drags[d].linha - Hero.linha) <= 1 && !Hero.arma())
					Hero.Dead();

			if (Hero.samePosition(Espada) && !Ei.armada())
				Hero.armado();

			for (int d = 0; d < Drags.length; d++)
				if (Math.abs(Drags[d].coluna - Hero.coluna) <= 1
						&& Math.abs(Drags[d].linha - Hero.linha) <= 1 && Hero.arma())
					Drags[d].Dead();

		}

		if (tab[Hero.linha][Hero.coluna] == 'S' &&  !AllDrakesD()) {
			Hero.coluna = Hero.coluna - dx;
			Hero.linha = Hero.linha - dy;

		}
	}

	public void movDrake(Drake Dmov) {

		if (Dmov.choice == DragonChoice.NOTMOV)
			return;

		int xx = 0;
		int yy = 0;

		boolean moveu = false;
		Random random = new Random();
		while (!moveu && Dmov.awake) {

			int generated = random.nextInt(5);

			switch (generated) {

			case 0:
				xx = 0;
				yy = 1;
				break;
			case 1:
				xx = 1;
				yy = 0;
				break;
			case 2:
				xx = 0;
				yy = -1;
				break;
			case 3:
				xx = -1;
				yy = 0;
				break;
			case 4:
				return;
			}

			if (Dmov.linha + yy > 0 && Dmov.linha + yy < tab.length
					&& Dmov.coluna + xx > 0 && Dmov.coluna + xx < tab.length
					&& tab[Dmov.linha + yy][Dmov.coluna + xx] != 'X'
					&& tab[Dmov.linha + yy][Dmov.coluna + xx] != 'S') {

				Dmov.linha = Dmov.linha + yy;
				Dmov.coluna = Dmov.coluna + xx;

				if ((Hero.coluna - Dmov.coluna) <= 1
						&& (Hero.linha - Dmov.linha) <= 1 && Hero.arma())
					Dmov.Dead();

				else if (Math.abs(Hero.coluna - Dmov.coluna) <= 1
						&& Math.abs(Hero.linha - Dmov.linha) <= 1)
					Hero.Dead();

				moveu = true;
			}

		}

		if (Dmov.choice == DragonChoice.MOVSLEEP) {
			int generated = random.nextInt(2);
			if (generated == 1) {
				Dmov.wakeup();
				Dmov.symbol = 'D';
			} else {
				Dmov.sleep();
				Dmov.symbol = 'd';
			}
		}

	}

	public void movEagle() {

		if (!Ei.flying)
			return;

		if(Ei.path.size() != 0){
		Cell next = Ei.nextPosition();
		
		Ei.coluna = next.colune;
		Ei.linha = next.line;
		
		Ei.removeFirst();
		}
		Ei.Dead(Drags);
		

		if(Ei.samePosition(Espada))
			Ei.withSword();
		
		if(Ei.samePosition(Hero))
			{Hero.armado();
			Ei.flying = false;}
		

	}

	public boolean AllDrakesD(){
		
		for (int d = 0; d < Drags.length; d++)
			if (Drags[d].alive())
				 return false;
		
		return true;
		
	}
	
	
	public char[][] giveTab(){
		
		char[][] temp = new char[tab.length][2 * tab.length];

		for (int i = 0; i < tab.length; i++)
			for (int a = 0; a < tab.length; a++) {
				if (temp[i][a] != 'X')
					temp[i][a] = ' ';
				temp[i][2 * a] = tab[i][a];
			}

		// System.out.printf("AQUI   ");

		temp[Hero.linha][2 * Hero.coluna] = Hero.symbol();

		for (int d = 0; d < Drags.length; d++)
			if (Drags[d].alive())
				temp[Drags[d].linha][2 * Drags[d].coluna] = Drags[d].symbol;

		if (!Hero.arma())
			temp[Espada.linha][2 * Espada.coluna] = Espada.symbol;

		for (int d = 0; d < Drags.length; d++)
			if (Espada.samePosition(Drags[d]))
				temp[Espada.linha][2 * Espada.coluna] = 'F';

		if (Ei.flying && Ei.alive) {
			temp[Ei.linha][2 * Ei.coluna] = 'W';
			if (Ei.Sword)
				temp[Ei.linha][2 * Ei.coluna + 1] = 'E';
		}
		
		
		return temp;
	}

	
	
	public char[][] giveTab2(){
		
		char[][] temp = new char[tab.length][tab.length];

		for(int i=0; i<tab.length; i++)
			  for(int j=0; j<tab.length; j++)
			    temp[i][j]=tab[i][j];

		temp[Hero.linha][Hero.coluna] = Hero.symbol();

		for (int d = 0; d < Drags.length; d++)
			if (Drags[d].alive())
				temp[Drags[d].linha][Drags[d].coluna] = Drags[d].symbol;

		if (!Hero.arma() && !Ei.armada())
			temp[Espada.linha][Espada.coluna] = Espada.symbol;

		for (int d = 0; d < Drags.length; d++)
			if (Espada.samePosition(Drags[d]))
				temp[Espada.linha][Espada.coluna] = 'F';

		if (Ei.flying && Ei.alive) {
			temp[Ei.linha][Ei.coluna] = 'W';
			
			if(Ei.Sword)
				temp[Ei.linha][Ei.coluna] = 'V';
		}
			
		
		return temp;
	}
	
	
	
	public void imprimeTab() {

		char[][]temp = giveTab();

		for (int linha = 0; linha < tab.length; linha++) {
			for (int coluna = 0; coluna < 2* temp.length; coluna++) {
				System.out.printf("%s", temp[linha][coluna]);
			}
			System.out.println();
		}

	}

	public Heroi getHero()
	{return Hero;}
	
	public Drake[] getDrags()
	{return Drags;}

	public Sword getSword()
	{return Espada;}
	
	public void EagleTime(){
		Ei.DrawPath(Espada, Hero);
	}
	
	public char[][] getOriTab(){
		return tab;
	}
	
	public Eagle getEagle(){
		return Ei;
	}

	public void movDrakeId(int d){
		movDrake( Drags[d]);
	}

}