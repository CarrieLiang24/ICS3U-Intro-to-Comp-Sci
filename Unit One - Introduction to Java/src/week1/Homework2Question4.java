package week1;

public class Homework2Question4 {
    public static void main(String[] args) {
        slope();
    }
        private static void slope(){
            double x1, x2, y1, y2, slope;
            x1=1;
            x2=2;
            y1=5;
            y2=7;

            slope=(y2-y1)/(x2-x1);
            System.out.println("Slope is: " + slope);
        }
}
