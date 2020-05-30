import java.util.Scanner;

public class Shop {
    public static int[] NumberDiscount;
    public static double[] discountAmount;
    public static String[] names;
    public static double[] amounts;
    public static double[] prices;
    public static Scanner sc = new Scanner(System.in);
    public static double[] AdditionalDiscount;

    public static String numSuffix(int i) {
        int rem = i % 10;
        switch (rem) {
            case 0:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                return (i + "th");
            case 1:
                if (i % 100 != 11)
                    return (i + "st");
                else
                    return (i + "th");
            case 2:
                if (i % 100 != 12)
                    return (i + "nd");
                else
                    return (i + "th");
            case 3:
                if (i % 100 != 13)
                    return (i + "rd");
                else
                    return (i + "th");
            default:
                break;
        }
        return "";
    }
    public static void intro(){
        System.out.println("This program supports 4 functions:\n" + "   1. Set Up Shop\n" + "   2. Buy\n" + "   3. List Items\n" + "   4. Checkout");
        System.out.print("Please choose the function you want: ");
    }
	public static void checkProcess(){
        boolean flag1 = false;
        boolean flag2 = false;
        boolean flag3 = false;
        boolean flag4 = false;
        int SetupShop = sc.nextInt();
        while (true){
            if (SetupShop == 1) {
                ShopFunction();
                flag1 = true;
                flag2 = false;
                flag3 = false;
                flag4 = false;
                intro();
                SetupShop = sc.nextInt();
            }
            if (SetupShop == 2 && flag1 == true){
                BuyFunction();
                flag2 = true;
                intro();
                SetupShop = sc.nextInt();
            }
            if (SetupShop == 2 && flag1 == false){
                System.out.println("\nShop is not set up yet!\n");
                intro();
                SetupShop = sc.nextInt();
            }
            if (SetupShop == 3 && flag1 == true && flag2 == true){
                System.out.println();
                ListItemsFunction();
                System.out.println();
                flag3 = true;
                intro();
                SetupShop = sc.nextInt();
            }
            if (SetupShop == 3 && flag1 == false){
                System.out.println("\nShop is not set up yet!\n");
                intro();
                SetupShop = sc.nextInt();
            }
            if (SetupShop == 3 && flag2 == false){
                System.out.println("\nYou have not bought anything!\n");
                intro();
                SetupShop = sc.nextInt();
            }
            if (SetupShop == 4 && flag1 == true && flag2 == true){
                CheckOutFunction();
                flag4 = true;
                break;
            }
            if (SetupShop == 4 && flag1 == false){
                System.out.println("\nShop is not set up yet!\n");
                intro();
                SetupShop = sc.nextInt();
            }
            if (SetupShop == 4 && flag2 == false){
                System.out.println("\nYou have not bought anything!\n");
                intro();
                SetupShop = sc.nextInt();
            }
            if(SetupShop > 4 || SetupShop < 1) {
                System.out.println("\nInvalid function!\n");
                intro();
                SetupShop = sc.nextInt();
            }
        }
    }

    public static void ShopFunction() {
        System.out.print("Please enter the number of items to setup shop: ");
        int numItems = sc.nextInt();
        NumberDiscount = new int[numItems];
        names = new String[numItems];
        prices = new double[numItems];
        amounts = new double[numItems];
        AdditionalDiscount= new double[2];
        System.out.println();

        for (int i = 0; i < numItems; i++) {
            System.out.print("Enter the name of the " + numSuffix(i + 1) + " product: ");
            String nameProduct = sc.next();
            names[i] = nameProduct;
            System.out.print("Enter the per package price of " + nameProduct + ": ");
            double pricePerPackage = sc.nextDouble();
            prices[i] = pricePerPackage;
            System.out.print("Enter the number of packages ('x') to qualify for Special Discount (buy 'x' get 1 free)\n" + "for " + nameProduct + ", or 0 if no Special Discount offered: ");
            int SpecialDiscounts = sc.nextInt();
            while (SpecialDiscounts < 0){
                System.out.print("Invalid input. Enter a value >= 0: ");
                SpecialDiscounts= sc.nextInt();
            }
            NumberDiscount[i] = SpecialDiscounts;
        }
        System.out.print("\nEnter the dollar amount to qualify for Additional Discount (or 0 if none offered): ");
        double additionalDiscount = sc.nextDouble();
        if (additionalDiscount > 0) {
            AdditionalDiscount[0] = additionalDiscount;
            System.out.print("Enter the Additional Discount rate (e.g., 0.1 for 10%): ");
            double additionalDiscountRate = sc.nextDouble();
            while (additionalDiscountRate <= 0 || additionalDiscountRate > 0.5) {
                System.out.print("Invalid input. Enter a value > 0 and  <= 0.5: ");
                additionalDiscountRate = sc.nextDouble();
            }
            AdditionalDiscount[1] = additionalDiscountRate;
        }
        System.out.println();
    }

