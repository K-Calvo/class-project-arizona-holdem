// Author: Kianny Calvo

package model;

public enum Suit {

	CLUBS, DIAMONDS, HEARTS, SPADES;

	@Override
	public String toString() {
		if (this.ordinal() == 0) {
			return '\u2663' + "";
		} else if (this.ordinal() == 1) {
			return '\u2666' + "";
		} else if (this.ordinal() == 2) {
			return '\u2665' + "";
		}
		return '\u2660' + "";
	}
}
