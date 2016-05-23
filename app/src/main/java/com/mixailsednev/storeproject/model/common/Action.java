package com.mixailsednev.storeproject.model.common;

public abstract class Action<Payload> {
    public abstract void run(Payload payload);
}
