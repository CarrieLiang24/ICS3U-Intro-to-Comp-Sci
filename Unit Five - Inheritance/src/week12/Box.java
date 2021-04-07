package week12;

// this means that a Box is a Rectangle 
// the parent class of the Box is the Rectangle 
public class Box extends Rectangle{

    // if you don't create a constructor, it'll auto call the parent
    private double height;

    public Box(double length, double width, double height){
        super(length, width); // you HAVE to call the parent constructor first 
        this.height = height;
    }


    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    public double getVolume() {
        return super.getArea() * height; // use super.getArea() if their is more than one value of area
    }
    
    //get the surface area
    public double getArea() {
        return 2 * super.getArea() + 2 * getLength() * height + 2 * getWidth() * height;
    }
    //sum of all the edges
    public double getPerimeter() {
        return super.getPerimeter()*2 + 4*height;
        
    }

    public boolean isSquare() {
       return false;
        
    }
    public boolean isCube(){
        return super.isSquare() && height == getLength();
        
    }
}