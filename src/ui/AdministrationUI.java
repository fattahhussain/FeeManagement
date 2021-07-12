package ui;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;

import sf.*;

public class AdministrationUI extends JFrame
{
	JPanel dataPanel;
	JButton viewAll, viewByClassButton,viewRedListButton, viewWhiteListButton, viewFeesRecordButton,
			viewAllRecordsButton, viewThisRecordButton, logoutButton;
	JTextField usernameField, amountField;
	JLabel usernameLabel, amountLabel, bgPic, totalAmount, thisMonthAmount;
	JTable dataTable, studentObj;
	JComboBox classes;
	String[] colNames = {"Name", "Father Name", "Fees","Username", "Password", "Class", "Due", "Status"};
	String[] rCols = {"Username", "Amount Paid", "Pay Type", "Pay Date"};
	
	
	public AdministrationUI(User user, String rank)
	{
		bgPic = new JLabel();
		URL imgUrl = getClass().getClassLoader().getResource("sf.jpg");
		bgPic.setIcon(Sys.imageIconCreator(1400, 150, imgUrl));
		classes = new JComboBox();
		try {
		      File myObj = new File(getClass().getClassLoader().getResource("classes").getPath());
		      Scanner myReader = new Scanner(myObj);
		      myReader.nextLine();
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        classes.addItem(data.split(":")[0]);
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		    	JOptionPane.showMessageDialog(null, "Classes File is Missing");
		}
		dataPanel = new JPanel(null);
		
		usernameField = new JTextField("");
		amountField = new JTextField("");
		usernameLabel = new JLabel("Username");
		amountLabel = new JLabel("Amount");
		viewByClassButton = new JButton("View By Class");
		viewAll = new JButton("View All");
		viewRedListButton = new JButton("View RedList");
		viewWhiteListButton = new JButton("View WhiteList");
		viewFeesRecordButton = new JButton("View Fees Record");
		logoutButton = new JButton("Logout");
		double balances[] = Sys.loadSchoolAccountBalance("jdbc:ucanaccess://"+this.getClass().getClassLoader().getResource("SF.accdb").getPath());
		totalAmount = new JLabel("Total Balance : " + balances[0]);
		thisMonthAmount = new JLabel("This Month Balance : " + balances[1]);
		
		dataPanel.setBackground(Color.LIGHT_GRAY);
		add(viewAll).setBounds(100, 100, 80, 40);
		add(viewRedListButton).setBounds(200, 100, 120, 40);
		add(viewWhiteListButton).setBounds(340, 100, 130, 40);
		add(classes).setBounds(480, 100, 55, 40);
		add(viewByClassButton).setBounds(540, 100, 120, 40);
		add(viewFeesRecordButton).setBounds(1000, 100, 150, 40);
		add(logoutButton).setBounds(1200,10, 100, 20);
		
		add(dataPanel).setBounds(100, 160, 1200, 600);
		add(totalAmount).setBounds(1100, 40, 200, 20);
		add(thisMonthAmount).setBounds(1100, 70, 300, 20);
	}
	
	public void formatTable(JTable t)
	{
		t.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
		t.setFont(new Font("Arial",Font.BOLD,15));

		t.setRowHeight(30);
		t.setEnabled(false);
	}
}
