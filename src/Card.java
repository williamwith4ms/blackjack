public class Card {
    private final String suit;
    private final String rank;
    private int value;
    private boolean faceUp;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
        this.value = getValue();
        faceUp = false;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getValue() {
        if (rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
            return 10;
        } else if (rank.equals("Ace")) {
            return 11;
        } else {
            return Integer.parseInt(rank);
        }
    }

    public void updateValue(int num) {
        value = num;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void reveal() {
        faceUp = true;
    }

    public String toString() {
        String facing = (faceUp ? "(Up)" : "(Down)");
        return switch (suit) {
            case "Hearts" -> rank + "♥" + " ";
            case "Diamonds" -> rank + "♦" + " ";
            case "Clubs" -> rank + "♣" + " ";
            case "Spades" -> rank + "♠" + " ";
            default -> "Invalid suit";
        };
    }
}
