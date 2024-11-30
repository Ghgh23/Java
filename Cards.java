import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CardDeck {
    private final List<Card> cards;
    private final Random random;

    public CardDeck() {
        cards = createStandardDeck();
        random = new Random();
    }

    private List<Card> createStandardDeck() {
        List<Card> deck = new ArrayList<>(52);
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
        return deck;
    }

    public void shuffle() {
        Collections.shuffle(cards, random);
    }

    public Card dealOneCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("Колода пуста!");
        }
        return cards.remove(0); // Возвращаем первую карту и удаляем её из колоды
    }

    public void returnCard(Card card) {
        if (card == null) {
            throw new NullPointerException("Карточка не может быть пустой!");
        }
        if (cards.contains(card)) {
            throw new IllegalArgumentException("Такая карточка уже находится в колоде!");
        }
        cards.add(card);
    }

    public int size() {
        return cards.size();
    }

    @Override
    public String toString() {
        return "CardDeck{" +
                "cards=" + cards +
                '}';
    }
}



public class Main {
    public static void main(String[] args) {
        CardDeck deck = new CardDeck();
        deck.shuffle(); // Перемешивание колоды

        // Сдача нескольких карт
        Card card1 = deck.dealOneCard();
        Card card2 = deck.dealOneCard();
        Card card3 = deck.dealOneCard();

        System.out.println("Сданные карты: " + card1 + ", " + card2 + ", " + card3);

        // Возврат одной карты в колоду
        deck.returnCard(card1);

        System.out.println("Размер колоды после возврата карты: " + deck.size());
    }
}
