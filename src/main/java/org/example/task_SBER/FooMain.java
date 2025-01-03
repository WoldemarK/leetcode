package org.example.task_SBER;

import java.util.concurrent.CopyOnWriteArrayList;

public class FooMain {
    public static void main(final String[] args) {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        Foo foo = new Foo(list);

        var third = new Thread(foo::third);
        var second = new Thread(foo::second);
        var first = new Thread(foo::first);

        third.start();
        second.start();
        first.start();

        try {
            third.join();
            second.join();
            first.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(list);

    }
}
