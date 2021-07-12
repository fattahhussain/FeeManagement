package sf;

import javax.swing.JOptionPane;

public class Admin extends Administration
{
	String conUrl;
	public Admin(String name, String username, String password)
	{
		super(name, username, password, "admin");
		conUrl = "jdbc:ucanaccess://"+this.getClass().getClassLoader().getResource("SF.accdb").getPath();
	}
	
	public boolean addStudent(String name, String fatherName, double feesAmount, String username, String password, String level)
	{
		
		if(Sys.update("insert into Students (nam,username,password,level,due,status, fatherName, fees) "
				+ "values ('"+name+"','"+username+"','"+password+"','"+level+"',"+feesAmount+",'red','"+fatherName+"',"+feesAmount+")",conUrl) &&
		Sys.update("insert into Users (nam,username,password,rank) values ('"+name+"','"+username+"','"+password+"','student')",conUrl))
		{
			studentsTree.insert(new Student(name, fatherName, feesAmount, username, password, level, feesAmount, "red"));
			return true;
		}
			
		else
			return false;
	}
	
	public String removeStudent(String username)
	{
		if(studentsTree.findStudent(username) != null)
		{
			String name = studentsTree.findStudent(username).getName();
			if(studentsTree.delete(username) != null)
			{
				if(Sys.update("delete * from Students where username='"+username.trim()+"'",conUrl) &&
				Sys.update("delete * from Users where username='"+username.trim()+"'",conUrl));
					return name;
			}
			else
				return null;
		}
		else
		{
			return null;
		}
		
	}
}
