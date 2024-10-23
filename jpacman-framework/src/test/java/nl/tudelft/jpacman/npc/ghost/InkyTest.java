package nl.tudelft.jpacman.npc.ghost;

import nl.tudelft.jpacman.board.Direction;
import nl.tudelft.jpacman.level.Level;
import nl.tudelft.jpacman.level.Player;
import nl.tudelft.jpacman.level.LevelFactory;
import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.level.PlayerFactory;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class InkyTest {
    private GhostMapParser ghostMapParser;
    private Inky inky;
    private Blinky blinky;
    private Player player;

    @BeforeEach
    public void setUp() {
        PacManSprites sprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(sprites);
        BoardFactory boardFactory = new BoardFactory(sprites);
        LevelFactory levelFactory = new LevelFactory(sprites, ghostFactory);

        // Create the GhostMapParser
        ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    }

    @Test
    void testPacmanFacesUpwardsTowardsInkyWithBlinkyUnderHim() {
        String[] map = {
            "################",
            "####### ########",
            "####### ########",
            "#######I########",
            "####### ########",
            "####### ########",
            "####### ########",
            "####### ########",
            "####### ########",
            "#######P########",
            "####### ########",
            "####### ########",
            "####### ########",
            "#######B########",
            "################",
        };
        Level level = ghostMapParser.parseMap(List.of(map));

        // Register the player and set its direction
        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        player = playerFactory.createPacMan();
        level.registerPlayer(player); // Register the player in the level
        player.setDirection(Direction.NORTH); // Set the player's initial direction

        inky = Navigation.findUnitInBoard(Inky.class, level.getBoard());
        blinky = Navigation.findUnitInBoard(Blinky.class, level.getBoard());
    }
}
