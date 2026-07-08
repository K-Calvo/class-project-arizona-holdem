//Author: Kianny Calvo

package model;

import java.util.ArrayList;
import java.util.Collections;

public class PokerHand implements Comparable<PokerHand> {

	public enum HandRank {

		HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH,
		ROYAL_FLUSH;

		@Override
		public String toString() {
			int value = this.ordinal();
			String str = "";
			switch (value) {
			case 0:
				str = "High Card";
				break;
			case 1:
				str = "Pair";
				break;
			case 2:
				str = "Two Pair";
				break;
			case 3:
				str = "Three of a Kind";
				break;
			case 4:
				str = "Straight";
				break;
			case 5:
				str = "Flush";
				break;
			case 6:
				str = "Full House";
				break;
			case 7:
				str = "Four of a Kind";
				break;
			case 8:
				str = "Straight Flush";
				break;
			case 9:
				str = "Royal Flush";
				break;
			}
			return str;
		}
	}

	private ArrayList<Card> hand;
	private int card1;
	private int card2;
	private int card3;
	private int card4;
	private int card5;

	public PokerHand(Card one, Card two, Card three, Card four, Card five) {
		hand = new ArrayList<Card>(5);
		hand.add(one);
		hand.add(two);
		hand.add(three);
		hand.add(four);
		hand.add(five);
		Collections.sort(hand);
		card1 = hand.get(0).getValue();
		card2 = hand.get(1).getValue();
		card3 = hand.get(2).getValue();
		card4 = hand.get(3).getValue();
		card5 = hand.get(4).getValue();
	}

	private boolean isFlush() {
		Suit suit = hand.get(0).getSuit();
		for (Card card : hand) {
			if (card.getSuit() != suit) {
				return false;
			}
		}
		return true;
	}

	private int iterativeCompareHighestRank(PokerHand other) {
		int comparedValue = 0;
		int cur = hand.size() - 1;
		while (cur >= 0) {
			comparedValue = this.hand.get(cur).compareTo(other.hand.get(cur));
			if (comparedValue != 0) {
				break;
			}
			cur -= 1;
		}
		return comparedValue;
	}

	private boolean isStraight() {
		return (card2 == card1 + 1) && (card3 == card2 + 1) && (card4 == card3 + 1) && (card5 == card4 + 1);
	}

	private int compareHighestRank(PokerHand other) {
		if (this.card5 > other.card5) {
			return 1;
		} else if (this.card5 < other.card5) {
			return -1;
		}
		return 0;
	}

	private boolean isStraightFlush() {
		return isStraight() && isFlush();
	}

	private boolean isFourOfKind() {
		return card1 == card4 || card2 == card5;
	}

	private boolean isFullHouse() {
		return (card1 == card2 && card2 != card3 && card3 == card5)
				|| (card5 == card4 && card4 != card3 && card3 == card1);
	}

	private boolean isThreeOfKind() {
		return (card1 == card3) || (card2 == card4) || (card3 == card5);
	}

	private boolean isTwoPair() {
		return (card1 == card2 && card2 != card3 && card3 == card4 && card4 != card5)
				|| (card1 == card2 && card2 != card3 && card3 != card4 && card4 == card5)
				|| (card1 != card2 && card2 == card3 && card3 != card4 && card4 == card5);
	}

	private boolean isPair() {
		return (card1 == card2 && card2 != card3) || (card1 != card2 && card2 == card3 && card3 != card4)
				|| (card2 != card3 && card3 == card4 && card4 != card5) || (card3 != card4 && card4 == card5);
	}

	// compare PokerHands 4 of a kind, full house, 3 of a kind, two pair, and pairs
	private int comparePairsPlus(PokerHand other) {
		ArrayList<Card> thisUniqueCards = new ArrayList<Card>();
		ArrayList<Card> otherUniqueCards = new ArrayList<Card>();
		// find matching values in this and other PokerHand
		for (int i = 0; i < 4; i++) {
			if (this.hand.get(i).compareTo(this.hand.get(i + 1)) == 0
					&& !(thisUniqueCards.contains(this.hand.get(i)))) {
				thisUniqueCards.add(0, this.hand.get(i));
			}
			if (other.hand.get(i).compareTo(other.hand.get(i + 1)) == 0
					&& !(otherUniqueCards.contains(other.hand.get(i)))) {
				otherUniqueCards.add(0, other.hand.get(i));
			}
		}
		// find other values in this and other PokerHand
		for (int i = 4; i >= 0; i--) {
			if (!thisUniqueCards.contains(this.hand.get(i))) {
				thisUniqueCards.add(this.hand.get(i));
			}
			if (!otherUniqueCards.contains(other.hand.get(i))) {
				otherUniqueCards.add(other.hand.get(i));
			}
		}
		// compare cards
		for (int i = 0; i < thisUniqueCards.size(); i++) {
			if (thisUniqueCards.get(i).compareTo(otherUniqueCards.get(i)) != 0) {
				return thisUniqueCards.get(i).compareTo(otherUniqueCards.get(i));
			}
		}
		return 0;
	}

	private HandRank getHandRank() {
		if (isStraightFlush() && card5 == 14) {
			return HandRank.ROYAL_FLUSH;
		} else if (isStraightFlush()) {
			return HandRank.STRAIGHT_FLUSH;
		} else if (isFourOfKind()) {
			return HandRank.FOUR_OF_A_KIND;
		} else if (isFullHouse()) {
			return HandRank.FULL_HOUSE;
		} else if (isFlush()) {
			return HandRank.FLUSH;
		} else if (isStraight()) {
			return HandRank.STRAIGHT;
		} else if (isThreeOfKind()) {
			return HandRank.THREE_OF_A_KIND;
		} else if (isTwoPair()) {
			return HandRank.TWO_PAIR;
		} else if (isPair()) {
			return HandRank.PAIR;
		}
		return HandRank.HIGH_CARD;
	}

	@Override
	public int compareTo(PokerHand other) {
		HandRank handRankOne = this.getHandRank();
		HandRank handRankTwo = other.getHandRank();
		int comparedValue = handRankOne.compareTo(handRankTwo);
		if (comparedValue > 0) {
			return 1;
		} else if (comparedValue < 0) {
			return -1;
		} else {
			switch (handRankOne) {
			case ROYAL_FLUSH:
				comparedValue = 0;
			case STRAIGHT_FLUSH:
				comparedValue = this.compareHighestRank(other);
				break;
			case FOUR_OF_A_KIND:
				comparedValue = this.comparePairsPlus(other);
				break;
			case FULL_HOUSE:
				comparedValue = this.comparePairsPlus(other);
				break;
			case FLUSH:
				comparedValue = this.iterativeCompareHighestRank(other);
				break;
			case STRAIGHT:
				comparedValue = this.compareHighestRank(other);
				break;
			case THREE_OF_A_KIND:
				comparedValue = this.comparePairsPlus(other);
				break;
			case TWO_PAIR:
				comparedValue = this.comparePairsPlus(other);
				break;
			case PAIR:
				comparedValue = this.comparePairsPlus(other);
				break;
			case HIGH_CARD:
				comparedValue = this.iterativeCompareHighestRank(other);
				break;
			}
			return comparedValue;
		}
	}

	@Override
	public String toString() {
		String handStr = "";
		for (Card card : hand) {
			handStr += card.toString() + " ";
		}
		HandRank handRank = getHandRank();
		return handStr + "   " + handRank.toString();
	}

}
