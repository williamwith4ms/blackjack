public class Card {
    private final String suit;
    private final String rank;
    private final int value;
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

    public boolean isFaceUp() {
        return faceUp;
    }

    public void reveal() {
        faceUp = true;
    }

    public String toString() {
        String facing = (faceUp ? "(Up)" : "(Down)");
        return switch (suit) {
            case "Hearts" -> rank + "♥" + " " + facing;
            case "Diamonds" -> rank + "♦" + " " + facing;
            case "Clubs" -> rank + "♣" + " " + facing;
            case "Spades" -> rank + "♠" + " " + facing;
            default -> "Invalid suit";
        };
    }
}
