package dev.vatuu.backbone.events.base;

public abstract class Event<T> {

    protected volatile T invoker;
    public final T invoker() { return invoker; }

    public abstract T register(T listener);
    public abstract void unregister(T listener);
}
