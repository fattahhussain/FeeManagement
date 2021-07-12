package ui;
import java.awt.Color;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import DS.Tree;
import sf.*;

public class SignIn extends JFrame implements ActionListener
{

	JTextField usernameField;
	JPasswordField passwordField;
	JButton signInButton;
	JLabel usernameLabel, passwordLabel, bgPic;
	Tree users;
	
	public SignIn()
	{
		setLayout(null);
		bgPic = new JLabel();
		URL imgUrl = getClass().getClassLoader().getResource("sf.jpg");
		
		bgPic.setIcon(Sys.imageIconCreator(1400, 150, imgUrl));
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		signInButton = new JButton("Sign In");
		usernameLabel = new JLabel("Username");
		passwordLabel = new JLabel("Password");
		
		add(usernameLabel).setBounds(400,230,100,25);
		add(usernameField).setBounds(400,255,500,40);
		add(passwordLabel).setBounds(400,305,100,25);
		add(passwordField).setBounds(400,330,500,40);
		add(signInButton).setBounds(400,420,500,40);
		
		signInButton.addActionListener(this);
		
		add(bgPic).setBounds(0,0,1400,150);
		setTitle("Signin - School Fianace");
		setVisible(true);
		setBounds(10,10,1400,800);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
		users = Sys.loadUsers("jdbc:ucanaccess://"+this.getClass().getClassLoader().getResource("SF.accdb").getPath());
	}
	public void actionPerformed(ActionEvent event)
	{
		String username, password, rank = null;
		if(event.getSource() == signInButton)
		{ 
			username = usernameField.getText();
			password = String.copyValueOf(passwordField.getPassword());
			User foundUser = users.findUser(username);
			if(foundUser != null && foundUser.getPassword().equals(password))
				rank = foundUser.getRank();
			if(rank != null)
			{
				if(rank.equals("admin"))
				{
					new AdminUI(foundUser);
					this.dispose();
				}
				else if(rank.equals("accountant"))
				{
					new AccountantUI(foundUser);
					this.dispose();
				}
				else
				{
					new StudentUI(Sys.getStudentObject(username,"jdbc:ucanaccess://"+this.getClass().getClassLoader().getResource("SF.accdb").getPath()));
					this.dispose();
				}
			}
			else
			{
				passwordField.setText("");
				usernameField.setText("");
				add(new JLabel("Username or Password is invalid! Please try again.")).setBounds(600,440,400,20);
			}
		}
	}
	
}
