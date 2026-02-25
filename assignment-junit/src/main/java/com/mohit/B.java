package com.mohit;

public class B {

    private I i;

    public B(I i) {
        this.i = i;
    }

    public void perform() {
        i.abc();
    }

    public void performMultipleTimes() {
        i.abc();
        i.abc();
        i.abc();
    }
}