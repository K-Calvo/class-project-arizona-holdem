//Author: Kianny Calvo

package model;

import java.util.ArrayList;

public class Player {

	private String name;
	private double balance;
	private ArrayList<Card> myCards;
	private ArrayList<Card> choices;
	private PokerHand bestHand;
	private ArrayList<ArrayList<Card>> possibleHands;

	public Player(int name) {
		this.name = "Player " + String.valueOf(name);
		balance = 100.00;
		choices = new ArrayList<Card>(7);
		possibleHands = new ArrayList<ArrayList<Card>>(21);
	}

	public double getBalance() {
		return balance;
	}

	public void payAnte() {
		balance -= 2.00;
	}

	public void addWinnings(double winnings) {
		balance += winnings;
	}

	public void setMyCards(ArrayList<Card> playerCards) {
		myCards = playerCards;
	}

	public ArrayList<Card> getMyCards() {
		return myCards;
	}

	public String showMyCards() {
		return myCards.get(0).toString() + " " + myCards.get(1).toString();
	}

	private void findPossibleHands(ArrayList<Card> community) {
		if (!(choices.isEmpty())) {
			choices.clear();
		}
		if (!(possibleHands.isEmpty())) {
			possibleHands.clear();
		}
		choices.addAll(myCards);
		choices.addAll(community);
		//
		for (int i = 0; i < 6; i++) {
			for (int j = i + 1; j < 7; j++) {
				ArrayList<Card> aCombo = new ArrayList<Card>(5);
				for (int cur = 0; cur < 7; cur++) {
					if (cur != i && cur != j) {
						aCombo.add(choices.get(cur));
					}
				}
				possibleHands.add(aCombo);
			}
		}
	}

	// to help test findPossibleHands
	public ArrayList<Card> getChoices() {
		return choices;
	}

	public ArrayList<ArrayList<Card>> getPossibleHands() {
		return possibleHands;
	}

	public void findBestHand(ArrayList<Card> community) {
		findPossibleHands(community);
		PokerHand best = null;
		for (ArrayList<Card> hand : possibleHands) {
			Card card1 = hand.get(0);
			Card card2 = hand.get(1);
			Card card3 = hand.get(2);
			Card card4 = hand.get(3);
			Card card5 = hand.get(4);
			PokerHand curPokerHand = new PokerHand(card1, card2, card3, card4, card5);
			if (best == null) {
				best = curPokerHand;
			} else if (curPokerHand.compareTo(best) > 0) {
				best = curPokerHand;
			}
		}
		bestHand = best;
	}

	public PokerHand getBestHand() {
		return bestHand;
	}

	public String showBestHand() {
		return bestHand.toString();
	}

	public String toString() {
		double rounded = Math.round((balance * 100)) / 100.00;
		return name + ": $" + String.format("%.2f", rounded);
	}
}
