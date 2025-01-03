package org.example.task_SBER;

import java.util.concurrent.CopyOnWriteArrayList;

public class Foo {
    private final CopyOnWriteArrayList<String> out;

    public Foo(CopyOnWriteArrayList<String> out) {
        this.out = out;
    }

    public void first() {
        this.out.add("first");
    }

    public void second() {
        this.out.add("second");
    }

    public void third() {
        this.out.add("third");
    }
}
