package bookManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class BookManager {

	public static void main(String[] args) {
		String username = "root"; // These credentials should be in an env file
		String password = "Password123";
		String url = "jdbc:mysql://localhost:3306/test_schema";
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			while (choice != 6) {
				System.out.println(
						"\nWelcome Book Manager\n1. Create        2. Read        3. Update         4. Delete        5. List All Books        6. Exit");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					creater(conn);
					break;
				case 2:
					reader(conn);
					break;
				case 3:
					updater(conn);
					break;
				case 4:
					deleter(conn);
					break;
				case 5:
					Book.listAll(conn);
					break;
				case 6:
					choice = 6;
					System.out.println("Done!");
					break;
				}
			}

			conn.close();
			sc.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

//	Wrappers
	public static void reader(Connection conn) throws SQLException {
		System.out.println("_READ_\nEnter id of the book: ");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		Book.read(conn, id);
	}

	public static void creater(Connection conn) throws SQLException {
		System.out.println("_CREATE_\nEnter id of the book: ");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter title of the book: ");
		String title = sc.nextLine();
		System.out.print("Enter price of the book: ");
		float price = sc.nextFloat();
		System.out.println(Book.create(conn, id, title, price));
	}

	public static void updater(Connection conn) throws SQLException {
		System.out.println("What would you like to update - title or price or both?");
		System.out.println(" Enter a number -\n 1. Title\n 2. Price\n 3. Both\n");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter book id: ");
		int id = sc.nextInt();
		sc.nextLine();

		switch (choice) {
		case 1:
			Book.updateTitle(conn, id);
			break;
		case 2:
			Book.updatePrice(conn, id);
			break;
		case 3:
			Book.updateFullBook(conn, id);
			break;
		}
	}

	public static void deleter(Connection conn) throws SQLException {
		System.out.println("_DELETE_\nEnter id of the book: ");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();

		Book.delete(conn, id);
	}
}
