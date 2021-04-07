package week12;

public class PetDriver {
    public static void main(String[] args) {
        Animal a1 = new Animal(10);
        Animal d1 = new Dog(20); // because a dog is an animal
        Animal c1 = new Cat(5);

        a1.makeSound();
        d1.makeSound();
        c1.makeSound();
    }
}
