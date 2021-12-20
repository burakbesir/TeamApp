package com.rahmanbesir.teamapp.service;

import com.rahmanbesir.teamapp.domain.Player;
import com.rahmanbesir.teamapp.domain.Position;
import com.rahmanbesir.teamapp.model.PlayerRequest;
import com.rahmanbesir.teamapp.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Test
    void it_should_create_player() {
        //Given
        var request = new PlayerRequest();
        request.setName("name");
        request.setSurname("surname");
        request.setPosition(Position.CENTER);

        Player player = new Player();
        when(playerRepository.save(any())).thenReturn(player);

        //When
        Player createdPlayer = playerService.create(request);

        //Then
        var captor = ArgumentCaptor.forClass(Player.class);
        verify(playerRepository).save(captor.capture());
        Player savedPlayer = captor.getValue();
        assertThat(savedPlayer.getName()).isEqualTo("name");
        assertThat(savedPlayer.getSurname()).isEqualTo("surname");
        assertThat(savedPlayer.getPosition()).isEqualTo(Position.CENTER);

        assertThat(createdPlayer).isEqualTo(player);
    }

    @Test
    void it_should_get_all_players() {
        //Given
        List<Player> existPlayers = List.of(new Player());
        when(playerRepository.findAllActivePlayers()).thenReturn(existPlayers);

        //When
        List<Player> players = playerService.getAllPlayers();

        //Then
        assertThat(players).isEqualTo(existPlayers);
    }

    @Test
    void it_should_delete_player_by_id() {
        //Given
        when(playerRepository.getById(1)).thenReturn(new Player());

        //When
        playerService.deleteById(1);

        //Then
        var captor = ArgumentCaptor.forClass(Player.class);
        verify(playerRepository).save(captor.capture());

        Player player = captor.getValue();
        assertThat(player.isDeleted()).isTrue();
    }
}