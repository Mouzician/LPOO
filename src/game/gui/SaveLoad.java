package game.gui;

import game.logic.*;

import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

/**
 * A classe Save Load gere os jogos guardados e os jogos a guardar
 */
public class SaveLoad extends JDialog {
	
	private static final long serialVersionUID = 1L;
	private GamePanel GPanel;
	private GameFrame GFrame;
	private JTextField txtSave;
	private Game CurrentGame;
	JButton Save;
	JButton Load;
	private List Files;
	
	
	private static final String SaveFolder = System
			.getProperty("user.dir") + "/Saved Games/";
	
	//private JPanel JSaveLoad;
	
	/**
	 * Construtor da classe
	 */
	public SaveLoad(GameFrame GF, GamePanel GP)
	{
		setSize(300, 200);
		setTitle("Save | Load");
		GFrame = GF;
		GPanel = GP;
		
		getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		ButtonsActions();
		addSave();
		addLoad();
		setVisible(false);
		WhenClosed();
	}
	
	
	private void WhenClosed(){
		addWindowListener(new WindowAdapter() 
		{
		  public void windowClosed(WindowEvent e)
		  {
		   //
		  }

		  public void windowClosing(WindowEvent e)
		  {
			  GPanel.requestFocus();
		  }
		});
	}
	
	/**
	 * Gere os inputs dos botões no menu Save/Load
	 */
	private void ButtonsActions() {
		
		Save = new JButton("Save");
		Save.setBackground(Color.BLACK);
		Save.setForeground(Color.GRAY);
		Save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (GPanel.Inicio() || GPanel.getGame() == null) {
					JOptionPane.showMessageDialog(null,
							"No game to Save");
					return;
				}

				if (txtSave.getText().length() == 0) {
					JOptionPane.showMessageDialog(null,
							"Write a name to Save the game");
					return;
				}

				File savesFolder = new File(SaveFolder);
				if (!savesFolder.exists())
					savesFolder.mkdir();

				ObjectOutputStream file = null;
				try {
					file = new ObjectOutputStream(new FileOutputStream(SaveFolder
							+ txtSave.getText()/* + ".dat"*/));
					
					
					file.writeObject(GPanel.getGame());
					file.close();
					JOptionPane.showMessageDialog(null, "Game successfully saved.");
					setVisible(false);
				} catch (IOException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,
							"An error occured while saving the game.");
				}
				
				
			}
			});
		
		
		Load = new JButton("Load");
		Load.setBackground(Color.BLACK);
		Load.setForeground(Color.GRAY);
		Load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String filename = String.valueOf(Files.getSelectedItem());
				
				if(filename != null)
				{
					
					if (Files.getSelectedItem() == null) {
						JOptionPane.showMessageDialog(null,
								"No game to Load");
						return;
					}
					
					
					try {
					FileInputStream fin = new FileInputStream(SaveFolder
							+ filename/* + ".dat"*/);
					ObjectInputStream ois = new ObjectInputStream(fin);
					CurrentGame = (Game) ois.readObject();
					ois.close();
					
					GFrame.LoadGame(CurrentGame);
					
					setVisible(false);
					}
					catch (Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Erro ao fazer Load");
					}		
				}
				}
			});
		
	}
	
	/**
	 * Guarda o jogo atual com o nome a ser fornecido pelo utilizador
	 */
	public void addSave(){
		
		JPanel JSave = new JPanel();
		
		JLabel titleSave = new JLabel("Name:");
		JSave.add(titleSave);
		
		
		 txtSave = new JTextField(8);
		 JSave.add(txtSave);
		
		 JSave.add(Save);
		 add(JSave);
		
	}
	
	
	public void updateFiles()
	{
		
		File folder = new File(SaveFolder);
		if (!folder.isDirectory())
			return;

		Files.removeAll();
		for (File file : folder.listFiles())
			Files.add(file.getName());
	}
	
	
	public void addLoad(){
		
		JPanel lisF = new JPanel();
		
		Files = new List();
		updateFiles();

		lisF.add(Files);
		lisF.add(Load);
		add(lisF);
		
	}
}

