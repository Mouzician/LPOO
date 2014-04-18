package game.gui;

import game.logic.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JButton;

/**
 * Classe referente à contrução do labirinto pelo utilizador através do rato
 */
public class CraftMode extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton HeroButton;
	private JButton DrakeButton;
	private JButton WallButton;
	private JButton SwordButton;
	private JButton FloorButton;
	private JButton EndButton;
	private JButton ExitButton;
	
	private JPanel CraftButtons;
	
	char tab[][];
	
	char print;
	
	Heroi Rambo;
	Sword Espadita;
	Drake Draks[];
	int FirstD = 0;
	Cell Out;
	
	
	private Image Wall;
	private Image Floor;
	private Image Hero;
	private Image Monster;
	private Image Sword;
	private Image End;
	private Image Craft;
	
	
	
	
	GameFrame GFrame;
	GameOptions GOptions;
	GamePanel GPanel;
	
	/**
	 * Construtor da classe
	 */
	public CraftMode(GameFrame GF,GameOptions GO,GamePanel GP)
	{
		Rambo = null;
		Sword = null;
		Out = null;
		addMouse();
		
		CraftButtons = new JPanel();
		setVisible(false);
		GFrame = GF;
		GOptions = GO;
		GPanel = GP;
		print = 'X';
		setFocusable(true);
		loadImages();
		ButtonsActions();
	}
	
	
	public void update(){
		
		Draks = new Drake[GOptions.getnewNrD()];
		
		for(int d = 0; d < Draks.length; d++)
			Draks[d] = null;
		
		
		if(GOptions.getnewSizeM() != 0)
			tab = new char[GOptions.getnewSizeM()][GOptions.getnewSizeM()];
		else
			tab = new char[11][11];
		
		
		for(int i = 0; i < tab.length; i++ )
			for(int a = 0; a < tab.length; a++)
				if((i == 0 || i == tab.length-1) || (a == 0 || a == tab.length-1))
					tab[i][a] = 'X';
				else
					tab[i][a] = 'C';
		
		
		addButtons();
		GFrame.setSize(688,705);
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

		// Monster
		temp = new ImageIcon(this.getClass().getResource("images/badguy.png"));
		Monster = temp.getImage();

		// Sword
		temp = new ImageIcon(this.getClass().getResource(
				"images/floorsword.png"));
		Sword = temp.getImage();
		
		// End
		temp = new ImageIcon(this.getClass().getResource("images/end2.png"));
		End = temp.getImage();

		// Craft
		temp = new ImageIcon(this.getClass().getResource("images/craft.png"));
		Craft = temp.getImage();

	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D) g;

		if(tab.length > 0)
		for (int i = 0; i < tab.length; i++)
			for (int a = 0; a < tab.length; a++) {
				drawSymbol(g2, a, i, tab[i][a]);
			}

	}

	private void drawSymbol(Graphics2D g2d, int x, int y, char s) {

		int nx = (getWidth() / tab.length) ;
		int ny = (getHeight() / tab.length);

		Image temp = Craft;

		switch (s) {
		case 'X':
			temp = Wall;
			break;
		case ' ':
			temp = Floor;
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
		case 'C':
			temp = Craft;
			break;
		case 'S':
			temp = End;
			break;
		}

		g2d.drawImage(temp, x * nx, y * ny, nx, ny, null);

	}
	
	
	private boolean MazeFinished(){
		boolean c = true;
		for(int i = 0; i < tab.length; i++)
			for(int a = 0; a < tab.length; a++)
			{
				if(tab[i][a] == 'C')
					c = false;
			}
		
		boolean drak = true;
		for(int d = 0; d < Draks.length; d++)
			if(Draks[d] == null)
				drak = false;

		return c && Rambo != null && Sword != null && drak && Out != null;
		
	}
	
	/**
	 * Gere os inputs nos botões do menu de Craft
	 */
	private void ButtonsActions() {
		
		HeroButton = new JButton("Hero");
		HeroButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = 'H';
				requestFocus();
			}

		});	
		
		DrakeButton = new JButton("Drake");
		DrakeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = 'D';
				requestFocus();
			}

		});	
		
		
		WallButton = new JButton("Wall");
		WallButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = 'X';
				requestFocus();
			}

		});	
		
		SwordButton = new JButton("Sword");
		SwordButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = 'E';
				requestFocus();
			}

		});
		
	    FloorButton = new JButton("Floor");
		FloorButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = ' ';
				requestFocus();
			}

		});	
		
		
		EndButton = new JButton("End (Maze)");
		EndButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = 'S';
				requestFocus();
			}

		});

		ExitButton  = new JButton("Exit/Play");
		ExitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				Icon icon = UIManager.getIcon("OptionPane.questionIcon");
				String[] buttons = { "Play", "Exit",
						"Cancel" };
				int choice = JOptionPane
						.showOptionDialog(null, "Play or Exit to Menu?",
								"Start Game", JOptionPane.PLAIN_MESSAGE, 0,
								icon, buttons, buttons[0]);
				if (choice == 0) 
					if( MazeFinished())
					playGame();
					
				if(choice == 1)
				destructur();
			}

		});
		
		
	}
	
	
	void setcolor(JButton b){
		b.setBackground(Color.BLACK);
		b.setForeground(Color.GRAY);
	}

	
	private void addButtons() {

		CraftButtons.setLayout(new GridLayout(1, 7));
		
		
		CraftButtons.add(HeroButton);
		setcolor(HeroButton);
		
		
		CraftButtons.add(DrakeButton);
	    setcolor(DrakeButton);
	    

	    CraftButtons.add(WallButton);
	    setcolor( WallButton);

	    CraftButtons.add(SwordButton);
	    setcolor(SwordButton);
	    
	    CraftButtons.add(FloorButton);
	    setcolor(FloorButton);
	    
	    CraftButtons.add(EndButton);
	    setcolor(EndButton);
	    
	    
		CraftButtons.add(ExitButton);
		setcolor( ExitButton);

	

		GFrame.getContentPane().add(CraftButtons, BorderLayout.NORTH);
		
		CraftButtons.setFocusable(false);
		
		GFrame.getContentPane().add(this, BorderLayout.CENTER);
		GFrame.pack();
		
		CraftButtons.setVisible(true);
		setVisible(true);
		requestFocus();

	}
	

	private void addsymbol(int lin, int col)
	{
		lin = (lin / (getHeight() / tab.length));
		col = (col / (getWidth()/ tab.length));
		
		if(lin >= tab.length || col >= tab.length)
			return;
		
		
		//Heroi
		if(print == 'H' && Rambo != null)
		{
			tab[Rambo.lin()][Rambo.col()] = 'C';
			Rambo.setlin(lin);
			Rambo.setcol(col);
			tab[lin][col] = print;
		}
		else if(print == 'H')
		{
			Rambo = new Heroi(lin, col);
			tab[lin][col] = print;
		}
		
		//Espada
		if(print == 'E' && Espadita != null)
		{
			tab[Espadita.lin()][Espadita.col()] = 'C';
			Espadita.setlin(lin);
			Espadita.setcol(col);
			tab[lin][col] = print;
		}
		else if(print == 'E')
		{
			Espadita = new Sword(lin, col);
			tab[lin][col] = print;
		}
		
		
		if(print == 'D')
		{
			for(int d = 0; d < Draks.length; d++)
			{
				if(Draks[d] == null)
				{
					Draks[d] = new Drake(lin,col,GOptions.getnewDrakeB());
				
				tab[Draks[d].lin()][Draks[d].col()] = print;
				repaint();
				return;}
			}
			
			tab[Draks[FirstD].lin()][Draks[FirstD].col()] = 'C';
			Draks[FirstD] = new Drake(lin,col,GOptions.getnewDrakeB());
			FirstD++;
			if(FirstD == Draks.length)
				FirstD = 0;
		}
		if(print == 'S' && Out == null)
		{
			if(lin == 0 || lin == tab.length-1 || col == 0 || col == tab.length-1)
			Out = new Cell(lin, col);
			else
				return;
			
		}
		else if(print == 'S')
		{
			if(lin == 0 || lin == tab.length-1 || col == 0 || col == tab.length-1){
			tab[Out.getLine()][Out.getCol()] = 'X';
			Out = new Cell(lin, col);}
			else return;
			
		}

		tab[lin][col] = print;
		repaint();
	}

	private void removesymbol(int lin, int col)
	{
		lin = (lin / (getHeight() / tab.length));
		col = (col / (getWidth()/ tab.length));
		
		if(lin >= tab.length || col >= tab.length)
			return;
		
		if(tab[lin][col] == 'H')
			Rambo = null;
		
		if(tab[lin][col] == 'E')
			Espadita = null;
		
		if(tab[lin][col] == 'D')
			for(int d = 0; d < Draks.length; d++)
				if(Draks[d].lin() == lin && Draks[d].col() == col)
					Draks[d] = null;
		
		if(tab[lin][col] == 'S')
			{Out = null;
			tab[lin][col] = 'X';
			repaint();
			return;
			}
		
		tab[lin][col] = 'C';
		repaint();
	}
	
	private void playGame(){
		GFrame.getContentPane().remove(CraftButtons);
		GFrame.getContentPane().remove(this);
		GFrame.addButtons();
		GFrame.setSize(660,710);
		
		Game Jogo = new Game(tab, Rambo, Draks, Espadita);
		GPanel.updateBegin(Jogo,GOptions.keys());
	}
	
	private void destructur(){
		GFrame.getContentPane().remove(CraftButtons);
		GFrame.getContentPane().remove(this);
		GFrame.addButtons();
		GFrame.setSize(793, 470);
	}
	
	/**
	 * Gestor dos inputs do rato
	 */
	private void addMouse(){
		
		addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                switch (e.getButton()) {
    			case MouseEvent.BUTTON1:
    				addsymbol(e.getY(), e.getX());
    				break;
    			case MouseEvent.BUTTON2:
    				break;
    			case MouseEvent.BUTTON3:
    				removesymbol(e.getY(), e.getX());
    				break;
    			}
                
                
                
                
                
            }
            public void mouseEntered(MouseEvent e) {
               // System.out.println("mouseEntered");
            }
            public void mouseExited(MouseEvent e) {
                //System.out.println("mouseExited");
            }
            public void mousePressed(MouseEvent e) {
                //System.out.println("mousePressed");
            }
            public void mouseReleased(MouseEvent e) {
                //System.out.println("mouseReleased");
            }
        });
	}

}