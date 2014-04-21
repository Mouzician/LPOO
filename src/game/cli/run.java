package game.cli;

import java.util.Scanner;

import game.logic.Drake;
import game.logic.Game;
import game.logic.Drake.DragonChoice;

public class run{
	
	static Game Jogo;
	
	public static boolean play() {

		char tab[][] = Jogo.getOriTab();
		
		
		
		while (!Jogo.getHero().arma() || tab[Jogo.getHero().lin()][Jogo.getHero().col()] != 'S') {
			int dx = 0;
			int dy = 0;

			Jogo.imprimeTab();

			Scanner in = new Scanner(System.in);
			System.out.println("Faz jogada: ");
			char comp = in.nextLine().charAt(0);

			switch (comp) {
			case 'd':
				dx = 1;
				dy = 0;
				break;
			case 'a':
				dx = -1;
				dy = 0;
				break;
			case 'w':
				dx = 0;
				dy = -1;
				break;
			case 's':
				dx = 0;
				dy = 1;
				break;
			case 'e':
				Jogo.EagleTime();
				break;
			}

			//in.close();
			
			Jogo.movHero(dx, dy);

			Drake tempD[] = Jogo.getDrags();
			for (int d = 0; d < tempD.length; d++)
				if ( tempD[d].alive())
					 Jogo.movDrakeId(d);

			if(Jogo.getEagle().alive() && Jogo.getEagle().flying())
				Jogo.movEagle();
			
			
			if (!Jogo.getHero().alive())
				return false;

			
			
		}

		return true;
	}
	
	
	
	public static void main(String[] args){
				
		Scanner in = new Scanner(System.in);
		System.out.println("1. Dragon Sleep/Mov \n2. No Moves\n3. Only Move ");
		char comp = in.nextLine().charAt(0);
		
		DragonChoice escolha = DragonChoice.MOV;
		switch (comp) {
		case '1':
			escolha = DragonChoice.MOVSLEEP;
			break;
		case '2':
			escolha = DragonChoice.NOTMOV;
			break;
		case '3':
			escolha = DragonChoice.MOV;
			break;
		}
			

		
		System.out.println("Number of Drakes: ");
	    int nrdrakes = in.nextInt();
		
	    int nrLados =0;
	    do{
	    System.out.println("Number of Sides( 0 for Demo): ");
	    nrLados = in.nextInt();
	    
	    }while(nrLados < 5 && nrLados != 0);
	    
	    if((nrLados % 2) ==0 && nrLados != 0)
	    	nrLados++;
	    
		Jogo = new Game(nrLados,nrdrakes,escolha);
		
		if(play())
			System.out.printf(" %s ", "Parabens MODAFACKA!");
		else
			System.out.printf(" %s ", "Es uma merda");
		
		in.close();
		
	}
	
	
	
}