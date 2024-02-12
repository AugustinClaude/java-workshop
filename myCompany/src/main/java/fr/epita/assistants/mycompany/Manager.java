package fr.epita.assistants.mycompany;

import java.util.HashSet;
import java.util.Set;

public class Manager extends Employee implements Assignable {
    Set<String> projects;

    public Manager(String firstName, String lastName) {
        super(firstName, lastName);
        projects = new HashSet<>();
    }

    @Override
    public void sayMyJob() {
        System.out.println(this + " is a manager.");
    }

    @Override
    public void addProject(String project) {
        projects.add(project);
    }

    @Override
    public boolean hasProject(String project) {
        return projects.contains(project);
    }

    @Override
    public void listProjects() {
        if (projects.isEmpty())
            return;

        System.out.println(this + "'s current projects are:");
        for (String project : projects) {
            System.out.println("- " + project);
        }
    }
}
