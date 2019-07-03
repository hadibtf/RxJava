package com.example.rxjava;

import io.reactivex.Observable;

public class Pres {
    private ListenerTest listenerTest;
    public void getData() {

    }

    public interface ListenerTest {
        void emit(String s);
    }
}
