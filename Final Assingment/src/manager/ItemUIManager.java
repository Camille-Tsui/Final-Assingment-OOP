package manager;

import java.util.Scanner;



public class ItemUIManager {
	private ItemManager im;
	private Scanner keyboard = new Scanner(System.in);

	public ItemUIManager() {
		im = new ItemManager();
		itemMenu();
	}

	void itemMenu() {
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
				addItem();
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
				System.out.println("Choose a Category");
				System.out.println("Book, EBook, CD");
				String category = keyboard.nextLine();
				if (category.equalsIgnoreCase("Book") || category.equalsIgnoreCase("EBook") || category.equalsIgnoreCase("CD")) {
					im.displayByCategory(category);					
				}
				else {
					System.out.println("Invalid category.");
				}
				break;

			case 5:
				im.displayAll();
				break;

			case 6:
				im.disconnect();
				break;

			default:
				System.out.println("Invalid choice.");
			}
		}
	}
	
	void addItem(){
		System.out.println("Choose an Item Type");
		System.out.println("Book, EBook, CD");
		System.out.println("Enter Item Type:");
		String itemType = keyboard.nextLine();
		// check category is correct
		if (itemType.equalsIgnoreCase("Book") || itemType.equalsIgnoreCase("EBook") || itemType.equalsIgnoreCase("CD")) {
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
			
			switch (itemType) {
			case "Book":
				im.addBook(id, title, author, genre, publisher);
				break;
			case "EBook":
				System.out.println("Enter file size: ");
				int fileSize = Integer.parseInt(keyboard.nextLine());
				im.addEBook(id, title, author, genre, publisher, fileSize);
				break;
			case "CD":
				im.addCD(id, title, author, genre, publisher);
			}
			System.out.println("Item has been added!");
		}
		else {
			System.out.println("Invalid category.");
		}
	}
	void editItemMenu() {
		System.out.println("\nWhat would you like to edit?");
		System.out.println("1. Title");
		System.out.println("2. Author");
		System.out.println("3. Genre");
		System.out.println("4. Publisher");
		System.out.println("5. Damage");

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
			System.out.println("Is item Damaged? (Y/N)");
			String isDamaged = keyboard.nextLine();
			if (isDamaged.equalsIgnoreCase("Y") || isDamaged.equalsIgnoreCase("N")) {
				im.editDamaged(itemId, isDamaged);
				System.out.println("Item Damage updated!");
			}
			else {
				System.out.println("Invalid option.");
			}
			break;

		default:
			System.out.println("Incorrect choice. Enter 1 to 5.");
		}
	}
}