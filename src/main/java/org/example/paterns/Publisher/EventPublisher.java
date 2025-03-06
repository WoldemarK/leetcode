package org.example.paterns.Publisher;

import java.util.ArrayList;
import java.util.List;

public class EventPublisher {

    List<EventListener> eventListeners = new ArrayList<>();

    public void addListener(EventListener listener) {
        eventListeners.add(listener);
    }

    public void removeListener(EventListener listener) {
        eventListeners.remove(listener);
    }
    public void notifyListeners(String event) {
        for (EventListener listener : eventListeners) {
            listener.onEvent(event);
        }
    }
}
