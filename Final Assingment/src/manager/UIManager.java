package manager;

import java.util.Scanner;

public class UIManager {
	private ItemManager im;
	private Scanner keyboard = new Scanner(System.in);
	
	public UIManager() {
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
			switch(choice)
			{
				case 1: 
					System.out.println("Enter Item Type:");
					String itemType = keyboard.nextLine();
					System.out.println("Enter Id: ");
					int id  = keyboard.nextInt();
					keyboard.nextLine(); // scanner bugs out if this line isnt added
					System.out.println("Enter Title: ");
					String title = keyboard.nextLine();
					System.out.println("Enter Author: ");
					String author = keyboard.nextLine();
					System.out.println("Enter Genre: ");
					String genre  = keyboard.nextLine();
					System.out.println("Enter Publisher: ");
					String publisher  = keyboard.nextLine();
					im.addItem(itemType, id, title, author, genre, publisher);
					System.out.println("Item has been added!");
					break;
				case 2:
					editItemMenu();
					break;
				case 3:
					System.out.println("Enter Id of Item to be removed: ");
					int item_id = Integer.parseInt(keyboard.nextLine());
					im.removeItem(item_id);
					System.out.println("Item has been removed!");
					break;
				case 4:
					im.displayByCategory();
					break;
				case 5:
					im.displayAll();
					break;
				case 6:
					im.disconnect();
					break;
				default:
					System.out.println("Incorrect choice. Enter 1 to 5.");
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
		int item_id = 0;
		if (choice >= 1 && choice <= 5) {
			System.out.println("Enter Id of Item: ");
			item_id = Integer.parseInt(keyboard.nextLine());
		}
		switch(choice)
		{
			case 1: 
				System.out.println("Etner new Title: ");
				String title = keyboard.nextLine();
				im.editItemTitle(item_id, title);
				System.out.println("Title updated!");
				break;
			case 2:
				System.out.println("Enter new Author: ");
				String author = keyboard.nextLine();
				im.editAuthor(item_id, author);
				System.out.println("Author updated!");
				break;
			case 3:
				System.out.println("Enter new Genre: ");
				String genre = keyboard.nextLine();
				im.editGenre(item_id, genre);
				System.out.println("Genre updated!");
				break;
			case 4:
				System.out.println("Enter new Publisher: ");
				String publisher = keyboard.nextLine();
				im.editPublisher(item_id, publisher);
				System.out.println("Publisher updated!");
				break;
			case 5:
				System.out.println("Enter new Item Type: ");
				String itemType = keyboard.nextLine();
				im.editItemType(item_id, itemType);
				System.out.println("Item type updated!");
				break;
			default:
				System.out.println("Incorrect choice. Enter 1 to 5.");
		}
	}
}
