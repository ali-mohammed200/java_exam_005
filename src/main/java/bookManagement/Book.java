package bookManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Book {
	int bookId;
	String bookTitle;
	float bookPrice;

	public Book() {
		super();
	}
	
	public Book(int bookId, String bookTitle, float bookPrice) {
		super();
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.bookPrice = bookPrice;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}
	
//	Below are static methods on the Book class which 
//	work with the database
//	They are not fully tied to an instance of a book class.
//	In order for the getter and setter methods above to be tied to the
//	database, we can create a save function on the instance which will
//	save any changes of the instance into the database
	
//	creates a book in the database and returns a new book instance
	public static Book create(Connection conn, int id, String title, float price) throws SQLException {
		String query = "INSERT INTO books (bookId, bookTitle, bookPrice) " + "VALUES (?, ?, ?)";
		PreparedStatement st = conn.prepareStatement(query);
		st.setInt(1, id);
		st.setString(2, title);
		st.setFloat(3, price);

		st.executeUpdate();
		return new Book(id, title, price);
	}

//	read a book in the database and returns a new book instance
	public static Book read(Connection conn, int id) throws SQLException {
		String query = "SELECT * FROM books WHERE bookId = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setInt(1, id);

		ResultSet rs = st.executeQuery();
		Book book = new Book();
		
		while (rs.next()) {
			book = new Book(rs.getInt(1), rs.getString(2), rs.getFloat(3));
			System.out.print(book);
			}
		
		return book;
	}

//	updates a book in the database and returns a new book instance via Book.read function
//	2 calls to the database. One for update and one for read
//	we can limit this by calling a calling a new instance of book instead of hitting the database
	public static Book updateTitle(Connection conn, int id) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("What is the value of title: ");
		String title = sc.nextLine();
		String query = "UPDATE books SET bookTitle = ? WHERE bookId = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, title);
		st.setInt(2, id);
		st.executeUpdate();
		
		return Book.read(conn, id);
	}

	public static Book updatePrice(Connection conn, int id) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("What is the value of price: ");
		float price = sc.nextFloat();
		String query = "UPDATE books SET bookPrice = ? WHERE bookId = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setFloat(1, price);
		st.setInt(2, id);
		st.executeUpdate();
		
		return Book.read(conn, id);
	}

	public static Book updateFullBook(Connection conn, int id) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.print("What is the value of title: ");
		String titl = sc.nextLine();
		System.out.print("What is the value of price: ");
		float pric = sc.nextFloat();
		String query = "UPDATE books SET bookTitle = ?, bookPrice = ? WHERE bookId = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, titl);
		st.setFloat(2, pric);
		st.setInt(3, id);
		st.executeUpdate();
		
		return Book.read(conn, id);
	}

//	deletes a book from the database
	public static void delete(Connection conn, int id) throws SQLException {
		String query = "DELETE FROM books WHERE bookId = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setInt(1, id);

		int count = st.executeUpdate();
		System.out.println(count + " records updated");
	}

//	Returns an array list of all the books in the database
	public static ArrayList<Book> listAll(Connection conn) throws SQLException {
		ArrayList<Book> books = new ArrayList<Book>();
		String query = "SELECT * FROM books";
		PreparedStatement st = conn.prepareStatement(query);

		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			Book book = new Book(rs.getInt(1), rs.getString(2), rs.getFloat(3));
			books.add(book);
			System.out.println(book);
		}
		
		return books;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookTitle=" + bookTitle + ", bookPrice=" + bookPrice + "]";
	}
}
