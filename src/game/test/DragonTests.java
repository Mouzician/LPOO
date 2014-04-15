package game.test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.logic.*;
import game.logic.Drake.DragonChoice;


public class DragonTests {
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
		Drake Dragao = new Drake(Demo,DragonChoice.NOTMOV);
		
		assertTrue("Dragao coluna <= 0 e > Demo.lenght", Dragao.col() >= 0 && Dragao.col() < Demo.length );
		assertTrue("Dragao linha <= 0 e > Demo.lenght", Dragao.lin() >= 0 && Dragao.lin() < Demo.length );
	}
	
	@Test
	public void DragaoMove() {
		Drake Dragao = new Drake(1,1,DragonChoice.NOTMOV);
		Cell c = new Cell(Dragao.lin(),Dragao.col());
		Dragao.MoveObj(Demo, 0, 1);
		
		assertFalse("Dragao linha <= 0 e > Demo.lenght", Dragao.samePosition(c) );
	}
	
	
	@Test
	public void DirecaoErrada() {
		Drake Dragao = new Drake(1,1,DragonChoice.NOTMOV);
		Cell c = new Cell(Dragao.lin(),Dragao.col());
		Dragao.MoveObj(Demo, -1, 0);
		
		assertTrue("Dragao nao moveu", Dragao.samePosition(c) );
	}
	
	
	@Test
	public void DragaoDorme() {
		Drake Dragao = new Drake(Demo,DragonChoice.MOVSLEEP);
		
		assertFalse("Dragao no inicio esta acordado", Dragao.sleeping() );
		
		Dragao.sleep();
		assertTrue("Dragao pode dormir", Dragao.sleeping() );
		
		Dragao.wakeup();
		assertFalse("Dragao pode voltar a acordar", Dragao.sleeping() );
		
		
	}
	
}
