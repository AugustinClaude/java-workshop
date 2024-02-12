package fr.epita.assistants.war;

public class Soldier extends Combatant {
    int hp;
    int dmg;
    String scream;

    public Soldier() {
        hp = 15;
        dmg = 3;
        scream = "No pity for losers!";
    }

    public void kill() {
        hp = 0;
    }

    @Override
    public void printState() {
        System.out.println("I have " + hp + " health points.");
    }

    public void attack(Soldier s) {
        s.hp -= dmg;
    }

    public void attack(Vehicle v) {
        System.out.println("I can't fight this.");
    }

    public void scream() {
        System.out.println(scream);
    }
}
