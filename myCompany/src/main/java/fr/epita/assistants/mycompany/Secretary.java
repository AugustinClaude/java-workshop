package fr.epita.assistants.mycompany;

public class Secretary extends Employee {
    public Secretary(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public void sayMyJob() {
        System.out.println(this + " is a secretary.");
    }
}
