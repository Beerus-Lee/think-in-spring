package org.own.think.in.spring.event;

import org.springframework.context.ApplicationEvent;

public class MySpringEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public MySpringEvent(Object source) {
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
