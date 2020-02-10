/**
 * This class is the blueprint for the players hand.
 * the Hand will consist of 4 cards in an array.
 * These four cards will be the first four in the deck.
 * 
 * @author Chris Hoffman
 */
public class Hand {
	
	private Card[] playerHand;
	private int nextCardIndex = 4;
	
	/**
	 * Constructs the hand object to be an array of 4 Cards.
	 */
	public Hand() {
		this.playerHand = new Card[4];
	}
	
	/**
	 * Places the top 4 cards of the deck into the players hand.
	 * 
	 * @param deck
	 * 			the deck array being used for the poker game
	 */
	public void getDeal( Deck deck ) {
		playerHand[0] = deck.getCard( 0 );
		playerHand[1] = deck.getCard( 1 );
		playerHand[2] = deck.getCard( 2 );
		playerHand[3] = deck.getCard( 3 );
	}
	
	/**
	 * Gets the card at the specified point in the hand.
	 * 
	 * @param cardNum
	 * 			the index of the card in the playerHand array
	 */
	public Card getCard( int cardNum) {
		return playerHand[ cardNum ];
	}
	
	/**
	 * Exchanges the card with the next card in the deck.
	 * 
	 * @param cardNumber
	 * 			the index of the card to be exchanged
	 * @param deck
	 * 			the deck array being used for the poker game		
	 */
	public void exchangeCards( int cardNumber, Deck deck ) {
		playerHand[cardNumber] = deck.getCard( nextCardIndex );
		nextCardIndex++;
	}
	
	/*
	 * Sorts the hand from least to greatest. This makes 
	 * finding straights in a hand easier.
	 */
	public void sortHand() {
		Card temp;
		for( int i = 0; i < playerHand.length; i++ ) {
			for( int j = 0; j < playerHand.length; j++ ) {
				if( playerHand[j].getValue() > playerHand[i].getValue() ) {
					temp = playerHand[j];
					playerHand[j] = playerHand[i];
					playerHand[i] = temp;
				}
			}
		}
	}
	
	/*
	 * Checks if the hand is a pair.
	 */
	public boolean isPair() {
		if( playerHand[0].getName().equals( playerHand[1].getName() ) || playerHand[0].getName().equals( playerHand[2].getName() ) 
			|| playerHand[0].getName().equals( playerHand[3].getName() ) || playerHand[1].getName().equals( playerHand[2].getName() ) 
			|| playerHand[1].getName().equals( playerHand[3].getName() ) || playerHand[2].getName().equals( playerHand[3].getName() ) ) {
			return true;
		}
		return false;
	}
	
	/*
	 * Checks if the hand is a two pair.
	 */
	public boolean isTwoPair() {
		if( playerHand[0].getName().equals( playerHand[1].getName() ) ) {
			if( playerHand[2].getName().equals( playerHand[3].getName() ) ) {
				if( !( playerHand[0].getName().equals( playerHand[2].getName() ) ) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	/*
	 * Checks if the hand is a three of a kind.
	 */
	public boolean isThreeOfKind() {
		if( playerHand[0].getName().equals( playerHand[1].getName() ) && playerHand[1].getName().equals( playerHand[2].getName() ) 
				|| playerHand[0].getName().equals( playerHand[2].getName() ) && playerHand[2].getName().equals( playerHand[3].getName() ) 
				|| playerHand[1].getName().equals( playerHand[2].getName() ) && playerHand[2].getName().equals( playerHand[3].getName() ) 
				|| playerHand[0].getName().equals( playerHand[1].getName() ) && playerHand[1].getName().equals( playerHand[3].getName() ) ) {
			return true;
		}
		return false;
	}
	
	/*
	 * Checks if he hand is a four of a kind.
	 */
	public boolean isFourOfKind() {
		for( int i = 0; i < playerHand.length - 1; i++ ) {
			if( !( playerHand[i].getName().equals( playerHand[i + 1].getName() ) ) ) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Checks if the hand is a straight.
	 */
	public boolean isStraight() {
		for( int i = 0; i < playerHand.length - 1; i++ ) {
			if( !( playerHand[i].getValue() ==  playerHand[i + 1].getValue() - 1) ) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Checks if the hand is a flush.
	 */
	public boolean isFlush() {
		for( int i = 0; i < playerHand.length - 1; i++ ) {
			if( !( playerHand[i].getSuit().equals( playerHand[i+1].getSuit() ) ) ) {
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Checks if the hand is a straight flush.
	 */
	public boolean isStraightFlush() {
		if( isStraight() && isFlush() ) {
			return true;
		}
		return false;
	}
	
}
