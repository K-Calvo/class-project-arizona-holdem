// Author: Kianny Calvo

package model;

public class Card implements Comparable<Card> {

	private Rank rank;
	private Suit suit;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Rank getRank() {
		return rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public int getValue() {
		return rank.getValue();
	}

	public String toString() {
		return rank.toString() + suit.toString();
	}

	@Override
	public int compareTo(Card other) {
		if (this.getValue() > other.getValue()) {
			return 1;
		} else if (this.getValue() < other.getValue()) {
			return -1;
		}
		return 0;
	}

}
