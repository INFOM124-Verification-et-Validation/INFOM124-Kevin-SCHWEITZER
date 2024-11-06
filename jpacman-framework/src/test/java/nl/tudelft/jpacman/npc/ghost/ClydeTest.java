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

public class ClydeTest {

    private GhostMapParser ghostMapParser;
    private Clyde clyde;
    private Player player;

    @BeforeEach
    void setUp() {
        // Initialize factories and GhostMapParser
        PacManSprites sprites = new PacManSprites();
        GhostFactory ghostFactory = new GhostFactory(sprites);
        BoardFactory boardFactory = new BoardFactory(sprites);
        LevelFactory levelFactory = new LevelFactory(sprites, ghostFactory);

        // Create the GhostMapParser
        ghostMapParser = new GhostMapParser(levelFactory, boardFactory, ghostFactory);
    }

    /**
     * Test case 1: Clyde is within 8 spaces horizontally from Pac-Man.
     * Clyde should flee in the opposite direction.
     */
    @Test
    void testClydeWithinEightSpacesHorizontally() {
        String[] map = {
            "############",
            "#P       C##",
            "############"
        };

        Level level = ghostMapParser.parseMap(List.of(map));

        // Register the player and set its direction
        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        player = playerFactory.createPacMan();
        level.registerPlayer(player); // Register the player in the level
        player.setDirection(Direction.EAST); // Set the player's initial direction

        clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        // Assert that both Clyde and the player are found
        assertThat(clyde).isNotNull();
        assertThat(player).isNotNull();

        Optional<Direction> nextMove = clyde.nextAiMove();
        assertThat(nextMove).contains(Direction.EAST);
    }

    /**
     * Test case 2: Clyde is within 8 spaces vertically from Pac-Man.
     * Clyde should flee in the opposite direction.
     */
    @Test
    void testClydeWithinEightSpacesVertically() {
        String[] map = {
            "############",
            "#          #",
            "# ##########",
            "#C##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "#P##########",
            "# ##########",
            "# ##########",
            "############"
        };

        Level level = ghostMapParser.parseMap(List.of(map));

        // Register the player and set its direction
        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        player = playerFactory.createPacMan();
        level.registerPlayer(player); // Register the player in the level
        player.setDirection(Direction.NORTH); // Set the player's initial direction

        clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        // Assert that both Clyde and the player are found
        assertThat(clyde).isNotNull();
        assertThat(player).isNotNull();

        Optional<Direction> nextMove = clyde.nextAiMove();
        assertThat(nextMove).contains(Direction.NORTH);
    }

    /**
     * Test case 3: Clyde is more than 8 spaces away from Pac-Man.
     * Clyde should move towards Pac-Man.
     */
    @Test
    void testClydeMoreThanEightSpacesAwayHorizontally() {
        String[] map = {
            "#####################",
            "#C                 P#",
            "#####################",
        };

        Level level = ghostMapParser.parseMap(List.of(map));

        // Register the player and set its direction
        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        player = playerFactory.createPacMan();
        level.registerPlayer(player); // Register the player in the level
        player.setDirection(Direction.WEST); // Set the player's initial direction

        clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        // Assert that both Clyde and the player are found
        assertThat(clyde).isNotNull();
        assertThat(player).isNotNull();

        Optional<Direction> nextMove = clyde.nextAiMove();
        assertThat(nextMove).contains(Direction.EAST);
    }

    /**
     * Test case 4: Clyde is more than 8 spaces away vertically from Pac-Man.
     * Clyde should move towards Pac-Man.
     */
    @Test
    void testClydeMoreThanEightSpacesAwayVertically() {
        String[] map = {
            "############",
            "#          #",
            "# ##########",
            "#C##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "# ##########",
            "#P##########",
            "#          #",
            "############"
        };

        Level level = ghostMapParser.parseMap(List.of(map));

        // Register the player and set its direction
        PlayerFactory playerFactory = new PlayerFactory(new PacManSprites());
        player = playerFactory.createPacMan();
        level.registerPlayer(player); // Register the player in the level
        player.setDirection(Direction.SOUTH); // Set the player's initial direction

        clyde = Navigation.findUnitInBoard(Clyde.class, level.getBoard());

        // Assert that both Clyde and the player are found
        assertThat(clyde).isNotNull();
        assertThat(player).isNotNull();

        Optional<Direction> nextMove = clyde.nextAiMove();
        assertThat(nextMove).contains(Direction.SOUTH);
    }
}
