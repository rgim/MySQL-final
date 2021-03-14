package bookApp.entity;

public class SearchList {
	private int bookIdNum;
	private String title;
	private String author;
	private String series;
		
	public SearchList(int bookIdNum, String title, String author, String series) {
		this.bookIdNum = bookIdNum;
		this.title = title;
		this.author = author;
		this.series = series;		
	}
	
	public int getBookIdNum() {
		return bookIdNum;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getSeries() {
		return series;
	}

}
