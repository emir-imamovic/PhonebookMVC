package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApplicationView extends Main {

	public static void home() {
		JPanel content = new JPanel();

		JLabel text = new JLabel("Welcome to BitBook");
		Font textFont = new Font("SansSerif", Font.BOLD, 30);
		text.setFont(textFont);
		content.add(text);

		JButton add = new JButton("Add Contact");
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton show = new JButton("Show Contacts");
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		content.add(add);
		content.add(show);
		Main.replaceContent(content);
	}
}