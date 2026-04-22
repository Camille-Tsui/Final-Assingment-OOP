package manager;

import java.util.Scanner;

public class RentalUIManager {
	private RentalManager rm;
	private Scanner keyboard = new Scanner(System.in);

	public RentalUIManager() {
		rm = new RentalManager();
		mainMenu();
	}

	void mainMenu() {
		int choice = 0;

		while (choice != 4) {
			System.out.println("\n----Rental Menu----");
			System.out.println("1. Add Rental.");
			System.out.println("2. Remove Rental.");
			System.out.println("3. Display all Rentals.");
			System.out.println("4. Exit.");

			choice = Integer.parseInt(keyboard.nextLine());

			switch (choice) {
			case 1:
				System.out.println("Enter Rental Id: ");
				int id = Integer.parseInt(keyboard.nextLine());
				System.out.println("Enter User Id: ");
				int userId = Integer.parseInt(keyboard.nextLine());
				System.out.println("Enter Item Id: ");
				int itemId = Integer.parseInt(keyboard.nextLine());
				System.out.println("Enter Start Date (YYYY-MM-DD): ");
				String startDate = keyboard.nextLine();
				System.out.println("Enter Return Date (YYYY-MM-DD): ");
				String returnDate = keyboard.nextLine();
				System.out.println("Enter Date Returned (YYYY-MM-DD): ");
				String dateReturned = keyboard.nextLine();

				rm.addRental(id, userId, itemId, startDate, returnDate, dateReturned);
				System.out.println("Rental has been added!");
				break;

			case 2:
				System.out.println("Enter Id of Rental to be removed: ");
				int rentalId = Integer.parseInt(keyboard.nextLine());
				rm.removeRental(rentalId);
				System.out.println("Rental has been removed!");
				break;

			case 3:
				rm.displayRentals();
				break;

			case 4:
				rm.disconnect();
				break;

			default:
				System.out.println("Incorrect choice. Enter 1 to 4.");
			}
		}
	}
}