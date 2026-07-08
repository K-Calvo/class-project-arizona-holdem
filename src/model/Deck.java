//Author: Kianny Calvo

package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Card> deck;
	private int curCard;

	public Deck() {
		deck = new ArrayList<Card>(52);
		curCard = 0;
	}

	// creates 52 cards in deck
	public void openDeck() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				deck.add(new Card(rank, suit));
			}
		}
	}

	public void shuffle() {
		Collections.shuffle(deck);
		curCard = 0;
	}

	public Card getCard() {
		int temp = curCard;
		curCard += 1;
		return deck.get(temp);
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public int getCurCard() {
		return curCard;
	}

}
