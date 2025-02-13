package org.example.paterns.AbstractFactory;

public class WindowsButton implements Button{
    @Override
    public void printButton() {
        System.out.println("You have created WindowsButton.");
    }
}
