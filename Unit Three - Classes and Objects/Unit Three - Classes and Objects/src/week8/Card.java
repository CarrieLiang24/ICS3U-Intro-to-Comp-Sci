package week8;

public class Card {
    private String face;
    private String suit;

    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
    }

    public String getFace(){
        return face; 
    }

    public String getSuit(){
        return suit; 
    }
    /**
     * this refers to thhe instance of the Card that is called compareTO
     * @param c
     * @return
     */
    public int compareTo(Card c){
        return getValue(this) - getValue(c);
    }

    public static int compare(Card c1, Card c2){ 
        return getCardValue(c1) - getCardValue(c2); // static method cannot make calls to 
    }
    /**
     * helper method because it is only accesible by the class and allows another method to work.
     * It is helping another method in the class
     * @param c
     * @return
     */
    private static int getValue(Card c){
        String face = c.face;
        try{
            int temp = Integer.parseInt(face);
            return temp;
        }catch (Exception ex){
            if(face.equals("J"))
                return 11;
            else if(face.equals("Q"))
                return 12;
            else if(face.equals("K"))
                return 13;
            return 14;
        }
        
    }

    private static int getCardValue(Card c){
        String face = c.face;
        try{
            int temp = Integer.parseInt(face);
            return temp;
        }catch (Exception ex){
            if(face.equals("J"))
                return 11;
            else if(face.equals("Q"))
                return 12;
            else if(face.equals("K"))
                return 13;
            return 14;
        }
        
    }
    /**
     * the word static means that a method or attribute in a class BELONGS to the class. 
     * And you would call or activate the method or attribute through the class name.
     * 
     * Non-static methods to an object (card1 and card2 were objects, card is the class)
     * card1.getSuit();
     */
}
