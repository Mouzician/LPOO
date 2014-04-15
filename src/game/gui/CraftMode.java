package game.gui;

import game.logic.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Image;

import javax.swing.JButton;


public class CraftMode extends JPanel {
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
	
	
	private Image Wall;
	private Image Floor;
	private Image Hero;
	private Image Monster;
	private Image Sword;
	private Image End;
	private Image Craft;
	
	
	
	
	GameFrame GFrame;
	GameOptions GOptions;
	
	
	public CraftMode(GameFrame GF,GameOptions GO)
	{
		setVisible(false);
		GFrame = GF;
		GOptions = GO;
		char print = 'X';
		setFocusable(true);
		loadImages();
		ButtonsActions();
	}
	
	public void update(){
		/*
		
		if(GOptions.getnewSizeM() != 0)
			tab = new char[GOptions.getnewSizeM()][GOptions.getnewSizeM()];
		else
			tab = new char[11][11];
		
		
		for(int i = 0; i < tab.length; i++ )
			for(int a = 0; a < tab.length; a++)
				if((i == 0 || i == tab.length-1) && (a == 0 || a == tab.length-1))
					tab[i][a] = 'X';
				else
					tab[i][a] = 'C';
		*/
		
		addButtons();
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
	
	private void ButtonsActions() {
		
		HeroButton = new JButton("Hero");
		HeroButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = 'H';
			}

		});	
		
		DrakeButton = new JButton("Drake");
		DrakeButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = 'D';
			}

		});	
		
		
		WallButton = new JButton("Wall");
		WallButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = 'X';
			}

		});	
		
		SwordButton = new JButton("Sword");
		SwordButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = 'E';
			}

		});
		
	    FloorButton = new JButton("Floor");
		FloorButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = ' ';
			}

		});	
		
		
		EndButton = new JButton("End (Maze)");
		EndButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				print = 'S';
			}

		});

		ExitButton  = new JButton("Exit/Play");
		ExitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				//check if all good
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

	}
}