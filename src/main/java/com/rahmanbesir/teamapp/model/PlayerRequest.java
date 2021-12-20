package com.rahmanbesir.teamapp.model;

import com.rahmanbesir.teamapp.domain.Player;
import com.rahmanbesir.teamapp.domain.Position;

import java.util.Objects;

public final class PlayerRequest {
    private String name;
    private String surname;
    private Position position;

    public Player toPlayer() {
        Player player = new Player();
        player.setName(name);
        player.setSurname(surname);
        player.setPosition(position);
        return player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
