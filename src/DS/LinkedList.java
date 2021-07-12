package DS;

public class LinkedList
{
	Record first;
	private int nItems;
	Record[] all;
	public LinkedList()
	{
		first = null;
		nItems = 0;
		all = null;
	}
	
	public boolean isEmpty()
	{
		return first == null;
	}
	public void insertFirst(Record newRecord)
	{
		newRecord.setNext(first);
		first = newRecord;
		nItems++;
	}
	
	public Record[] pullAllRecords()
	{
		int counter = 0;
		all = new Record[nItems];
		Record current = first;
		while(current!=null)
		{
			all[counter++] = current;
			current = current.getNext();
		}
		return all;
	}
	public Record getFirst() {
		return first;
	}

	public void setFirst(Record first) {
		this.first = first;
	}

	public int getnItems() {
		return nItems;
	}

	public void setnItems(int nItems) {
		this.nItems = nItems;
	}

}
