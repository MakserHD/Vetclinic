public class Dog extends Pet {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }
    public String getBreed() { return breed; }

    @Override
    public String toString() {
        return "[DOG] " + super.toString() + ", Breed: " + breed;
    }
}

class Cat extends Pet {
    private boolean hasNiceFur;

    public Cat(String name, int age, boolean hasNiceFur) {
        super(name, age);
        this.hasNiceFur = hasNiceFur;
    }
    public boolean hasNiceFur() { return hasNiceFur; }

    @Override
    public String toString() {
        return "[CAT] " + super.toString() + ", Nice Fur: " + hasNiceFur;
    }
}