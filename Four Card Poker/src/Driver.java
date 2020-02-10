import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * This class will make use of it's supplier classes
 * and handle the UX design.
 * 
 * @author Chris Hoffman
 */
public class Driver implements ActionListener, MouseListener {
	private JFrame gameBoard, endScreen;
	private JButton playAgain, deal, re_deal, bet1, bet10, bet100, betMinus1, betMinus10, betMinus100, quit, restart;
	private int chips = 100, bet = 0, wins = 0, loss = 0, rounds = 0, maxBet;
	private JLabel handOutcome, displayBet, displayChips, bankLabel, betLabel;
	private Deck dealer;
	private Card sA, s2, s3, s4, s5, s6, s7, s8, s9, s10, sJ, sQ, sK,
				 hA, h2, h3, h4, h5, h6, h7, h8, h9, h10, hJ, hQ, hK,
				 dA, d2, d3, d4, d5, d6, d7, d8, d9, d10, dJ, dQ, dK,
				 cA, c2, c3, c4, c5, c6, c7, c8, c9, c10, cJ, cQ, cK;
	private Hand player;
	private boolean shouldSwitch = false, shouldBet = false;
	private Image card1, card2, card3, card4, deckImage;
	private Color newGreen = new Color( 100, 200, 30 );
	
	/**
	 * Simple main that creates and instance of the class,
	 * loads up the game, and initializes the deck to be used.
	 */
	public static void main ( String[] args ) {
		Driver game = new Driver();
		game.initializeDeck();
		game.createGameBoard();
		game.playGame();
	}
	
