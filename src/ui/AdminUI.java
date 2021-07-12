package ui;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;
import sf.*;

public class AdminUI extends AdministrationUI implements ActionListener
{
	Admin admin;
	JButton addStudentButton, removeStudentButton, addButton, removeButton, editClassButton;
	JTextField studentNameField, fatherNameField, passwordField, feesField;
	JLabel nameLabel, passwordLabel;
	JComboBox classes2;
	
	
	public AdminUI(User user)
	{
		super(user, "Admin");
		admin = new Admin(user.getName(), user.getUsername(), user.getPassword());
		addStudentButton = new JButton("Add Student");
		removeStudentButton = new JButton("Remove Student");
		editClassButton = new JButton("Edit Classes");
		removeButton = new JButton("Remove");
		addButton = new JButton("Add");
		feesField = new JTextField();
		
		viewThisRecordButton = new JButton("View This");
		viewAllRecordsButton = new JButton("View All");
		
		dataTable = new JTable(admin.getAllStudents(), colNames)
		{
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
		};
		dataPanel.add(new JScrollPane(dataTable)).setBounds(0, 0, 1200, 600);
		formatTable(dataTable);
		
		add(addStudentButton).setBounds(670, 100, 150, 40);
		add(removeStudentButton).setBounds(840, 100, 140, 40);
		add(editClassButton).setBounds(1170, 100, 100, 40);
		viewByClassButton.addActionListener(this);
		viewAll.addActionListener(this);
		viewRedListButton.addActionListener(this);
		viewWhiteListButton.addActionListener(this);
		viewFeesRecordButton.addActionListener(this);
		addStudentButton.addActionListener(this);
		removeStudentButton.addActionListener(this);;
		logoutButton.addActionListener(this);
		editClassButton.addActionListener(this);
		removeButton.addActionListener(this);
		addButton.addActionListener(this);
		viewThisRecordButton.addActionListener(this);
		viewAllRecordsButton.addActionListener(this);
		
		setLayout(null);
		setTitle("Admin - School Fianace");
		setVisible(true);
		setBounds(10,10,1400,800);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
		add(bgPic).setBounds(0,0,1400,150);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == viewAll)
		{
			setViewMode();
			dataTable = new JTable(admin.getAllStudents(), colNames)
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
			dataTable = new JTable(admin.getRedList(), colNames)
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
			dataTable = new JTable(admin.getWhiteList(), colNames)
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
			dataTable = new JTable(admin.getClassList(selectedClass), colNames)
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
			String username = usernameField.getText().trim();
			viewFeesRecordButton.doClick();
			usernameField.setText(username);
			String data[][] = new String[1][8];
			data[0] = admin.findStudentFromList(username);
			if(data[0] != null)
			{
				dataTable = new JTable(admin.loadStudentFeesRecord(username), rCols)
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
			
		}
		else if(e.getSource() == viewAllRecordsButton)
		{
			viewFeesRecordButton.doClick();
			dataTable = new JTable(admin.loadAllRecords(), rCols)
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
			
			
			admin.loadFeesRecords();
			dataPanel.repaint();
		}
		else if(e.getSource() == addStudentButton)
		{
			setViewMode();
			
			studentNameField = new JTextField("");
			fatherNameField = new JTextField("");
			passwordField = new JTextField("");
			passwordLabel = new JLabel("Password :");
			nameLabel = new JLabel("Name :");
			classes2 = new JComboBox();
			try {
				URL fileUrl = getClass().getClassLoader().getResource("classes");
			      File myObj = new File(fileUrl.getPath());
			      Scanner myReader = new Scanner(myObj);
			      myReader.nextLine();
			      while (myReader.hasNextLine()) {
			        String data = myReader.nextLine();
			        classes2.addItem(data.split(":")[0]);
			      }
			      myReader.close();
			    } catch (FileNotFoundException exc) {
			    	JOptionPane.showMessageDialog(null, "Classes File is Missing");
			}
			
			usernameField.setText("");
			dataPanel.add(nameLabel).setBounds(200, 60, 100, 40);
			dataPanel.add(studentNameField).setBounds(400, 60, 400, 40);
			dataPanel.add(new JLabel("Father's Name :")).setBounds(200, 130, 100, 40);
			dataPanel.add(fatherNameField).setBounds(400, 130, 400, 40);
			dataPanel.add(usernameLabel).setBounds(200, 200, 100, 40);
			dataPanel.add(usernameField).setBounds(400, 200, 400, 40);
			dataPanel.add(passwordLabel).setBounds(200, 270, 100, 40);
			dataPanel.add(passwordField).setBounds(400, 270, 400, 40);
			dataPanel.add(new JLabel("Class :")).setBounds(200, 340, 100, 40);
			dataPanel.add(classes2).setBounds(400, 340, 50, 40);
			dataPanel.add(new JLabel("Fees : ")).setBounds(200,410,100,40);
			dataPanel.add(feesField).setBounds(400,410,400,40);
			dataPanel.add(addButton).setBounds(400, 460, 400, 40);
			
			
			dataPanel.repaint();
		}
		else if(e.getSource() == removeStudentButton)
		{
			setViewMode();
			
			usernameField.setText("");
			
			dataPanel.add(usernameLabel).setBounds(200, 140, 100, 40);
			dataPanel.add(usernameField).setBounds(400, 140, 400, 40);
			dataPanel.add(removeButton).setBounds(400, 410, 400, 40);
			
			
			dataPanel.repaint();
		}
		else if(e.getSource() == addButton)
		{
			String name = studentNameField.getText().trim();
			String username = usernameField.getText().trim();
			String password = passwordField.getText().trim();
			String fatherName = fatherNameField.getText().trim();
			String level = classes2.getSelectedItem().toString();
			double feesAmount = Double.parseDouble(feesField.getText());
			if(!(name.equals("") || username.equals("") || password.equals("")))
			{
				if(admin.addStudent(name, fatherName, feesAmount, username, password, level))
					JOptionPane.showMessageDialog(null, "Student: "+ name +" Added Successfully!!");
				else
					JOptionPane.showMessageDialog(null, "Error!! Couldn't add the student");
			}
			else
				JOptionPane.showMessageDialog(null, "Complete Fields!!");
			studentNameField.setText("");
			usernameField.setText("");
			passwordField.setText("");
			fatherNameField.setText("");
			feesField.setText("");
		}
		else if(e.getSource() == removeButton)
		{
			String username = usernameField.getText().trim();
			String name = admin.removeStudent(username);
			if(name != null)
			{
				JOptionPane.showMessageDialog(null, "Removed Student : "+ name);
			}
			else
				JOptionPane.showMessageDialog(null, "No Such Student!!!");
		}
		else if(e.getSource() == editClassButton)
		{
			new EditClassBox();
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

}
