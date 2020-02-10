/**
 * This class is the blueprint for a Card object.
 * These cards will make up a deck.
 * 
 * @author Chris Hoffman
 */
public class Card {
	private String suit;
	private String name;
	private int value;
	private String img;
	
	/**
	 * Simple constructor.
	 * Creates a card object with a specified suit,
	 * name (i.e Ace, Jack, one, two), and value.
	 * 
	 * @param suit
	 * 			the suit of the card
	 * @param name
	 * 			the name of the card (Ace, King, One, Two)
	 * @param value	
	 * 			the value of the card
	 * @param img
	 * 			the image associated with the card
	 */
	public Card( String suit, String name, int value, String img ) {
		this.suit = suit;
		this.name = name;
		this.value = value;
		this.img = img;
	}
	
	/**
	 * Returns the suit of the specified card.
	 * 
	 * @return the suit of the card
	 */
	public String getSuit() {
		return suit;
	}
	
	/**
	 * Returns the name of the specified card.
	 * 
	 * @return the name of the card
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the value of the specified card.
	 * 
	 * @return the value of the card
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Returns the images string name.
	 * 
	 * @return the image of the card
	 */
	public String getImg() {
		return img;
	}

}
