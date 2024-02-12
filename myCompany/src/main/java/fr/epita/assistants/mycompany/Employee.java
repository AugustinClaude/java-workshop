package fr.epita.assistants.mycompany;

public abstract class Employee {
    private final String lastName;
    private final String firstName;

    public Employee(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void sayMyName() {
        System.out.println("My name is " + this + ".");
    }

    public abstract void sayMyJob();
}
