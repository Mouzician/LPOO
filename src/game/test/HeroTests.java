package game.test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.logic.*;
import game.logic.Drake.DragonChoice;

public class HeroTests {
/*
	@org.junit.Test
	public void test() {
		fail("Not yet implemented");
	}
*/
	char Demo[][] = {
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
	
	@Test
	public void Coordenadas() {
		Heroi Hero = new Heroi(Demo);
		
		assertTrue("Heroi coluna <= 0 e > Demo.lenght", Hero.col() >= 0 && Hero.col() < Demo.length );
		assertTrue("Heroi linha <= 0 e > Demo.lenght", Hero.lin() >= 0 && Hero.lin() < Demo.length );
	}
	
	@Test
	public void Movimento(){
		Heroi Hero  = new Heroi(1,1);

		assertTrue("Heroi moveu-se para direita", Hero.MoveObj(Demo, 1, 0) );
		assertTrue("Heroi moveu-se para baixo", Hero.MoveObj(Demo, 0, 1) );
	}
	
	@Test
	public void SemMovimento() {
		Heroi Hero  = new Heroi(1,1);

		assertTrue("Heroi nao conseguiu mover para a esquerda", !Hero.MoveObj(Demo, -1, 0) );
		assertTrue("Heroi nao conseguiu mover para cima", !Hero.MoveObj(Demo, 0, -1) );
	}
	
	@Test
	public void Armado(){
		Heroi Hero  = new Heroi(1,1);
		Sword S = new Sword(1,1);
		Drake D[] = new Drake[0];
		Game G = new Game(Demo, Hero, D,S);
		
		G.movHero(0, 0);
		
		assertTrue("Heroi armado", Hero.arma() );
		
	}
	
	@Test
	public void HeroiMorto(){
		Heroi Hero  = new Heroi(1,1);
		Sword S = new Sword(9,9);
		Drake Drake = new Drake(1,2,DragonChoice.NOTMOV);
		Drake D[] = new Drake[1];
		D[0] = Drake;
		Game G = new Game(Demo, Hero, D,S);
		
		G.movHero(1, 0);
		
		assertTrue("Heroi morreu", !Hero.alive() );
	}
	
	@Test
	public void DragaoMorto(){
		Heroi Hero  = new Heroi(1,1);
		Sword S = new Sword(1,1);
		Drake Drake = new Drake(1,3,DragonChoice.NOTMOV);
		Drake D[] = new Drake[1];
		D[0] = Drake;
		Game G = new Game(Demo, Hero, D,S);

		G.movHero(0, 0);
		G.movHero(1, 0);
		
		assertTrue("Heroi matou dragao", !D[0].alive() );
	}
	
	@Test
	public void Vitoria(){
		Cell Saida = new Cell(9,6);
		
		Heroi Hero  = new Heroi(8,3);
		Sword S = new Sword(8,4);
		Drake Drake = new Drake(8,6,DragonChoice.NOTMOV);
		Drake D[] = new Drake[1];
		D[0] = Drake;
		Game G = new Game(Demo, Hero, D,S);
		
		G.movHero(0, 1);
		G.movHero(0, 1);
		G.movHero(0, 1);
		G.movHero(1, 0);
		
		
		assertTrue("Heroi esta na saida", Hero.samePosition(Saida));
	}
	
	@Test
	public void NaoConsegueSair(){
		Cell Saida = new Cell(9,6);
		
		Heroi Hero  = new Heroi(9,5);
		Sword S = new Sword(9,5);
		Drake Drake = new Drake(1,1,DragonChoice.NOTMOV);
		Drake D[] = new Drake[1];
		D[0] = Drake;
		Game G = new Game(Demo, Hero, D,S);
		
		G.movHero(0, 0);
		G.movHero(1, 0);
		
		
		assertTrue("Heroi nao consegue sair", !Hero.samePosition(Saida));
	}
	
	
	
	
}