    public static void BuyFunction(){
        System.out.println();
        for (int i = 0; i < names.length; i++){
            System.out.print("Enter the number of " + names[i] + " packages to buy: ");
            double numPackages = sc.nextInt();
            while (numPackages < 0){
                System.out.print("Invalid input. Enter a value >= 0: ");
                numPackages = sc.nextInt();
            }
            amounts[i] = numPackages;
        }
        System.out.println();
    }
    public static void ListItemsFunction(){
        double subtotal = 0;
        for(int i = 0; i < names.length; i++) {
            if (amounts[i] > 0 && amounts[i]!= 1) {
                System.out.printf("%.1f packages of %s @ $%.2f = $%.2f\n", amounts[i], names[i], prices[i], prices[i] * amounts[i]);
            }
            else if(amounts[i]==1) {
                System.out.printf("%.1f package of %s @ $%.2f = $%.2f\n", amounts[i], names[i], prices[i], prices[i] * amounts[i]);
            }
        }
        for(int i = 0; i < names.length; i++) {
            subtotal+=prices[i]*amounts[i];
        }
        if (subtotal == 0){
            System.out.println("No items were purchased.");
        }
    }

    public static void CheckOutFunction(){
        double subtotal = 0;
        double amountOfDiscount = 0;
        discountAmount = new double[names.length];
        for(int i = 0; i < names.length; i++) {
            subtotal+=prices[i]*amounts[i];
        }
        System.out.printf("\nOriginal Sub Total:               $%.2f", subtotal);
        for (int i = 0; i <discountAmount.length; i++){
            if (amounts[i] > 0.0 && NumberDiscount[i] != 0.0) {
                int freePackages = (int) (amounts[i] / (NumberDiscount[i] + 1));
                if (amounts[i] > 0.0) {
                    discountAmount[i] = (prices[i]) * (freePackages);
                }
            }
            amountOfDiscount+=discountAmount[i];
        }
        if (amountOfDiscount == 0){
            System.out.print("\nNo Special Discounts applied");
        }
        else {
            System.out.printf("\nSpecial Discounts:               -$%.2f", amountOfDiscount);
        }
        double newSubtotal = subtotal-amountOfDiscount;
        System.out.printf("\nNew Sub Total:                    $%.2f", newSubtotal);
        double AdditionalDiscountDeduction = 0.0;
        if ((newSubtotal > AdditionalDiscount[0]) && AdditionalDiscount[1] != 0){
            AdditionalDiscountDeduction += newSubtotal*(AdditionalDiscount[1]);
            System.out.printf("\nAdditional %.0f%% Discount:         -$%.2f", (AdditionalDiscount[1]*100), AdditionalDiscountDeduction);
        }
        else {
            System.out.print("\nYou did not qualify for an Additional Discount");
        }
        System.out.printf("\nFinal Sub Total:                  $%.2f\n", newSubtotal-AdditionalDiscountDeduction);
        System.out.println("\nThanks for coming!");
    }

    public static void main(String[] args) {
        boolean runAgain = true;
        while (runAgain == true) {
            intro();
            checkProcess();
            System.out.println("\n-------------------------------------------------");
            System.out.print("Would you like to re-run (1 for yes, 0 for no)? ");
            int response = sc.nextInt();
            System.out.print("-------------------------------------------------");
            if (response==0){
                runAgain = false;
            }
            else {
                System.out.println();
            }
        }
        sc.close();
    }
}