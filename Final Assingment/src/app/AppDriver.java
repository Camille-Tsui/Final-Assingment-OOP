package app;

import java.util.Scanner;


import manager.DatabaseManager;
import manager.ItemUIManager;
import manager.UserUIManager;
import manager.RentalUIManager;

public class AppDriver {

	public static void main(String[] args) {
		new DatabaseManager();
		
		Scanner keyboard = new Scanner(System.in);

		System.out.println("Choose a module:");
		System.out.println("1. Item");
		System.out.println("2. User");
		System.out.println("3. Rental");

		int choice = Integer.parseInt(keyboard.nextLine());

		switch (choice) {
		case 1:
			new ItemUIManager();
			break;
		case 2:
			new UserUIManager();
			break;
		case 3:
			new RentalUIManager();
			break;
		default:
			System.out.println("Invalid choice.");
		}

		keyboard.close();
	}
}