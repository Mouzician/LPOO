package game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.event.KeyAdapter;


public class GamePanel extends JPanel implements ActionListener {
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
		
	private int upKey = KeyEvent.VK_W;
	private int leftKey = KeyEvent.VK_A;
	private int rightKey = KeyEvent.VK_D;
	private int downKey = KeyEvent.VK_S;
	private int sendEagleKey = KeyEvent.VK_E;
	
	char[][] tab;
	
	private boolean Inicio;
	
	public GamePanel(char[][] t) {
		Inicio = false;
		loadImages();
		tab = t;
		
	}

	private void loadImages(){
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
		temp = new ImageIcon(this.getClass().getResource("images/herosword.png"));
		HeroS = temp.getImage();
		
		// Monster
		temp = new ImageIcon(this.getClass().getResource("images/badguy.png"));
		Monster = temp.getImage();
		
		// Sword
		temp = new ImageIcon(this.getClass().getResource("images/floorsword.png"));
		Sword = temp.getImage();
		
		// Bird
		temp = new ImageIcon(this.getClass().getResource("images/bird.png"));
		Bird = temp.getImage();
		
		//End
		temp = new ImageIcon(this.getClass().getResource("images/end.png"));
		End = temp.getImage();
			
		//Intro
		temp = new ImageIcon(this.getClass().getResource("images/intro.png"));
		Intro = temp.getImage();
		
		//Intro
		temp = new ImageIcon(this.getClass().getResource("images/badguysword.png"));
		MonsterS = temp.getImage();
		
		
	}
	
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponents(g);
	    Graphics2D g2 = (Graphics2D) g;
	    
	    if(Inicio)
	    g2.drawImage(Intro,0,0,null);
	    else
	    	DrawMaze(g2);
	
	    
	   }
	
	private void DrawMaze(Graphics2D g2d){
		
		for (int i = 0; i < tab.length; i++)
			for (int a = 0; a < tab.length; a++)
			{
					drawSymbol(g2d,a,i,tab[i][a]);
				
			}
		
		
		
		
	}
	
	private boolean exists(char t)
	{
		for (int i = 0; i < tab.length; i++)
			for (int a = 0; a < tab.length; a++)
				if(tab[i][a] == t)
					return true;
	
	return false;
	}
	
	
	
	private void drawSymbol(Graphics2D g2d,int x, int y, char s)
	{
		int nx = x * 50;
		int ny = y * 50;
		
		Image temp = Intro;
		
		switch(s)
		{
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
		}
		
		g2d.drawImage(temp, nx, ny, 50, 50 , null);

		
		
	}
	
	
	private void updateTab(char[][] t)
	{tab = t;}
	
	
}