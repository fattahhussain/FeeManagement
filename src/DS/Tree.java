package DS;
import sf.*;

public class Tree
{
	private Student sroot;
	private User uroot;
	private int nItems;
	Student[] allStudents;
	int counter = 0;
	
	public Tree()
	{
		sroot = null;
		uroot = null;
		nItems = 0;
		allStudents = null;
	}
	
	public void insert(Student snode)
	{
		if(sroot == null)
			sroot = snode;
		else
		{
			Student parent = sroot;
			Student current = sroot;
			while(current != null)
			{
				parent = current;
				if(snode.getUsername().compareToIgnoreCase(current.getUsername()) < 0)
					current = current.getLeftChild();
				else
					current = current.getRightChild();
			}
			if(snode.getUsername().compareToIgnoreCase(parent.getUsername()) < 0)
				parent.setLeftChild(snode);
			else
				parent.setRightChild(snode);
		}
		nItems++;
	}
	
	public void insert(User unode)
	{
		if(uroot == null)
			uroot = unode;
		else
		{
			User parent = uroot;
			User current = uroot;
			while(current != null)
			{
				parent = current;
				if(unode.getUsername().compareTo(current.getUsername()) < 0)
					current = current.getLeftChild();
				else
					current = current.getRightChild();
			}
			if(unode.getUsername().compareTo(parent.getUsername()) < 0)
				parent.setLeftChild(unode);
			else
				parent.setRightChild(unode);
		}
		nItems++;
	}
	public Student findStudent(String username)
	{	
		Student current = sroot;
		while(current!=null)
		{
			if(username.equalsIgnoreCase(current.getUsername()))
				break;
			else if(username.compareToIgnoreCase(current.getUsername()) < 0)
				current = current.getLeftChild();
			else
				current = current.getRightChild();
		}
		return current;
	}
	
	public User findUser(String username)
	{	
		User current = uroot;
		while(current!=null)
		{
			if(username.equalsIgnoreCase(current.getUsername()))
				break;
			else if(username.compareToIgnoreCase(current.getUsername()) < 0)
				current = current.getLeftChild();
			else
				current = current.getRightChild();
		}
		return current;
	}
	
	public Student delete(String username)
	{
		Student current = sroot;
		Student parent = sroot;
		boolean isCurrentLeft = true;
		while(!username.equalsIgnoreCase(current.getUsername()))
		{
			parent = current;
			if(username.compareToIgnoreCase(current.getUsername()) < 0)
			{
				current = current.getLeftChild();
				isCurrentLeft = true;
			}
			else
			{
				current = current.getRightChild();
				isCurrentLeft = false;
			}	
		}
		if(current.getLeftChild() == null && current.getRightChild() == null)
		{
			if(current == sroot)
				sroot = null;
			else if(isCurrentLeft)
				parent.setLeftChild(null);
			else
				parent.setRightChild(null);
		}
		else if(current.getLeftChild() == null)
		{
			if(current == sroot)
				sroot = sroot.getRightChild();
			else if(isCurrentLeft)
				parent.setLeftChild(current.getRightChild());
			else
				parent.setRightChild(current.getRightChild());
		}
		else if(current.getRightChild() == null)
		{
			if(current == sroot)
				sroot = sroot.getLeftChild();
			else if(isCurrentLeft)
				parent.setLeftChild(current.getLeftChild());
			else
				parent.setRightChild(current.getLeftChild());
		}
		else
		{
			Student successor = getSuccessor(current);
			
			if(current == sroot)
				sroot = successor;
			else if(isCurrentLeft)
				parent.setLeftChild(successor);
			else
				parent.setRightChild(successor);
			
			successor.setLeftChild(current.getLeftChild());
		}
		nItems--;
		return current;		
	}
	
	public Student getSuccessor(Student delNode)
	{
		Student successorParent = delNode;
		Student successor = delNode;
		Student current = delNode.getRightChild();
		
		while(current != null)
		{
			successorParent = successor;
			successor = current;
			current = current.getLeftChild();
		}
		
		if(current != delNode.getRightChild())
		{
			successorParent.setLeftChild(successor.getRightChild());
			successor.setRightChild(delNode.getRightChild());
		}
		
		return successor;
	}
	
	public void inorder(Student localRoot)
	{
		if(localRoot != null)
		{
			inorder(localRoot.getLeftChild());
			allStudents[counter++] = localRoot;
			inorder(localRoot.getRightChild());
		}
	}
	
	public Student updateStudent(String username, double amount)
	{
		Student foundStudent = findStudent(username);
		if(foundStudent != null)
		{
			foundStudent.setDue(foundStudent.getDue()-amount);
			if(foundStudent.getDue() > 0)
				foundStudent.setStatus("red");
			else
				foundStudent.setStatus("white");
			return foundStudent;
		}
		else
			return null;
	}
	public Student fineStudent(String username, double amount)
	{
		Student foundStudent = findStudent(username);
		if(foundStudent != null)
		{
			foundStudent.setDue(foundStudent.getDue()+amount);
			if(foundStudent.getDue() > 0)
				foundStudent.setStatus("red");
			else
				foundStudent.setStatus("white");
			return foundStudent;
		}
		else
			return null;
	}
	public void updateAll(double amount, Student localRoot)
	{
		if(localRoot != null)
		{
			updateAll(amount, localRoot.getLeftChild());
			if(amount == 0)
				localRoot.setDue(localRoot.getDue()+300.0);
			else
				localRoot.setDue(localRoot.getDue()+amount);
			if(localRoot.getDue()>0)
				localRoot.setStatus("red");
			else
				localRoot.setStatus("white");
			updateAll(amount, localRoot.getRightChild());
		}
	}
	public Student[] getDataList(String status)
	{
		allStudents = new Student[nItems];
		counter = 0;
		inorder(sroot);
		return allStudents;
	}
	public Student getSroot() {
		return sroot;
	}

	public void setSroot(Student sroot) {
		this.sroot = sroot;
	}

	public User getUroot() {
		return uroot;
	}

	public void setUroot(User uroot) {
		this.uroot = uroot;
	}

	public int getnItems() {
		return nItems;
	}

	public void setnItems(int nItems) {
		this.nItems = nItems;
	}
}

