package org.own.think.in.spring.event;

public class MySpringEvent2 extends MySpringEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MySpringEvent2(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    public Object getMessage() {
        return getSource();
    }
}
