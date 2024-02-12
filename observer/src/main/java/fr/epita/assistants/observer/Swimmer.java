package fr.epita.assistants.observer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Swimmer implements Observable<Swimmer> {
    private final String name;
    private SwimmerStatus status;
    private final Set<Observer<Swimmer>> observers;

    public Swimmer(String name) {
        this.name = name;
        status = SwimmerStatus.OK;
        observers = new HashSet<>();
        System.out.println(this.name + " goes into the sea.");
    }

    public String getName() {
        return name;
    }

    public SwimmerStatus getStatus() {
        return status;
    }

    public void setStatus(SwimmerStatus status) {
        if (status == SwimmerStatus.DROWNING)
            System.out.println(name + ": I'm drowning, help!!");
        else if (status == SwimmerStatus.WAVING)
            System.out.println(name + ": Waves towards the shore.");
        this.status = status;
        fire(this);
    }

    @Override
    public Set<Observer<Swimmer>> getObservers() {
        return observers;
    }

    @Override
    public void register(Observer<Swimmer>... observers) {
        this.observers.addAll(Arrays.asList(observers));
    }

    @Override
    public void unregister(Observer<Swimmer> observer) {
        observers.remove(observer);
    }

    @Override
    public void fire(Swimmer event) {
        for (Observer<Swimmer> observer : observers) {
            observer.onEvent(event);
        }
    }
}
