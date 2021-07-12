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

public class Challan extends JFrame implements ActionListener
{
	JTextField nameField, usernameField, fatherNameField, classField, admissionFeesField, tuitionFeesField, fineField, boardRegFeesField, arrearsField, otherFeesField, totalAmountField; 
	JCheckBox admissionFeesCB, tuitionFeesCB, fineCB, boardRegFeesCB, arrearsCB, otherFeesCB; 
	double totalAmount;
	double admissionFees,tuitionFees,fine,boardRegFees,arrears,otherFees;
	JButton calculateButton, printButton;
	public Challan(Student student)
	{
		totalAmount = 0;
		admissionFees = 0;
		tuitionFees = 0;
		fine = 0;
		boardRegFees = 0;
		arrears = 0;
		otherFees = 0;
		
		
		nameField = new JTextField();
		nameField.setText(student.getName());
		
		usernameField = new JTextField();
		usernameField.setText(student.getUsername());
		
		fatherNameField = new JTextField();
		fatherNameField.setText(student.getFatherName());
		
		classField = new JTextField();
		classField.setText(student.getLevel());
		
		admissionFeesField = new JTextField();
		tuitionFeesField  = new JTextField();
		fineField = new JTextField();
		boardRegFeesField  = new JTextField();
		arrearsField = new JTextField();
		
		tuitionFeesField.setText(""+student.getFeesAmount());
		arrearsField.setText(""+(student.getDue() - student.getFeesAmount()));
		otherFeesField  = new JTextField();
		totalAmountField = new JTextField();
		
		totalAmountField.setEditable(false);
		totalAmountField.setForeground(Color.BLACK);
		
		admissionFeesCB = new JCheckBox();
		tuitionFeesCB  = new JCheckBox();
		fineCB = new JCheckBox();
		boardRegFeesCB = new JCheckBox();
		arrearsCB = new JCheckBox();
		otherFeesCB = new JCheckBox();
		
		calculateButton = new JButton("Calculate");
		printButton = new JButton("Print");
		printButton.setEnabled(false);
		
		add(new JLabel("Name :")).setBounds(20, 20, 100, 30);
		add(nameField).setBounds(130, 20, 300, 30);
		add(new JLabel("Father's Name :")).setBounds(20, 60, 100, 30);
		add(fatherNameField).setBounds(130, 60, 300, 30);
		add(new JLabel("Username :")).setBounds(20, 100, 100, 30);
		add(usernameField).setBounds(130, 100, 300, 30);
		add(new JLabel("Class : ")).setBounds(20, 140, 100, 30);
		add(classField).setBounds(130, 140, 300, 30);
		add(new JLabel("Fees")).setBounds(20, 180, 100,30);
		add(new JLabel("Admission :")).setBounds(20, 220, 100, 30);
		add(admissionFeesField).setBounds(130, 220, 100, 30);
		add(new JLabel("Tuition :")).setBounds(20,260, 100, 30);
		add(tuitionFeesField).setBounds(130, 260, 100, 30);
		add(new JLabel("Fine :")).setBounds(20, 300, 100,30);
		add(fineField).setBounds(130, 300, 100, 30);
		add(new JLabel("Board Reg. : ")).setBounds(20, 340, 100, 30);
		add(boardRegFeesField).setBounds(130, 340, 100, 30);
		add(new JLabel("Arrears :")).setBounds(20, 380, 100, 30);
		add(arrearsField).setBounds(130, 380, 100,30);
		add(new JLabel("Other :")).setBounds(20, 420, 100, 30);
		add(otherFeesField).setBounds(130, 420, 100, 30);
		add(new JLabel("Total :")).setBounds(20, 460, 100, 30);
		add(totalAmountField).setBounds(130, 460, 100, 30);
		add(calculateButton).setBounds(340, 460, 100, 30);
		add(printButton).setBounds(340, 520, 100, 30);
		
		calculateButton.addActionListener(this);
		printButton.addActionListener(this);
		setLayout(null);
		setTitle("Challan - School Fianace");
		setVisible(true);
		setBounds(200,80,500,600);
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		setResizable(false);
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == calculateButton)
		{
			if(!admissionFeesField.getText().equals(""))
			{
				totalAmount += Double.parseDouble(admissionFeesField.getText());
				admissionFees = Double.parseDouble(admissionFeesField.getText());
			}
				
			if(!tuitionFeesField.getText().equals(""))
			{
				totalAmount += Double.parseDouble(tuitionFeesField.getText());
				tuitionFees = Double.parseDouble(tuitionFeesField.getText());
			}
				
			if(!fineField.getText().equals(""))
			{
				totalAmount += Double.parseDouble(fineField.getText());
				fine = Double.parseDouble(fineField.getText());
			}
				
			if(!boardRegFeesField.getText().equals(""))
			{
				totalAmount += Double.parseDouble(boardRegFeesField.getText());
				boardRegFees += Double.parseDouble(boardRegFeesField.getText());
			}
				
			if(!arrearsField.getText().equals(""))
			{
				totalAmount += Double.parseDouble(arrearsField.getText());
				arrears += Double.parseDouble(arrearsField.getText());
			}
				
			if(!otherFeesField.getText().equals(""))
			{
				totalAmount += Double.parseDouble(otherFeesField.getText());
				otherFees += Double.parseDouble(otherFeesField.getText());
			}
				
			
			totalAmountField.setText(""+totalAmount);
			printButton.setEnabled(true);
		}
		else if(e.getSource() == printButton)
		{
			URL imgUrl = this.getClass().getClassLoader().getResource("challan.png");
			URL fileUrl = this.getClass().getClassLoader().getResource("classes");
			Sys.printChallan(nameField.getText(),fatherNameField.getText(), usernameField.getText(), classField.getText(), 
							admissionFees, tuitionFees,fine, boardRegFees, arrears, otherFees, totalAmount, imgUrl.getPath(), fileUrl.getPath());
		}
		
	}

	
}
