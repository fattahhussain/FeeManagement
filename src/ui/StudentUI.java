package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.*;
import DS.*;
import sf.*;

public class StudentUI extends JFrame implements ActionListener
{
	Student student;
	String records[][];
	JTable table;
	JLabel bgPic;
	JButton logoutButton;
	String[] rCols = {"Username", "Amount Paid", "Pay Type", "Pay Date"};
	public StudentUI(Student student)
	{
		this.student = student;
		URL imgUrl = this.getClass().getResource("/sf.jpg");
		bgPic = new JLabel();
		bgPic.setIcon(Sys.imageIconCreator(1400, 150, imgUrl));
		setLayout(null);
		logoutButton = new JButton("Logout");
		add(setLabel(new JLabel("Name : "+student.getName()))).setBounds(100, 150, 200, 20);
		add(setLabel(new JLabel("Dues : "+student.getDue()))).setBounds(310, 150, 200, 20);
		add(setLabel(new JLabel("Status : "+student.getStatus()))).setBounds(520, 150, 200, 20);
		
		Record[] all = student.getRecords().pullAllRecords();
		records = new String[all.length][4];
		for(int i=0; i<all.length; i++)
			records[i] = all[i].doDataArray();
		table = new JTable(records, rCols)
		{
			public boolean isCellEditable(int row,int column)
			{
				return false;
			}
		};
		formatTable(table);
		add(new JScrollPane(table)).setBounds(100, 200, 1200, 600);
		add(bgPic).setBounds(0,0,1400,150);
		add(logoutButton).setBounds(1200,10, 100, 20);

		logoutButton.addActionListener(this);
		setTitle("Student - School Fianace");
		setVisible(true);
		setBounds(100,40,1400,800);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
	}
	public void formatTable(JTable t)
	{
		t.getTableHeader().setFont(new Font("Arial",Font.PLAIN,20));
		t.setFont(new Font("Arial",Font.BOLD,20));
		t.setRowHeight(30);
		t.setEnabled(false);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == logoutButton)
		{
			new SignIn();
			this.dispose();
		}
	}
	public JLabel setLabel(JLabel l)
	{
		l.setFont(new Font("Arial",Font.PLAIN,20));
		l.setForeground(Color.blue);
		return l;
	}
}
