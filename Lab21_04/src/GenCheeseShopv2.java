import java.util.Random;
import java.util.Scanner;

public class GenCheeseShopv2 {

	/*
	 * Displays the intro message informing the user of various cheeses sold
	 * while populating the names and prices arrays, and initializing the
	 * amounts array.
	 */
	public static void intro(String[] names, double[] prices, double[] amounts) {
		System.out.println();
		System.out.print("We sell " + names.length + " kinds of Cheese (in 0.5 lb packages)\n");
		if (0 < names.length & names.length<27){
			names[0] = "Humboldt Fog";
			prices[0] = 25.00;


			if (1 < names.length){
				names[1] = "RedHawk";
				prices[1] = 40.5;
			}

			if (2 < names.length){
				names[2] = "Teleme";
				prices[2] = 17.25;

			}
			System.out.println(names[0] + ": $" + prices[0] + " per pound");


			if (1 < names.length)
				System.out.println(names[1] + ": $" + prices[1] + " per pound");


			if (2 < names.length)
				System.out.println(names[2] + ": $" + prices[2] + " per pound");

			Random ranGen = new Random(100);

			for (int i = 3; i < names.length; i++) { //Question; i=3 because the three cheeses were given already
				names[i] = "Cheese Type " + (char)('A'+i);
				prices[i] = ranGen.nextInt(1000)/100.0;
				amounts[i] = 0;

				System.out.println(names[i] + ": $" + prices[i] + " per pound");

			}
		}
	}



	/*
	 * Gets the amount of each cheese the user would like to purchase and populates
	 * the amounts array with the user inputs. Performs with input validation
	 * (amount >= 0 and multiple of 0.5).
	 */
	public static void getAmount(Scanner sc, String[] names, double[] amounts) {
		System.out.println();
		for (int i = 0; i < names.length; i++) {
			System.out.print("Enter the amount of " + names[i] + " in lb: ");
			double otherCheese = sc.nextDouble();
			while (otherCheese < 0.0 || otherCheese % 0.5 != 0) {
				if (otherCheese < 0.0) {
					System.out.print("Invalid input. Enter a value >= 0: ");
					otherCheese = sc.nextDouble();
				}
				if (otherCheese % 0.5 != 0) {
					System.out.print("Invalid input. Enter a value that's multiple of 0.5: ");
					otherCheese = sc.nextDouble();
				}
			}
			amounts[i] = otherCheese;
		}

	}

	/*
	 * Displays the itemized list of all cheeses bought or a special message if none
	 * were purchased.
	 */
	public static void itemizedList(String[] names, double[] prices, double[] amounts) {
		double subtotal = 0;
		for(int i = 0; i < names.length; i++) {
			if (amounts[i] > 0) {
				System.out.printf("%.1f lb of %s @ $%.2f = $%.2f\n", amounts[i], names[i], prices[i], prices[i] * amounts[i]);
			}

		}
		for(int i = 0; i < names.length; i++) {
			subtotal+=prices[i]*amounts[i];
		}
		if (subtotal == 0){
			System.out.println("No items were purchased.");
		}

	}

	/*
	 * Calculates the Original Sub Total, which is the price*amount of each
	 * cheese added together. Returns the Original Sub Total.
	 */
	public static double calcSubTotal(double[] prices, double[] amounts) {
		double sub = 0;

		for(int i = 0; i < amounts.length; i++) {
			sub += prices[i]*amounts[i];
		}

		return sub;
	}

	/*
	 *  Calculates discounts based on special offers on Humboldt Fog and Red Hawk,
	 *  stores them in disSpecials[0] and disSpecials[1], and returns the array.
	 */
	public static double[] discountSpecials(double[] amounts, double[] prices){
		double[] disSpecials = new double[2];
		double totalDiscount;
		double HumDiscount = 0;
		double RedDiscount = 0;
		if (amounts.length != 0) {
			if (amounts.length >= 1) {
				int freePackages_Hum = (int) ((amounts[0] / 0.5) / 2.0);
				if (amounts[0] > 0.0 && ((amounts[0] / 0.5) % 2.0 >= 0.0) && (freePackages_Hum > 0)) {
					HumDiscount = 12.5 * (freePackages_Hum);
					disSpecials[0] = HumDiscount;

				}
				if (amounts.length >=2){
					int freePackages_Red = (int) ((amounts[1] / 0.5) / 3.0);
					if (amounts[1] > 0.0 && ((amounts[1] / 0.5) % 3.0 >= 0.0) && (freePackages_Red > 0)) {
						RedDiscount = 20.25 * (freePackages_Red);
						disSpecials[1] = RedDiscount;
					}
				}
			}
			else{
				System.out.println("None                              -$0.0");
			}
		}

		return disSpecials;		
	}

	/*
	 * Displays the Original Sub Total, discounts based on specials, and the New Sub 
	 * Total. Returns the New Sub Total.
	 */
	public static double printSubTotals(double subTotal, double[] disSpecials) {
		System.out.println();
		System.out.printf("Original Sub Total:               $%.2f", subTotal);
		System.out.println("\nSpecials...");
			if (disSpecials[0] != 0) {
				System.out.printf("Humboldt Fog (Buy 1 Get 1 Free): -$%.2f", disSpecials[0]);
			}
			else if (disSpecials[1] != 0){
				System.out.printf("Red Hawk (Buy 2 Get 1 Free):     -$%.2f", disSpecials[1]);
			}
			else {
			System.out.print("None:               			  $0.00");
			}
		System.out.printf("\nNew Sub Total:                    $%.2f", subTotal-disSpecials[0]-disSpecials[1]);


		return subTotal-disSpecials[0]-disSpecials[1] ;
	}

	/*
	 * Calculates the additional discount based on the New Sub Total and displays 
	 * the Final Total.
	 */
	public static void printFinalTotal(double newSubTotal) {
		double totalDiscount2 = 0;
		if (newSubTotal > 250) {
			totalDiscount2 += (newSubTotal * 0.25);
			System.out.printf("\nAdditional 25%% Discount:         -$%.2f", totalDiscount2);
		}
		else if (newSubTotal > 150){
			totalDiscount2 += (newSubTotal * 0.10);

			System.out.printf("\nAdditional 10%% Discount:         -$%.2f", totalDiscount2);
		}
		else {
			System.out.print("\nAdditional 0% Discount:          -$0.0");
		}
		System.out.printf("\nFinal Total:                      $%.2f", (newSubTotal - totalDiscount2));
		
	}
	
	/*
	 * Program starts here
	 */
	public static void main(String[] args) {

		final int MAXCHEESE;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the number of Cheeses for shop setup: ");
		MAXCHEESE = sc.nextInt();

		// DO NOT CHANGE ANYTHING BELOW
		String[] names = new String[MAXCHEESE];
		double[] prices = new double[MAXCHEESE];
		double[] amounts = new double[MAXCHEESE];

		intro(names, prices, amounts);

		getAmount(sc, names, amounts);

		double subTotal = calcSubTotal(prices, amounts);
		
		
		if (MAXCHEESE != 0 ) {
			System.out.print("\nDisplay the itemized list? (1 for yes) ");
			int display = sc.nextInt();
			
			if (display == 1) {
				itemizedList(names, prices, amounts);
			}
		}

		double newSubTotal = printSubTotals(subTotal, discountSpecials(amounts, prices));
		
		printFinalTotal(newSubTotal);
		
		sc.close();
	}
}