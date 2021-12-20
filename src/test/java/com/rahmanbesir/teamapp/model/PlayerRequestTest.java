package com.rahmanbesir.teamapp.model;

import com.rahmanbesir.teamapp.domain.Player;
import com.rahmanbesir.teamapp.domain.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerRequestTest {

    @Test
    void it_should_convert_to_player() {
        //Given
        var request = new PlayerRequest();
        request.setName("name");
        request.setSurname("surname");
        request.setPosition(Position.CENTER);

        //When
        Player player = request.toPlayer();

        //Then
        assertThat(player.getName()).isEqualTo("name");
        assertThat(player.getSurname()).isEqualTo("surname");
        assertThat(player.getPosition()).isEqualTo(Position.CENTER);
    }
}