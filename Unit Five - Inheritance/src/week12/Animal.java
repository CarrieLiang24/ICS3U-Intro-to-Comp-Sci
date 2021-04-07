package week12;

public class Animal {
    private int weight;

    public Animal(int weight){
        this.weight = weight; //constructor that sets weight
    }
    public void makeSound(){

    }
    public void eat(){
        weight++;
    }
    public int getWeight(){
        return weight;
    }
}
