import java.util.Scanner;

public class RunShop {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of Cheeses for shop setup: ");
		int numCheese = input.nextInt();
		ShopArr shop = new ShopArr(numCheese);
		shop.run();
		System.out.print("Ran with Cheese Total: " + numCheese);
		input.close();
	}

}