	/**
	 * Creates the layout of the game board.
	 */
	private void createGameBoard() {
		int cardSizeX = 100;
		int cardSizeY = 180;
		int cardLocationX = 50;
		int cardLocationY = 400;
		int boardSize = 800;
		int boardLocationX = 500;
		int boardLocationY = 200;
		int deckLocationX = ( boardSize - cardSizeX ) / 2;
		int deckLocationY = 70;
		int buttonSizeX = 100;
		int buttonSizeY = 40;
		int buttonLocX = ( boardSize - buttonSizeX ) / 2;
		int buttonLocY = 650;
		int outcomeSizeX = 120;
		int outcomeSizeY = 40;
		int outcomeLocX = ( boardSize - outcomeSizeX ) / 2;
		int outcomeLocY = 300;
		int chipButtonSizeX = 70;
		int chipButtonSizeY = 40;
		int chipButtonLocX = boardSize - chipButtonSizeX;
		int chipButtonLocY = 0;
		int quitButtonLocY = boardSize - buttonSizeY * 2 + 5;
		
		Color newYellow = new Color( 255, 255, 100 );
		
		gameBoard = new JFrame( "Poker Game" );
		gameBoard.setVisible( true );
		gameBoard.setLayout( null );
		gameBoard.setSize( boardSize, boardSize );
		gameBoard.setLocation( boardLocationX, boardLocationY );
		gameBoard.setResizable( false );
		gameBoard.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		gameBoard.getContentPane().setBackground( newGreen );
				
		handOutcome = new JLabel();
		handOutcome.setLocation( outcomeLocX, outcomeLocY );
		handOutcome.setSize( outcomeSizeX, outcomeSizeY );
		handOutcome.setOpaque( true );
		handOutcome.setVisible( true );
		handOutcome.setBackground( newYellow );
		
		bet100 = new JButton( "+100" ); 
		bet100.setSize( chipButtonSizeX, chipButtonSizeY );
		bet100.setLocation( chipButtonLocX, chipButtonLocY );
		bet100.setVisible( true );
		bet100.addActionListener( this );
		
		bet10 = new JButton( "+10" );
		bet10.setSize( chipButtonSizeX, chipButtonSizeY );
		bet10.setLocation( chipButtonLocX - chipButtonSizeX, chipButtonLocY );
		bet10.setVisible( true );
		bet10.addActionListener( this );
		
		bet1 = new JButton( "+1" );
		bet1.setSize( chipButtonSizeX, chipButtonSizeY );
		bet1.setLocation( chipButtonLocX - chipButtonSizeX * 2, chipButtonLocY );
		bet1.setVisible( true );
		bet1.addActionListener( this );
		
		displayBet = new JLabel( "0" );
		displayBet.setLocation( chipButtonLocX - chipButtonSizeX * 3, chipButtonLocY );
		displayBet.setSize( chipButtonSizeX, chipButtonSizeY );
		displayBet.setOpaque( true );
		displayBet.setVisible( true );
		displayBet.setBackground( newYellow );
		
		betMinus1 = new JButton( "-1" );
		betMinus1.setSize( chipButtonSizeX, chipButtonSizeY );
		betMinus1.setLocation( chipButtonLocX - chipButtonSizeX * 4, chipButtonLocY );
		betMinus1.setVisible( true );
		betMinus1.addActionListener( this );
		
		betMinus10 = new JButton( "-10" );
		betMinus10.setSize( chipButtonSizeX, chipButtonSizeY );
		betMinus10.setLocation( chipButtonLocX - chipButtonSizeX * 5, chipButtonLocY );
		betMinus10.setVisible( true );
		betMinus10.addActionListener( this );
		
		betMinus100 = new JButton( "-100" );
		betMinus100.setSize( chipButtonSizeX, chipButtonSizeY );
		betMinus100.setLocation( chipButtonLocX - chipButtonSizeX * 6, chipButtonLocY );
		betMinus100.setVisible( true );
		betMinus100.addActionListener( this );
		
		bankLabel = new JLabel( "Bank: " );
		bankLabel.setSize( chipButtonSizeX, chipButtonSizeY );
		bankLabel.setLocation( 0, 0 );
		bankLabel.setVisible( true );
		
		displayChips = new JLabel( chips + "" );
		displayChips.setSize( chipButtonSizeX, chipButtonSizeY );
		displayChips.setLocation( 0 + chipButtonSizeX, chipButtonLocY );
		displayChips.setOpaque( true );
		displayChips.setVisible( true );
		displayChips.setBackground( newYellow );
		
		betLabel = new JLabel( "Bet: " );
		betLabel.setSize( chipButtonSizeX, chipButtonSizeY );
		betLabel.setLocation( chipButtonLocX - chipButtonSizeX * 7, chipButtonLocY );
		betLabel.setVisible( true );
		
		playAgain = new JButton( "Play Again?" );
		playAgain.setSize( buttonSizeX, buttonSizeY );
		playAgain.setLocation( buttonLocX, buttonLocY );
		playAgain.setVisible( false );
		playAgain.addActionListener( this );
		
		deal = new JButton( "Deal" );
		deal.setSize( buttonSizeX, buttonSizeY );
		deal.setLocation( buttonLocX + buttonSizeX, buttonLocY );
		deal.setVisible( true );
		deal.addActionListener( this );
		
		re_deal = new JButton( "Re-Deal" );
		re_deal.setSize( buttonSizeX, buttonSizeY );
		re_deal.setLocation( buttonLocX + buttonSizeX * 2, buttonLocY );
		re_deal.setVisible( false );
		re_deal.addActionListener( this );
		
		quit = new JButton( "Quit" );
		quit.setSize( buttonSizeX, buttonSizeY );
		quit.setLocation( 0,  quitButtonLocY );
		quit.setVisible( true );
		quit.addActionListener( this );
		
		card1 = new Image( cardLocationX, cardLocationY, cardSizeX, cardSizeY );
		card2 = new Image( cardLocationX + cardSizeX * 2, cardLocationY, cardSizeX, cardSizeY );
		card3 = new Image( cardLocationX + cardSizeX * 4, cardLocationY, cardSizeX, cardSizeY );
		card4 = new Image( cardLocationX + cardSizeX * 6, cardLocationY, cardSizeX, cardSizeY );
		
		card1.addMouseListener( this );
		card2.addMouseListener( this );
		card3.addMouseListener( this );
		card4.addMouseListener( this );
		
		deckImage = new Image( deckLocationX, deckLocationY, cardSizeX, cardSizeY, "red_back.png" );
		
		gameBoard.add( card1, 0 );
		gameBoard.add( card2, 0 );
		gameBoard.add( card3, 0 );
		gameBoard.add( card4, 0 );
		gameBoard.add( deckImage );
		gameBoard.add( bet100 );
		gameBoard.add( bet10 );
		gameBoard.add( bet1 );
		gameBoard.add( displayBet );
		gameBoard.add( betMinus1 );
		gameBoard.add( betMinus10 );
		gameBoard.add( betMinus100 );
		gameBoard.add( bankLabel );
		gameBoard.add( displayChips );
		gameBoard.add( betLabel );
		gameBoard.add( playAgain );
		gameBoard.add( handOutcome );
		gameBoard.add( re_deal );
		gameBoard.add( deal );
		gameBoard.add( quit );
		gameBoard.repaint();
	}
	
