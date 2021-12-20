package com.rahmanbesir.teamapp.service;

import com.rahmanbesir.teamapp.domain.Player;
import com.rahmanbesir.teamapp.exceptions.PlayerCountExceedException;
import com.rahmanbesir.teamapp.model.PlayerRequest;
import com.rahmanbesir.teamapp.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final int MAX_PLAYER_COUNT = 12;

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player create(PlayerRequest request) {
        List<Player> allActivePlayers = playerRepository.findAllActivePlayers();

        if (MAX_PLAYER_COUNT == allActivePlayers.size()) {
            throw new PlayerCountExceedException();
        }

        return playerRepository.save(request.toPlayer());
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAllActivePlayers();
    }

    public void deleteById(Integer id) {
        Player player = playerRepository.getById(id);
        player.setAsDeleted();
        playerRepository.save(player);
    }
}
