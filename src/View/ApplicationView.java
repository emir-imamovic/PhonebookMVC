package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controller.ApplicationController;
import Model.Contact;

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
				ApplicationController.addContact();

			}
		});

		JButton show = new JButton("Show Contacts");
		show.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.list();
			}
		});
		content.add(add);
		content.add(show);
		Main.replaceContent(content);
	}

	public static void addContact() {

		JPanel content = new JPanel();

		JLabel name = new JLabel("Name");
		name.setPreferredSize(new Dimension(300, 10));

		JLabel surname = new JLabel("Surname");
		surname.setPreferredSize(new Dimension(300, 10));

		JLabel number = new JLabel("Number");
		number.setPreferredSize(new Dimension(300, 10));

		final JTextField nameField = new JTextField();
		nameField.setPreferredSize(new Dimension(350, 100));

		final JTextField surnameField = new JTextField();
		surnameField.setPreferredSize(new Dimension(350, 100));

		final JTextField numberField = new JTextField();
		numberField.setPreferredSize(new Dimension(350, 100));

		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/* back to home view */
				ApplicationController.home();

			}
		});

		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * get the data from the input and send it to create method
				 */
				String cName = nameField.getText();
				String cSurname = surnameField.getText();
				String cNumber = numberField.getText();

				ApplicationController.create(cName, cSurname, cNumber);

			}
		});

		content.add(name);
		content.add(nameField);
		content.add(surname);
		content.add(surnameField);
		content.add(number);
		content.add(numberField);
		content.add(save);
		content.add(cancel);

		Main.replaceContent(content);
	}

	public static void list(Contact[] all) {
		JPanel panel = new JPanel();
		int buttonHeight = 50;
		panel.setPreferredSize(new Dimension(ApplicationView.windowWidth - 70,
				all.length * (buttonHeight + 20)));
		if (all.length < 1) {
			JLabel message = new JLabel("You have no friends");
			Font messageFont = new Font("SansSerif", Font.BOLD, 30);
			message.setFont(messageFont);
			panel.add(message);
		}
		// TODO kasnije ubaciti add

		/*
		 * creates a button for each contact in the list sets the label and name
		 * for the button connects an action listener and adds the button to the
		 * content panel
		 */
		for (int i = 0; i < all.length; i++) {
			JButton current = new JButton(all[i].getDisplayName());
			current.setName(Integer.toString(all[i].getId()));
			current.setPreferredSize(new Dimension(
					ApplicationView.windowWidth - 75, buttonHeight));
			current.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO redirect to contact view
					JButton clicked = (JButton) (e.getSource());
					int id = Integer.parseInt(clicked.getName());
					System.out.println("Korisnik: " + id);
				}
			});
			panel.add(current);
		}
		JScrollPane scrollPane = new JScrollPane(panel);

		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		JButton addContact = new JButton("Add Contact");
		addContact.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.addContact();
			}
		});
		panel.add(addContact);
		JButton back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ApplicationController.home();
			}
		});
		panel.add(back);
		replaceContent(scrollPane);
	}

}
