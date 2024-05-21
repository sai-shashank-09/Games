import java.util.Scanner;
import java.util.Random;
public class RPS 
{
   public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int playerScore = 0;
        int computerScore = 0;
        int totalRounds = 0;
        boolean playAgain = true;
        System.out.println("Welcome to Rock, Paper, Scissors game!");
        while (playAgain) 
        {
            totalRounds++;
            System.out.println("\nRound " + totalRounds);
            System.out.println("Enter your choice (rock, paper, or scissors): ");
            String playerChoice = scanner.nextLine().toLowerCase();
            while (!isValidChoice(playerChoice)) 
            {
                System.out.println("Invalid input! Please enter rock, paper, or scissors: ");
                playerChoice = scanner.nextLine().toLowerCase();
            }
            int computerChoice = random.nextInt(3);
            String[] choices = {"rock", "paper", "scissors"};
            String computerChoiceStr = choices[computerChoice];
            System.out.println("Computer chooses: " + computerChoiceStr);
            if (playerChoice.equals(computerChoiceStr)) 
            {
                System.out.println("It's a tie!");
            } 
            else if ((playerChoice.equals("rock") && computerChoiceStr.equals("scissors")) ||
                    (playerChoice.equals("paper") && computerChoiceStr.equals("rock")) ||
                    (playerChoice.equals("scissors") && computerChoiceStr.equals("paper"))) 
                    {
                System.out.println("You win!");
                playerScore++;
            } 
            else 
            {
                System.out.println("Computer wins!");
                computerScore++;
            }
            System.out.println("Player Score: " + playerScore);
            System.out.println("Computer Score: " + computerScore);
            System.out.println("Do you want to play again? (yes/no): ");
            String playAgainChoice = scanner.nextLine().toLowerCase();
            playAgain = playAgainChoice.equals("yes");
        }
        System.out.println("\nTotal Rounds Played: " + totalRounds);
        System.out.println("Player Total Score: " + playerScore);
        System.out.println("Computer Total Score: " + computerScore);
        if (playerScore > computerScore) 
        {
            System.out.println("Congratulations! You are the overall winner!");
        } 
        else if (playerScore < computerScore) 
        {
            System.out.println("Computer wins! Better luck next time.");
        } 
        else 
        {
            System.out.println("It's a tie overall!");
        }
        System.out.println("Thanks for playing!");
        scanner.close();
    }
    public static boolean isValidChoice(String choice) 
    {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissors");
    }
}