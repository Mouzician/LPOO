package game.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Label;

import javax.swing.JLabel;

import game.logic.*;
import game.logic.Drake.DragonChoice;

import javax.swing.*;

public class GameFrame  extends JFrame {
	
	private Game Jogo;
	
	boolean jogoAtivo;
	private JButton GNewGame;
	private JButton GOptions;
	private JButton GSaveload;
	private JButton Gexit;
	private JPanel GButtons;
	private GamePanel GPanel;
	private JDialog options;
	
	
	public GameFrame(){
		jogoAtivo = true;
		Jogo = new Game(0,1,DragonChoice.NOTMOV);
		GPanel = new GamePanel(Jogo.giveTab2());
		
		
		setTitle("Maze Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		GButtons = new JPanel();
		
		ButtonsActions();
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		addButtons();
		setSize(793, 470);
	}
	
	
	
	private void ButtonsActions()
	{
		GNewGame = new JButton("New Game");
		GNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//a preencher
			}
		
		}
		);
		
		
		GOptions = new JButton("Options");
		GOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//a preencher
			}
		}
		);
		
		GSaveload = new JButton("Save|Load");
		GSaveload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!jogoAtivo)
					JOptionPane.showMessageDialog(rootPane , "No game to save","Error", JOptionPane.ERROR_MESSAGE);
				
				//a preencher
			}
		}
		);
		
		Gexit = new JButton("Quit");
		Gexit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "Are you sure you want to quit?";
				int res = JOptionPane.showConfirmDialog(rootPane, msg);

				if (res == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		
	}
	
	
	
	
	
	private void addButtons() {
		
		GButtons.setLayout(new GridLayout(1, 4));
		GButtons.add(GNewGame);
		GButtons.add(GSaveload);
		GButtons.add(GOptions);
		GButtons.add(Gexit);
		
		
		
		getContentPane().add(GButtons, BorderLayout.NORTH);
		getContentPane().add(GPanel,BorderLayout.CENTER);
		pack();
		
		//teste
		//getContentPane().add(GSymbols, BorderLayout.NORTH);
	}


}

