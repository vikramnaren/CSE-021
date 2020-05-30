import java.util.*;

public class ShopArr {

	// Instance Variables
	private Cheese[] cheese;
	
	// Initialize method
	private void init(int max) {

		// Create max number of Cheese pointers
		cheese = new Cheese[max];

		if (max > 0) {
			cheese[0] = new Cheese();
			cheese[0].setName("Humboldt Fog");
			cheese[0].setPrice(25.00);

			if (max > 1) {
				cheese[1] = new Cheese("Red Hawk");
				cheese[1].setPrice(40.50);

				if (max > 2) {
					cheese[2] = new Cheese("Teleme", 17.25);
					//cheese[2].setName("Wrong Name");
				}
			}
		}

		Random ranGen = new Random(100);

		for (int i = 3; i < cheese.length; i++) {
			cheese[i] = new Cheese();
			cheese[i].setName("Cheese Type " + (char) ('A' + i));
			cheese[i].setPrice(ranGen.nextInt(1000) / 100.0);
		}

	}
	public ShopArr() {
		init(10);
	}
	
	public ShopArr(int max) {

		init(max);
	}

	/*
	 * Displays the intro message informing the user of various cheeses sold and
	 * Gets the amount of each cheese the user would like to purchase. 
	 */
	private void intro(Scanner input) {

		System.out.println("We sell " + cheese.length + " kinds of Cheese (in 0.5 lb packages)");
		for (int i = 0; i < cheese.length; i++){
			System.out.print(cheese[i].getName() + ": $" + cheese[i].getPrice() + " per pound\n");
		}
		System.out.println();
		for (int i = 0; i < cheese.length; i++) {
			System.out.print("Enter the amount of " + cheese[i].getName() + " in lb: ");
			cheese[i].setAmount(input);
		}
	}

	/*
	 * Displays the itemized list of all cheeses bought or a special message if none 
	 * were purchased.
	 */
	private void itemizedList(){
		double amt = 0, price = 0;
		for(int i = 0; i < cheese.length; i++) {
			if (cheese[i].getAmount() > 0) {
				System.out.printf("%.1f lb of %s @ $%.2f = $%.2f\n", cheese[i].getAmount(), cheese[i].getName(), cheese[i].getPrice(), cheese[i].getPrice() * cheese[i].getAmount());
			}

		}
		for(int i = 0; i < cheese.length; i++) {
			price+=cheese[i].getPrice() * cheese[i].getAmount();
		}
		if (price == 0){
			System.out.println("No items were purchased.");
		}
	}

	/*
	 * Calculates the Original Sub Total, which is the price*amount of each 
	 * cheese added together. Returns the Original Sub Total.
	 */
	private double calcSubTotal() {
		double subTotal = 0;
		for(int i = 0; i < cheese.length; i++) {
			subTotal += cheese[i].getPrice() * cheese[i].getAmount();
		}

		return subTotal;
	}

	/*
	 * Calculates discounts based on special offers on Humboldt Fog and Red Hawk, 
	 * stores them in disSpecials[0] and disSpecials[1], and returns the array. 
	 * Minor changes from Lab 07 (identical logic). 
	 */
	private double[] discountSpecials() {
		double[] disSpecials = {0, 0};
		double hfAmt = 0;
		double rhAmt = 0;
		if (cheese.length > 0) {

			hfAmt = cheese[0].getAmount();


			if (hfAmt > 0) {
				int freePackages_Hum = (int) ((hfAmt / 0.5) / 2.0);
				if (hfAmt > 0.0 && ((hfAmt / 0.5) % 2.0 >= 0.0) && (freePackages_Hum > 0)) {
					disSpecials[0] = 12.5 * (freePackages_Hum);

				}
			}
			if (cheese.length >= 2){
				rhAmt = cheese[1].getAmount();
			if (rhAmt > 0) {
				int freePackages_Red = (int) ((rhAmt / 0.5) / 3.0);
				if (rhAmt > 0.0 && ((rhAmt / 0.5) % 3.0 >= 0.0) && (freePackages_Red > 0)) {
					disSpecials[1] = 20.25 * (freePackages_Red);

				}
			}
		}
	}
		return disSpecials;
	}

	/*
	 * Displays the Original Sub Total, discounts based on specials, and the New Sub 
	 * Total. Returns the New Sub Total. Identical to Lab 07.
	 */
	private double printSubTotals(double subTotal, double[] disSpecials) {
		System.out.println();
		System.out.printf("Original Sub Total:               $%.2f", subTotal);
		System.out.println("\nSpecials...");
		if (disSpecials[0] != 0) {
			System.out.printf("Humboldt Fog (Buy 1 Get 1 Free): -$%.2f", disSpecials[0]);
		}
		if (disSpecials[1] != 0){
			System.out.printf("\nRed Hawk (Buy 2 Get 1 Free):     -$%.2f", disSpecials[1]);
		}
		if (disSpecials[0] == 0 && disSpecials[1] == 0 ){
			System.out.print("None:               			  $0.00");
		}
		System.out.printf("\nNew Sub Total:                    $%.2f", subTotal-disSpecials[0]-disSpecials[1]);


		return subTotal-disSpecials[0]-disSpecials[1] ;
	}

	/*
	 * Calculates the additional discount based on the New Sub Total and displays 
	 * the Final Total. Identical to Lab 07.
	 */
	private void printFinalTotal(double newSubTotal) {
		double totalDiscount2 = 0;
		if (newSubTotal > 250) {
			totalDiscount2 += (newSubTotal * 0.25);
			System.out.printf("\nAdditional 15%% Discount:         -$%.2f", totalDiscount2);
		}
		else if (newSubTotal > 150){
			totalDiscount2 += (newSubTotal * 0.10);

			System.out.printf("\nAdditional 10%% Discount:         -$%.2f", totalDiscount2);
		}
		else {
			System.out.print("\nAdditional 0% Discount:          -$0.0");
		}
		System.out.printf("\nFinal Total:                      $%.2f", (newSubTotal - totalDiscount2));
		System.out.println();
	}

	private void printFree(){
		double amt;
		System.out.println();
		System.out.println("Today is your lucky day!");
		for (int i = 0; i < cheese.length; i++) 		
			if ((amt = cheese[i].getAmount()) > 0)
				System.out.println(amt + " lb of " + cheese[i].getName() + " @ $0 = $" + 0);
		System.out.println("Total Price: FREE!!!\n");
	}

	public void run() {

		Scanner input = new Scanner(System.in);
		intro(input);
		double subTotal = calcSubTotal();
		
		System.out.println();
		System.out.print("Display the itemized list? (1 for yes): ");
		int list = input.nextInt();
		if (list == 1)
			itemizedList();	

		int free = (new Random()).nextInt(100);
		//System.out.println("Random num is " + free);
		if (free != 0) {
			double newSubTotal = printSubTotals(subTotal, discountSpecials());
			printFinalTotal(newSubTotal);
		} else {
			printFree();
			return;
		}
		
		System.out.println();
		System.out.print("Do you wish to redo your whole order? (1 for yes): ");
		int redo = input.nextInt();

		System.out.println();

		if (redo == 1)
			run();
		else
			System.out.print("Thanks for coming!\n");
	}
}
