package week6;

import java.util.Scanner;

public class ThreeCardPoker {
    private static final int STARTING_WALLET = 500;
    private static final int MIN_BET = 50;
    private static final int MAX_BET = 100;
    
    private static final double NUM_FACES = 13;
    private static final int JACK = 11;
    private static final int QUEEN = 12;
    private static final int KING = 13;
    private static final int ACE = 14;

    private static final double NUM_SUITS = 4;
    private static final int HEARTS = 0;
    private static final int DIAMONDS = 1;
    private static final int CLUBS = 2;
    private static final int SPADES = 3;

    private static final int STRAIGHT_FLUSH = 40;
    private static final int THREE_OF_A_KIND = 30;
    private static final int STRAIGHT = 6;
    private static final int FLUSH = 3;
    private static final int PAIR = 1;
    private static final int HIGH_CARD = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to 3 card poker. Your wallet has $" + STARTING_WALLET + ".");
        Scanner in = new Scanner(System.in);
        boolean isGameOver = false;
        
        while(!isGameOver){
            int playerWallet = 500;
            int anteWager = getAnteWager(in); 
            playerWallet -= anteWager;  

            int pairPlus = getPairPlusWager(in);

            String playerHand = dealCards();
            String dealerHand = dealCards();
   
            System.out.println("Player: " + playerHand);
            
            
            System.out.println("Dealer: ?? ?? ??");

            boolean continueBet = continueRound(in, playerWallet);
            if(continueBet == true){
                playerWallet -= anteWager; // subtract the ante wager bc its the same as play wager
                System.out.println("Dealer: " + dealerHand);

                // check to see if the dealer qualifies with at least a queen high
                int dealerHighestCard = highestCard(dealerHand);
                int dealerHandType = handType(dealerHand);
                
                if((dealerHandType == 0 && dealerHighestCard < QUEEN) || dealerHighestCard < QUEEN){ // if dealer has jack-high or worse
                    playerWallet += anteWager;
                    System.out.println("Since the dealer has jack-high or worse, your play wager of $" + anteWager + " has been returned. And your pair plus wager of $" + pairPlus + " has been returned. Your balance is now $" + playerWallet);
                    boolean playAgain = playAnother(in);
                }
                else // the dealer has a queen high 
                    if (dealerHandType == STRAIGHT_FLUSH)
                        System.out.println("The dealer has a straight flush.");
                    else if(dealerHandType == THREE_OF_A_KIND)
                        System.out.println("The dealer has a three of a kind.");
                    else if(dealerHandType == STRAIGHT)
                        System.out.println("The dealer has a straight.");
                    else if(dealerHandType == FLUSH)
                        System.out.println("The dealer has a flush.");
                    else if(dealerHandType == PAIR)
                        System.out.println("The dealer has a pair.");
                // find out who wins
                int winner = getWinner(playerHand, dealerHand, anteWager);
                int winnings = payOut(winner, anteWager, playerWallet, pairPlus);
                int playerHandType = handType(playerHand);
                int pairPlusWins = getPairPlusWinnings(playerHandType, playerWallet, pairPlus, winner, anteWager, in);
            }   
            else if(continueBet == false){
                System.out.println("Game over! The dealer collected your ante wager of $" + anteWager + " and your pair plus wager (if applicable) of $" + pairPlus + ".");
                System.out.println("Your current balance is: $" + playerWallet);
                boolean playAgain = playAnother(in);
            }
        }
        
    }
    
    /**
     * asks if the player wants to play again 
     * @param in
     * @param isGameOver
     * @return
     */
    private static boolean playAnother(Scanner in) {
        boolean validInput = false;
        while(!validInput){
            System.out.print("Do you want to play again? (y/n): ");
            String play = in.nextLine();
            if(play.equals("y")){ 
                validInput = true;
                return true;
            }else if (play.equals("n")){
                System.out.println("Thanks for playing! ");
                return true;
            }else {
                System.out.print("Please enter a valid answer (y/n): ");
                play = in.nextLine();
            }
        }
        return false;
        
    }

    /**
     * determines if the player gets any pair plus winnings, and if so how much 
     * @param playerHandType 
     * @param playerWallet
     * @param pairPlus
     * @return the amount of pair plus winnings 
     */
    private static int getPairPlusWinnings(int playerHandType, int playerWallet, int pairPlus, int winner, int anteWager, Scanner in) {
        if(playerHandType < PAIR){
            if(winner == 3){ // a tie
                playerWallet -= anteWager;
                playerWallet -= pairPlus;
                System.out.println("You don't have a special hand. You lost your pair plus wager of $" + pairPlus + ".");
                System.out.println("Your current balance is: $" + playerWallet + ".");
            }else{
                playerWallet -= pairPlus;
                System.out.println("You don't have a special hand. You lost your pair plus wager of $" + pairPlus + ".");
                System.out.println("Your current balance is: $" + playerWallet + ".");
                boolean playAgain = playAnother(in);
            }
        }
        else if(playerHandType == STRAIGHT_FLUSH){
            playerWallet += pairPlus; // original wager they bet
            playerWallet += STRAIGHT_FLUSH * pairPlus; // pair plus bonus (40:1)
            System.out.println("Congradulations! You won a pair plus bonus of $" + STRAIGHT_FLUSH * pairPlus + ". And you get your pair plus wager of $" + pairPlus + " back.");
            System.out.println("Your current balance is: $" + playerWallet + ".");
            boolean playAgain = playAnother(in);
        }else if(playerHandType == THREE_OF_A_KIND){
            playerWallet += pairPlus; // original wager they bet
            playerWallet += THREE_OF_A_KIND * pairPlus; // pair plus bonus (30:1)
            System.out.println("Congradulations! You won a pair plus bonus of $" + THREE_OF_A_KIND * pairPlus + ".");
            System.out.println("Your current balance is: $" + playerWallet + ".");
            boolean playAgain = playAnother(in);
        }else if(playerHandType == STRAIGHT){
            playerWallet += (pairPlus + (STRAIGHT * pairPlus)); // original wager they bet
            //playerWallet += STRAIGHT * pairPlus; // pair plus bonus (6:1)
            System.out.println("Congradulations! You won a pair plus bonus of $" + STRAIGHT * pairPlus + ".");
            System.out.println("Your current balance is: $" + playerWallet + ".");
            boolean playAgain = playAnother(in);
        }else if(playerHandType == FLUSH){
            playerWallet += (pairPlus + (FLUSH * pairPlus)); // original wager they bet
            //playerWallet += FLUSH * pairPlus; // pair plus bonus (3:1)
            System.out.println("Congradulations! You won a pair plus bonus of $" + FLUSH * pairPlus + ".");
            System.out.println("Your current balance is: $" + playerWallet + ".");
            boolean playAgain = playAnother(in);
        }else if(playerHandType == PAIR){
            if(winner == 2){ // dealer won
                playerWallet += PAIR * pairPlus; // pair plus bonus (1:1)
                System.out.println("Congradulations! You won a pair plus bonus of $" + PAIR * pairPlus + ".");
                System.out.println("Your current balance is: $" + playerWallet + ".");
                boolean playAgain = playAnother(in);
            
            }else if(winner == 1){ // dealer won
                playerWallet += pairPlus;
            }
            else
            playerWallet += pairPlus; // original wager they bet
            playerWallet += PAIR * pairPlus; // pair plus bonus (1:1)
            System.out.println("Congradulations! You won a pair plus bonus of $" + PAIR * pairPlus + ".");
            System.out.println("Your current balance is: $" + playerWallet + ".");
            boolean playAgain = playAnother(in);
        }
        return playerWallet;
    }
    /**
     * determines how much money the player gains or loses
     * @param winner
     * @param anteWager
     * @param playerWallet
     * @param pairPlus
     * @return amount of money won or lost
     */
    private static int payOut(int winner, int anteWager, int playerWallet, int pairPlus) {
        if(winner == 1){
            playerWallet +=  (2 * anteWager); //ante and play wager are paid out 1 to 1
            System.out.println("You win! You won your ante wager of $" + anteWager + " and your play wager of $" + anteWager + ".");
            System.out.println("Your current balance is: $" + playerWallet + ".");
        }
        else if(winner == 2){
            //playerWallet += pairPlus; // add the pair plus back bc the next line will subtract the pair plus wager (or else it will subtract twice)
            
            System.out.println("You lost! You lost your ante wager of $" + anteWager + ", and your play wager of $" + anteWager + ".");
            System.out.println("Your current balance is: $" + playerWallet + ".");
        }
        else if(winner == 3){
            playerWallet += anteWager;
            System.out.println("It's a tie! You won your play wager of $" + anteWager + " but you lost your ante wager of $" + anteWager + ".");
            System.out.println("Your current balance is: $" + playerWallet + ".");
        }
        return winner;
    }
    /**
     * checks to see if the dealer or player won
     * 1 = player won
     * 2 = dealer won
     * 3 = push
     * @param playerHand
     * @param dealerHand
     * @return if player wins, dealer wins, or tie -> 1,2, or 3 (respectively)
     */
    private static int getWinner(String playerHand, String dealerHand, int anteWager) {
        int playerHandType = handType(playerHand);
        int dealerHandType = handType(dealerHand);
        if (playerHandType > dealerHandType){
            return 1;
        }else if (dealerHandType > playerHandType){
            return 2;
        }else{ // they both have the same hand type, so need to find out who has highest card
            int playerHighestCard = getMax(playerHand);
            int dealerHighestCard = getMax(dealerHand);
            if(playerHighestCard > dealerHighestCard){
                return 1; 
            }
            else if(dealerHighestCard > playerHighestCard){
                return 2;
            }

        return 3; // a tie
        }
    }
    /**
     * finds the highest card value
     * @param hand 
     * @return highest card in hand
     */
    private static int getMax(String hand) {
        //identifies each space in the hand
        int space1 = hand.indexOf(" ");
        int space2 = hand.indexOf(" ", space1 + 1);
        int space3 = hand.indexOf(" ", space2 + 1);
 
        String first = hand.substring(0, space1);
        String second = hand.substring(space1 + 1, space2);
        String third = hand.substring(space2 + 1, space3);
 
        int cardVal1 = getCardVal(first);
        int cardVal2 = getCardVal(second);      
        int cardVal3 = getCardVal(third);

        int get_max = Math.max(cardVal1, Math.max(cardVal2, cardVal3));
        return get_max;
    }
    /**
     * Asks the player if they want to play or fold, after seeing cards
     * @return true or false, depending if player answers "p" or "f"
     */
    private static boolean continueRound(Scanner in, int playerWallet) {
        System.out.print("Would you like to put a play wager or fold? (p/f): ");
        String continueAnswer = in.nextLine().toLowerCase();
        
        boolean validInput = false;
        while(!validInput){
            if(continueAnswer.equals("p")){
                return true;
            }
            else if(continueAnswer.equals("f"))
                return false;
        }
        return false;
    }
    /**
     * determines if dealer or player have a special hand
     * @param hand
     * @return an int value that corresponds to each special hand (i.e. pair = 1)
     */
    private static int handType(String hand) {
        //identifies each space in the hand
        int space1 = hand.indexOf(" ");
        int space2 = hand.indexOf(" ", space1 + 1);
        int space3 = hand.indexOf(" ", space2 + 1);
 
        String first = hand.substring(0, space1);
        String second = hand.substring(space1 + 1, space2);
        String third = hand.substring(space2 + 1, space3);
 
        int cardVal1 = getCardVal(first);
        int cardVal2 = getCardVal(second);      
        int cardVal3 = getCardVal(third);

        int get_max = Math.max(cardVal1, Math.max(cardVal2, cardVal3));
        int get_min = -Math.max(-cardVal1, Math.max(-cardVal2, -cardVal3));
        int get_mid = (cardVal1 + cardVal2 + cardVal3) - (get_max + get_min);
        
        if(isStraightFlush(hand, get_max, get_min, get_mid))
            return STRAIGHT_FLUSH;
        else if(isThreeOfAKind(cardVal1, cardVal2, cardVal3))
            return THREE_OF_A_KIND;
        else if(isStraight(hand, get_max, get_min, get_mid))
            return STRAIGHT;
        else if(isFlush(hand))
            return FLUSH;
        else if(isPair(cardVal1, cardVal2, cardVal3))
            return PAIR;
        else
            return HIGH_CARD;
            
    }
    /**
     * checks if the hand is 3 consecutive faces and same suits (straight flush)
     */
    private static boolean isStraightFlush(String hand, int get_max, int get_min, int get_mid) {  
        if(isStraight(hand, get_max, get_min, get_mid) && isFlush(hand))
            return true;
        else
            return false;
    }
    /**
     * checks for three of the same face (3 of a kind) (ex. 9D, 9H, 9S)
     * @param cardVal1 the value of card 1 (i.e. cardVal1 = 5)
     * @param cardVal2
     * @param cardVal3
     * @return
     */
    private static boolean isThreeOfAKind(int cardVal1, int cardVal2, int cardVal3) {
        return (cardVal1 == cardVal2 && cardVal1 == cardVal3 && cardVal1 == cardVal2);
    }
    /**
     * checks for 3 consecutive card number (straight)
     */
    private static boolean isStraight(String hand, int get_max, int get_min, int get_mid) {
        if (get_mid - get_min == 1 && get_max - get_mid == 1) //would be used if all 3 cards were from 1-10
            return true;
        else if(hand.indexOf("A")!=-1 && hand.indexOf("2") != -1 && hand.indexOf("3") != -1) //A, 2, 3 is considered a straight
            return true;
        else if(hand.indexOf("Q")!=-1 && hand.indexOf("K") != -1 && hand.indexOf("A") != -1) //Q, K, A is considered a straight
            return true;
        else if(hand.indexOf("K")!=-1 && hand.indexOf("A") != -1 && hand.indexOf("2") != -1) //K, A, 2 is considered a straight
            return true;
        return false;
    }
    /**
     * checks for 3 of the same suit (flush)
     */
    private static boolean isFlush(String hand) {
        int space1 = hand.indexOf(" ");
        int space2 = hand.indexOf(" ", space1 + 1);
        String card1 = hand.substring(0, space1);
        String card2 = hand.substring(space1 + 1, space2);
        String card3 = hand.substring(space2 + 1, hand.length()-1);
       
        if(card1.substring(card1.length() - 1).equals(card2.substring(card2.length() - 1)) && card2.substring(card2.length() - 1).equals(card3.substring(card3.length() - 1)))
            return true;
        return false;
        
    }
    /**
     * checks for 2 the same (pair)
     */
    private static boolean isPair(int cardVal1, int cardVal2, int cardVal3) {
        return (cardVal1 == cardVal2 || cardVal2 == cardVal3 || cardVal1 == cardVal3);
    }
    /**
     * determines if its 2-10 or JQKA
     * @param card
     * @return int value of the card
     */
    private static int checkFace(String card){
        int cardFace = 0;
        if(card.substring(0, card.length()-1).equals("J"))
            cardFace = 11;
        else if(card.substring(0, card.length()-1).equals("Q"))
            cardFace = 12;
        else if(card.substring(0, card.length()-1).equals("K"))
            cardFace = 13;
        else if(card.substring(0, card.length()-1).equals("A"))
            cardFace = 14;
        else 
            cardFace = Integer.parseInt(card.substring(0, card.length()-1));
        
        return cardFace;
    }
    /**
     * determines the card value that corresponds to constants
     * @param card
     * @return an int that corresponds to constants (i.e. JACK = 11)
     */
    private static int getCardVal(String card){
        // 10 is the only card that is 3 chars long
        String face = card.substring(0,1);
        if (card.length() == 3)
            return 10;
        else if(face.equals("J"))
            return JACK;
        else if(face.equals("Q"))
            return QUEEN;
        else if(face.equals("K"))
            return KING;
        else if(face.equals("A"))
            return ACE;
        else
            return (Integer.parseInt(face));
    }
    /**
     * checks what the player's highest card is 
     * @param playerHand
     * @return
     */
    private static int highestCard(String playerHand) {
        //identifies each space in the hand
        int space1 = playerHand.indexOf(" ");
        int space2 = playerHand.indexOf(" ", space1 + 1);
        int space3 = playerHand.indexOf(" ", space2 + 1);

        String first = playerHand.substring(0, space1);
        String second = playerHand.substring(space1 + 1, space2);
        String third = playerHand.substring(space2 + 1, space3);

        int cardVal1 = getCardVal(first);
        int cardVal2 = getCardVal(second);
        int cardVal3 = getCardVal(third);

        int get_max = Math.max(cardVal1, Math.max(cardVal2, cardVal3));
        return get_max;
    }
    /**
     * deals 3 cards to player
     * getCard() will get face and suit
     * isUnique() will make sure each card is different
     * @return
     */
    private static String dealCards() {
        String cards = "";

        for(int i = 0; i < 3; i++){
            Boolean hasCard = false;
            while(!hasCard){
                String card = getCard();
                if(isUnique(cards,card)){
                    cards += card + " ";
                    hasCard = true;
                }
            }
        }
        return cards;
    }
    
    /**
     * makes sure cards are not the same
     */
    private static boolean isUnique(String playerHand, String card) {
        return playerHand.indexOf(card) == -1;
    }

    /**
     * @return calls the getFace and getSuit method to get cards
     */
    private static String getCard() {
        return getFace() + getSuit();
    }

    /**
     * @return random suit
     */
    private static String getSuit() {
        int suit = (int)(Math.random() * NUM_SUITS);
        if(suit == HEARTS)
            return "H";
        else if(suit == DIAMONDS)
            return "D";
        else if(suit == CLUBS)
            return "C";
        else if(suit == SPADES)
            return "S";
        else
            return null;
    }

    /**
     * @return random face card from 2-10, and JQKA
     */
    private static String getFace() {
        int face = (int)(Math.random() * NUM_FACES + 2);
        if(face >= 2 && face <= 10)
            return "" + face;
        else if(face == JACK)
            return "J";
        else if(face == QUEEN)
            return "Q";
        else if(face == KING)
            return "K";
        else if(face == ACE)
            return "A";
        else
            return null;
    }
    /**
     * asks for player's pair plus wager
     * @param in
     * @return player's pair plus wager as an int
     */
    private static int getPairPlusWager(Scanner in) {
        System.out.print("How much do you want to bet for your pair plus wager? ($50-$100): ");
        String pairPlusWagerAsText = in.nextLine();
        
        boolean validInput = false;
        int wager = 0;
        while(!validInput){
            try {
                wager = Integer.parseInt(pairPlusWagerAsText);
                if(wager >= 0 && wager <= MAX_BET){ // >= 0 since the pair plus wager is optional
                    validInput = true;
                }
            } catch (NumberFormatException ex) {
                System.out.print("Please enter a valid pair plus wager from ($50-$100): ");
                pairPlusWagerAsText = in.nextLine();
            }
        }
        return wager;
    }
    /**
     * asks for player's ante wager
     * @param in
     * @return player's ante wager as an int
     */
    private static int getAnteWager(Scanner in) {
        System.out.print("How much do you want to bet for your ante wager? ($50-$100): ");
        String anteWagerAsText = in.nextLine();

        boolean validInput = false;
        int wager = 0;
        while(!validInput){
            try {
                wager = Integer.parseInt(anteWagerAsText);
                if(wager >= MIN_BET && wager <= MAX_BET)
                    validInput = true;
                else if(wager * 2 > STARTING_WALLET)
                    System.out.println("You do not have enough money in your wallet to play.");
            } catch (NumberFormatException ex) {
                System.out.print("Please enter a valid ante wager from ($50-$100): ");
                anteWagerAsText = in.nextLine();
            }
        }
        return wager;
    }
}
