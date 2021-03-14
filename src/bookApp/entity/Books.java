package bookApp.entity;

public class Books {
	
	private int bookId;
	private String title;
		
	public Books(int bookId, String title) {
		this.bookId = bookId;
		this.title = title;		
	}	
	
	public int getBookId() {
		return bookId;
	}	
	
	public String getTitle() {
		return title;
	}
}
