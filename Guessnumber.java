import java.util.Scanner;
public class Guessnumber 
{
    public static void main(String[] args) 
    {
        System.out.println("Welcome to the Guessing Game! I have a number in mind. Can you guess the number?");
        int randomNumber = (int) (Math.random() * 100) + 1;
        Scanner scanner = new Scanner(System.in);
        int guess;
        int attempts = 0;
        do {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;
            if (guess < randomNumber) 
            {
                System.out.println("Too low! Try again.");
            } 
            else if (guess > randomNumber) 
            {
                System.out.println("Too high! Try again.");
            }
        } 
        while (guess != randomNumber);
        System.out.println("Congratulations! You guessed it right in " + attempts + " attempts!");
        if (attempts <= 5) 
        {
            System.out.println("Wow, you're a mind reader!");
        }
        else if (attempts <= 10) 
        {
            System.out.println("Not bad, but I've seen better.");
        } 
        else 
        {
            System.out.println("It took you quite a few attempts... Better luck next time!");
        }
    }
}