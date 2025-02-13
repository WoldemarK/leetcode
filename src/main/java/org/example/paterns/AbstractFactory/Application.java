package org.example.paterns.AbstractFactory;

public class Application {

    private final Button button;
    private final Checkbox checkbox;

    public Application(GUIFactory guiFactory) {
        button = guiFactory.createButton();
        checkbox = guiFactory.createCheckbox();
    }

    public void print() {
        button.printButton();
        checkbox.printCheckbox();
    }
}
