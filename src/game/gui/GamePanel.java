package game.gui;

import game.logic.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * A classe Game Panel trata da janela do jogo
 */
public class GamePanel extends JPanel implements  KeyListener {
	
	private static final long serialVersionUID = 1L;
	private Image Wall;
	private Image Floor;
	private Image Hero;
	private Image HeroS;
	private Image Monster;
	private Image Sword;
	private Image Bird;
	private Image BirdS;
	private Image Intro;
	private Image End;
	private Image MonsterS;
	private Image SpleepMonster;

	private int upKey = KeyEvent.VK_W;
	private int leftKey = KeyEvent.VK_A;
	private int rightKey = KeyEvent.VK_D;
	private int downKey = KeyEvent.VK_S;
	private int sendEagleKey = KeyEvent.VK_E;

	Game Jogo;
	GameFrame GFrame;

	private boolean Inicio;

	/**
	 * Construtor da classe
	 */
	public GamePanel(GameFrame g) {
		GFrame = g;
		Inicio = true;
		loadImages();
		Jogo = null;
		addKeyListener(this);
		setFocusable(true);
		
	}

	/**
	 * Carrega as imagens usadas no desenho dos vários elementos do labirinto
	 */
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

		//BirdS
		temp = new ImageIcon(this.getClass().getResource("images/birdS.png"));
		BirdS = temp.getImage();
		
		
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
			g2.drawImage(Intro, 0,0 ,getWidth(),getHeight(), null);
		else
			DrawMaze(g2);

	}

	/**
	 * Este método desenha o labirinto
	 * 
	 * @see {@link drawSymbol(Graphics2D, int, int, char)}
	 */
	private void DrawMaze(Graphics2D g2d) {
		
		char[][] tab = Jogo.giveTab2();
		
		for (int i = 0; i < tab.length; i++)
			for (int a = 0; a < tab.length; a++) {
				drawSymbol(g2d, a, i, tab[i][a]);
			}
		
		
	}

	/**
	 *Transforma o char do elemento char[][] tab que contém os elementos do 
	 *labirinto numa imagem a desenhar na janela.
	 *
	 *@param x coordenada x do elemento
	 *
	 *@param y coordenada y do elemento
	 *
	 *@param s representação em string do objecto a transformar numa imagem
	 */
	private void drawSymbol(Graphics2D g2d, int x, int y, char s) {

		int nx = (getWidth() / Jogo.giveTab2().length) ;
		int ny = (getHeight() / Jogo.giveTab2().length);

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
		case 'd':
			temp = SpleepMonster;
			break;
		case 'W':
			temp = Bird;
			break;
		case 'V':
			temp = BirdS;
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
	
	/**
	 *Gestor das tecas premidas para a movimentação do heroi e da aguia durante
	 *o jogo.
	 *Efectua a ação transmitida pelas teclas e volta a imprimir o labirinto
	 *
	 *@param e tecla premida a ser tratada
	 */
	@Override
	public void keyPressed(KeyEvent e) {
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
		else if (key == sendEagleKey && !Jogo.getEagle().flying() && !Jogo.getHero().arma())
			Jogo.EagleTime();
			
		Drake tempD[] = Jogo.getDrags();
		for (int d = 0; d < tempD.length; d++)
			if ( tempD[d].alive())
				 Jogo.movDrakeId(d);

		if(Jogo.getEagle().alive() && Jogo.getEagle().flying())
			Jogo.movEagle();
		
		
		repaint();
		
		if (!Jogo.getHero().alive() || Jogo.getOriTab()[Jogo.getHero().lin()][Jogo.getHero().col()] == 'S')
			{	 
			final ImageIcon icon;
			
			if(!Jogo.getHero().alive())
			{
				icon= new ImageIcon(this.getClass().getResource("images/gameover.png"));
			 JOptionPane.showMessageDialog(null, "YOU DEAD BITCH", "GAME OVER", JOptionPane.INFORMATION_MESSAGE, icon);
			}
			else
				{icon = new ImageIcon(this.getClass().getResource("images/cookie.png"));
				JOptionPane.showMessageDialog(null, "give that man a cookie", "YOU WON", JOptionPane.INFORMATION_MESSAGE, icon);
				}
	       
	        Inicio = true;
	        GFrame.setSize(793, 470);
			}
		

	
		
	}
	  @Override
      public void keyTyped(KeyEvent e) {
	  }

      @Override
      public void keyReleased(KeyEvent e) {
      }
	
     /**
  	 *Muda a imagem da janela da imagem inicial para a imagem do jogo
  	 *
  	 * @param keys Representam as teclas atribuidas pelo utilizador para mover o jogador e a águia
  	 */
	public void updateBegin(Game j,int[] keys) {
		Jogo = j;
		Inicio = false;

		
		upKey = keys[0];
		downKey = keys[1];
		leftKey = keys[2];
		rightKey = keys[3];
		sendEagleKey = keys[4];
		
		
		repaint();

		requestFocus();
	}


	public boolean Inicio(){
		return Inicio;
	}
	
	public Game getGame(){
		return Jogo;
	}
	public void setGame(Game G)
	{
		Jogo = G;
	}
	
	/**
	 * Substitui a imagem do labirinto pelo "Wallpaper" pré-definido
	 */
	public void BackToStart(){
		GFrame.setSize(793, 470);
		Jogo = null;
		Inicio = true;
		repaint();
	}
	

}