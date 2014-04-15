package game.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Label;

import game.logic.*;
import game.logic.Drake.DragonChoice;

import javax.swing.*;

import game.logic.Cell;

public class GameFrame extends JFrame {

	private JButton GNewGame;
	private JButton GOptions;
	private JButton GSaveload;
	private JButton Gexit;
	private JPanel GButtons;
	private GamePanel GPanel;
	private GameOptions Gameoptions;
	private CraftMode CraftGame;

	public GameFrame() {
		Gameoptions = new GameOptions(GPanel);
		
		GPanel = new GamePanel(this);
		GPanel.setVisible(true);

		setTitle("Maze Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

		GButtons = new JPanel();

		ButtonsActions();
		getContentPane().setLayout(new BorderLayout(0, 0));

		addButtons();
		setSize(793, 470);
		
		CraftGame = new CraftMode(this,Gameoptions);
		
	}

	private void ButtonsActions() {
		GNewGame = new JButton("New Game");
		GNewGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				Icon icon = UIManager.getIcon("OptionPane.questionIcon");
				String[] buttons = { "New Game", "Create Game",
						"Cancel" };
				int choice = JOptionPane
						.showOptionDialog(rootPane, "Do you want New Game?",
								"Start Game", JOptionPane.PLAIN_MESSAGE, 0,
								icon, buttons, buttons[0]);
				if (choice == 0) {
					
					setSize(660,710);
					Game Jogo = new Game(Gameoptions.getnewSizeM(), Gameoptions
							.getnewNrD(), Gameoptions.getnewDrakeB());
					
					
					GPanel.updateBegin(Jogo,Gameoptions.keys());
					
					

				}
				else if (choice == 1) {
					
					
					setSize(660,710);
					getContentPane().remove(GPanel);
					getContentPane().remove(GButtons);
					//GPanel.setVisible(false);
					//GButtons.setVisible(false);
					CraftGame.update();
				}
				else if (choice == 2) {
					JOptionPane.getRootFrame().dispose();
				}
				GPanel.requestFocus();
			}

		});

		GOptions = new JButton("Options");
		GOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gameoptions.setVisible(true);
				
			}
		});

		GSaveload = new JButton("Save|Load");
		GSaveload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (GPanel.Inicio())
					JOptionPane.showMessageDialog(rootPane, "No game to save",
							"Error", JOptionPane.ERROR_MESSAGE);
				
				GPanel.requestFocus();
			}
		});

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
		GNewGame.setBackground(Color.BLACK);
		GNewGame.setForeground(Color.GRAY);

		GButtons.add(GSaveload);
		GSaveload.setBackground(Color.BLACK);
		GSaveload.setForeground(Color.GRAY);

		GButtons.add(GOptions);
		GOptions.setBackground(Color.BLACK);
		GOptions.setForeground(Color.GRAY);

		GButtons.add(Gexit);
		Gexit.setBackground(Color.BLACK);
		Gexit.setForeground(Color.GRAY);
		
		

		getContentPane().add(GButtons, BorderLayout.NORTH);
		
		GButtons.setFocusable(false);
		
		getContentPane().add(GPanel, BorderLayout.CENTER);
		pack();

	}

}
