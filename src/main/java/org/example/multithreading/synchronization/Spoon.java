package org.example.multithreading.synchronization;

public class Spoon {

    private Eater owner;

    public Spoon(Eater owner) {
        this.owner = owner;
    }

    public Eater getOwner() {
        return owner;
    }

    public synchronized void setOwner(Eater owner) {
        this.owner = owner;
    }

    public synchronized void use() {
        System.out.printf("%s has eaten!", owner.getName());
    }
}
