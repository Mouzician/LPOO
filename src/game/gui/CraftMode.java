package game.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.JButton;


public class CraftMode extends JPanel {
	private JButton HeroButton;
	private JButton DrakeButton;
	private JButton WallButton;
	private JButton ExitButton;
	private JButton SaveButton;
	private JButton SwordButton;
	private JButton FloorButton;
	private JPanel CraftButtons;
	
	char tab[][];
	
	char print;
	
	GameFrame GFrame;
	GameOptions GOptions;
	
	
	public CraftMode(GameFrame GF,GameOptions GO)
	{
		GFrame = GF;
		GOptions = GO;
		
		tab = new char[GO.getnewSizeM()][GO.getnewSizeM()];
		for(int i = 0; i < tab.length; i++ )
			for(int a = 0; a < tab.length; a++)
				if((i == 0 || i == tab.length-1) && (a == 0 || a == tab.length-1))
					tab[i][a] = 'X';
				else
					tab[i][a] = 'C';
		
		char print = 'X';
		
		
	
		setFocusable(true);
	
		
		
		
	}
	
	
}