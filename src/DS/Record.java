package DS;

public class Record
{
	private String username;
	private double amountPaid;
	private String payType;
	private String payDate;
	
	private Record next;
	
	public Record(String username, double amountPaid, String payType, String payDate)
	{
		this.username = username;
		this.amountPaid = amountPaid;
		this.payType = payType;
		this.payDate = payDate;
	}
	public String[] doDataArray()
	{
		String[] a = {this.getUsername(),""+this.getAmountPaid(),this.payType, this.payDate};
		return a;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public Record getNext() {
		return next;
	}

	public void setNext(Record next) {
		this.next = next;
	}
}
