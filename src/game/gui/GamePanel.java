package game.gui;

import game.logic.*;
import game.logic.Drake.DragonChoice;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import game.logic.Cell;

public class GamePanel extends JPanel implements  KeyListener {
	private Image Wall;
	private Image Floor;
	private Image Hero;
	private Image HeroS;
	private Image Monster;
	private Image Sword;
	private Image Bird;
	private Image Intro;
	private Image End;
	private Image MonsterS;
	private Image SpleepMonster;

	private int FSizeX;
	private int FSizeY;

	private int upKey = KeyEvent.VK_W;
	private int leftKey = KeyEvent.VK_A;
	private int rightKey = KeyEvent.VK_D;
	private int downKey = KeyEvent.VK_S;
	private int sendEagleKey = KeyEvent.VK_E;

	Game Jogo;

	private boolean Inicio;

	public GamePanel() {
		Inicio = true;
		loadImages();
		Jogo = null;
		requestFocus();
		addKeyListener(this);
		setFocusable(true);

		
	}

	private void loadImages() {
		ImageIcon temp;

		// wall
		temp = new ImageIcon(this.getClass().getResource("images/wall.png"));
		Wall = temp.getImage();

		// Floor
		temp = new ImageIcon(this.getClass().getResource("images/floor.png"));
		Floor = temp.getImage();

		// Hero
		temp = new ImageIcon(this.getClass().getResource("images/hero.png"));
		Hero = temp.getImage();

		// HeroS
		temp = new ImageIcon(this.getClass()
				.getResource("images/herosword.png"));
		HeroS = temp.getImage();

		// Monster
		temp = new ImageIcon(this.getClass().getResource("images/badguy.png"));
		Monster = temp.getImage();

		// Sword
		temp = new ImageIcon(this.getClass().getResource(
				"images/floorsword.png"));
		Sword = temp.getImage();

		// Bird
		temp = new ImageIcon(this.getClass().getResource("images/bird.png"));
		Bird = temp.getImage();

		// End
		temp = new ImageIcon(this.getClass().getResource("images/end2.png"));
		End = temp.getImage();

		// Intro
		temp = new ImageIcon(this.getClass().getResource("images/intro.png"));
		Intro = temp.getImage();

		// Monster&Sword
		temp = new ImageIcon(this.getClass().getResource(
				"images/badguysword.png"));
		MonsterS = temp.getImage();
		
		// Monster Sleeping
		temp = new ImageIcon(this.getClass().getResource(
				"images/badguysleep.png"));
		 SpleepMonster = temp.getImage();
		

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;

		if (Inicio)
			g2.drawImage(Intro, 0, 0, null);
		else
			DrawMaze(g2);

	}

	private void DrawMaze(Graphics2D g2d) {
		char[][] tab = Jogo.giveTab2();
		
		for (int i = 0; i < tab.length; i++)
			for (int a = 0; a < tab.length; a++) {
				drawSymbol(g2d, a, i, tab[i][a]);

			}

		FSizeX = (FSizeX * tab.length);
		FSizeY = (FSizeY * tab.length);
	}



	private void drawSymbol(Graphics2D g2d, int x, int y, char s) {

		int nx = (FSizeX / Jogo.giveTab2().length);
		int ny = (FSizeY / Jogo.giveTab2().length);

		Image temp = Intro;

		switch (s) {
		case 'X':
			temp = Wall;
			break;
		case ' ':
			temp = Floor;
			break;
		case 'A':
			temp = HeroS;
			break;
		case 'H':
			temp = Hero;
			break;
		case 'E':
			temp = Sword;
			break;
		case 'D':
			temp = Monster;
			break;
		case 'B':
			temp = Bird;
			break;
		case 'S':
			temp = End;
			break;
		case 'F':
			temp = MonsterS;
			break;
		}

		g2d.drawImage(temp, x * nx, y * ny, nx, ny, null);

	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		 System.out.println("keyPressed: "+e);
		if (Inicio)
			return;

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT || key == rightKey)
			Jogo.movHero(1, 0);
		else if (key == KeyEvent.VK_DOWN || key == downKey)
			Jogo.movHero(0, 1);
		else if (key == KeyEvent.VK_LEFT || key == leftKey)
			Jogo.movHero(-1, 0);
		else if (key == KeyEvent.VK_UP || key == upKey)
			Jogo.movHero(0, -1);
		
		//else if (key == sendEagleKey)
			
		Drake tempD[] = Jogo.getDrags();
		for (int d = 0; d < tempD.length; d++)
			if ( tempD[d].alive())
				 Jogo.movDrakeId(d);

		if(Jogo.getEagle().alive() && Jogo.getEagle().flying())
			Jogo.movEagle();
		
		
		if (!Jogo.getHero().alive())
			Inicio = true;
		
		repaint();
	}
	  @Override
      public void keyTyped(KeyEvent e) {
		  System.out.println("keyTyped: "+e);
	  }

      @Override
      public void keyReleased(KeyEvent e) {
    	  System.out.println("keyReleased: "+e);
      }
	
	


	public void updateBegin(Game j,int[] keys) {
		Jogo = j;
		Inicio = false;
		FSizeX = 660;
		FSizeY = 660;
		setSize(660,660);
		
		upKey = keys[0];
		leftKey = keys[1];
		rightKey = keys[2];
		downKey = keys[3];
		sendEagleKey = keys[4];

		repaint();
	}

	public Cell newSize() {
		return new Cell(FSizeY, FSizeX);
	}
	public boolean Inicio(){
		return Inicio;
	}
	

}