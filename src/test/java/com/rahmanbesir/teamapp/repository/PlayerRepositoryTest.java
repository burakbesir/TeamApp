package com.rahmanbesir.teamapp.repository;

import com.rahmanbesir.teamapp.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void it_should_get_all_active_players() {
        //Given
        Player p1 = new Player();
        Player p2 = new Player();
        p2.setAsDeleted();
        Player p3 = new Player();

        playerRepository.saveAll(List.of(p1, p2, p3));

        //When
        List<Player> allActivePlayers = playerRepository.findAllActivePlayers();

        //Then
        List<Integer> activePlayerIds = allActivePlayers.stream().map(Player::getId).toList();
        assertThat(activePlayerIds).containsExactlyInAnyOrder(p1.getId(), p3.getId());
    }
}