package com.rahmanbesir.teamapp.controller.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration;
import com.rahmanbesir.teamapp.domain.Player;
import com.rahmanbesir.teamapp.domain.Position;
import com.rahmanbesir.teamapp.model.PlayerRequest;
import com.rahmanbesir.teamapp.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DgsAutoConfiguration.class, PlayerController.class})
class PlayerControllerTest {

    @Autowired
    private DgsQueryExecutor dgsQueryExecutor;

    @MockBean
    private PlayerService playerService;

    @Test
    void it_should_get_all_players() {
        //Given
        Player p1 = new Player();
        p1.setId(1);
        Player p2 = new Player();
        p2.setId(2);
        when(playerService.getAllPlayers()).thenReturn(List.of(p1, p2));

        //When
        List<Integer> ids = dgsQueryExecutor.executeAndExtractJsonPath(
                """
                        {
                          list {
                            id
                          }
                        }
                        """,
                "data.list[*].id");

        //Then
        assertThat(ids).containsExactlyInAnyOrder(1, 2);
    }

    @Test
    void it_should_create_player() {
        //Given
        Player player = new Player();
        player.setId(1);
        when(playerService.create(any())).thenReturn(player);

        //When
        Integer id = dgsQueryExecutor.executeAndExtractJsonPath("""
                mutation {
                   create(request: {
                      name: "Name",
                     surname: "Surname",
                     position: CENTER
                   }) {
                     id
                   }
                 }
                 """, "data.create.id");

        //Then
        assertThat(id).isEqualTo(1);
        var captor = ArgumentCaptor.forClass(PlayerRequest.class);
        verify(playerService).create(captor.capture());
        var request = captor.getValue();
        assertThat(request.getName()).isEqualTo("Name");
        assertThat(request.getSurname()).isEqualTo("Surname");
        assertThat(request.getPosition()).isEqualTo(Position.CENTER);
    }

    @Test
    void it_should_delete_player() {
        //When
        dgsQueryExecutor.execute("""
                  mutation {
                     delete(id : 1)
                   }
                """);

        //Then
        verify(playerService).deleteById(1);
    }

}