package bookApp;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//import bookApp.entity.Books;   // since I never list the books without the author and series
import bookApp.dao.BookDao;
import bookApp.entity.Author;
import bookApp.dao.AuthorDao;
import bookApp.entity.Series;
import bookApp.dao.SeriesDao;
import bookApp.entity.SearchList;
import bookApp.dao.SearchListDao;

public class Menu {
private Scanner scanner = new Scanner(System.in);
private BookDao bookDao = new BookDao();
private AuthorDao authorDao = new AuthorDao();
private SeriesDao seriesDao = new SeriesDao();
private SearchListDao searchListDao = new SearchListDao();
	
	public void start() {
		int option = 0;
		int type = 0;
			
		System.out.println("\n\tWelcome to our book collection");
		
		do {
			listMenu();
						
			try {
				System.out.print("\nEnter a selection: ");
				option = Integer.parseInt(scanner.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("\n\t*****\tSelection must be a number\t*****");
			}			
			
			try {
				switch(option) {
				case 1: //view
					type = pickType("viewing");					
					if(type == 1) 
						listAll();
					if(type == 2)	
						listSeries();
					if(type == 3) 
						listAuthors();						
					break;
				case 2://add
					type = pickType("adding");	
					if(type == 1) 
						addBook();
					if(type == 2)	
						addSeries();
					if(type == 3) 
						addAuthor();				
					break;
				case 3://update			
					type = pickType("updating");					
					if(type == 1) 
						updateBook();
					if(type == 2)	
						updateSeries();
					if(type == 3) 
						updateAuthor();						
					break;
				case 4://delete					
					type = pickType("deleting");
					if(type == 1) 
						deleteBook();
					if(type == 2)	
						deleteSeries();
					if(type == 3) 
						deleteAuthor();						
					break;
				case 5:
					System.out.println("\n\tHave a nice day");
					System.out.println("\n\tPress any key to exit");
					scanner.nextLine();
					System.exit(0);
					break;
				default:
					System.out.println("\n\t*****\tInvalid selecion, try again!\t*****");
				}
			}
			catch(SQLException e) {
				System.out.println("There was an error during the ***** switch statement ****** " + e.getMessage());
				//break;
			}
				
		}while (option != 5);		
		
	}//end of Main

	private void listMenu() {
		System.out.println("\nMenu\n------------------------------------------");
		System.out.println("1- VIEW   a list of books, series, or authors");
		System.out.println("2- ADD    a book, series, or author");
		System.out.println("3- UPDATE a book, series, or author");
		System.out.println("4- DELETE a book, series, or author");
		System.out.println("5- EXIT PROGRAM");
	}
	
	private int pickType(String type) {
		int xPick = 0;
		do {
			try {
			System.out.println("\nWhat are you " + type + "?");
			System.out.println("1- Book\n2- Series\n3- Author");
			xPick = Integer.parseInt(scanner.nextLine());	
			return xPick;
			}		
			catch(NumberFormatException e) {
				System.out.println("\n\t*****\tSelection must be a number\t*****");
			}				
			return xPick;			
		}while (xPick < 1 || xPick >3);		
	}
	
	// to get id number for book, author, or series
	// for a future version, send an array of valid options? 
	private int pickIdNumber(String type) {
		int xGetNum = -1;
		try {
			System.out.println("\nEnter the number 0 if the " + type + " is NOT on the list. ");
			System.out.print("Enter the " + type + "'s ID# : ");
			xGetNum = Integer.parseInt(scanner.nextLine());
			//return xGetNum;
			}
			catch(NumberFormatException e) {
				System.out.println("\n\t*****\tSelection must be a number\t*****");
			}		
		return xGetNum;		
	}
	
	private int doubleCheck (String type) {
		int xPick = -1;
		System.out.println("\n\t*** Please double check the " + type + " is not already on the list ***");
		System.out.println("\t*** Continue only if the " + type + "is not already on the list ***");
		do {
			try {			
			System.out.println("0- EXIT the add/update menu\n1- CONTINUE and add/update a(n) " + type);
			xPick = Integer.parseInt(scanner.nextLine());	
			return xPick;
			}		
			catch(NumberFormatException e) {
			System.out.println("\n\t*****\tSelection must be a number\t*****");
			}				
			return xPick;			
		}while (xPick != 0 || xPick != 1);	
	}
		
	private void listAuthors() throws SQLException {
		List<Author> authorlist = authorDao.listAuthors();
		System.out.println("\n\tID#\tAuthor\n\t--------------------");		
		for (Author line : authorlist) {
			System.out.println("\t" + line.getAuthorId() + "\t" + (line.getFirstName()+ " " + line.getLastName()));
		}
	}
	
	private void listSeries() throws SQLException {
		List<Series> serieslist = seriesDao.listSeries();
		System.out.println("\n\tID#\tSeries\n\t--------------------");		
		for (Series line : serieslist) {
			System.out.println("\t" + line.getSeriesId() + "\t" + line.getSeriesName());
		}
	}
	
	// add
	private void addBook() throws SQLException { // check author and series
		System.out.println("\n\t*** Before adding a book, both the author and series must be in the program ***");
		System.out.println("\t*** We will check for both of those now ***");
		listAuthors();
		int checkAuthor = pickIdNumber("author");
		
		if (checkAuthor == 0) {
			System.out.println("\n\t*** Returning you to the main menu to add the author;");
			return;
		}
		
		listSeries();
		int checkSeries = pickIdNumber("series");
		
		if (checkSeries == 0) {
			System.out.println("\n\t*** Returning you to the main menu to add the series;");
			return;
		}
		
		System.out.print("Enter the book title: ");
		String title = scanner.nextLine();
		
		bookDao.addBook(title, checkAuthor, checkSeries);
		
		System.out.println("\n\t*** Add successful ***");
	}
	
	private void addAuthor() throws SQLException {
		listAuthors();
		int check = doubleCheck("Author");
		
		if (check == 1) {
			System.out.print("Enter the author's first name: ");
			String first = scanner.nextLine();
			System.out.print("Enter the author's last name: ");
			String last = scanner.nextLine();
			authorDao.addAuthor(first, last);			

			System.out.println("\n\t*** Add successful ***");
		} else if(check == 0) {
			// leaving this here just to check for issues
		} else {
			System.out.print("There was an error during ******  addAuthor() *****    ");
		}
		return;
	}
	
	private void addSeries() throws SQLException {
		listSeries();
		int check = doubleCheck("Series");
		
		if (check == 1) {
			System.out.print("Enter the series name: ");
			String name = scanner.nextLine();
			seriesDao.addSeries(name);
			
			System.out.println("\n\t*** Add successful ***");
		} else if(check == 0) {
			// leaving this here just to check for issues
		}else {
			System.out.print("There was an error during ******  addSeries() *****    ");
		}
		return;		
	}
	
	// update	
	private void updateBook() throws SQLException { 
		listAll();	
		int pick = pickIdNumber("book");
		int option = -1;
		
		if(option < 1 || option > 3) {
			try {
				System.out.print("What are you correcting?\n1- Book title\n2- Book author\n3- Book series\n\nEnter 1, 2, or 3: ");
				option = Integer.parseInt(scanner.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("\n\t*****\tSelection must be either 1, 2, or 3\t*****");
			}
		}		
		if (option == 1) {
			System.out.print("Enter the correct title for this book:");
			String title = scanner.nextLine();
			bookDao.updateBookTitle(pick, title);
			System.out.println("\n\t*** Update successful ***");
		}else if(option == 2) {
			listAuthors();
			int author = pickIdNumber("author");			
			bookDao.updateBookAuthor(pick, author);
			System.out.println("\n\t*** Update successful ***");
		}else if(option == 3) {
			listSeries();
			int series = pickIdNumber("series");			
			bookDao.updateBookSeries(pick, series);
			System.out.println("\n\t*** Update successful ***");
		}else {
			System.out.print("There was an error during ******  updateAuthor() *****    ");
		}
		return;				
	}
	
	private void updateAuthor() throws SQLException {
		listAuthors();
		int pick = pickIdNumber("author");	
		int option = -1;
		String newName;
		
		if(option != 1 || option != 2) {
			try {
				System.out.print("What are you correcting?\n1- First name\n2- Last name\n\nEnter 1 or 2: ");
				option = Integer.parseInt(scanner.nextLine());
			}
			catch(NumberFormatException e) {
				System.out.println("\n\t*****\tSelection must be either 1 or 2\t*****");
			}
		}		
		if (option == 1) {
			System.out.print("Enter the correct first name for this author:");
			newName = scanner.nextLine();
			authorDao.updateAuthorFn(newName, pick);
			System.out.println("\n\t*** Update successful ***");
		}else if(option == 2) {
			System.out.print("Enter the correct last name for this author:");
			newName = scanner.nextLine();
			authorDao.updateAuthorLn(newName, pick);
			System.out.println("\n\t*** Update successful ***");
		}else {
			System.out.print("There was an error during ******  updateAuthor() *****    ");
		}
		return;
	}
	
	private void updateSeries() throws SQLException {
		listSeries();
		int pick = pickIdNumber("Series");
		System.out.print("Enter the correct name for this series:");
		String newName = scanner.nextLine();
		seriesDao.updateSeries(pick, newName);
	}
	
	// delete	
	private void deleteBook() throws SQLException {
		listAll();
		int pick = pickIdNumber("book");
		bookDao.deleteBook(pick);
		System.out.println("\n\t*** Delete successful ***");
	}
	
	private void deleteAuthor() throws SQLException {
		listAuthors();
		int pick = pickIdNumber("author");
		
		try {
			authorDao.deleteAuthor(pick);
			System.out.println("\n\t*** Delete successful ***");
		} catch (SQLException e) {
			System.out.println("\n\t*** You cannot delete this author, since they have books assigned to them ***");
		}		
	}
	
	private void deleteSeries() throws SQLException {
		listSeries();
		int pick = pickIdNumber("series");
		 
		try {
			seriesDao.deleteSeries(pick);
			System.out.println("\n\t*** Delete successful ***");
		} catch (SQLException e) {
			System.out.println("\n\t*** You cannot delete this series, since it has books assigned to it ***");
		}
	}
	

	
	//for display with no id numbers - look into this later
	
	  private void listAll() throws SQLException { 
		  List<SearchList> bookList = searchListDao.listAll();
		  System.out.printf("%-5s %-35s %-30s %-30s \n","Id#", "Title", "Author", "Series"); 
		  for (SearchList book : bookList) { 
			  System.out.printf("%-5s %-35s %-30s %-30s \n", book.getBookIdNum(), book.getTitle(), book.getAuthor(), book.getSeries());
		  }
	  }
	  
		/*
		 * private void listByAuthor() throws SQLException {
		 * System.out.println("Which author list would you like to see?");
		 * listAuthors(); int authorId = pickIdNumber(); List<SearchList> list =
		 * searchListDao.listByAuthor(authorId);
		 * System.out.println("\n\tTITLE\tAUTHOR\tSERIES\n----------------------"); for
		 * (SearchList line : list) { System.out.println("\t" + line.getTitle() + "\t" +
		 * line.getAuthor() + "\t" + line.getSeries()); } }
		 * 
		 * private void listBySeries() throws SQLException {
		 * System.out.println("Which series would you like to see?"); listSeries(); int
		 * seriesId = pickIdNumber(); List<SearchList> list =
		 * searchListDao.listBySeries(seriesId);
		 * System.out.println("\n\tTITLE\tAUTHOR\tSERIES\n----------------------"); for
		 * (SearchList line : list) { System.out.println("\t" + line.getTitle() + "\t" +
		 * line.getAuthor() + "\t" + line.getSeries()); } }
		 */
	 
	
}
