package sf;

import DS.LinkedList;

public class Student extends User
{
	private String level;
	private String status;
	private double due;
	private String fatherName;
	private double feesAmount;
	private LinkedList records;
	private Student leftChild;
	private Student rightChild;
	
	public Student getLeftChild() {
		return this.leftChild;
	}

	public void setLeftChild(Student leftChild) {
		this.leftChild = leftChild;
	}

	public Student getRightChild() {
		return this.rightChild;
	}

	public void setRightChild(Student rightChild) {
		this.rightChild = rightChild;
	}

	public Student(String name, String fatherName, double feesAmount, String username, String password, String level, double due, String status)
	{
		super(name, username, password, "student");
		this.level = level;
		this.status = status;
		this.due = due;
		this.fatherName = fatherName;
		this.feesAmount = feesAmount;
		
		leftChild = rightChild = null;
	}
	
	public String[] doDataArray()
	{
		String[] a = {this.getName(),this.fatherName, ""+this.feesAmount, this.getUsername(),this.getPassword(),""+this.getLevel(),""+this.getDue(),this.getStatus()};
		return a;
	}
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public LinkedList getRecords() {
		return records;
	}
	
	public void setRecords(LinkedList records) {
		this.records = records;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public double getFeesAmount() {
		return feesAmount;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public double getDue() {
		return due;
	}

	public void setDue(double due) {
		this.due = due;
	}
}
