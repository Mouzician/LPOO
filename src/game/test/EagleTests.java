package game.test;

import static org.junit.Assert.*;

import org.junit.Test;

import game.logic.*;
import game.logic.Drake.DragonChoice;

public class EagleTests {

	
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
	public void AguiaExiste() {
		
		Heroi Hero = new Heroi(8,3);
		Eagle eagle = new Eagle(Hero);

		assertFalse(eagle.armada());
		assertTrue(eagle.alive());
		assertFalse(eagle.flying());
		assertTrue("Aguia so é criada a partir do Hero", Hero.samePosition(eagle));
	}
	
	@Test
	public void AguiaVoa(){
		
		Heroi Hero  = new Heroi(1,1);
		Sword S = new Sword(1,8);
		Drake D[] = new Drake[0];
		Game G = new Game(Demo, Hero, D,S);
		
		G.EagleTime();
		
		
		assertTrue("Aguia a voar", G.getEagle().flying());
		
		G.movEagle();
		assertFalse("Posiçao diferente do Hero",Hero.samePosition(G.getEagle()));
	}
	

}
