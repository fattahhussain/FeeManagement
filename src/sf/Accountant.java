package sf;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Accountant extends Administration
{
	String conUrl;

	public Accountant(String name, String username, String password)
	{
		super(name, username, password, "accountant");
		conUrl = "jdbc:ucanaccess://"+this.getClass().getClassLoader().getResource("SF.accdb").getPath();
	}
	
	public boolean collectMonthly(String username, double amount)
	{
		Student student = studentsTree.updateStudent(username.trim(), amount);
		if(student != null)
		{
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			Sys.update("update students set due=due-"+amount+" where username='"+username+"'",conUrl);
			Sys.update("update students set status = (CASE WHEN due < 1 THEN 'white' ELSE 'red' END) where username='"+username+"'",conUrl);
			Sys.update("insert into FeesRecord (username,amountPaid,payType,payDate) values ('"+username+"',"+amount+",'Monthly','"+format.format(date)+"')",conUrl);
			return true;
		}
		else
			return false;
		
	}
	
	public void collectExtra(String username, double amount, String payType)
	{
		if(studentsTree.updateStudent(username, amount) != null)
		{
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date = new Date();
			Sys.update("update students set due=due-"+amount+" where username='"+username+"'",conUrl);
			Sys.update("update students set status = (CASE WHEN due < 1 THEN 'white' ELSE 'red' END) where username='"+username+"'",conUrl);
			Sys.update("insert into FeesRecord (username,amountPaid,payType,payDate) values ('"+username+"',"+amount+",'"+payType+"','"+format.format(date)+"')",conUrl);
			JOptionPane.showMessageDialog(null, "Collected Successfully!!");
		}
		else
			JOptionPane.showMessageDialog(null, "Student Not Found!!");
	}
	
	public void implyMonthlyDues(String fileUrl)
	{
		
		
//		try {
//		      File myObj = new File(fileUrl);
//		      Scanner myReader = new Scanner(myObj);
//		      myReader.nextLine();
//		      while (myReader.hasNextLine()) {
//		        String data = myReader.nextLine();
//		        String cls = data.split(":")[0];
//		        String fees = data.split(":")[1];
//		        Sys.update("update students set due = due+"+fees+" Where level='"+cls+"'",conUrl);
//		      }
//		      myReader.close();
//		    } catch (FileNotFoundException e) {
//		    	JOptionPane.showMessageDialog(null, "Classes File is Missing");
//		}
		Sys.update("update students set due=due+fees", conUrl);
		Sys.update("update students set status = (CASE WHEN due > 0 THEN 'red' ELSE 'white' END)",conUrl);
		JOptionPane.showMessageDialog(null, "Implied Successfully!!");
		studentsTree = Sys.loadStudents("jdbc:ucanaccess://"+getClass().getClassLoader().getResource("SF.accdb").getPath());
	}
	
	public void implyExtraDues(double amount)
	{
		studentsTree.updateAll(amount, studentsTree.getSroot());
		Sys.update("update students set due = due+"+amount,conUrl);
		Sys.update("update students set status = (CASE WHEN due > 0 THEN 'red' ELSE 'white' END)",conUrl);
		JOptionPane.showMessageDialog(null, "Implied Successfully!!");
	}
	
	public String implyFine(String username, double amount)
	{
		Student student = studentsTree.fineStudent(username, amount);
		if(student != null)
		{
			if(Sys.update("update students set due=due+"+amount+" where username='"+username+"'", conUrl) &&
			Sys.update("update students set status = (CASE WHEN due > 0 THEN 'red' ELSE 'white' END)",conUrl))
				return student.getName();
			else
				return null;
				
		}
		else
			return null;
	}

}
