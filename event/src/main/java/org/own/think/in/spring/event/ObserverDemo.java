package org.own.think.in.spring.event;

import java.util.EventObject;
import java.util.Observable;
import java.util.Observer;

public class ObserverDemo {
    public static void main(String[] args) {
        EventObservable eventObservable = new EventObservable();
        eventObservable.addObserver(new EventObserver());
        eventObservable.notifyObservers(new EventObject("hello world"));

    }

    static class EventObservable extends Observable {
        @Override
        public void notifyObservers(Object arg) {
            setChanged();
            System.out.println("发送消息......");
            super.notifyObservers(arg);
            clearChanged();
        }

        @Override
        protected synchronized void setChanged() {
            super.setChanged();
        }
    }


    static class EventObserver implements Observer {

        @Override
        public void update(Observable o, Object event) {
            EventObject eventObject = (EventObject) event;
            System.out.println("接受到消息：" + eventObject);

        }
    }
}
