package week1;

public class Homework2Question5 {
    public static void main(String[] args) {
        coins();

    }
        private static void coins(){
            int pennies, nickles, dimes, quarters, loonies, toonies;
            double sum;
            pennies = 5;
            nickles = 5;
            dimes = 5;
            quarters = 5;
            loonies = 5;
            toonies=5;
            
            sum = (pennies*0.01) + (nickles*0.05)+(dimes*0.10)+(quarters*0.25)+(loonies*1)+(toonies*2);
            System.out.println("You have: $"+sum);
        }
}
