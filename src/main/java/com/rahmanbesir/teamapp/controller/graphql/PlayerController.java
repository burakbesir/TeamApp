package com.rahmanbesir.teamapp.controller.graphql;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import com.rahmanbesir.teamapp.domain.Player;
import com.rahmanbesir.teamapp.model.PlayerRequest;
import com.rahmanbesir.teamapp.service.PlayerService;

import java.util.List;

@DgsComponent
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @DgsQuery(field = "list")
    public List<Player> getPlayers() {
        return playerService.getAllPlayers();
    }

    @DgsMutation(field = "create")
    public Player createPlayer(@InputArgument("request") PlayerRequest request) {
        return playerService.create(request);
    }

    @DgsMutation(field = "delete")
    public void deleteById(@InputArgument("id") Integer id) {
        playerService.deleteById(id);
    }

}
