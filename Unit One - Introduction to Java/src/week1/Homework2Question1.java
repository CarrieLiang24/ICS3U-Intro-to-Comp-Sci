package week1;

public class Homework2Question1 {
    public static void main(String[] args) {
        areaOfACircle();
    }
        private static void areaOfACircle() {
        double radius,area;
        radius = 10;

        area = Math.PI*(Math.pow(radius, 2));
        System.out.println("Area of the circle is: " + area + " units squared.");
        }
    
}
