
package note;

import java.awt.Font;

import javax.swing.*;

public class About extends JFrame {

	About() {
		setBounds(100, 100, 600, 500);
		setTitle("About");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		ImageIcon icon = new ImageIcon(getClass().getResource("gp.png"));
		setIconImage(icon.getImage());
		JLabel iconLabel = new JLabel(new ImageIcon(getClass().getResource("gp.png")));
		iconLabel.setBounds(100, 50, 100, 100);
		add(iconLabel);

		JLabel textLabel = new JLabel(
				"<html>This command sends multiple echo Request messages <br>to each router between a source and destination,<br> over a period of time, and then computes results based on <br>the packets returned from each router.</html>");
		textLabel.setBounds(100, 50, 350, 300);
		textLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
		add(textLabel);
		setLayout(null);

	}

	public static void main(String[] args) {
		new About().setVisible(true);

	}

}
