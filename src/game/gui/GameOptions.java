package game.gui;


import game.logic.Drake.DragonChoice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import java.awt.event.*;

/**
 * 
 *A classe Game Options trata de construir e gerir os inputs da 
 *janela de opções do jogo
 *
 */
public class GameOptions extends JDialog {
	
	private static final long serialVersionUID = 1L;
	JSlider MazeSize;
	JSlider Drakenr;
	JButton Apply;
	JButton Cancel;
	private int MSize = 0;
	private int DNumber = 1;
	JPanel PanelOpt;
	JRadioButton[] Drakeb;
	private DragonChoice newDChoice = DragonChoice.NOTMOV;

	private String Upkey = "W";
	private String Downkey = "S";
	private String Leftkey = "A";
	private String Rightkey = "D";
	private String Eaglekey = "E";
	
	JTextField keyUp;
	JTextField keyDown;
	JTextField keyLeft;
	JTextField keyRight;
	JTextField keyEagle;
	
	GamePanel GPanel;
	
	/**
	 * 
	 *Construtor da classe.
	 *
	 */
	public GameOptions(GamePanel g) {
		setBackground(Color.GRAY);
		PanelOpt = new JPanel();
		setTitle("Game Options");
		
		GPanel = g;
		checkSlider();
		checkBehav();
		keyChange();
		ButtonsActions();
		setSize(300, 400);
		addButtons();
		WhenClosed();
		setLocationRelativeTo(null);
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
	 * Gestor dos dois sliders referentes ao tamanho do maze e ao número de
	 * dragões.
	 */
	private void checkSlider() {

		// Maze Size
		JLabel titleSize = new JLabel("Maze Dimension");
		titleSize.setHorizontalAlignment(SwingConstants.CENTER);

		getContentPane().add(PanelOpt);

		MazeSize = new JSlider(JSlider.HORIZONTAL, 5, 27, 11);

		MazeSize.setMajorTickSpacing(10);
		MazeSize.setMinorTickSpacing(1);
		MazeSize.setPaintTicks(true);
		MazeSize.setPaintLabels(true);
		MazeSize.setSnapToTicks(true);

		// Drake Numbers
		JLabel titleDrake = new JLabel("Number of Drakes");
		titleDrake.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		titleDrake.setHorizontalAlignment(SwingConstants.CENTER);

		getContentPane().add(PanelOpt, BorderLayout.CENTER);

		Drakenr = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);

		Drakenr.setMajorTickSpacing(10);
		Drakenr.setMinorTickSpacing(1);
		Drakenr.setPaintTicks(true);
		Drakenr.setPaintLabels(true);
		Drakenr.setSnapToTicks(true);

		PanelOpt.add(titleSize);
		PanelOpt.add(MazeSize);

		PanelOpt.add(titleDrake);
		PanelOpt.add(Drakenr);

	}
	
	/**
	 * Gestor dos botões de comportamento dos dragões.
	 */
	private void checkBehav() {

		// Maze Size
		JLabel titleBeha = new JLabel(
				"                             Drake(s) Behav");
		titleBeha.setHorizontalAlignment(SwingConstants.CENTER);

		Drakeb = new JRadioButton[3];
		Drakeb[0] = new JRadioButton("Move");
		Drakeb[1] = new JRadioButton("Move/Sleep");
		Drakeb[2] = new JRadioButton("Not Move");
		Drakeb[2].setSelected(true);

		// PanelOpt.setLayout(new GridLayout(4, 1));~
		// enter
		JLabel enter = new JLabel("                             ");
		titleBeha.setHorizontalAlignment(SwingConstants.CENTER);

		PanelOpt.add(titleBeha);
		PanelOpt.add(enter);
		for (int i = 0; i < Drakeb.length; i++)
			PanelOpt.add(Drakeb[i]);

		ButtonGroup group = new ButtonGroup();
		for (int i = 0; i < Drakeb.length; i++)
			group.add(Drakeb[i]);
	}
	
