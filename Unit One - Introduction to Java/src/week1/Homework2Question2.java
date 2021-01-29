package week1;

public class Homework2Question2 {
    public static void main(String[] args) {
        volumeOfASphere();
    }
        private static void volumeOfASphere(){
            double radius, volume;
            radius=10;

            volume=(4*Math.PI*radius*radius*radius)/3;
            System.out.println("Volume of the sphere is: " + volume + " units cubed.");
    }    
}
