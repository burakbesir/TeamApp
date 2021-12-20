package com.rahmanbesir.teamapp.repository;

import com.rahmanbesir.teamapp.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Integer> {

    @Query("SELECT p FROM Player p WHERE p.deleted = false")
    List<Player> findAllActivePlayers();

}
