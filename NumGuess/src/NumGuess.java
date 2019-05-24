import java.util.Scanner;

public class NumGuess {
    private int goal;
    private int guess;
    private Scanner input = new Scanner(System.in);
    private NumGuess (int min, int max, int guessNum) {
        goal = (int) (Math.random() * 100) + 1;
        while (goal > max || goal < min) {
            goal = (int) (Math.random() * 100) + 1;
        }
        System.out.println();
        System.out.println("I have my number!");
        while (true) {
            guessNum -= 1;
            System.out.println();
            System.out.print("Your Guess : ");
            guess = input.nextInt();
            if (guess == goal) {
                System.out.println();
                System.out.println("You got the number!");
                break;
            }
            else {
                    if (guess > goal) {
                        System.out.println();
                        System.out.println("Nope!");
                        System.out.println("Too High!");
                    } else {
                        System.out.println();
                        System.out.println("Nope!");
                        System.out.println("Too Low!");
                    }
                if (guessNum > 0) {
                    System.out.println("Guess Again!");
                }
                else if (guessNum <= 0) {
                    System.out.println("Out of Guesses!");
                    break;
                }
            }
        }
        System.out.println();
        System.out.print("Play Again? : ");
        if (input.next().toLowerCase().contains("y")) {
            String x[]={"A","B"};
            main(x);
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to my number guessing game!");
        System.out.println("What shall be the number range?");
        System.out.println();
        System.out.print("Min : ");
        int min = scan.nextInt();
        System.out.println();
        System.out.print("Max : ");
        int max = scan.nextInt();
        System.out.println(String.format("You must guess a number between %d and %d!", min, max));
        System.out.println();
        System.out.print("Number of Guesses : ");
        int guessNum = scan.nextInt();
        System.out.println("Let's Start!");
        NumGuess guessMyNum = new NumGuess(min, max, guessNum);
    }
}
