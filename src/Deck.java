import java.util.Random;

public class Deck {
    private final Card[] cards;
    public Card[] remaingCards;
    public int numCards;

    public Deck(int numDecks) {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        numCards = 52 * numDecks;

        cards = new Card[52 * numDecks];
        int index = 0;
        for (int i = 0; i < numDecks; i++) {
            for (String suit : suits) {
                for (String rank : ranks) {
                    cards[index] = new Card(suit, rank);
                    index++;
                }
            }
        }
    }

    public Card[] getCards() {
        return cards;
    }

    public void shuffle() {
        Random random = new Random();
        Card[] tempCards = cards;
        for (int i = 0; i < numCards; i++) {
            int randomIndex = random.nextInt(52);
            Card temp = tempCards[i];
            tempCards[i] = tempCards[randomIndex];
            tempCards[randomIndex] = temp;
        }
        remaingCards = tempCards;
    }

    public void printDeck() {
        for (Card card : remaingCards) {
            System.out.println(card);
        }
    }

    public Card dealCard(boolean faceUp) {
        Card card = remaingCards[0];
        Card[] tempCards = new Card[remaingCards.length - 1];
        System.arraycopy(remaingCards, 1, tempCards, 0, remaingCards.length - 1);
        remaingCards = tempCards;
        if (faceUp) {
            card.reveal();
        }
        return card;
    }

}