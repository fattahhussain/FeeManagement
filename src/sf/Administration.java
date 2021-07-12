package sf;
import DS.*;


public class Administration extends User
{
	Tree studentsTree;
	Student[] allStudents;
	String[][] dataArray;
	LinkedList records;
	
	int counter = 0;
	
	public Administration(String name, String username, String password, String rank)
	{
		super(name, username, password, rank);
		dataArray = null;
		studentsTree = Sys.loadStudents("jdbc:ucanaccess://"+getClass().getClassLoader().getResource("SF.accdb").getPath());
		allStudents = studentsTree.getDataList(null);
		records = null;
	}
	
	public String[][] getAllStudents()
	{
		allStudents = studentsTree.getDataList(null);
		dataArray = new String[allStudents.length][8];
		for(int i=0; i<allStudents.length; i++)
		{
			dataArray[i] = allStudents[i].doDataArray(); 
		}
			
		return dataArray;
	}
	public Student[] getAllStudentObjs()
	{
		
		return allStudents;
	}
	public String[][] getRedList()
	{
		counter = 0;
		allStudents = studentsTree.getDataList(null);
		dataArray = new String[allStudents.length][8];
		for(int i=0; i<allStudents.length; i++)
		{
			if(allStudents[i].getStatus().trim().equalsIgnoreCase("red"))
				dataArray[counter++] = allStudents[i].doDataArray();
		}
		return dataArray;
	}
	
	public String[][] getWhiteList()
	{
		counter = 0;
		allStudents = studentsTree.getDataList(null);
		dataArray = new String[allStudents.length][8];
		for(int i=0; i<allStudents.length; i++)
		{
			if(allStudents[i].getStatus().trim().equalsIgnoreCase("white"))
				dataArray[counter++] = allStudents[i].doDataArray();
		}
		return dataArray;
	}
	
	public String[][] getClassList(String level)
	{
		counter = 0;
		allStudents = studentsTree.getDataList(null);
		dataArray = new String[allStudents.length][8];
		for(int i=0; i<allStudents.length; i++)
		{
			if(allStudents[i].getLevel().equals(level))
				dataArray[counter++] = allStudents[i].doDataArray();
		}
		return dataArray;
	}
	
	public void loadFeesRecords()
	{
		records = Sys.loadFeesRecords("jdbc:ucanaccess://"+getClass().getClassLoader().getResource("SF.accdb").getPath());
	}
	public String[] findStudentFromList(String username)
	{
		Student foundStudent = studentsTree.findStudent(username);
		if(foundStudent != null)
			return foundStudent.doDataArray();
		else
			return null;
	}
	public Student findStudentObjFromList(String username)
	{
		Student foundStudent = studentsTree.findStudent(username);
		if(foundStudent != null)
			return foundStudent;
		else
			return null;
	}
	public String[][] loadAllRecords()
	{
		dataArray = new String[records.getnItems()][4];
		Record[] all= records.pullAllRecords();
		for(int i=0; i<dataArray.length; i++)
		{
			dataArray[i] = all[i].doDataArray();
		}
		return dataArray;
	}
	
	public String[][] loadStudentFeesRecord(String username)
	{
		counter = 0;
		Record current = records.getFirst();
		dataArray = new String[records.getnItems()][4];
		while(current != null)
		{
			if(current.getUsername().equalsIgnoreCase(username))
				dataArray[counter++] = current.doDataArray();
			current = current.getNext();
		}
		
		return dataArray;
	}
}
