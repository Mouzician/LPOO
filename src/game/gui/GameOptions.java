package game.gui;

import game.logic.Game;
import game.logic.Drake.DragonChoice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;

import java.awt.GridLayout;

public class GameOptions extends JDialog {
	GameFrame Game;
	GamePanel GPanel;
	JSlider MazeSize;
	JSlider Drakenr;
	JButton Apply;
	JButton Cancel;
	private int MSize;
	private int DNumber;
	JPanel PanelOpt;
	JRadioButton[] Drakeb;
	private DragonChoice newDChoice;

	public GameOptions(GameFrame g, GamePanel p) {
		PanelOpt = new JPanel();
		this.Game = g;
		this.GPanel = p;

		checkSlider();
		checkBehav();
		ButtonsActions();
		setSize(300, 500);
		addButtons();

	}

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

				setVisible(false);
			}

		});

		Cancel = new JButton("Cancel");
		Cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String msg = "Are you sure you want to Cancel?";
				int res = JOptionPane.showConfirmDialog(rootPane, msg);

				if (res == JOptionPane.YES_OPTION)
					setVisible(false);
			}
		});
	}

	private void addButtons() {
		PanelOpt.add(Apply);
		Apply.setBackground(Color.BLACK);
		Apply.setForeground(Color.GRAY);

		PanelOpt.add(Cancel);
		Cancel.setBackground(Color.BLACK);
		Cancel.setForeground(Color.GRAY);
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

}