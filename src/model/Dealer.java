//Author: Kianny Calvo

package model;

import java.util.ArrayList;

public class Dealer {

	private ArrayList<Card> community;
	private double pot;
	private Deck deck;

	public Dealer(Deck deck) {
		community = new ArrayList<Card>(5);
		pot = 0.00;
		this.deck = deck;
	}

	public void dealCommunity() {
		if (!(community.isEmpty())) {
			community.clear();
		}
		for (int i = 0; i < 5; i++) {
			community.add(deck.getCard());
		}
	}

	public ArrayList<Card> getCommunity() {
		return community;
	}

	public String toString() {
		String communityString = "";
		for (Card card : community) {
			communityString += card.toString() + " ";
		}
		communityString.trim();
		return communityString;
	}

	public ArrayList<Card> dealPlayerCards() {
		ArrayList<Card> playerCards = new ArrayList<Card>(2);
		playerCards.add(deck.getCard());
		playerCards.add(deck.getCard());
		return playerCards;
	}

	public void collectAnte(int numberOfPlayers) {
		pot = numberOfPlayers * 2.00;
	}

	public double divvyThePot(int numberOfWinners) {
		double winnings = pot;
		if (numberOfWinners != 0) {
			winnings = pot / numberOfWinners;
		}
		pot = 0;
		return winnings;
	}

	public double getPot() {
		return pot;
	}
}
