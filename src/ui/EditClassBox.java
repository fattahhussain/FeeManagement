package ui;

import java.awt.Color;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;
import DS.Tree;
import sf.*;

public class EditClassBox extends JFrame implements ActionListener
{

	JButton saveButton;
	JTextArea classesText;
	public EditClassBox()
	{
		
		classesText = new JTextArea();
		try {
			URL fileUrl = getClass().getClassLoader().getResource("classes");
		      File myObj = new File(fileUrl.getPath());
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		        String data = myReader.nextLine();
		        classesText.append(data+"\n");
		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		    	JOptionPane.showMessageDialog(null, "Classes File is Missing");
		}
		
		add(classesText).setBounds(10,10,380,350);
		
		saveButton = new JButton("Save");
		add(saveButton).setBounds(10,360, 100, 35);
		saveButton.addActionListener(this);
		
		setLayout(null);
		setTitle("Class Info - School Fianace");
		setVisible(true);
		setBounds(200,80,400,500);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == saveButton)
		{
			try {
			      FileWriter myWriter = new FileWriter(getClass().getClassLoader().getResource("classes").getPath());
			      myWriter.write(classesText.getText());
			      myWriter.close();
			      JOptionPane.showMessageDialog(null, "Classes Updated");
			    } catch (IOException ee) {
			    	JOptionPane.showMessageDialog(null, "Classes File is Missing");

			    }
		}
		
	}

	
}
