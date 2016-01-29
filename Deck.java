
import java.util.Vector;
import java.util.Random;
/*
 * 	
 * 		My program Deck will ideally create a deck of cards by implementing the
 * data structure, Vector(), of type card (which will be a class I shall create),
 * that is ordered by descending value and by suit.  It should print out starting
 * with the Ace of Hearts and ending with the 2 of Diamonds. After the initial deck
 * is created I will then implement a shuffling method that will shuffle the deck 
 * completely at random by using the Java utility, Random(). The algorithm I am 
 * using implements a nested for loop in order to add <card>s to the initialized
 * Vector<card>. The first for loop's int variable represents the suit(Heart, Club,
 * Spade, Diamond) of the card and the second for loop's int variable represents 
 * the card number (1-13). It then uses an if-else statement in order to ensure 
 * that the Ace card will be organized at the top of each stack of its respective 
 * suit. The algorithm I implemented in my shuffleDeck() I will explain in more 
 * detail at the method itself. */
 
 /*		This class creates an object called card with two integers, the card
 * number and suit. The card suit will correspond to a String based upon which
 * int is is(0 is Hearts, 1 is Clubs, etc). The card number may correspond to
 * a String if its value is 1(Ace), 11(Jack), 12(Queen), or 13(King). Otherwise,
 * it is just a number. There are two constructors. One has parameters and one
 * does not. The card without parameters corresponds to its own String which 
 * tells you that it is not a card. There are two basic getters and setters.
 * The toString() method uses a series of if-else statements in order to 
 * decipher which suit and which number to print out. It then returns a 
 * String. */
 class card {
		private int CardNum;
		private int CardSuit;
	
	public card(){
		this.CardNum = -1;
		this.CardSuit = -1;
	}
	public card(int cNum, int cSuit){
		this.CardNum = cNum;
		this.CardSuit = cSuit;
	}
	
	public int getCardNum(){
		return CardNum;
	}
	public int getCardSuit(){
		return CardSuit;
	}
	public void setCardNum(int cN){
		this.CardNum = cN;
	}
	public void setCardSuit(int cS){
		this.CardSuit = cS;
	}
	
	public String toString(){
		String cN = null;
		String cS = null;
		if(CardSuit == 0){cS = "Hearts";}
		if(CardSuit == 1){cS = "Clubs";}
		if(CardSuit == 2){cS = "Spades";}
		if(CardSuit == 3){cS = "Diamonds";}
		if(CardNum == 1){cN = "Ace";}
		else if(CardNum == 11){cN = "Jack";}
		else if(CardNum == 12){cN = "Queen";} 
		else if(CardNum == 13){cN = "King";}
		else cN = String.valueOf(CardNum);
		if(CardSuit==-1 || CardNum==-1){return "This is not a card.";}
		return cN + " of " + cS + "\n";
		
	}	
}
 
 
 
public class Deck {
	
	
	/*		My main method implements the methods createDeck(), shuffleDeck(), 
	 * and then utilizes my toString() method for card() via a for loop in order
	 * to print my deck, both the initial deck and the shuffled one. The main
	 * method also utilized Random() in order to generate a random number between 
	 * 20 and 30 in order to decide how many times the deck will be shuffled.*/
	public static void main(String[] args) {
		Vector<card> deck = createDeck();
		for(int i = 0; i < 52; i++){
			System.out.print(deck.get(i).toString());
			if((i+1)%13 == 0)
				System.out.print("\n");
		}
		Random rGen = new Random(); 
		int timesShuff = rGen.nextInt(11) + 20;
		System.out.println("Your deck will be shuffled " + timesShuff + " times. \n");
		for(int i = 0; i < timesShuff; i++){	
			deck = shuffleDeck(deck);
		}
		System.out.println("Your newly shuffled deck: ");
		for(int i = 0; i < 52; i++){
			System.out.print(deck.get(i).toString());
		}
	}
	
	
	/*		My method, createDeck(), initializes a new Vector<card> of capacity 52
	 * and then utilizes a nested for loop in order to add new cards to the deck. 
	 * In the card(j,i), j represents the card number and i represents the suit. 
	 * The if-else statements check for the Ace and then adds it into the vector 
	 * above the King. This method then returns a Vector<card>.*/
	 public static Vector<card> createDeck(){
		 Vector<card> DeckOfCards = new Vector<>(52);
			for(int i = 0; i < 4; i++){
				for(int j = 13; j>=1; j--){
					card nCard = new card(j, i);
					if(j==1)
						DeckOfCards.add(i*13, nCard);
					else
						DeckOfCards.add(nCard);	
				}
			}
		return DeckOfCards;	
	 }
	 
	 
	 /*		This method, shuffleDeck(), initializes a new Vector<card> of default
	  * capacity, and sets its size to the same size as our original deck. This 
	  * new Vector will have a null; statements at each index. The x variable
	  * will be a random int generated between 0 and the size of our original deck,
	  * z will be a random int generated beteween 0 and the size of sDeck, and y 
	  * will be a decreasing variable that will match the size of our oD(original 
	  * deck) as it shrinks. The algorithm I used to shuffle implements a while loop
	  * that will run as long as our original deck is not empty. Each iteration will
	  * create a new random x and z. The first if statement checks to make sure the
	  * object at the indicated index of oD is not a null, and the second verifies 
	  * that the object at the indicated index of sDeck is null, otherwise it 
	  * implements continue;. If all conditions are met the loop will add the card()
	  * at oD.get(x) into the z index, remove the card at the x index of oD, and lower
	  * the value of y by one. This method returns sDeck of type Vector<card>. */
	 public static Vector<card> shuffleDeck(Vector<card> oD){
		 Vector<card> sDeck = new Vector<>();
		 sDeck.setSize(oD.size());
		 Random shuffler = new Random();
		 int x,z;
		 int y = oD.size();
		 while(oD.isEmpty() != true){
			x = shuffler.nextInt(y);
			z = shuffler.nextInt(sDeck.size());
			if(oD.get(x) != null){
				if(sDeck.get(z) != null)
					continue;
				sDeck.set(z, oD.get(x));
				oD.remove(x);
				y--;
			}
		 }
		 return sDeck;
	 }
	


}
