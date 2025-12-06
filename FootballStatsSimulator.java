/**
 * Name: Christopher bostian
 * Date: December 6, 2025
 * 
 * Purpose:This program tracks and analyzes football player stats using arrays and methods.
 * The user can add players, enter game stats, view a leaderboards, and see 
 * averages per game.
 * 
 */

import java.util.Scanner;
import java.util.Random;

public class FootballStatsSimulator {

    // Constants
    static final int Max_Players = 20;
    
    // Arrays for players and stats
    static string[] playerNames = new String[max_Players];
    static int[] gamesPlayed = new int[Max_Players];
    static int[] passingYards = new int[Max_Players];
    static int[] touchdown = new int[Max_Players];
    static int[] completions = new int[Max_Players]; 
    static int[] interceptions = new int[Max_Players];

    // Number of players currently stored
    static int playerCount = 0;

    // Random object for optional simulated game
    static Random rand = new Random();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int choice;
        
        do {
            printMenu();
            choice = getChoice(input);

            switch (choice) {
                case 1:
                    addPlayers(input);
                    break;
                case 2:
                    enterGamesStats(input);
                    break;
                case 3:
                    displayLeaderboard();
                    break;
                case 4:
                    displayAverages();
                    break;
                case 5:
                    simulateRandomGame(imput); // freature using Random
                    break;
                case 6:
                    System.out.println("Exiting Fottball Stats Simulator, Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");                        
            }
        } while (choice !=6);

        input.close();
    }

    // Menu mathods

    public static void printMenu() {
        System.out.println();
        System.out.println("===== Football Stats Simulator =====");
        System.out.println("1. Add players");
        System.out.println("2. Enter stats for a game");
        System.out.println("3. Display leaderboard (sorted by rating)");
        System.out.println("4. Display averages per game");
        System.out.println("5. Simulate a random game for a player");
        System.out.println("6. Exit");
        System.out.println("Enter your choice: ");
    }
    
    public static int getChoice(Scanner input) {
        while (!input.hasNextInt()) {
            System.out.print("Please enter a number: ");
            input.next(); // clear invalid input
        }
        int c = input.nextInt();
        return c;
    }

    // Core functional methods

    // 1) Input: Add players
    public static void addPlayers(Scanner input) {
        System.out.print("How many players do you want to add? ");
        while (!input.hasnextInt()) {
            System.out.print("Please enter a number: ");
            input.next();
        }
        int toAdd = input.nextInt();
        input.nextLine();

        for (int i = 0; i < toAdd; i++) {
            if (playerCount == Max_Players) {
                System.out.println("Player list is full. Cannot add more player.");
                break;
            }

            System.out.print("Enter name for player " + (playerCount + 1) + ": ");
            String name = input.nextLine();

            playernames[playerCount] = name;
            gamesPlayed[playerCount] = 0;
            passingYards[playerCount] = 0;
            touchdowns[playerCount] = 0;
            completions[playerCount] = 0;
            interceptions[playerCount] = 0;

            playerCount++;
        }
        System.out.println("Current number of players: " + playerCount);
    }

        // Input: Enter stats for a game for one player
        public static void enterGameStats(Scanner input) {
            if (playerCount == 0) {
                System.out.println("No player yet. Please add player first.");
                return;
            }

            input.nextLine();
            System.out.print("Enter player name to update stat: ");
            String name = input.nextLine();

            int index = findPlayerIndex(name);
            if (index == -1) {
                System.out.println("Player not found.");
                return;
            }

            System.out.print("Passing yards this game: ");
            int yards = readNonNegativeInt(input);

            System.out.print("Touchdowns this game: ");
            int tds =  readNonNegativeInt(input);

            System.out.print("completions this game: ");
            int comps = readNonNegativeInt(input);

            System.out.print("interceptions this game: ");
            int ints = readNonNegativeInt[input];

            // Update totals 
            gamesPlayed[index]++;
            passingYards[index] += yards;
            touchdowns[index] += comps;
            interceptions[index] += ints;

            System.out.println("Stats updated for " + playerNames[index] + ".");
        }

        // Read a non-negative intgers. 


}
