import java.util.Random;

public class Numbercube {
    private int[] Tosses;

    public static void main(String[] args) {
        Numbercube cube = new Numbercube(10);
    }

    Numbercube(int numTosses) {
        Tosses = new int[numTosses];
        for (int i = 0; i < numTosses; i++) {
            Tosses[i] = Toss();
        }
        for (int i : Tosses) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private int Toss() {
//        return (int) (Math.random() * 6) + 1;
        return (int) (Math.random()*(6 - 1) + 1);
    }

//    private int[] getLongestRun() {
//        int startIndex = -1;
//        int endIndex = -1;
//        int maxCount = 0;
//        int currentCount = 0;
//        for (int i = 0; i < Tosses.length; i++) {
//        }
//    }
}
