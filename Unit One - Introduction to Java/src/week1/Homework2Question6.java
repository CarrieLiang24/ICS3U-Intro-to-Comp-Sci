package week1;

public class Homework2Question6 {
    public static void main(String[] args) {
        roots();
    }
        private static void roots(){
            double a, b, c, x1, x2;
            a=1;
            b=7;
            c=12;


            x1=(-b + (Math.sqrt((b*b)-(4*a*c))))/2*a;
            x2=(-b - (Math.sqrt((b*b)-(4*a*c))))/2*a;

            System.out.println("The two roots are: "+ x1 + " and " + x2);

        }
}
