package com.example.Api.scheduler;

public abstract class AbstractScheduler {

    protected final long minutes = 60;
    protected final long hours = 60 * minutes;
    protected final long days = 24 * hours;
    protected final long year = 365 * days;
}
