import java.util.ArrayList;
import java.util.Scanner;

public class Game {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck(6);
        deck.shuffle();


        Player dealer = new Player();
        Player player = new Player();


        while (true) {

            if (deck.numCards < 52) {
                deck = new Deck(6);
                deck.shuffle();
                System.out.println("deck low on cards, reshuffling");
            }

            round(player, dealer, deck, scanner);
            System.out.println("Would you like to play again? (y/n)");
            String playAgain = scanner.nextLine();

            if (playAgain.equals("n")) {
                break;
            }

            player.hand.clear();
            dealer.hand.clear();
        }

    }

    private static void round(Player player, Player dealer, Deck deck, Scanner scanner) {
        dealCard(player, dealer, deck);

        System.out.println("Player's hand:");
        player.printHand();

        System.out.println("\nDealer's hand:");
        dealer.printHand();
        dealer.calculateHandTotal();

        player.calculateHandTotal();
        System.out.println("\nWould you like to hit? (y/n)");
        String hit = scanner.nextLine();

        // Player's turn
        playersTurn(player, deck, scanner, hit, dealer);

        // skip if bust

        if (!player.bust) {
            dealersTurn(dealer, deck, player);
        }

        determineWinner(dealer, player, deck);
    }

    private static void determineWinner(Player dealer, Player player, Deck deck) {
        // i love if statements
        if (player.bust) {
            System.out.println("Dealer wins!");
        } else if (dealer.bust) {
            System.out.println("Player wins!");
        } else if (player.handTotal > dealer.handTotal) {
            System.out.println("Player wins!");
        } else if (dealer.handTotal > player.handTotal) {
            System.out.println("Dealer wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }

    private static void dealersTurn(Player dealer, Deck deck, Player player) {
        while (dealer.handTotal < 17) {
            dealer.hand.get(1).reveal();
            dealer.hit(deck);
            printGameState(player, dealer);

            if (dealer.handTotal > 21) {
                System.out.println("\nDealer busts!");
                dealer.bust = true;
                break;
            }
        }
    }

    private static void playersTurn(Player player, Deck deck, Scanner scanner, String hit, Player dealer) {
        while (hit.equals("y")) {
            player.hit(deck);
            player.calculateHandTotal();
            printGameState(player, dealer);
            if (player.handTotal > 21) {
                // check for aces
                System.out.println("\nPlayer busts!");
                player.bust = true;
                break;
            }
            System.out.println("\nWould you like to hit? (y/n)");
            hit = scanner.nextLine();
        }
    }

    private static void printGameState(Player player, Player dealer) {
        System.out.println("Player's hand:");
        player.printHand();

        System.out.println("\nDealer's hand:");
        dealer.printHand();
        dealer.calculateHandTotal();
    }


    public static void dealCard(Player player, Player dealer, Deck deck) {
        player.hand.add(deck.dealCard(true));
        dealer.hand.add(deck.dealCard(true));
        player.hand.add(deck.dealCard(true));
        dealer.hand.add(deck.dealCard(false));
    }
}

class Player {
    ArrayList<Card> hand = new ArrayList<Card>();
    int handTotal = 0;
    boolean bust = false;

    Player() {
        hand = new ArrayList<Card>();
    }

    public void printHand() {
        for (Card card : hand) {
            if (!card.isFaceUp()) {
                System.out.print("Hidden card");
            } else {
                System.out.print(card);
            }
        }
    }

    public void calculateHandTotal() {
        handTotal = 0;
        for (Card card : hand) {
            handTotal += card.getValue();
        }

        if (handTotal > 21) {
            for (Card card : hand) {
                if (card.getRank().equals("Ace")) {
                    card.updateValue(1);
                    handTotal -= 10;
                    if (handTotal <= 21) {
                        break;
                    }
                }
            }
        }

    }

    public void hit(Deck deck) {
        hand.add(deck.dealCard(true));
        calculateHandTotal();
    }

}