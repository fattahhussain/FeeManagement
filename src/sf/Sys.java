package sf;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import DS.*;

public class Sys
{
	public static Tree loadUsers(String conURL)
	{
		Tree tree = new Tree();
		Connection con = null;
		try
		{
			con = DriverManager.getConnection(conURL);
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("Select nam, username, password, rank From users");
			while(rs.next())
			{
				tree.insert(new User(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4)));
			}
			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Loading Database!"+e.getMessage());
		}
		
		return tree;
	}
	public static void printChallan(String name, String fatherName, String username, String cls, 
									double admissionFees, double tuitionFees, double fine, double boardRegFees,
									double arrears, double otherFees, double totalAmount, String imgUrl, String fileUrl)
	{
		
		BufferedImage img = null;
		try
		{
			
			img = ImageIO.read(new File(imgUrl));
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Challan Image was deleted!");
		}
		

		// Obtain the Graphics2D context associated with the BufferedImage.
		Graphics2D g2 = img.createGraphics();

		
		Font font = new Font("Cardana", Font.BOLD, 12);
		g2.setFont(font);
		g2.setPaint(Color.BLACK);
		
		g2.drawString(name, 20, 138);
		g2.drawString(name, 225, 138);
		g2.drawString(name, 435, 138);
		
		g2.drawString(fatherName, 20, 188);
		g2.drawString(fatherName, 225, 188);
		g2.drawString(fatherName, 435, 188);
		
		g2.drawString(username, 20, 238);
		g2.drawString(username, 225, 238);
		g2.drawString(username, 435, 238);
		
		g2.drawString(cls, 20, 288);
		g2.drawString(cls, 225, 288);
		g2.drawString(cls, 435, 288);
		
		if(admissionFees != 0)
		{
			g2.drawString(""+admissionFees, 85, 341);
			g2.drawString(""+admissionFees, 295, 341);
			g2.drawString(""+admissionFees, 500, 341);
		}
		if(tuitionFees != 0)
		{
			g2.drawString(""+tuitionFees, 85, 363);
			g2.drawString(""+tuitionFees, 295, 363);
			g2.drawString(""+tuitionFees, 500, 363);
		}
		if(fine != 0)
		{
			g2.drawString(""+fine, 85, 385);
			g2.drawString(""+fine, 295, 385);
			g2.drawString(""+fine, 500, 385);
		}
		if(boardRegFees != 0)
		{
			g2.drawString(""+boardRegFees, 85, 407);
			g2.drawString(""+boardRegFees, 295, 407);
			g2.drawString(""+boardRegFees, 500, 407);
		}
		if(arrears != 0)
		{
			g2.drawString(""+arrears, 85, 429);
			g2.drawString(""+arrears, 295, 429);
			g2.drawString(""+arrears, 500, 429);
		}
		if(otherFees != 0)
		{
			g2.drawString(""+otherFees, 85, 451);
			g2.drawString(""+otherFees, 295, 451);
			g2.drawString(""+otherFees, 500, 451);
		}
		g2.drawString(""+totalAmount, 85, 473);
		g2.drawString(""+totalAmount, 295, 473);
		g2.drawString(""+totalAmount, 500, 473);
		
		String date = ""+Calendar.getInstance().get(Calendar.DATE)+"/"+(Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR);
		font = new Font("Cardana", Font.BOLD, 9);
		g2.setFont(font);
		
		g2.drawString(date, 79, 80);
		g2.drawString(date, 289, 80);
		g2.drawString(date, 499, 80);
		
		String month = "";
		if(Calendar.getInstance().get(Calendar.DATE) > 10)
			month = ""+(Calendar.getInstance().get(Calendar.MONTH)+2);
		else
			month = ""+(Calendar.getInstance().get(Calendar.MONTH)+1);
		String lastDate = "10/"+month+"/"+Calendar.getInstance().get(Calendar.YEAR);
		g2.drawString(lastDate, 79, 97);
		g2.drawString(lastDate, 289, 97);
		g2.drawString(lastDate, 499, 97);
		try
		{
			ImageIO.write(img, "png", new File("E://tempImage.png"));
			new Thread(new PrintActionListener(img)).start();
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error"+e.getMessage());
		}
		g2.dispose();
	}
	public static Tree loadStudents(String conUrl)
	{
		Tree tree = new Tree();
		Connection con = null;
		
		try
		{
			con = DriverManager.getConnection(conUrl);
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("Select * from Students");
			while(rs.next())
				tree.insert(new Student(rs.getString(1), rs.getString(7), rs.getDouble(8), rs.getString(2), rs.getString(3), rs.getString(4),rs.getDouble(5), rs.getString(6)));
			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Loading Database!");
		}
		
		return tree;
	}
	
	public static Student getStudentObject(String username, String conUrl)
	{
		Student student = null;
		Connection con = null;
		try
		{
			con = DriverManager.getConnection(conUrl);
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("Select * From students where username = '"+username+"'");
			rs.next();
			LinkedList list = new LinkedList();
			student = new Student(rs.getString(1), rs.getString(7),  rs.getDouble(8), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6));
			rs = s.executeQuery("Select * From FeesRecord where username = '"+username+"'");
			while(rs.next())
				list.insertFirst(new Record(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getString(4)));
			student.setRecords(list);
			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Loading Database!");
		}
		
		return student;
	}
	
	public static boolean update(String query, String conUrl)
	{
		
		Connection con = null;
		int i = 0;
		try
		{
			con = DriverManager.getConnection(conUrl);
			PreparedStatement s = con.prepareStatement(query);
			i = s.executeUpdate();
			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Loading Database!!"+e.getMessage());
		}
		if(i>0)
			return true;
		else
			return false;
	}
	
	public static LinkedList loadFeesRecords(String conUrl)
	{
		LinkedList list = new LinkedList();
		Connection con = null;
		try
		{
			con = DriverManager.getConnection(conUrl);
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("Select * from FeesRecord");
			while(rs.next())
			{
				list.insertFirst(new Record(rs.getString(1), rs.getDouble(2), rs.getString(3), rs.getString(4)));
			}
			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return list;
	}
	
	public static double[] loadSchoolAccountBalance(String conUrl)
	{
		Calendar c = Calendar.getInstance();
		double amounts[] = new double[2];
		Connection con = null;
		try
		{
			con = DriverManager.getConnection(conUrl);
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("Select * from FeesRecord");
			while(rs.next())
			{
				amounts[0] += rs.getDouble(2);
				if((Integer.parseInt(rs.getString(4).split("/")[1])== (c.get(Calendar.MONTH))+1) && (Integer.parseInt(rs.getString(4).split("/")[2])== c.get(Calendar.YEAR)))
				{
					amounts[1]+= rs.getDouble(2);
				}
			}
			con.close();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		return amounts;
	}
	
	
	public static ImageIcon imageIconCreator(int width, int height, URL imgUrl)
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(imgUrl);
		}
		catch(IOException e)
		{
			JOptionPane.showMessageDialog(null, "Some files are missing!"+imgUrl);
		}
		
		Image nimg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		
		ImageIcon newImageIcon = new ImageIcon(nimg);
		
		return newImageIcon;
	}
}
