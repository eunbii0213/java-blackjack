package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final int FIRST_INDEX = 0;

    private final List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    public static Deck generateByRandomCard() {
        return new Deck(getShuffledCards());
    }

    public static List<Card> getShuffledCards() {
        List<Card> cards = getCards();
        Collections.shuffle(cards);
        return cards;
    }

    private static List<Card> getCards() {
        return Arrays.stream(Symbol.values())
                .flatMap(symbol -> Arrays.stream(Number.values()).map(number -> Card.of(symbol.getName(), number.getName())))
                .collect(Collectors.toList());
    }

    public Card makeOneCard() {
        return cards.remove(FIRST_INDEX);
    }
}