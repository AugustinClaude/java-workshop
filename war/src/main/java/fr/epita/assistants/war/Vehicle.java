package fr.epita.assistants.war;

public class Vehicle extends Combatant {
    String model;
    int defense;

    public Vehicle(String model, int defense) {
        this.model = model;
        this.defense = defense;
    }

    @Override
    public void printState() {
        System.out.println("I have " + defense + " defense points.");
    }

    void attack(Soldier s) {
        s.kill();
    }

    void attack(Vehicle v) {
        v.defense /= 2;
    }

    void scream() {
        System.out.println("I'm " + model + "!");
    }
}
