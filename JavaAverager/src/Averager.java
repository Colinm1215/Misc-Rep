import java.util.ArrayList;
import java.util.Scanner;


public class Averager {
    public static void main(String[] args) {
        getAverages ave = new getAverages();
        ave.main();
    }
}

class getAverages {
    private int howMany = 0;
    private double total = 0.0;
    private ArrayList<Double> numbers = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    void main() {
        this.howMany = getHowMany();
        getValues();
        for (double i : numbers) {
            total += i;
        }
        double ave = getAverage(total, howMany);
        System.out.println();
        System.out.println("Numbers :");
        for (double i : numbers) {
            System.out.println(i);
        }
        System.out.println("Average:");
        System.out.println(ave);

    }

    private int getHowMany() {
        System.out.println("How many numbers?");
        return input.nextInt();
    }

    private void getValues() {
        for (int i = 0; i < howMany; i++) {
            System.out.println();
            System.out.println("Enter a Number:");
            numbers.add(input.nextDouble());
        }
    }

    private double getAverage(double total, int number) {
        return total / (double) number;
    }
}