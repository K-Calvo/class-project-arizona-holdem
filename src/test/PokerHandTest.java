//Author: Kianny Calvo

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.PokerHand;

import model.Card;

import model.Rank;

import model.Suit;

class PokerHandTest {

	@Test
	public void testRoyalFlushWins() {
		PokerHand a = new PokerHand(H10, HJ, HQ, HK, HA);
		PokerHand b = new PokerHand(C9, C10, CJ, CQ, CK);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void testRoyalTie() {
		PokerHand a = new PokerHand(H10, HJ, HQ, HK, HA);
		PokerHand b = new PokerHand(D10, DJ, DQ, DK, DA);
		assertTrue(a.compareTo(b) == 0);
	}

	@Test
	public void testStraightFlushWins() {
		PokerHand a = new PokerHand(C9, C10, CJ, CQ, CK);
		PokerHand b = new PokerHand(D9, D2, H2, C2, S2);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void testStraightFlushTies() {
		PokerHand a = new PokerHand(C9, C10, CJ, CQ, CK);
		PokerHand b = new PokerHand(D10, DJ, DQ, DK, D9);
		assertTrue(a.compareTo(b) == 0);
	}

	@Test
	public void testStraightFlushTiesLoses() {
		PokerHand a = new PokerHand(C9, C10, CJ, CQ, C8);
		PokerHand b = new PokerHand(D10, DJ, DQ, DK, D9);
		assertTrue(a.compareTo(b) == -1);
	}

	@Test
	public void testStraightFlushTiesWins() {
		PokerHand a = new PokerHand(C9, C10, CJ, CQ, C8);
		PokerHand b = new PokerHand(D10, DJ, DQ, DK, D9);
		assertTrue(b.compareTo(a) == 1);
	}

	@Test
	public void testFoursWins() {
		PokerHand a = new PokerHand(D3, S3, C3, D2, C2);
		PokerHand b = new PokerHand(D9, D2, H2, C2, S2);
		assertTrue(b.compareTo(a) > 0);
	}

	@Test
	public void testFoursTiesLoses() {
		PokerHand a = new PokerHand(D3, S3, C3, H3, C2);
		PokerHand b = new PokerHand(D9, D2, H2, C2, S2);
		assertTrue(b.compareTo(a) == -1);
	}

	@Test
	public void fullHouseWin() {
		PokerHand a = new PokerHand(D3, S3, C3, D2, C2);
		PokerHand b = new PokerHand(D9, DK, D5, D7, D4);
		assertTrue(a.compareTo(b) == 1);
	}

	@Test
	public void fullHouseTieWin() {
		PokerHand a = new PokerHand(D3, S3, C3, D2, C2);
		PokerHand b = new PokerHand(S4, D4, H4, H2, S2);
		assertTrue(b.compareTo(a) > 0);
	}

	@Test
	public void flushWin() {
		PokerHand a = new PokerHand(H4, S3, C5, D2, C6);
		PokerHand b = new PokerHand(D9, DK, D5, D7, D4);
		assertTrue(b.compareTo(a) == 1);
	}

	@Test
	public void flushTie() {
		PokerHand a = new PokerHand(C5, C7, C4, CK, C9);
		PokerHand b = new PokerHand(D9, DK, D5, D7, D4);
		assertTrue(a.compareTo(b) == 0);
	}

	@Test
	public void flushTieWin() {
		PokerHand a = new PokerHand(C5, C7, C4, CK, C9);
		PokerHand b = new PokerHand(D9, DK, D5, D7, D3);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void straightWin() {
		PokerHand a = new PokerHand(H4, S3, C5, D2, C6);
		PokerHand b = new PokerHand(D9, S9, H9, D7, D4);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void straightTie() {
		PokerHand a = new PokerHand(H4, S3, C5, D2, C6);
		PokerHand b = new PokerHand(H2, C3, S4, D5, H6);
		assertTrue(b.compareTo(a) == 0);
	}

	@Test
	public void straightTieWin() {
		PokerHand a = new PokerHand(H4, S3, C5, D2, C6);
		PokerHand b = new PokerHand(D9, D8, D6, C7, D5);
		assertTrue(b.compareTo(a) > 0);
	}

	@Test
	public void threeWin() {
		PokerHand a = new PokerHand(H4, S4, C5, D5, C6);
		PokerHand b = new PokerHand(D9, S9, H9, D7, D4);
		assertTrue(b.compareTo(a) > 0);
	}

	@Test
	public void threeTieWin() {
		PokerHand a = new PokerHand(HK, SK, CK, D2, C6);
		PokerHand b = new PokerHand(D9, S9, H9, D7, D4);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void twoPairWin() {
		PokerHand a = new PokerHand(H4, S4, C5, D5, C6);
		PokerHand b = new PokerHand(DA, HK, CK, S3, H6);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void twoPairTie() {
		PokerHand a = new PokerHand(H4, S4, C5, D5, C6);
		PokerHand b = new PokerHand(D4, C4, H5, S5, D6);
		assertTrue(b.compareTo(a) == 0);
	}

	@Test
	public void twoPairTieWinWithFirstPair() {
		PokerHand a = new PokerHand(H4, S4, C5, D5, C6);
		PokerHand b = new PokerHand(HA, SA, H5, S5, D6);
		assertTrue(b.compareTo(a) > 0);
	}

	@Test
	public void twoPairTieWinWithSecondPair() {
		PokerHand a = new PokerHand(C5, D5, HK, C2, D2);
		PokerHand b = new PokerHand(H5, S5, CA, C3, D3);
		assertTrue(b.compareTo(a) > 0);
	}

	@Test
	public void twoPairTieWinWithSingleCard() {
		PokerHand a = new PokerHand(H4, S4, C5, D5, HJ);
		PokerHand b = new PokerHand(D4, C4, H5, S5, S9);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void pairWin() {
		PokerHand a = new PokerHand(DA, S3, H4, S4, C5);
		PokerHand b = new PokerHand(D5, S7, H9, CJ, D2);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void pairTie() {
		PokerHand a = new PokerHand(H5, S5, D6, C8, H10);
		PokerHand b = new PokerHand(C5, D5, S6, H8, C10);
		assertTrue(a.compareTo(b) == 0);
	}

	@Test
	public void pairTieWinWithNonPairOne() {
		PokerHand a = new PokerHand(D8, C8, DQ, C2, D3);
		PokerHand b = new PokerHand(S8, H8, SK, C4, S3);
		assertTrue(b.compareTo(a) > 0);
	}

	@Test
	public void pairTieWinWithNonPairTwo() {
		PokerHand a = new PokerHand(D8, C8, DQ, C2, D3);
		PokerHand b = new PokerHand(S8, H8, SQ, C4, S3);
		assertTrue(b.compareTo(a) > 0);
	}

	@Test
	public void pairTieWinWithNonPairThree() {
		PokerHand a = new PokerHand(D8, C8, DQ, C3, D4);
		PokerHand b = new PokerHand(S8, H8, SQ, C4, S2);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void highCardTie() {
		PokerHand a = new PokerHand(H2, D4, H6, D8, H10);
		PokerHand b = new PokerHand(S2, C4, S6, C8, S10);
		assertTrue(a.compareTo(b) == 0);
	}

	@Test
	public void highCardTieWinWithFirstCard() {
		PokerHand a = new PokerHand(H2, D4, H6, D8, HJ);
		PokerHand b = new PokerHand(S2, C4, S6, C8, S10);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void highCardTieWithSecondCard() {
		PokerHand a = new PokerHand(H2, D4, H6, D9, H10);
		PokerHand b = new PokerHand(S2, C4, S6, C8, S10);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void highCardTieWithThirdCard() {
		PokerHand a = new PokerHand(H2, D4, H7, D8, H10);
		PokerHand b = new PokerHand(S2, C4, S6, C8, S10);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void highCardTieWithFourthCard() {
		PokerHand a = new PokerHand(H2, D5, H6, D8, H10);
		PokerHand b = new PokerHand(S2, C4, S6, C8, S10);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void highCardTieWithFifthCard() {
		PokerHand a = new PokerHand(H3, D4, H6, D8, H10);
		PokerHand b = new PokerHand(S2, C4, S6, C8, S10);
		assertTrue(a.compareTo(b) > 0);
	}

	@Test
	public void testLosing() {
		PokerHand a = new PokerHand(H9, S9, H3, S3, H4);
		PokerHand b = new PokerHand(H5, S5, H2, S2, HJ);
		assertTrue(b.compareTo(a) < 0);
	}

	// failed tests

	@Test
	public void pairTieWinWithPair() {
		PokerHand a = new PokerHand(H5, S5, D6, C8, H10);
		PokerHand b = new PokerHand(D3, C2, H7, H4, S4);
		assertTrue(a.compareTo(b) > 0);
	}

	// TEST PAIRS

	@Test
	public void testPair5() {
		assertTrue(pairHigh.compareTo(pairLow) > 0);
	}

	@Test
	public void testPair6() {
		assertTrue(pairHigh.compareTo(pair) > 0);
	}

	@Test
	public void testFullHouseHands() {
		PokerHand a = new PokerHand(S6, D6, DA, CA, HA);
		PokerHand b = new PokerHand(S3, D3, DA, CA, HA);
		assertTrue(a.compareTo(b) > 0);
		assertTrue(b.compareTo(a) < 0);
	}

	@Test
	public void testTwoPairWhenAtIndexesForCodeCoverage() {
		PokerHand a = new PokerHand(C2, D3, C5, D5, CJ);
		PokerHand b = new PokerHand(H2, S3, H3, S5, SJ);
		assertTrue(a.compareTo(b) > 0);
		assertTrue(b.compareTo(a) < 0);
	}

	@Test
	public void testFourOfAKind4() {
		PokerHand a = new PokerHand(S8, CA, DA, HA, SA);
		PokerHand b = new PokerHand(D7, CA, DA, HA, SA);
		assertTrue(a.compareTo(b) > 0);
		assertTrue(b.compareTo(a) < 0);
	}

	// Trips

	@Test
	public void testTrips1() {
		PokerHand a = new PokerHand(H7, C7, D7, S9, S10);
		PokerHand b = new PokerHand(H9, C10, H6, C6, D6);
		assertTrue(a.compareTo(b) > 0); // returned false
		assertTrue(b.compareTo(a) < 0);
	}

	@Test
	public void testFullHouseHands3() {
		PokerHand a = new PokerHand(S6, D6, DA, CA, HA);
		PokerHand b = new PokerHand(C7, H7, DA, CA, HA);
		assertTrue(a.compareTo(b) < 0);
		assertTrue(b.compareTo(a) > 0);
	}

	@Test
	public void testThreeOfAKind() {
		PokerHand a = new PokerHand(S6, D7, DA, CA, HA);
		PokerHand b = new PokerHand(C5, H6, DA, CA, HA);
		assertTrue(a.compareTo(b) > 0);
		assertTrue(b.compareTo(a) < 0);
	}

	@Test
	public void testHandRankString() {
		PokerHand a = new PokerHand(H10, HJ, HQ, HK, HA);
		PokerHand b = new PokerHand(C9, C10, CJ, CQ, CK);
		PokerHand c = new PokerHand(D9, D2, H2, C2, S2);
		PokerHand d = new PokerHand(D3, S3, C3, D2, C2);
		PokerHand e = new PokerHand(D9, DK, D5, D7, D4);
		PokerHand f = new PokerHand(H4, S3, C5, D2, C6);
		PokerHand g = new PokerHand(D9, S9, H9, D7, D4);
		PokerHand h = new PokerHand(H4, S4, C5, D5, C6);
		PokerHand i = new PokerHand(DA, S3, H4, S4, C5);
		PokerHand j = new PokerHand(H2, D4, H6, D8, H10);
		assertEquals("10♥ J♥ Q♥ K♥ A♥    Royal Flush", a.toString());
		assertEquals("9♣ 10♣ J♣ Q♣ K♣    Straight Flush", b.toString());
		assertEquals("2♦ 2♥ 2♣ 2♠ 9♦    Four of a Kind", c.toString());
		assertEquals("2♦ 2♣ 3♦ 3♠ 3♣    Full House", d.toString());
		assertEquals("4♦ 5♦ 7♦ 9♦ K♦    Flush", e.toString());
		assertEquals("2♦ 3♠ 4♥ 5♣ 6♣    Straight", f.toString());
		assertEquals("4♦ 7♦ 9♦ 9♠ 9♥    Three of a Kind", g.toString());
		assertEquals("4♥ 4♠ 5♣ 5♦ 6♣    Two Pair", h.toString());
		assertEquals("3♠ 4♥ 4♠ 5♣ A♦    Pair", i.toString());
		assertEquals("2♥ 4♦ 6♥ 8♦ 10♥    High Card", j.toString());
	}

	// cards

	private final static Card C2 = new Card(Rank.DEUCE, Suit.CLUBS);
	private final static Card C3 = new Card(Rank.THREE, Suit.CLUBS);
	private final static Card C4 = new Card(Rank.FOUR, Suit.CLUBS);
	private final static Card C5 = new Card(Rank.FIVE, Suit.CLUBS);
	private final static Card C6 = new Card(Rank.SIX, Suit.CLUBS);
	private final static Card C7 = new Card(Rank.SEVEN, Suit.CLUBS);
	private final static Card C8 = new Card(Rank.EIGHT, Suit.CLUBS);
	private final static Card C9 = new Card(Rank.NINE, Suit.CLUBS);
	private final static Card C10 = new Card(Rank.TEN, Suit.CLUBS);
	private final static Card CJ = new Card(Rank.JACK, Suit.CLUBS);
	private final static Card CQ = new Card(Rank.QUEEN, Suit.CLUBS);
	private final static Card CK = new Card(Rank.KING, Suit.CLUBS);
	private final static Card CA = new Card(Rank.ACE, Suit.CLUBS);

	private final static Card D2 = new Card(Rank.DEUCE, Suit.DIAMONDS);
	private final static Card D3 = new Card(Rank.THREE, Suit.DIAMONDS);
	private final static Card D4 = new Card(Rank.FOUR, Suit.DIAMONDS);
	private final static Card D5 = new Card(Rank.FIVE, Suit.DIAMONDS);
	private final static Card D6 = new Card(Rank.SIX, Suit.DIAMONDS);
	private final static Card D7 = new Card(Rank.SEVEN, Suit.DIAMONDS);
	private final static Card D8 = new Card(Rank.EIGHT, Suit.DIAMONDS);
	private final static Card D9 = new Card(Rank.NINE, Suit.DIAMONDS);
	private final static Card D10 = new Card(Rank.TEN, Suit.DIAMONDS);
	private final static Card DJ = new Card(Rank.JACK, Suit.DIAMONDS);
	private final static Card DQ = new Card(Rank.QUEEN, Suit.DIAMONDS);
	private final static Card DK = new Card(Rank.KING, Suit.DIAMONDS);
	private final static Card DA = new Card(Rank.ACE, Suit.DIAMONDS);

	private final static Card H2 = new Card(Rank.DEUCE, Suit.HEARTS);
	private final static Card H3 = new Card(Rank.THREE, Suit.HEARTS);
	private final static Card H4 = new Card(Rank.FOUR, Suit.HEARTS);
	private final static Card H5 = new Card(Rank.FIVE, Suit.HEARTS);
	private final static Card H6 = new Card(Rank.SIX, Suit.HEARTS);
	private final static Card H7 = new Card(Rank.SEVEN, Suit.HEARTS);
	private final static Card H8 = new Card(Rank.EIGHT, Suit.HEARTS);
	private final static Card H9 = new Card(Rank.NINE, Suit.HEARTS);
	private final static Card H10 = new Card(Rank.TEN, Suit.HEARTS);
	private final static Card HJ = new Card(Rank.JACK, Suit.HEARTS);
	private final static Card HQ = new Card(Rank.QUEEN, Suit.HEARTS);
	private final static Card HK = new Card(Rank.KING, Suit.HEARTS);
	private final static Card HA = new Card(Rank.ACE, Suit.HEARTS);

	private final static Card S2 = new Card(Rank.DEUCE, Suit.SPADES);
	private final static Card S3 = new Card(Rank.THREE, Suit.SPADES);
	private final static Card S4 = new Card(Rank.FOUR, Suit.SPADES);
	private final static Card S5 = new Card(Rank.FIVE, Suit.SPADES);
	private final static Card S6 = new Card(Rank.SIX, Suit.SPADES);
	private final static Card S7 = new Card(Rank.SEVEN, Suit.SPADES);
	private final static Card S8 = new Card(Rank.EIGHT, Suit.SPADES);
	private final static Card S9 = new Card(Rank.NINE, Suit.SPADES);
	private final static Card S10 = new Card(Rank.TEN, Suit.SPADES);
	private final static Card SJ = new Card(Rank.JACK, Suit.SPADES);
	private final static Card SQ = new Card(Rank.QUEEN, Suit.SPADES);
	private final static Card SK = new Card(Rank.KING, Suit.SPADES);
	private final static Card SA = new Card(Rank.ACE, Suit.SPADES);

	// PokerHand Pairs

	private static PokerHand pair = new PokerHand(D2, H2, D3, S7, H6);
	private static PokerHand pairLow = new PokerHand(C2, D2, C3, H4, H5);
	private static PokerHand pairHigh = new PokerHand(HA, CA, CK, CQ, HJ);

}
