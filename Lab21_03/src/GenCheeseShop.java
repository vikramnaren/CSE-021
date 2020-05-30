import java.util.*;
public class GenCheeseShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of Cheeses for shop setup: ");
        int numCheese = sc.nextInt();
        System.out.println();
        final int MAXCHEESE = numCheese;

        String[] names = new String[MAXCHEESE];
        double[] prices = new double[MAXCHEESE];
        double[] amounts = new double[MAXCHEESE];

        // Three Special Cheeses
        if (0 < MAXCHEESE && MAXCHEESE < 27) {
            names[0] = "Humboldt Fog";
            prices[0] = 25.00;

            if (1 < MAXCHEESE) {
                names[1] = "Red Hawk";
                prices[1] = 40.50;
            }

            if (2 < MAXCHEESE) {
                names[2] = "Teleme";
                prices[2] = 17.25;
            }

            System.out.println("We sell " + MAXCHEESE + " kinds of Cheese (in 0.5 lb packages)");
            if (MAXCHEESE > 0) {
                System.out.println(names[0] + ": $" + prices[0] + " per pound");
                if (MAXCHEESE > 1) {
                    System.out.println(names[1] + ": $" + prices[1] + " per pound");
                    if (MAXCHEESE > 2) {
                        System.out.println(names[2] + ": $" + prices[2] + " per pound");
                    }
                }
            }
            Random ranGen = new Random(100);

            for (int i = 3; i < MAXCHEESE; i++) {
                names[i] = "Cheese Type " + (char) ('A' + i);
                prices[i] = ranGen.nextInt(1000) / 100.0;
                amounts[i] = 0;

                System.out.println(names[i] + ": $" + prices[i] + " per pound");

            }
        }
        else {
            System.out.println("We sell 0 kinds of Cheese (in 0.5 lb packages)");
        }
        if (MAXCHEESE > 0) {
            System.out.println();
            System.out.print("Enter the amount of " + names[0] + " in lb: ");
            double numHum = sc.nextDouble();
            while (numHum < 0.0 || numHum % 0.5 != 0) {
                if (numHum < 0.0) {
                    System.out.print("Invalid input. Enter a value >= 0: ");
                    numHum = sc.nextDouble();
                }
                if (numHum % 0.5 != 0) {
                    System.out.print("Invalid input. Enter a value that's multiple of 0.5: ");
                    numHum = sc.nextDouble();
                }
            }
            amounts[0] = numHum;
            if (MAXCHEESE > 1) {
                System.out.print("Enter the amount of " + names[1] + " in lb: ");
                double numRed = sc.nextDouble();
                while (numRed < 0.0 || numRed % 0.5 != 0) {
                    if (numRed < 0.0) {
                        System.out.print("Invalid input. Enter a value >= 0: ");
                        numRed = sc.nextDouble();
                    }
                    if (numRed % 0.5 != 0) {
                        System.out.print("Invalid input. Enter a value that's multiple of 0.5: ");
                        numRed = sc.nextDouble();
                    }
                }
                amounts[1] = numRed;

                if (MAXCHEESE > 2) {
                    System.out.print("Enter the amount of " + names[2] + " in lb: ");
                    double Tel = sc.nextDouble();
                    while (Tel < 0.0 || Tel % 0.5 != 0) {
                        if (Tel < 0.0) {
                            System.out.print("Invalid input. Enter a value >= 0: ");
                            Tel = sc.nextDouble();
                        }
                        if (Tel % 0.5 != 0) {
                            System.out.print("Invalid input. Enter a value that's multiple of 0.5: ");
                            Tel = sc.nextDouble();
                        }
                    }
                    amounts[2] = Tel;
                }
            }
            for (int i = 3; i < MAXCHEESE; i++) {
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
        double subtotal = 0;

        for (int i = 0; i < MAXCHEESE; i++) {
            subtotal += (prices[i] * amounts[i]);
        }
        if (MAXCHEESE > 0) {
            System.out.println();
            System.out.print("Display the itemized list? (1 for yes) ");
            int DisplayItem = sc.nextInt();
            if(DisplayItem == 1) {
                for (int i = 0; i < MAXCHEESE; i++) {
                    if (amounts[i] != 0) {
                        System.out.printf("%.1f lb of %s @ $%.2f = $%.2f\n", amounts[i], names[i], prices[i], prices[i] * amounts[i]);
                    }
                }

                if (subtotal == 0){
                    System.out.print("No items were purchased.\n");
                }
                System.out.println();
            }
            else {
                System.out.println();
            }
        }

        System.out.printf("Orginal Sub Total:                $%.2f", subtotal);
        System.out.println("\nSpecials...");
        if (subtotal == 0) {
            System.out.println("None                             -$0.0");
        }

        double totalDiscount;
        double HumDiscount = 0;
        double RedDiscount  = 0;
        if (MAXCHEESE != 0) {
            if (MAXCHEESE >= 1) {
                int freePackages_Hum = (int) ((amounts[0] / 0.5) / 2.0);
                if (amounts[0] > 0.0 && ((amounts[0] / 0.5) % 2.0 >= 0.0) && (freePackages_Hum > 0)) {
                    HumDiscount = 12.5 * (freePackages_Hum);
                    System.out.println("Humboldt Fog (Buy 1 Get 1 Free): -$" + HumDiscount);
                }
            }
            if (MAXCHEESE >=2){
                int freePackages_Red = (int) ((amounts[1] / 0.5) / 3.0);
                if (amounts[1] > 0.0 && ((amounts[1] / 0.5) % 3.0 >= 0.0) && (freePackages_Red > 0)) {
                    RedDiscount = 20.25 * (freePackages_Red);
                    System.out.println("Red Hawk (Buy 2 Get 1 Free):     -$" + RedDiscount);
                }
            }
            else {
                System.out.println("None                              -$0.0");
            }
        }
        totalDiscount = HumDiscount + RedDiscount;
        subtotal -=  totalDiscount;

        if (MAXCHEESE == 0) {
            System.out.print("New Sub Total:                    $0.0");
        }
        else{
            System.out.printf("New Sub Total:                    $%.2f", subtotal);

        }
        double totalDiscount2 = 0;
        if (subtotal > 250) {
            totalDiscount2 += (subtotal * 0.25);
            System.out.printf("\nAdditional 25%% Discount:         -$%.2f", totalDiscount2);
        }
        else if (subtotal > 150){
            totalDiscount2 += (subtotal * 0.10);

            System.out.printf("\nAdditional 10%% Discount:        -$%.2f", totalDiscount2);
        }
        else {
            System.out.print("\nAdditional 0% Discount:          -$0.0");
        }
        System.out.printf("\nFinal Total:                      $%.2f", (subtotal - totalDiscount2));
    }
}