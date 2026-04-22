package manager;

import java.util.Scanner;

public class UserUIManager {
	private UserManager um;
	private Scanner keyboard = new Scanner(System.in);

	public UserUIManager() {
		um = new UserManager();
		mainMenu();
	}

	void mainMenu() {
		int choice = 0;

		while (choice != 4) {
			System.out.println("\n----User Menu----");
			System.out.println("1. Add User.");
			System.out.println("2. Remove User.");
			System.out.println("3. Display all Users.");
			System.out.println("4. Exit.");

			choice = Integer.parseInt(keyboard.nextLine());

			switch (choice) {
			case 1:
				System.out.println("Enter Id: ");
				int id = Integer.parseInt(keyboard.nextLine());
				System.out.println("Enter First Name: ");
				String firstName = keyboard.nextLine();
				System.out.println("Enter Last Name: ");
				String lastName = keyboard.nextLine();
				System.out.println("Enter Date of Birth (YYYY-MM-DD): ");
				String dateOfBirth = keyboard.nextLine();
				System.out.println("Enter Phone Number: ");
				String phoneNumber = keyboard.nextLine();

				um.addUser(id, firstName, lastName, dateOfBirth, phoneNumber);
				System.out.println("User has been added!");
				break;

			case 2:
				System.out.println("Enter Id of User to be removed: ");
				int userId = Integer.parseInt(keyboard.nextLine());
				um.removeUser(userId);
				System.out.println("User has been removed!");
				break;

			case 3:
				um.displayUsers();
				break;

			case 4:
				um.disconnect();
				break;

			default:
				System.out.println("Incorrect choice. Enter 1 to 4.");
			}
		}
	}
}