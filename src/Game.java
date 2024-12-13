import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();

        Player player = new Player();
        Dealer dealer = new Dealer();

        dealCard(player, dealer, deck);

        System.out.println("Player's hand:");
        player.printHand();

        System.out.println("Dealer's hand:");
        dealer.printHand();
        dealer.calculateHandTotal();
        System.out.println("Dealer's hand total: " + dealer.handTotal);

        player.calculateHandTotal();
        System.out.println("Player's hand total: " + player.handTotal);

        System.out.println("Would you like to hit? (y/n)");
        String hit = scanner.nextLine();


        // Player's turn
        while (hit.equals("y")) {
            player.hit(deck);
            player.printHand();
            player.calculateHandTotal();
            System.out.println("Player's hand total: " + player.handTotal);
            if (player.handTotal > 21) {
                System.out.println("Player busts!");
                player.bust = true;
                break;
            }
            System.out.println("Would you like to hit? (y/n)");
            hit = scanner.nextLine();
        }

        // dealer's turn
        while (dealer.handTotal < 17) {
            dealer.hit(deck);
            System.out.println("Dealer's hand total: " + dealer.handTotal);
            if (dealer.handTotal > 21) {
                System.out.println("Dealer busts!");
                dealer.bust = true;
                break;
            }
        }

        // determine winner

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

    public static void dealCard(Player player, Dealer dealer, Deck deck) {
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
            System.out.println(card);
        }
    }

    public void calculateHandTotal() {
        handTotal = 0;
        for (Card card : hand) {
            handTotal += card.getValue();
        }
    }

    public void hit(Deck deck) {
        hand.add(deck.dealCard(true));
        calculateHandTotal();
    }



}

class Dealer {
    ArrayList<Card> hand = new ArrayList<Card>();
    int handTotal = 0;
    boolean bust = false;

    Dealer() {
        hand = new ArrayList<Card>();
    }

    public void printHand() {
        for (Card card : hand) {
            if (card.isFaceUp()) {
                System.out.println(card);
            } else {
                System.out.println("Card is face down");
            }
        }
    }

    public void calculateHandTotal() {
        handTotal = 0;
        for (Card card : hand) {
            handTotal += card.getValue();
        }
    }

    public void hit(Deck deck) {
        hand.add(deck.dealCard(true));
        calculateHandTotal();
    }
}
