package fr.epita.assistants.exceptions;

import java.util.List;

public class Student {
    String name;
    int age;
    String major;

    public Student(String name, int age, String major) throws InvalidNameException, InvalidAgeException, InvalidMajorException {
        if (name.matches(".*[0-9]+.*"))
            throw new InvalidNameException(name);
        if (age <= 0 || age >= 130)
            throw new InvalidAgeException(age);
        if (!List.of("IMAGE", "GISTRE", "SRS", "SCIA", "MTI", "TCOM", "SIGL", "GITM", "ICE", "SANTE", "SSIE", "IF", "STARTUP", "Q").contains(major.toUpperCase()))
            throw new InvalidMajorException(major);

        this.name = name;
        this.age = age;
        this.major = major.toUpperCase();
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Major: " + major;
    }
}
