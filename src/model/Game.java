//Author: Kianny Calvo

package model;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private int numOfPlayers;
	private ArrayList<Card> community;
	private ArrayList<Player> winners;
	private Player[] players;
	private Deck deck;
	private Dealer dealer;
	private boolean gameActive;

	public Game() {
		deck = new Deck();
		dealer = new Dealer(deck);
		gameActive = false;
	}

	private void setPlayers(String num) {
		numOfPlayers = Integer.valueOf(num);
		players = new Player[numOfPlayers];
	}

	private void createEachPlayer(int num) {
		if (num > numOfPlayers) {
			return;
		}
		players[num - 1] = new Player(num);
		createEachPlayer(num + 1);
	}

	private void setCommunity(ArrayList<Card> list) {
		community = list;
	}

	private void printRound() {
		if (gameActive) {
			System.out.println();
		}
		System.out.println("Community Cards: " + dealer.toString());
		System.out.println();
		for (Player player : players) {
			System.out.println(player.toString() + " - " + player.showMyCards());
			System.out.println("\tBest hand: " + player.showBestHand());
			System.out.println();
		}
	}

	private PokerHand findWinningHand() {
		PokerHand winningHand = null;
		for (Player player : players) {
			if (winningHand == null) {
				winningHand = player.getBestHand();
			} else if (player.getBestHand().compareTo(winningHand) > 0) {
				winningHand = player.getBestHand();
			}
		}
		return winningHand;
	}

	private void setWinners(PokerHand winningHand) {
		winners = new ArrayList<Player>();
		for (Player player : players) {
			if (player.getBestHand().compareTo(winningHand) == 0) {
				winners.add(player);
			}
		}
	}

	private void printRoundResults() {
		if (winners.size() > 1) {
			System.out.println("Winning hands (tie)");
			for (Player winner : winners) {
				System.out.println(winner.showBestHand() + " " + winner.toString());
			}
			System.out.println();
		} else {
			System.out.println("Winner: " + winners.get(0).toString() + "\n" + winners.get(0).showBestHand());
			System.out.println();
		}
	}

	public void start() {
		Scanner keyboard = new Scanner(System.in);

		// game set up
		if (!gameActive) {
			System.out.print("How many players? ");
			String playersNum = keyboard.next().trim();
			setPlayers(playersNum);
			deck.openDeck();
			createEachPlayer(1);
			gameActive = true;
		}

		// a round of game play
		deck.shuffle();
		dealer.dealCommunity();
		setCommunity(dealer.getCommunity());
		for (int i = 0; i < numOfPlayers; i++) {
			Player cur = players[i];
			cur.setMyCards(dealer.dealPlayerCards());
			cur.payAnte();
			cur.findBestHand(community);
		}
		dealer.collectAnte(numOfPlayers);
		printRound();
		PokerHand winningHand = findWinningHand();
		setWinners(winningHand);
		double prize = dealer.divvyThePot(winners.size());
		for (Player winner : winners) {
			winner.addWinnings(prize);
		}
		printRoundResults();

		// starts new round or end game
		System.out.print("Play another game? <y or n> ");
		String playAgain = keyboard.next().trim();
		if (playAgain.equals("y")) {
			start();
		}
		keyboard.close();
		return;
	}

}
