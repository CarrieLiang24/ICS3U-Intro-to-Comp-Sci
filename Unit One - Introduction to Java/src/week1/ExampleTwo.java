package week1;

/*
* This example is going to cover Escape Sequences
*/
public class ExampleTwo {
    public static void main(String[] args) {
        System.out.println("This is very \"exciting\" code!"); // " identifies the beginning and end of a String Literal - test you want to include in the code
                                                            // backsplash\ identifies an escape sequence. it creates a special character
        //System.out.println("This \is an error.");           // we can only escape certain characters (AP escape sequences are \n \" \\)
        System.out.println("This\nis\na\nvalid\nescape\nsequence.\n");
    }
}
