import java.util.*;
public class CheeseShop {
    public static double HumboldtFog;
    public static  double RedHawk;
    public static double Teleme;
    public static double HumboldtFogCost = 25.0;
    public static  double RedHawkCost = 40.5;
    public static double TelemeCost = 17.25;
    public static double subtotal;

    public static void amount (){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the amount of Humboldt Fog in lbs: ");
        double amnt = sc.nextDouble();
        while(amnt < 0.0 || amnt % 0.5 != 0) {
            if (amnt < 0.0) {
                System.out.print("Invalid input. Enter a value >= 0: ");
                amnt = sc.nextDouble();
            }
            if (amnt % 0.5 != 0) {
                System.out.print("Invalid input. Enter a value that's multiple of 0.5: ");
                amnt = sc.nextDouble();
            }
        }
        HumboldtFog = amnt;

        System.out.print("Enter the amount of Red Hawk in lbs: ");
        double amnt2 = sc.nextDouble();
        while(amnt2 < 0.0 || amnt2 % 0.5 != 0) {
            if (amnt2 < 0.0) {
                System.out.print("Invalid input. Enter a value >= 0: ");
                amnt2 = sc.nextDouble();
            }
            if (amnt2 % 0.5 != 0) {
                System.out.print("Invalid input. Enter a value that's multiple of 0.5: ");
                amnt2 = sc.nextDouble();
            }
        }
        RedHawk = amnt2;

        System.out.print("Enter the amount of Teleme in lbs: ");
        double amnt3 = sc.nextDouble();
        while(amnt3 < 0.0 || amnt3 % 0.5 != 0) {
            if (amnt3 < 0.0) {
                System.out.print("Invalid input. Enter a value >= 0: ");
                amnt3 = sc.nextDouble();
            }
            if (amnt3 % 0.5 != 0) {
                System.out.print("Invalid input. Enter a value that's multiple of 0.5: ");
                amnt3 = sc.nextDouble();
            }
        }
        Teleme = amnt3;
    }
    public static double itemizedList(){
        double totalHum = HumboldtFog * HumboldtFogCost;
        double totalRed = RedHawk * RedHawkCost;
        double totalTel = Teleme * TelemeCost;
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.print("Display the itemized list? (1 for yes) ");
        int num = sc.nextInt();
        subtotal = totalHum + totalRed + totalTel;
        if (subtotal == 0.0){
            System.out.println("No items were purchased.");
            System.out.println();
            System.out.println("Sub total:                        $0.0");
            return 0.0;
        }
        if (num == 1){
            if (HumboldtFog > 0.0){
                System.out.println(HumboldtFog +" lb of Humboldt Fog @ $25.0 = $" + totalHum);

            }
            if (RedHawk > 0.0){
                System.out.println(RedHawk +" lb of Red Hawk @ $45.0 = $" + totalRed);

            }
            if (Teleme > 0.0){
                System.out.println(Teleme +" lb of Teleme @ $17.25 = $" + totalTel);

            }
            System.out.println();
        }
        System.out.println();
        System.out.println("Sub total:                        $" + subtotal);
        return subtotal;


    }
    public static double Discounts(){
        double totalDiscount;
        double HumDiscount = 0;
        double RedDiscount = 0;
        int freePackages_Hum = (int)((HumboldtFog/0.5) / 2.0);
        int freePackages_Red = (int)((RedHawk/0.5) / 3.0);
        System.out.println("Discounts...");
        if (HumboldtFog > 0.0 && ((HumboldtFog / 0.5) % 2.0 >= 0.0) && (freePackages_Hum > 0)){
            HumDiscount = 12.5 * (freePackages_Hum);
            System.out.println("Humboldt Fog (Buy 1 Get 1 Free): -$" + HumDiscount);
        }
        if (RedHawk > 0.0 && ((RedHawk / 0.5) % 3.0 >= 0.0 ) && (freePackages_Red > 0) ){
            RedDiscount = 20.25 * (freePackages_Red);
            System.out.println("Red Hawk (Buy 2 Get 1 Free):     -$" + RedDiscount);
        }

        else if ((HumboldtFog <= 0.0 || ((HumboldtFog/0.5) % 2.0 != 0.0) )&&(RedHawk <= 0.0 || ((RedHawk / 0.5) % 3.0 != 0.0 ) )){
            System.out.println("None                             -$0.0");
        }
        totalDiscount = HumDiscount + RedDiscount;
        return totalDiscount;
    }


    public static void main(String[] args) {
        System.out.println("We sell 3 kinds of Cheese (in 0.5 lb packages): ");
        System.out.println("Humboldt Fog: $25.0 per pound\n" + "Red Hawk: $40.5 per pound\n" + "Teleme: $17.25 per pound");
        System.out.println();
        amount();
        itemizedList();
        System.out.print("Final Total:                      $" +(subtotal-Discounts()));

    }
}