	/**
	 * Creates an ending screen when the user decides to quit.
	 * Displays the number of rounds won, number of rounds lost, and 
	 * the number of chips won overall.
	 */
	private void createEndScreen() {
		
		int screenLocationX = 500;
		int screenLocationY = 200;
		int screenSize = 500;
		int labelSizeX = 100;
		int labelSizeY = 30;
		int labelLocX = ( screenSize - labelSizeX ) / 2;
		int labelLocY = 50;
		int graphicSizeX = 200;
		int graphicSizeY = 100;
		double chipConversion = ( (int) ( ( chips + bet ) * 1.61 * 100 ) ) / 100.0;
		
		
		endScreen = new JFrame( "Thank's For Playing!" );
		endScreen.setSize( screenSize, screenSize );
		endScreen.setLocation( screenLocationX, screenLocationY );
		endScreen.getContentPane().setBackground( newGreen );
		endScreen.setLayout( null );
		endScreen.setVisible( true );
		
		gameBoard.setVisible( false );
		
		JLabel endBank = new JLabel( "Bank: $" + chipConversion );
		endBank.setLocation( labelLocX, labelLocY );
		endBank.setSize( labelSizeX, labelSizeY );
		endBank.setVisible( true );
		
		JLabel roundsPlayed = new JLabel( "Rounds: " + rounds );
		roundsPlayed.setLocation( labelLocX , labelLocY + labelSizeY );
		roundsPlayed.setSize( labelSizeX, labelSizeY );
		roundsPlayed.setVisible( true );
		
		JLabel winsAcquired = new JLabel( "Wins: " + wins );
		winsAcquired.setLocation( labelLocX , labelLocY + labelSizeY * 2 );
		winsAcquired.setSize( labelSizeX, labelSizeY );
		winsAcquired.setVisible( true );
		
		JLabel lossesAcquired = new JLabel( "Losses: " + loss );
		lossesAcquired.setLocation( labelLocX , labelLocY + labelSizeY * 3 );
		lossesAcquired.setSize( labelSizeX, labelSizeY );
		lossesAcquired.setVisible( true );
		
		restart = new JButton( "Restart" );
		restart.setSize( labelSizeX, labelSizeY );
		restart.setLocation( labelLocX , labelLocY + labelSizeY * 5 );
		restart.setVisible( true );
		restart.addActionListener( this );
		
		Image graphic = new Image( ( endScreen.getWidth() - graphicSizeX ) / 2, labelLocY +labelSizeY * 7, graphicSizeX, graphicSizeY, "honors_spade-14.png" );

		
		endScreen.add( endBank );
		endScreen.add( roundsPlayed );
		endScreen.add( winsAcquired );
		endScreen.add( lossesAcquired );
		endScreen.add( graphic );
		endScreen.add( restart );
	
	}
	
