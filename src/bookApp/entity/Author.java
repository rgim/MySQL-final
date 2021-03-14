package bookApp.entity;

public class Author {
	
	private int authorId;
	private String firstName;
	private String lastName;
	
	public Author(int authorId, String first, String last) {
		this.authorId = authorId;
		this.firstName = first;
		this.lastName = last;
	}

	public int getAuthorId() {
		return authorId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}		
	
}
