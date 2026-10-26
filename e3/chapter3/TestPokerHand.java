package e3.chapter3;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

public class TestPokerHand {

    private boolean invokeIsStraightFlush(PokerHand hand) {
        try {
            Method method = PokerHand.class
                    .getDeclaredMethod("isStraightFlush");

            method.setAccessible(true);

            return (boolean) method.invoke(hand);

        } catch (NoSuchMethodException
                | IllegalAccessException
                | InvocationTargetException e) {

            fail("Cannot invoke isStraightFlush(): " + e.getMessage());
            return false;
        }
    }
    
    @Test
    void testIsStraightFlush_True() {
        PokerHand hand = new PokerHand(
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.THREE, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.CLUBS),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.SIX, Suit.CLUBS)
        );

        assertTrue(invokeIsStraightFlush(hand));
    }

    @Test
    void testIsStraightFlush_WrongSuit() {
        PokerHand hand = new PokerHand(
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.THREE, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.CLUBS),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.SIX, Suit.DIAMONDS)
        );

        assertFalse(invokeIsStraightFlush(hand));
    }

    @Test
    void testIsStraightFlush_WrongRank() {
        PokerHand hand = new PokerHand(
                new Card(Rank.TWO, Suit.CLUBS),
                new Card(Rank.THREE, Suit.CLUBS),
                new Card(Rank.FOUR, Suit.CLUBS),
                new Card(Rank.FIVE, Suit.CLUBS),
                new Card(Rank.SEVEN, Suit.CLUBS)
        );

        assertFalse(invokeIsStraightFlush(hand));
    }
}