	/**
	 *Gestor de todos os inputs da janela de opões. 
	 */
	private void ButtonsActions() {
		Apply = new JButton("Apply");
		Apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (MazeSize.getValue() % 2 == 0)
					MSize = MazeSize.getValue() + 1;
				else
					MSize = MazeSize.getValue();

				
					DNumber = Drakenr.getValue();

				if (Drakeb[0].isSelected())
					newDChoice = DragonChoice.MOV;
				else if (Drakeb[1].isSelected())
					newDChoice = DragonChoice.MOVSLEEP;
				else if (Drakeb[2].isSelected())
					newDChoice = DragonChoice.NOTMOV;

				
				if(StringKey(keyUp.getText()) != 0)
					Upkey = keyUp.getText();
				else
					keyUp.setText(String.valueOf(Upkey));
				
				if(StringKey(keyDown.getText()) != 0)
					Downkey = keyDown.getText();
				else
					keyDown.setText(String.valueOf(Downkey));
				
				if(StringKey(keyLeft.getText()) != 0)
					Leftkey = keyLeft.getText();
				else
					keyLeft.setText(String.valueOf(Leftkey));
				
				if(StringKey(keyRight.getText()) != 0)
					Rightkey = keyRight.getText();
				else
					keyRight.setText(String.valueOf(Rightkey));
				
				if(StringKey(keyEagle.getText()) != 0)
					Eaglekey = keyEagle.getText();
				else
					keyEagle.setText(String.valueOf(Eaglekey));
				
				
				
				setVisible(false);
				setFocusable(false);
				GPanel.requestFocus();
			}

		});

		Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "Are you sure you want to Cancel?";
				int res = JOptionPane.showConfirmDialog(rootPane, msg);

				if (res == JOptionPane.YES_OPTION)
					setVisible(false);
			
				setFocusable(false);
				GPanel.requestFocus();
			}
		});
		
		
	}

	/**
	 * Adicionar botões "Apply" e "Cancel" a janela de opções.
	 */
	private void addButtons() {
		PanelOpt.add(Apply);
		Apply.setBackground(Color.BLACK);
		Apply.setForeground(Color.GRAY);

		PanelOpt.add(Cancel);
		Cancel.setBackground(Color.BLACK);
		Cancel.setForeground(Color.GRAY);
	}
	
	/**
	 *Gestor da área de seleção das teclas a usar para jogar.
	 *
	 * @see {@link StringKey(String)}
	 */
	private void keyChange(){
		JLabel titleControl = new JLabel(
				"                           Controls               ");
		JLabel enter = new JLabel( "                             ");
	
		PanelOpt.add(titleControl);
		PanelOpt.add(enter);
		
		
		JLabel Up = new JLabel("                   Up key: ");
		keyUp = new JTextField(String.valueOf(Upkey), 3);
		PanelOpt.add(Up);
		PanelOpt.add(keyUp);
		
		
		JLabel Down = new JLabel("Down key: ");
		keyDown = new JTextField(String.valueOf(Downkey), 3);
		PanelOpt.add(Down);
		PanelOpt.add(keyDown);
		
		JLabel Left = new JLabel("          Left key: ");
		keyLeft = new JTextField(String.valueOf(Leftkey), 3);
		PanelOpt.add(Left);
		PanelOpt.add(keyLeft);
		
		JLabel Right = new JLabel("Right key: ");
		keyRight = new JTextField(String.valueOf(Rightkey), 3);
		PanelOpt.add(Right);
		PanelOpt.add(keyRight);
		
		JLabel Eagle = new JLabel("      Eagle key: ");
		keyEagle = new JTextField(String.valueOf(Eaglekey), 3);
		PanelOpt.add(Eagle);
		PanelOpt.add(keyEagle);
		
		
		PanelOpt.add(enter);
		
	}
	
	
	public int getnewSizeM() {
		return MSize;
	}

	public int getnewNrD() {
		return DNumber;
	}

	public DragonChoice getnewDrakeB() {
		return newDChoice;
	}
	
	/**
	 * Transforma as strings recebidas da área de mudança de teclas a usar no
	 * jogo em KeyEvent's
	 */
	public static int StringKey(String str) {
		str = str.toUpperCase();

		switch (str) {
		case "A": return KeyEvent.VK_A;
		case "B": return KeyEvent.VK_B;
		case "C": return KeyEvent.VK_C;
		case "D": return KeyEvent.VK_D;
		case "E": return KeyEvent.VK_E;
		case "F": return KeyEvent.VK_F;
		case "G": return KeyEvent.VK_G;
		case "H": return KeyEvent.VK_H;
		case "I": return KeyEvent.VK_I;
		case "J": return KeyEvent.VK_J;
		case "K": return KeyEvent.VK_K;
		case "L": return KeyEvent.VK_L;
		case "M": return KeyEvent.VK_M;
		case "N": return KeyEvent.VK_N;
		case "O": return KeyEvent.VK_O;
		case "P": return KeyEvent.VK_P;
		case "Q": return KeyEvent.VK_Q;
		case "R": return KeyEvent.VK_R;
		case "S": return KeyEvent.VK_S;
		case "T": return KeyEvent.VK_T;
		case "U": return KeyEvent.VK_U;
		case "V": return KeyEvent.VK_V;
		case "W": return KeyEvent.VK_W;
		case "X": return KeyEvent.VK_X;
		case "Y": return KeyEvent.VK_Y;
		case "Z": return KeyEvent.VK_Z;
		}

		return 0;
	}

	public int[] keys(){
		int[] array = {StringKey(Upkey),StringKey(Downkey),StringKey(Leftkey),StringKey(Rightkey),StringKey(Eaglekey)};
		return array;
	}

}