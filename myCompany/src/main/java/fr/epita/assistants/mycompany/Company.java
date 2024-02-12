package fr.epita.assistants.mycompany;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Company {
    String name;
    List<Employee> employees;
    HashMap<Employee, Manager> employeeManagement;

    public Company(String name) {
        this.name = name;
        employees = new ArrayList<>();
        employeeManagement = new HashMap<>();
    }

    public int size() {
        return employees.size();
    }

    public void hire(Employee p) {
        employees.add(p);
    }

    public void fire(Employee p) {
        employees.remove(p);
    }

    public void printEmployees() {
        System.out.println("The company " + name + " employs:");
        for (Employee employee : employees) {
            System.out.println("- " + employee);
        }
    }

    public boolean addEmployeeManagement(Employee e, Manager m) {
        if (!employees.contains(e)) {
            System.out.println(e + " is not employed by " + name + ".");
            if (!employees.contains(m))
                System.out.println(m + " is not employed by " + name + ".");
            return false;
        }

        if (!employees.contains(m)) {
            System.out.println(m + " is not employed by " + name + ".");
            return false;
        }

        if (!employeeManagement.containsKey(e)) {
            employeeManagement.put(e, m);
            System.out.println(e + " now has a manager.");
            return true;
        }

        employeeManagement.put(e, m);
        System.out.println(e + " has a new manager.");
        return true;
    }

    public void printManager(Employee e) {
        if (employeeManagement.containsKey(e)) {
            System.out.println(employeeManagement.get(e) + " manages " + e + ".");
        } else {
            System.out.println(e + " is managed by nobody.");
        }
    }

    public void printEmployeesProject() {
        for (Employee employee : employees) {
            if (employee.getClass() == Engineer.class)
                ((Engineer) employee).listProjects();
            else if (employee.getClass() == Manager.class)
                ((Manager) employee).listProjects();
        }
    }
}
