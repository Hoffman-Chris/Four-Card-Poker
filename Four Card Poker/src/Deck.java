/**
 * This class will be the blueprint for creating a deck of 52 
 * standard cards. The deck will be formatted as an array of
 * Card objects. This class will also have methods to manipulate
 * the deck.
 * 
 * @author Chris Hoffman
 */
public class Deck {

	private Card[] deck;
	
	/**
	 * constructor for the Deck object. Takes in 52 Card objects and places them in a deck array.
	 * 
	 * @param Card
	 * 			a parameter for each of the 52 cards in a standard deck
	 */
	public Deck( Card c1, Card c2, Card c3, Card c4, Card c5, Card c6, Card c7, Card c8, Card c9,
				 Card c10, Card c11, Card c12, Card c13, Card c14, Card c15, Card c16, Card c17, Card c18,
				 Card c19, Card c20, Card c21, Card c22, Card c23, Card c24, Card c25, Card c26, Card c27, 
				 Card c28, Card c29, Card c30, Card c31, Card c32, Card c33, Card c34, Card c35, Card c36, 
				 Card c37, Card c38, Card c39, Card c40, Card c41, Card c42, Card c43, Card c44, Card c45, 
				 Card c46, Card c47, Card c48, Card c49, Card c50, Card c51, Card c52 ) 
	{
		Card[] tempDeck = { c1, c2, c3, c4, c5, c6, c7, c8, c9,
							c10, c11, c12, c13, c14, c15, c16, c17, c18,
							c19, c20, c21, c22, c23, c24, c25, c26, c27,
							c28, c29, c30, c31, c32, c33, c34, c35, c36, c37,
							c38, c39, c40, c41, c42, c43, c44, c45, c46, 
							c47, c48, c49, c50, c51, c52 };
		
		deck = new Card[tempDeck.length];
		
		for( int i = 0; i < tempDeck.length; i++ ) {
			deck[i] = tempDeck[i];
		}
	}
	
	/**
	 * Shuffles the deck by iterating through and switching the current card index
	 * with another random card in the deck.
	 */
	public void shuffleDeck() {
		int numberOfShuffles = (int) ( Math.random()  * 4 + 1 ) ;
		Card temp;
		
		for( int j = 0; j < numberOfShuffles; j++ ) {
			for( int i = 0; i < deck.length; i++ ) {
				int randSwitchNum = (int) (Math.random() * 52);
				temp = deck[i];
				deck[i] = deck[randSwitchNum];
				deck[randSwitchNum] = temp;
			}
		}	
	}
	
	/**
	 * Gets the card at the specified point in the deck.
	 * 
	 * @param cardNum
	 * 			the index of the card in the deck array
	 * 
	 * @return the card at the specified index
	 */
	public Card getCard( int cardNum ) {
		return deck[ cardNum ];
	}
}
