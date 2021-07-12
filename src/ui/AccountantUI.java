package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;

import javax.swing.*;

import sf.*;

public class AccountantUI extends AdministrationUI implements ActionListener
{
	Accountant accountant;
	JButton printChallan, allClassChallan, collectButton, implyDueButton, implyButton, implyMonthlyButton, implyFine, implyFineButton, collectMonthlyButton, collectExtraButton;
	JLabel payTypeLabel;
	JComboBox payTypesBox;
	String payType = null;
	
	public AccountantUI(User user)
	{
		super(user, "Accountant");
		accountant = new Accountant(user.getName(), user.getUsername(), user.getPassword());
		setLayout(null);
		payTypesBox = new JComboBox();
		payTypeLabel = new JLabel("Fees Type");
		printChallan = new JButton("Custom Challan");
		allClassChallan = new JButton("Class Challan");
		
		implyFine = new JButton("Imply Extra Fees");
		implyFineButton = new JButton("Imply");
		implyButton = new JButton("Imply");
		
		payTypesBox.addItem("Examination");payTypesBox.addItem("Transportation");payTypesBox.addItem("Hostel");payTypesBox.addItem("Tour");
		dataTable = new JTable(accountant.getAllStudents(), colNames)
		{
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
		};
		formatTable(dataTable);
		dataPanel.add(new JScrollPane(dataTable)).setBounds(0, 0, 1200, 600);
		collectMonthlyButton = new JButton("Collect Monthly Fees");
		collectExtraButton = new JButton("Collect Extra Fees");
		implyDueButton = new JButton("Imply Dues");
		implyMonthlyButton = new JButton("Imply Monthly");
		collectButton = new JButton("Collect");

		viewThisRecordButton = new JButton("View This");
		viewAllRecordsButton = new JButton("View All");
		
		add(collectMonthlyButton).setBounds(670, 100, 150, 40);
		add(collectExtraButton).setBounds(840, 100, 140, 40);
		add(implyDueButton).setBounds(1170, 100, 130, 40);
		add(printChallan).setBounds(1000, 10, 140, 20);
		add(allClassChallan).setBounds(850,10,140,20);
		
		printChallan.addActionListener(this);
		allClassChallan.addActionListener(this);
		collectMonthlyButton.addActionListener(this);
		collectExtraButton.addActionListener(this);
		implyDueButton.addActionListener(this);
		implyMonthlyButton.addActionListener(this);
		viewByClassButton.addActionListener(this);
		viewAll.addActionListener(this);
		viewRedListButton.addActionListener(this);
		viewWhiteListButton.addActionListener(this);
		viewFeesRecordButton.addActionListener(this);
		logoutButton.addActionListener(this);
		implyButton.addActionListener(this);
		implyFine.addActionListener(this);
		implyFineButton.addActionListener(this);
		collectButton.addActionListener(this);
		viewThisRecordButton.addActionListener(this);
		viewAllRecordsButton.addActionListener(this);
		
		setTitle("Accountant - School Fianace");
		setVisible(true);
		setBounds(10,10,1400,800);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
		add(bgPic).setBounds(0,0,1400,150);
	}

	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == collectMonthlyButton)
		{
			setCollectionMode();
			dataPanel.remove(payTypeLabel);
			dataPanel.remove(payTypesBox);
			payType = "monthly";
		}
		else if(e.getSource() == collectExtraButton)
		{
			setCollectionMode();
			dataPanel.add(payTypeLabel).setBounds(200,150,170,40);
			dataPanel.add(payTypesBox).setBounds(400,150,400,40);
			dataPanel.repaint();
			payType = "extra";
		}
		else if(e.getSource() == collectButton)
		{
			if(!usernameField.getText().equals("") && !amountField.getText().equals(""))
			{
				String currentStudentUsername = usernameField.getText();
				double currentPaymentAmount = Double.parseDouble(amountField.getText().trim());
				
				if(payType.equals("monthly"))
				{
					
					if(JOptionPane.showConfirmDialog(null, "Collect Fees of : "+ accountant.findStudentObjFromList(currentStudentUsername).getName()) == JOptionPane.YES_OPTION)
					{
						accountant.collectMonthly(currentStudentUsername, currentPaymentAmount);
						JOptionPane.showMessageDialog(null, "Collected Successfully!!");
						collectMonthlyButton.doClick();
					}	
					else
						JOptionPane.showMessageDialog(null, "Operation Cancelled!!");
					
				}
				else
				{
					accountant.collectExtra(currentStudentUsername, currentPaymentAmount, payTypesBox.getSelectedItem().toString());
					collectExtraButton.doClick();
				}
				double balances[] = Sys.loadSchoolAccountBalance("jdbc:ucanaccess://"+this.getClass().getClassLoader().getResource("SF.accdb").getPath());
				totalAmount.setText("Total Balance : " + balances[0]);
				thisMonthAmount.setText("This Month Balance : " + balances[1]);
			}
		}
		
		else if(e.getSource() == implyDueButton)
		{
			setViewMode();
			amountField.setText("");
		
			dataPanel.add(implyFine).setBounds(400, 90, 400, 40);
			dataPanel.add(implyMonthlyButton).setBounds(400,150, 400, 40);
			dataPanel.add(new JLabel("OR EXTRA")).setBounds(570, 210, 100, 20);
			dataPanel.add(payTypeLabel).setBounds(200,250,170,40);
			dataPanel.add(payTypesBox).setBounds(400,250,400,40);
			dataPanel.add(amountLabel).setBounds(200,350,170,40);
			dataPanel.add(amountField).setBounds(400,350,400,40);
			dataPanel.add(implyButton).setBounds(400,450,400,40);
			dataPanel.repaint();
			
			
		}
		else if(e.getSource() == implyFine)
		{
			
			dataPanel.removeAll();
			usernameField.setText("");
			amountField.setText("");
			dataPanel.add(usernameLabel).setBounds(200,250,170,40);
			dataPanel.add(usernameField).setBounds(400,250,400,40);
			dataPanel.add(amountLabel).setBounds(200,350,170,40);
			dataPanel.add(amountField).setBounds(400,350,400,40);
			dataPanel.add(implyFineButton).setBounds(400,450,400,40);
			dataPanel.repaint();
			
		}
		else if(e.getSource() == implyFineButton)
		{
			if(!usernameField.getText().equals("") && !amountField.getText().trim().equals(""))
			{
				String currentStudentUsername = usernameField.getText();
				try
				{
					double currentFineAmount = Double.parseDouble(amountField.getText().trim());
					String name = accountant.implyFine(currentStudentUsername, currentFineAmount);
					if(name != null)
						JOptionPane.showMessageDialog(null, "Student : "+ name+" - Fees Implied Successfully!!");
					else

						JOptionPane.showMessageDialog(null, "Error! Couldn't imply Fees!!");
				}
				catch(Exception exp)
				{
					JOptionPane.showMessageDialog(null, "Please Input Amount correctly" + exp.getMessage());
				}
				
			}
			
			
		}
		else if(e.getSource() == implyMonthlyButton)
		{
			URL fileUrl = getClass().getClassLoader().getResource("classes");
			accountant.implyMonthlyDues(fileUrl.getPath());
		}
		else if(e.getSource() == implyButton)
		{
			double amountToImply = Double.parseDouble(amountField.getText());
			accountant.implyExtraDues(amountToImply);
			amountField.setText("");
		}
		else if(e.getSource() == viewAll)
		{
			setViewMode();
			dataTable = new JTable(accountant.getAllStudents(), colNames)
			{
				public boolean isCellEditable(int row,int column)
				{
					return false;
				}
			};
			formatTable(dataTable);
			dataPanel.add(new JScrollPane(dataTable)).setBounds(0, 0, 1200, 600);
		}
		else if(e.getSource() == viewRedListButton)
		{
			setViewMode();
			dataTable = new JTable(accountant.getRedList(), colNames)
			{
				public boolean isCellEditable(int row,int column)
				{
					return false;
				}
			};
			formatTable(dataTable);
			dataPanel.add(new JScrollPane(dataTable)).setBounds(0, 0, 1200, 600);
			dataPanel.repaint();
		}
		else if(e.getSource() == viewWhiteListButton)
		{
			setViewMode();
			dataTable = new JTable(accountant.getWhiteList(), colNames)
			{
				public boolean isCellEditable(int row,int column)
				{
					return false;
				}
			};
			formatTable(dataTable);
			dataPanel.add(new JScrollPane(dataTable)).setBounds(0, 0, 1200, 600);
			dataPanel.repaint();
		}
		else if(e.getSource() == viewByClassButton)
		{
			setViewMode();
			String selectedClass = classes.getSelectedItem().toString();
			dataTable = new JTable(accountant.getClassList(selectedClass), colNames)
			{
				public boolean isCellEditable(int row,int column)
				{
					return false;
				}
			};
			formatTable(dataTable);
			dataPanel.add(new JScrollPane(dataTable)).setBounds(0, 0, 1200, 600);
		}
		else if(e.getSource() == viewThisRecordButton)
		{
			String username = usernameField.getText();
			viewFeesRecordButton.doClick();
			usernameField.setText(username);
			String data[][] = new String[1][7];
			data[0] = accountant.findStudentFromList(username);
			if(data[0] != null)
			{
				dataTable = new JTable(accountant.loadStudentFeesRecord(username), rCols)
				{
					public boolean isCellEditable(int row,int column)
					{
						return false;
					}
				};
				formatTable(dataTable);
				studentObj = new JTable(data, colNames)
				{
					public boolean isCellEditable(int row,int column)
					{
						return false;
					}
				};
				formatTable(studentObj);
				dataPanel.add(new JScrollPane(studentObj)).setBounds(40, 65, 1120, 90);
				dataPanel.add(new JScrollPane(dataTable)).setBounds(40, 150, 1120, 447);
				
				
				dataPanel.repaint();
			}
			else
				JOptionPane.showMessageDialog(null, "Not Found!!!");
			
			
		}
		else if(e.getSource() == printChallan)
		{
			String username = JOptionPane.showInputDialog("Input Username : ");
			if(!username.equals(""))
			{
				Student student = accountant.findStudentObjFromList(username);
				if(student != null)
				{
					new Challan(student);
				}
				else
					JOptionPane.showMessageDialog(null, "Student Not Found!");
			}
			else
				JOptionPane.showMessageDialog(null, "Empty Username!");
		}
		else if(e.getSource() == allClassChallan)
		{
			String cls = JOptionPane.showInputDialog("Input Class : ");
			if(!cls.equals(""))
			{
				Student[] students= accountant.getAllStudentObjs();
				if(students.length > 0)
				{
					for(int i=0; i<students.length; i++)
					{
						if(students[i].getLevel().equals(cls))
							new Challan(students[i]);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "No Students Found in given class!");
				
			}
			else
				JOptionPane.showMessageDialog(null, "Please input Class!");
		}
		else if(e.getSource() == viewAllRecordsButton)
		{
			viewFeesRecordButton.doClick();
			dataTable = new JTable(accountant.loadAllRecords(), rCols)
			{
				public boolean isCellEditable(int row,int column)
				{
					return false;
				}
			};
			formatTable(dataTable);
			dataPanel.add(new JScrollPane(dataTable)).setBounds(40, 150, 1120, 447);
			dataPanel.repaint();
		}
		else if(e.getSource() == viewFeesRecordButton)
		{
			setViewMode();
			
			
			usernameField.setText("");
			dataPanel.add(usernameLabel).setBounds(50, 20, 100, 40);
			dataPanel.add(usernameField).setBounds(160, 20, 500, 40);
			dataPanel.add(viewThisRecordButton).setBounds(710,20, 150, 40);
			dataPanel.add(viewAllRecordsButton).setBounds(900,20, 150, 40);
			
			
			accountant.loadFeesRecords();
			dataPanel.repaint();
		}
		else if(e.getSource() == logoutButton)
		{
			new SignIn();
			this.dispose();
		}
		
	}
	
	void setViewMode()
	{
		add(dataPanel).setBounds(100, 160, 1200, 600);
		dataPanel.removeAll();
		repaint();
	}
	
	void setCollectionMode()
	{
		dataPanel.removeAll();
		usernameField.setText("");
		amountField.setText("");
		dataPanel.add(usernameLabel).setBounds(200,250,170,40);
		dataPanel.add(usernameField).setBounds(400,250,400,40);
		dataPanel.add(amountLabel).setBounds(200,350,170,40);
		dataPanel.add(amountField).setBounds(400,350,400,40);
		dataPanel.add(collectButton).setBounds(400,450,400,40);
		
		
		dataPanel.repaint();
		repaint();
	}


	
	
}


