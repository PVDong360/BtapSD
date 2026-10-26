package e3.chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Comparator;

public class PokerHand {

    private final List<Card> aCards;

    public PokerHand(Card... pCards) {
        assert pCards.length == 5;

        aCards = Arrays.asList(pCards);
        aCards.sort(new Comparator<Card>() {
            @Override
            public int compare(Card pCard1, Card pCard2) {
                return pCard1.rank().compareTo(pCard2.rank());
            }
        });
    }

    private boolean isStraightFlush() {
        Suit suit = aCards.get(0).suit();
        Rank rank = aCards.get(0).rank();

        Iterator<Card> iterator = aCards.iterator();
        iterator.next();

        while (iterator.hasNext()) {
            Card card = iterator.next();

            if (card.suit() != suit
                    || card.rank().ordinal() - rank.ordinal() != 1) {
                return false;
            }

            rank = card.rank();
        }

        return true;
    }
}