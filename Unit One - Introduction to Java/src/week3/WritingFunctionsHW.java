package week3;

public class WritingFunctionsHW {
    public static void main(String[] args) {
        double QuestionOne = getTotalCost(100,8);
        System.out.println("Your total cost is $" + QuestionOne);

        double QuestionTwo = getArea(4.5, 2.3);
        double QuestionTwoPeri = getPeri(4.5, 2.3);
        System.out.println("The area of the rectangle is " + QuestionTwo + " feet squared and the perimeter is " + QuestionTwoPeri + " feet.");

        int QuestionThree = getMins (1);
        System.out.println("There are " + QuestionThree + " minutes in a year.");

        double QuestionFour = getLight((3*Math.pow(10,8)),365);
        System.out.println("The distance that a light beam would travel in one year is " + QuestionFour + " meters.");

        double QuestionFive = getWinning(110, 44);
        System.out.println("The New York Yankees winning percentage for 1927 is " + QuestionFive + " %.");

        double QuestionSix = getMomentum(10, 12);
        System.out.println("The momentum is " + QuestionSix + " kg*m/s.");

        double QuestionSeven = getCelsius (98);
        System.out.println("98 degrees Farenheit is " + QuestionSeven + " degrees Celsius.");

        double QuestionEight = getSquare(4);
        double QuestionEightRoot = getRoot(4);
        System.out.println("The square is " + QuestionEight + " and the square root is " + QuestionEightRoot);

        double QuestionNine = getPay(1);
        System.out.println("The amount of pay due is $ " + QuestionNine);

        double QuestionTen = getArea2(2,3);
        double QuestionTenPeri = getPeri2(2,3);
        System.out.println("The area is "+ QuestionTen + " units squared and the perimeter is " + QuestionTenPeri + " units.");

        double QuestionEleven = getKinEnergy(2,3);
        System.out.println("The kinetic energy is " + QuestionEleven + "J."); 
    }

    private static double getKinEnergy(double mass, double velocity) {
        return (0.5 * mass * velocity * velocity);
    }

    private static double getPeri2(double length, double width) {
        return 2 * (length + width);
    }

    private static double getArea2(double length, double width) {
        return length * width;
    }

    private static double getPay(double sales) {
        return sales * 0.27;
    }

    private static double getRoot(int num) {
        return Math.sqrt(num);
    }

    private static double getSquare(int num) {
        return Math.pow(num, 2);
    }

    private static double getCelsius(double Farenheit) {
        return ((5*(Farenheit - 32))/9);
    }

    private static double getMomentum(double mass, double velocity) {
        return mass * velocity;
    }

    private static double getWinning(double won, double lost) {
        return (won / (won + lost) * 100);
    }

    private static double getLight(double seconds, int days) {
        return seconds * 60 * 60 * 24 * days;
    }

    private static int getMins(int years) {
        return 60 * 24 * 365 * years;
    }

    private static double getPeri(double length, double width) {
        return (2 * length) + (2 * width);
    }

    private static double getArea(double length, double width) {
        return length * width;
    }

    private static double getTotalCost(int price, int tax) {
        return price + price * tax / 100;
    }
}
