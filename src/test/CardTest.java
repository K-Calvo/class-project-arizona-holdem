// Author: Kianny Calvo

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Card;

import model.Rank;

import model.Suit;

public class CardTest {

	@Test
	public void testGetters() {
		Card c1 = new Card(Rank.DEUCE, Suit.CLUBS);
		assertEquals(Rank.DEUCE, c1.getRank());
		assertEquals(Suit.CLUBS, c1.getSuit());
		assertEquals(2, c1.getValue());
	}

	@Test
	public void testCompareTo() {
		Card c1 = new Card(Rank.DEUCE, Suit.CLUBS);
		Card c2 = new Card(Rank.THREE, Suit.DIAMONDS);
		assertTrue(c1.compareTo(c2) < 0);
		assertTrue(c2.compareTo(c1) > 0); // Rank.THREE > Rank.DEUCE
		assertTrue(c1.compareTo(c1) == 0);
	}

	@Test
	public void testMoreComparisons() {
		Card c1 = new Card(Rank.DEUCE, Suit.CLUBS);
		Card c2 = new Card(Rank.THREE, Suit.DIAMONDS);
		assertEquals(-1, c1.compareTo(c2));
		assertEquals(1, c2.compareTo(c1));
		assertEquals(0, c1.compareTo(c1));
	}

	@Test
	public void testToString() {
		Card c1 = new Card(Rank.ACE, Suit.CLUBS);
		System.out.println(c1.toString());
		assertEquals("A" + '\u2663', c1.toString()); // A♣

		Card c2 = new Card(Rank.KING, Suit.DIAMONDS);
		System.out.println(c2.toString());
		assertEquals("K" + '\u2666', c2.toString()); // K♦

		Card c3 = new Card(Rank.QUEEN, Suit.HEARTS);
		System.out.println(c3.toString());
		assertEquals("Q" + '\u2665', c3.toString()); // Q♥

		Card c4 = new Card(Rank.JACK, Suit.SPADES);
		System.out.println(c4.toString());
		assertEquals("J" + '\u2660', c4.toString()); // J♠
	}

	@Test
	public void testToStringNumberCards() {
		Card c1 = new Card(Rank.THREE, Suit.CLUBS);
		System.out.println(c1.toString());
		assertEquals("3" + '\u2663', c1.toString()); // 3♣

		Card c2 = new Card(Rank.FIVE, Suit.CLUBS);
		System.out.println(c2.toString());
		assertEquals("5" + '\u2663', c2.toString()); // 5♣

		Card c3 = new Card(Rank.SEVEN, Suit.CLUBS);
		System.out.println(c3.toString());
		assertEquals("7" + '\u2663', c3.toString()); // 7♣

		Card c4 = new Card(Rank.NINE, Suit.CLUBS);
		System.out.println(c4.toString());
		assertEquals("9" + '\u2663', c4.toString()); // 9♣
	}

}
