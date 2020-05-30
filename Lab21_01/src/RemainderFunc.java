import java.util.*;
public class RemainderFunc {
    public static int maxnum;
    public static int divisor;

    public static void input(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the max number:");
        int num = sc.nextInt();
        while(num < 0) {
            if (num < 0) {
                System.out.println("Invalid input. Please enter a valid max number (>= 0):");
                num = sc.nextInt();
            }
        }
        System.out.println("Please enter the divisor");
        int div = sc.nextInt();
        while(div <=0) {
            if (divisor <= 0) {
                System.out.println("Invalid input. Please enter a valid divisor (> 0):");
                div = sc.nextInt();
            }
        }
        maxnum = num;
        divisor = div;
        }
    public static void multiples (int m, int n) {
        System.out.println("Multiples of " + n + " between 1 and " + m + " (inclusive) are: ");
        int count = 0;
        for (int i = 1; i <= m ; i++) {
            if (i % n == 0) {
                System.out.println(i);
            }
        }
        if (count == 0){
            System.out.println("No number were found.");
        }
    }
    public static void main(String[] args) {
         input();
         multiples(maxnum, divisor);
    }
}
