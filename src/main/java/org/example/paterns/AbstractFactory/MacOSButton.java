package org.example.paterns.AbstractFactory;

public class MacOSButton implements Button {
    @Override
    public void printButton() {
        System.out.println("You have created MacOSButton.");
    }
}
