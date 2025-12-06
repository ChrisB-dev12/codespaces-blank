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
    static String[] playerNames = new String[Max_Players];
    static int[] gamesPlayed = new int[Max_Players];
    static int[] passingYards = new int[Max_Players];
    static int[] touchdowns = new int[Max_Players];
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
                    enterGameStats(input);
                    break;
                case 3:
                    displayLeaderboard();
                    break;
                case 4:
                    displayAverages();
                    break;
                case 5:
                    simulateRandomGame(input); // freature using Random
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
        while (!input.hasNextInt()) {
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

            playerNames[playerCount] = name;
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
            int ints = readNonNegativeInt(input);

            // Update totals 
            gamesPlayed[index]++;
            passingYards[index] += yards;
            touchdowns[index] += comps;
            interceptions[index] += ints;

            System.out.println("Stats updated for " + playerNames[index] + ".");
        }

        // Read a non-negative intgers. 
        public static int readNonNegativeInt(Scanner input) {
            int value;
            while (true) {
                while (!input.hasNextInt()) {
                    System.out.print("Please enter a whole number: ");
                    input.next();
                }
                value = input.nextInt();
                if (value < 0) {
                    System.out.print("Please enter a non-negative number: ");
                } else {
                    break;
                }
            }
            return value;
        }

        // Find player index by name
        public static int findPlayerIndex(String name) {
            for (int i = 0; i < playerCount; i++) {
                if (playerNames[i].equalsIgnoreCase(name)) {
                    return i;
                }
            }
            return -1;
        }

        // Output: display leaderboard sorted by rating
        public static void displayLeaderboard() {
            if (playerCount == 0) {
                System.out.println("No player to display.");
                return;
            }

            // make an array of indices for sorting
            int[] indices = new int[playerCount];
            for (int i = 0; i < playerCount; i++) {
                indices[i] = i;
            }

            // bubble sort by rating
            for (int i = 0; i < playerCount - 1; i++) {
                for (int j = 0; j < playerCount - 1 -i; j++) {
                    double ratingJ = getRating(indices[j]);
                    double ratingNext = getRating(indices[j + 1]);
                    if (ratingJ < ratingNext) {
                        int temp = indices[j];
                        indices[j] = indices[j + 1];
                        indices[j + 1] = temp;
                    }
                }
            }

            System.out.println();
            System.out.println("===== Leader (by Rating) =====");
            System.out.printf("%-20s %10s %10s %10s %10s %10s\n", 
                    "Player", "Yards", "TDs", "Comps", "INTs", "Rating");
            
            for (int rank = 0; rank < playerCount; rank++) {
                int i = indices[rank];
                double rating = getRating(i);
                System.out.printf("%-20s %10d %10d %10d %10d %10.2f\n",
                    playerNames[i],
                    passingYards[i],
                    touchdowns[i],
                    completions[i],
                    interceptions[i], 
                    rating);
            }

        }

        // Output: display averages per game
        public static void displayAverages() {
            if (playerCount == 0) {
                System.out.println("No player to display.");
                return;
            }

            System.out.println();
            System.out.println("===== Averages per game =====");
            System.out.printf("%-20s %10s %10s %10s\n",
                "player", "Yds/Game", "TD/Game", "Comp/Game");
            
            for (int i = 0; i < playerCount; i++) {
                if (gamesPlayed[i] == 0) {
                    System.out.printf("%-20s %10s %10s %10s\n",
                        playerNames[i], "N/A", "N/A", "N/A");
                } else {
                    double ydsPerGame = getYardsPerGame(i);
                    double tdPerGame = getTDsPerGame(i);
                    double comPerGame = getCompsPerGame(i);

                    System.out.printf("%-20s %10.2f %10.2f %10.2f\n",
                        playerNames[i], ydsPerGame, tdPerGame, comPerGame);
                }
            }
        }

        // Methods for averages and rating

        public static double getYardsPerGame(int index) {
            if (gamesPlayed[index] == 0) {
                return 0.0;
            }
            return (double) passingYards[index] / gamesPlayed[index];
        }

        public static double getTDsPerGame(int index) {
            if (gamesPlayed[index] == 0) {
                return 0.0;
            }
            return (double) touchdowns[index] / gamesPlayed[index];
        }

        public static double getCompsPerGame(int index) {
            if (gamesPlayed[index] == 0) {
                return 0.0;
            }
            return (double) completions[index] / gamesPlayed[index];
        }

        // Simple rating formula using stats
        public static double getRating(int index) {
            double rating = 0.0;
            rating += passingYards[index] * 0.05;
            rating += touchdowns[index] * 10;
            rating += interceptions[index] * 5;
            return rating;
        }

        // Simulate a random game for players
        public static void simulateRandomGame(Scanner input) {
            if (playerCount == 0) {
                System.out.println("No player yets Please add player first.");
                return;
            }

            input.nextLine();
            System.out.print("Enter player name to simulate a game: ");
            String name = input.nextLine();

            int index = findPlayerIndex(name);
            if (index == -1) {
                System.out.println("Player not found. ");
                return;
            }

            int yards = rand.nextInt(401); // 0-400
            int tds = rand.nextInt(6); // 0-5
            int comps = rand.nextInt(41); // 0-40
            int ints = rand.nextInt(4); // 0-3

            gamesPlayed[index]++;
            passingYards[index] += yards;
            touchdowns[index] += tds;
            completions[index] += comps;
            interceptions[index] += ints;

            System.out.println("Random game simulated for " + playerNames[index] + ":");
            System.out.println("Yards: " + yards + ", TDs: " + tds + 
                ", Completions: " + comps + ", INTs: " + ints);
        }
}
