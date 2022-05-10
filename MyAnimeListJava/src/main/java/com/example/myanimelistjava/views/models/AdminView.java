package com.example.myanimelistjava.views.models;

import java.util.UUID;

public class AdminView {

    //TODO no tengo seguro que campos necesita esto porque no entiendo como se lo vamos a enseñar al cliente xd

    private final UUID id;

    public UUID getId() {
        return id;
    }

    public AdminView(UUID id) {
        this.id = id;
    }
}
