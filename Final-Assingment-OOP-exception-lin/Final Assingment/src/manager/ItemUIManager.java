package manager;

import java.util.Scanner;

public class ItemUIManager {
	private ItemManager im;
	private Scanner keyboard = new Scanner(System.in);

	public ItemUIManager() {
		im = new ItemManager();
		mainMenu();
	}

	void mainMenu() {
		int choice = 0;

		while (choice != 6) {
			System.out.println("\n----Item Menu----");
			System.out.println("1. Add Item.");
			System.out.println("2. Edit Item.");
			System.out.println("3. Remove Item.");
			System.out.println("4. Display Items by Category.");
			System.out.println("5. Display all Items.");
			System.out.println("6. Exit.");

			choice = Integer.parseInt(keyboard.nextLine());

			switch (choice) {
			case 1:
				System.out.println("Enter Item Type:");
				String itemType = keyboard.nextLine();
				System.out.println("Enter Id: ");
				int id = Integer.parseInt(keyboard.nextLine());
				System.out.println("Enter Title: ");
				String title = keyboard.nextLine();
				System.out.println("Enter Author: ");
				String author = keyboard.nextLine();
				System.out.println("Enter Genre: ");
				String genre = keyboard.nextLine();
				System.out.println("Enter Publisher: ");
				String publisher = keyboard.nextLine();

				im.addItem(itemType, id, title, author, genre, publisher);
				System.out.println("Item has been added!");
				break;

			case 2:
				editItemMenu();
				break;

			case 3:
				System.out.println("Enter Id of Item to be removed: ");
				int itemId = Integer.parseInt(keyboard.nextLine());
				im.removeItem(itemId);
				System.out.println("Item has been removed!");
				break;

			case 4:
				System.out.println("Enter Genre: ");
				String category = keyboard.nextLine();
				im.displayByCategory(category);
				break;

			case 5:
				im.displayAll();
				break;

			case 6:
				im.disconnect();
				break;

			default:
				System.out.println("Incorrect choice. Enter 1 to 6.");
			}
		}
	}

	void editItemMenu() {
		System.out.println("\nWhat would you like to edit?");
		System.out.println("1. Title");
		System.out.println("2. Author");
		System.out.println("3. Genre");
		System.out.println("4. Publisher");
		System.out.println("5. Item Type");

		int choice = Integer.parseInt(keyboard.nextLine());
		int itemId = 0;

		if (choice >= 1 && choice <= 5) {
			System.out.println("Enter Id of Item: ");
			itemId = Integer.parseInt(keyboard.nextLine());
		}

		switch (choice) {
		case 1:
			System.out.println("Enter new Title: ");
			String title = keyboard.nextLine();
			im.editItemTitle(itemId, title);
			System.out.println("Title updated!");
			break;

		case 2:
			System.out.println("Enter new Author: ");
			String author = keyboard.nextLine();
			im.editAuthor(itemId, author);
			System.out.println("Author updated!");
			break;

		case 3:
			System.out.println("Enter new Genre: ");
			String genre = keyboard.nextLine();
			im.editGenre(itemId, genre);
			System.out.println("Genre updated!");
			break;

		case 4:
			System.out.println("Enter new Publisher: ");
			String publisher = keyboard.nextLine();
			im.editPublisher(itemId, publisher);
			System.out.println("Publisher updated!");
			break;

		case 5:
			System.out.println("Enter new Item Type: ");
			String itemType = keyboard.nextLine();
			im.editItemType(itemId, itemType);
			System.out.println("Item type updated!");
			break;

		default:
			System.out.println("Incorrect choice. Enter 1 to 5.");
		}
	}
}