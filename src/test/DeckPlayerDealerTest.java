//Author: Kianny Calvo

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import model.Card;

import model.Rank;

import model.Suit;

import model.Deck;

import model.Dealer;

import model.Player;

class DeckPlayerDealerTest {

	// test Deck
	Deck deck = new Deck();

	@Test
	void testDeckSize() {
		deck.openDeck();
		assertEquals(52, deck.getDeck().size());
	}

	@Test
	void testRetrieveDeck() {
		ArrayList<Card> testDeck = new ArrayList<Card>(52);
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				testDeck.add(new Card(rank, suit));
			}
		}
		deck.openDeck();
		for (Card card : testDeck) {
			assertTrue(card.compareTo(deck.getCard()) == 0);
		}
	}

	@Test
	void testShuffleAndCurCard() {
		deck.openDeck();
		assertEquals(0, deck.getCurCard());
		deck.getCard();
		deck.getCard();
		deck.getCard();
		deck.getCard();
		assertEquals(4, deck.getCurCard());
		deck.shuffle();
		assertEquals(0, deck.getCurCard());
	}

	@Test
	void testShuffle() {
		ArrayList<Card> testDeck = new ArrayList<Card>(52);
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				testDeck.add(new Card(rank, suit));
			}
		}
		deck.openDeck();
		assertTrue(testDeck.get(0).compareTo(deck.getCard()) == 0);
		deck.shuffle();
		assertFalse(testDeck.get(0).compareTo(deck.getCard()) == 0);
	}

	// test Dealer
	Dealer dealer = new Dealer(deck);

	@Test
	void testCommunity() {
		deck.openDeck();
		deck.shuffle();
		dealer.dealCommunity();
		assertEquals(5, dealer.getCommunity().size());
		ArrayList<Card> test = dealer.getCommunity();
		String testString = "";
		for (Card card : test) {
			testString += card.toString() + " ";
		}
		testString.trim();
		assertEquals(testString, dealer.toString());
	}

	@Test
	void testDealToPlayer() {
		deck.openDeck();
		deck.shuffle();
		dealer.dealCommunity();
		ArrayList<Card> player1 = dealer.dealPlayerCards();
		assertEquals(2, player1.size());
		ArrayList<Card> player2 = dealer.dealPlayerCards();
		assertEquals(2, player2.size());
	}

	@Test
	void testCollectAnte() {
		assertEquals(0.00, dealer.getPot());
		dealer.collectAnte(5);
		assertEquals(10.00, dealer.getPot());
	}

	@Test
	void testDivvyThePot() {
		dealer.collectAnte(6);
		double wins = dealer.divvyThePot(0);
		assertEquals(12.00, wins);
		assertEquals(0.00, dealer.getPot());
	}

	@Test
	void testDivvyThePotIfTieDivisible() {
		dealer.collectAnte(5);
		double wins = dealer.divvyThePot(2);
		assertEquals((10.00 / 2), wins);
		assertEquals(0.00, dealer.getPot());
	}

	@Test
	void testDivvyThePotIfTieNotDivisible1() {
		dealer.collectAnte(5);
		double wins = dealer.divvyThePot(3);
		assertEquals((10.00 / 3), wins);
		assertEquals(0.00, dealer.getPot());
	}

	@Test
	void testDivvyThePotIfTieNotDivisible2() {
		dealer.collectAnte(5);
		double wins = dealer.divvyThePot(7);
		assertEquals((10.00 / 7), wins);
		assertEquals(0.00, dealer.getPot());
	}

	// test Player

	Player player1 = new Player(1);

	@Test
	void testPayAnte() {
		assertEquals(100.00, player1.getBalance());
		player1.payAnte();
		assertEquals(98.00, player1.getBalance());
	}

	@Test
	void testaddWinnings() {
		player1.addWinnings(0);
		assertEquals(100.00, player1.getBalance());
		player1.addWinnings(5.67);
		assertEquals(105.67, player1.getBalance());
	}

	@Test
	void testNegativeBalance() {
		for (int i = 1; i < 52; i++) {
			player1.payAnte();
		}
		assertEquals(-2.00, player1.getBalance());
	}

	@Test
	void testPlayerToString() {
		assertEquals("Player 1: $100.00", player1.toString());
		player1.addWinnings(10.00 / 3);
		assertEquals("Player 1: $103.33", player1.toString());
		player1.addWinnings(2.056);
		assertEquals("Player 1: $105.39", player1.toString());
	}

	@Test
	void testMyCards() {
		deck.openDeck();
		deck.shuffle();
		ArrayList<Card> test = dealer.dealPlayerCards();
		player1.setMyCards(test);
		assertEquals(test, player1.getMyCards());
		assertEquals(2, player1.getMyCards().size());
	}

	@Test
	void testShowMyCards() {
		deck.openDeck();
		deck.shuffle();
		ArrayList<Card> test = dealer.dealPlayerCards();
		player1.setMyCards(test);
		String testString = test.get(0).toString() + " " + test.get(1).toString();
		assertEquals(testString, player1.showMyCards());
	}

	@Test
	void testGetBestHand() {
		deck.openDeck();
		deck.shuffle();
		dealer.dealCommunity();
		ArrayList<Card> player1Cards = dealer.dealPlayerCards();
		player1.setMyCards(player1Cards);
		player1.findBestHand(dealer.getCommunity());
		ArrayList<Card> choices = player1.getChoices();
		assertEquals(7, choices.size());
		assertTrue(choices.containsAll(dealer.getCommunity()));
		assertTrue(choices.containsAll(player1.getMyCards()));
		ArrayList<ArrayList<Card>> possibilities = player1.getPossibleHands();
		assertEquals(21, possibilities.size());
		Card card1 = choices.get(0);
		Card card2 = choices.get(1);
		Card card3 = choices.get(2);
		Card card4 = choices.get(3);
		Card card5 = choices.get(4);
		Card card6 = choices.get(5);
		Card card7 = choices.get(6);
		ArrayList<Card> hand1 = new ArrayList<Card>(Arrays.asList(card3, card4, card5, card6, card7));
		ArrayList<Card> hand2 = new ArrayList<Card>(Arrays.asList(card1, card4, card5, card6, card7));
		ArrayList<Card> hand3 = new ArrayList<Card>(Arrays.asList(card1, card2, card4, card5, card7));
		ArrayList<Card> hand4 = new ArrayList<Card>(Arrays.asList(card1, card2, card3, card4, card5));
		assertEquals(hand1, possibilities.get(0));
		assertEquals(hand2, possibilities.get(6));
		assertEquals(hand3, possibilities.get(13));
		assertEquals(hand4, possibilities.get(20));
		System.out.println(player1.showBestHand());
	}

}