	/**
	 * initializes the deck that will be used by the dealer
	 */
	private void initializeDeck() {
		
		s2 = new Card( "Spade", "2", 2, "2S.png" );
		s3 = new Card( "Spade", "3", 3, "3S.png" );
		s4 = new Card( "Spade", "4", 4, "4S.png" );
		s5 = new Card( "Spade", "5", 5, "5S.png" );
		s6 = new Card( "Spade", "6", 6, "6S.png" );
		s7 = new Card( "Spade", "7", 7, "7S.png" );
		s8 = new Card( "Spade", "8", 8, "8S.png" );
		s9 = new Card( "Spade", "9", 9, "9S.png" );
		s10 = new Card( "Spade", "10", 10, "10S.png" );
		sJ = new Card( "Spade", "Jack", 11, "JS.png" );
		sQ = new Card( "Spade", "Queen", 12, "QS.png" );
		sK = new Card( "Spade", "King", 13, "KS.png" );
		sA = new Card( "Spade", "Ace", 14, "AS.png" );
		
		h2 = new Card( "Heart", "2", 2, "2H.png" );
		h3 = new Card( "Heart", "3", 3, "3H.png" );
		h4 = new Card( "Heart", "4", 4, "4H.png" );
		h5 = new Card( "Heart", "5", 5, "5H.png" );
		h6 = new Card( "Heart", "6", 6, "6H.png" );
		h7 = new Card( "Heart", "7", 7, "7H.png" );
		h8 = new Card( "Heart", "8", 8, "8H.png" );
		h9 = new Card( "Heart", "9", 9, "9H.png" );
		h10 = new Card( "Heart", "10", 10, "10H.png" );
		hJ = new Card( "Heart", "Jack", 11, "JH.png" );
		hQ = new Card( "Heart", "Queen", 12, "QH.png" );
		hK = new Card( "Heart", "King", 13, "KH.png" );
		hA = new Card( "Heart", "Ace", 14, "AH.png" );

		d2 = new Card( "Diamond", "2", 2, "2D.png" );
		d3 = new Card( "Diamond", "3", 3, "3D.png" );
		d4 = new Card( "Diamond", "4", 4, "4D.png" );
		d5 = new Card( "Diamond", "5", 5, "5D.png" );
		d6 = new Card( "Diamond", "6", 6, "6D.png" );
		d7 = new Card( "Diamond", "7", 7, "7D.png" );
		d8 = new Card( "Diamond", "8", 8, "8D.png" );
		d9 = new Card( "Diamond", "9", 9, "9D.png" );
		d10 = new Card( "Diamond", "10", 10, "10D.png" );
		dJ = new Card( "Diamond", "Jack", 11, "JD.png" );
		dQ = new Card( "Diamond", "Queen", 12, "QD.png"  );
		dK = new Card( "Diamond", "King", 13, "KD.png" );
		dA = new Card( "Diamond", "Ace", 14, "AD.png" );
		
		c2 = new Card( "Club", "2", 2, "2C.png" );
		c3 = new Card( "Club", "3", 3, "3C.png" );
		c4 = new Card( "Club", "4", 4, "4C.png" );
		c5 = new Card( "Club", "5", 5, "5C.png" );
		c6 = new Card( "Club", "6", 6, "6C.png" );
		c7 = new Card( "Club", "7", 7, "7C.png" );
		c8 = new Card( "Club", "8", 8, "8C.png" );
		c9 = new Card( "Club", "9", 9, "9C.png" );
		c10 = new Card( "Club", "10", 10, "10C.png" );
		cJ = new Card( "Club", "Jack", 11, "JC.png" );
		cQ = new Card( "Club", "Queen", 12, "QC.png" );
		cK = new Card( "Club", "King", 13, "KC.png" );
		cA = new Card( "Club", "Ace", 14, "AC.png");
		
		dealer = new Deck( sA, s2, s3, s4, s5, s6, s7, s8, s9, s10, sJ, sQ, sK,
						   hA, h2, h3, h4, h5, h6, h7, h8, h9, h10, hJ, hQ, hK, 
						   dA, d2, d3, d4, d5, d6, d7, d8, d9, d10, dJ, dQ, dK, 
						   cA, c2, c3, c4, c5, c6, c7, c8, c9, c10, cJ, cQ, cK );					   
	}
	
	/*
	 * Displays the cards on the game board that are in the
	 * players hand.
	 */
	private void displayHand() {
		card1.setImage( player.getCard( 0 ).getImg() );
		card2.setImage( player.getCard( 1 ).getImg() );
		card3.setImage( player.getCard( 2 ).getImg() );
		card4.setImage( player.getCard( 3 ).getImg() );

		gameBoard.repaint();

	}
	
	/**
	 * displays the outcome of the hand
	 */
	private void checkHand() {
		if( player.isStraightFlush() ) {
			handOutcome.setText( "Straight Flush" );
			chips += bet * 200;
			wins++;
			
		}
		else if( player.isStraight() ) {
			handOutcome.setText( "Straight" );
			chips += bet * 25;
			wins++;
		}
		else if( player.isFlush() ) {
			handOutcome.setText( "Flush" );
			chips += bet * 50;
			wins++;
		}
		else if( player.isFourOfKind() ) {
			handOutcome.setText( "Four Of a Kind" );
			chips += bet * 100;
			wins++;
		}
		else if( player.isThreeOfKind() ) {
			handOutcome.setText( "Three Of a Kind" );
			chips += bet * 15;
			wins++;
		}
		else if( player.isTwoPair() ) {
			handOutcome.setText( "Two Pair" );
			chips += bet * 10;
			wins++;
		}
		else if( player.isPair() ) {
			handOutcome.setText( "Pair" );
			chips += bet * 2;
			wins++;
		}
		else {
			handOutcome.setText( "Loss" );
			loss++;
		}
		
		bet = 0;
		rounds++;
		
		if( chips == 0 ) {
			createEndScreen();
		}
		
		shouldSwitch = false;
		shouldBet = false;
	}
	
	/**
	 * Hides the cards in the players hand prior to 
	 * being dealt.
	 */
	private void hideCards() {
		card1.setVisible( false );
		card2.setVisible( false );
		card3.setVisible( false );
		card4.setVisible( false );
	}
	
	/**
	 * Shows the cards in the players hand.
	 */
	private void showCards() {
		card1.setVisible( true );
		card2.setVisible( true );
		card3.setVisible( true );
		card4.setVisible( true );
	}
	
