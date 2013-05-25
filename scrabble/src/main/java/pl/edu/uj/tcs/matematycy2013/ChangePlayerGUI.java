package pl.edu.uj.tcs.matematycy2013;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class ChangePlayerGUI extends JFrame {
	
	JPanel panel;
	JLabel message;
	JLabel name;
	JLabel PS;
	JButton ok;
	Game game;
	
	public ChangePlayerGUI (Game game) {
		this.game = game;
		format();
	}
	private void format () {
		setPreferredSize(new Dimension (400, 200));
		
		message = new JLabel("Waiting for");
		message.setPreferredSize(new Dimension(200, 50));
		message.setFont(new Font("Serif", Font.PLAIN, 30));
		message.setHorizontalAlignment(SwingConstants.CENTER);
		
		name = new JLabel();
		name.setPreferredSize(new Dimension(200, 50));
		name.setFont(new Font("Serif", Font.PLAIN, 30));
		name.setHorizontalAlignment(SwingConstants.CENTER);
		
		PS = new JLabel();
		PS.setPreferredSize(new Dimension(200, 50));
		PS.setFont(new Font("Serif", Font.PLAIN, 20));
		PS.setHorizontalAlignment(SwingConstants.CENTER);
		
		ok = new JButton("OK");
		ok.addActionListener( new ActionListener() {
            
			public void actionPerformed(ActionEvent e) {
				game.beginTurn();
				ChangePlayerGUI.this.setVisible(false);
			}
		});
		panel = new JPanel (new GridBagLayout());
		getContentPane().add(panel);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.weightx = 0.5;
		gbc.weighty = 0.5;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(message, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(name, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(PS, gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(ok, gbc);
		pack();
		setVisible(false);
		
	}
	public void showChangePlayerGUI (Player player, Player last, boolean flag) {
		
		name.setText(player.getNick());
		PS.setText("(" + last.getNick() + " has no time left)");
		PS.setVisible(flag);
		setVisible(true);
	}
	

}
