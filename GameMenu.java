import java.util.Scanner;
import java.util.Random;
public class GameMenu 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

        boolean playAgain = true;
        while (playAgain) 
        {
            System.out.println("\nWelcome to the Game Menu!");
            System.out.println("Choose a game to play:");
            System.out.println("1. Guessing Game");
            System.out.println("2. Rock, Paper, Scissors");
            System.out.println("3. Tic Tac Toe");
            System.out.println("4. Quit");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    playGuessingGame(scanner);
                    break;
                case 2:
                    playRockPaperScissors(scanner);
                    break;
                case 3:
                    playTicTacToe(scanner);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    playAgain = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                    break;
            }
        }
        scanner.close();
    }

    public static void playGuessingGame(Scanner scanner) {
        System.out.println("Welcome to the Guessing Game! I have a number in mind. Can you guess the number?");
        int randomNumber = (int) (Math.random() * 100) + 1;
        int guess;
        int attempts = 0;
        do {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            scanner.nextLine(); 
            attempts++;
            if (guess < randomNumber) {
                System.out.println("Too low! Try again.");
            } else if (guess > randomNumber) {
                System.out.println("Too high! Try again.");
            }
        } while (guess != randomNumber);
        System.out.println("Congratulations! You guessed it right in " + attempts + " attempts!");
        if (attempts <= 5) {
            System.out.println("Wow, you're a mind reader!");
        } else if (attempts <= 10) {
            System.out.println("Not bad, but I've seen better.");
        } else {
            System.out.println("It took you quite a few attempts... Better luck next time!");
        }
    }

    public static void playRockPaperScissors(Scanner scanner) {
        Random random = new Random();
        int playerScore = 0;
        int computerScore = 0;
        int totalRounds = 0;
        boolean playAgain = true;
        System.out.println("Welcome to Rock, Paper, Scissors game!");
        while (playAgain) {
            totalRounds++;
            System.out.println("\nRound " + totalRounds);
            System.out.println("Enter your choice (rock, paper, or scissors): ");
            String playerChoice = scanner.nextLine().toLowerCase();
            while (!isValidChoice(playerChoice)) {
                System.out.println("Invalid input! Please enter rock, paper, or scissors: ");
                playerChoice = scanner.nextLine().toLowerCase();
            }
            int computerChoice = random.nextInt(3);
            String[] choices = {"rock", "paper", "scissors"};
            String computerChoiceStr = choices[computerChoice];
            System.out.println("Computer chooses: " + computerChoiceStr);
            if (playerChoice.equals(computerChoiceStr)) {
                System.out.println("It's a tie!");
            } else if ((playerChoice.equals("rock") && computerChoiceStr.equals("scissors")) ||
                    (playerChoice.equals("paper") && computerChoiceStr.equals("rock")) ||
                    (playerChoice.equals("scissors") && computerChoiceStr.equals("paper"))) {
                System.out.println("You win!");
                playerScore++;
            } else {
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
        if (playerScore > computerScore) {
            System.out.println("Congratulations! You are the overall winner!");
        } else if (playerScore < computerScore) {
            System.out.println("Computer wins! Better luck next time.");
        } else {
            System.out.println("It's a tie overall!");
        }
        System.out.println("Thanks for playing Rock, Paper, Scissors!");
    }

    public static void playTicTacToe(Scanner scanner) {
        TicTacToe game = new TicTacToe();

        while (!game.isBoardFull() && !game.checkForWin()) {
            game.printBoard();
            System.out.println("Player " + game.currentPlayer + ", enter your move (row[0-2] col[0-2]): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            game.makeMove(row, col);
        }
        game.printBoard();
        if (game.checkForWin()) {
            System.out.println("Player " + game.currentPlayer + " wins!");
        } else {
            System.out.println("It's a draw!");
        }
        System.out.println("Thanks for playing Tic Tac Toe!");
    }

    public static boolean isValidChoice(String choice) {
        return choice.equals("rock") || choice.equals("paper") || choice.equals("scissors");
    }
}

class TicTacToe {
    private char[][] board;
    public char currentPlayer;

    public TicTacToe() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    public boolean isBoardFull() {
        boolean isFull = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    isFull = false;
                }
            }
        }
        return isFull;
    }

    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        return (checkRowCol(board[0][0], board[1][1], board[2][2]) ||
                checkRowCol(board[0][2], board[1][1], board[2][0]));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '-') && (c1 == c2) && (c2 == c3));
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void makeMove(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            changePlayer();
        } else {
            System.out.println("Invalid move! Try again.");
        }
    }
}