	/**
	 * Checks the players bet to make sure it is valid.
	 * Makes changes to the bet as needed.
	 */
	private void checkBet() {
		
		if( bet > maxBet ) {
			bet = maxBet;
			chips = 0;
		}
		
		if( bet < 0 ) {
			bet = 0;
			chips = maxBet;
		}
		
	}
	
	/**
	 * Runs the core mechanics of the game.
	 */
	private void playGame() {
		maxBet = chips;
		player = new Hand();
		dealer.shuffleDeck();
		player.getDeal( dealer );
		player.sortHand();
		displayHand();
		hideCards();
	
		handOutcome.setText( "" );
		
		//play again will only appear after the round is played
		playAgain.setVisible( false );
		re_deal.setVisible( false );
		deal.setVisible( true );
		
		shouldSwitch = true;
		shouldBet = true;
	}

	@Override
	public void actionPerformed( ActionEvent e ) {
		if( e.getSource() == playAgain ) {
			playGame();
		}
		else if( e.getSource() == re_deal ) {
			
			//re display the cards that disappeared when switching them out
			showCards();
			
			re_deal.setVisible( false );
			displayHand();
			player.sortHand();
			
			//checks the new set of cards
			checkHand();
			playAgain.setVisible( true );
		}
		else if( e.getSource() == deal ) {
			deal.setVisible( false );
			showCards();
			shouldBet = false;
			re_deal.setVisible( true );
		}
		else if( e.getSource() == quit ) {
			createEndScreen();
			endScreen.setVisible( true );
		}
		else if( e.getSource() == restart ) {
			rounds = 0;
			wins = 0;
			loss = 0;
			chips = 100;
			bet = 0;
			endScreen.setVisible( false );
			gameBoard.setVisible( true );
			playGame();
		}
		
		if( shouldBet ) {
			if( e.getSource() == bet1 ) {
				bet += 1;
				chips -= 1;
				checkBet();
			}
			else if( e.getSource() == bet10 ) {
				bet += 10;
				chips -= 10;
				checkBet();
			}
			else if( e.getSource() == bet100 ) {
				bet += 100;
				chips -= 100;
				checkBet();
			}
			else if( e.getSource() == betMinus1 ) {
				bet -= 1;
				chips += 1;
				checkBet();
			}
			else if( e.getSource() == betMinus10 ) {
				bet -= 10;
				chips += 10;
				checkBet();
			}
			else if( e.getSource() == betMinus100 ) {
				bet -= 100;
				chips += 100;
				checkBet();
			}
			
			displayBet.setText( bet + "" );
			displayChips.setText( chips + "" );
		}
		
		gameBoard.repaint();
	}

	@Override
	public void mouseClicked( MouseEvent e ) {
		//switches out cards when clicked only if it is at the appropriate time
		if( shouldSwitch ) {
			if( (Image)e.getSource() == card1 ) {
				player.exchangeCards( 0, dealer );
				card1.setVisible( false );
			}
			else if( (Image)e.getSource() == card2 ) {
				player.exchangeCards( 1, dealer );
				card2.setVisible( false );
			}
			else if( (Image)e.getSource() == card3 ) {
				player.exchangeCards( 2, dealer );
				card3.setVisible( false );
			}
			else if( (Image)e.getSource() == card4 ) {
				player.exchangeCards( 3, dealer );
				card4.setVisible( false );
			}	
		}
	}

	@Override
	public void mouseEntered( MouseEvent e ) {
		
		if( shouldSwitch ) {
			if( (Image)e.getSource() == card1 ) {
				card1.setLocation( card1.getX(), card1.getY() - 20 );
			}
			else if( (Image)e.getSource() == card2 ) {
				card2.setLocation( card2.getX(), card2.getY() - 20 );
			}
			else if( (Image)e.getSource() == card3 ) {
				card3.setLocation( card3.getX(), card3.getY() - 20 );
			}
			else if( (Image)e.getSource() == card4 ) {
				card4.setLocation( card4.getX(), card4.getY() - 20 );
			}
			gameBoard.repaint();
		}
	}

	@Override
	public void mouseExited( MouseEvent e ) {
		if( shouldSwitch ) {
			if( (Image)e.getSource() == card1 ) {
				card1.setLocation( card1.getX(), card1.getY() + 20 );
			}
			else if( (Image)e.getSource() == card2 ) {
				card2.setLocation( card2.getX(), card2.getY() + 20 );
			}
			else if( (Image)e.getSource() == card3 ) {
				card3.setLocation( card3.getX(), card3.getY() + 20 );
			}
			else if( (Image)e.getSource() == card4 ) {
				card4.setLocation( card4.getX(), card4.getY() + 20 );
			}
			gameBoard.repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

}
