package org.example.task_SBER;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MultiIterator<T> implements Iterator<T> {

    private int index = 0;
    private final Iterator<T> a;
    private final Iterator<T> b;

    public MultiIterator(Iterator<T> a, Iterator<T> b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean hasNext() {
        return a.hasNext() || b.hasNext();
    }

    @Override
    public T next() {
        if (a.hasNext() || b.hasNext()) {
            index = 1;
            return a.next();
        }
        index = 2;
        return b.next();
    }

    @Override
    public void remove() {
        if (index == 0) {
            throw new NoSuchElementException("No more elements");
        }
        if (index == 1) {
            a.remove();
        }
        b.remove();
    }
}
