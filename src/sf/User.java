package sf;


public class User
{
	private String name;
	private String username;
	private String password;
	private String rank;
	private User leftChild, rightChild;
	
	public User(String name, String username, String password, String rank)
	{
		this.name = name;
		this.username = username;
		this.password = password;
		this.rank = rank;
		leftChild = rightChild = null;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}
	
	public User getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(User leftChild) {
		this.leftChild = leftChild;
	}

	public User getRightChild() {
		return rightChild;
	}

	public void setRightChild(User rightChild) {
		this.rightChild = rightChild;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
