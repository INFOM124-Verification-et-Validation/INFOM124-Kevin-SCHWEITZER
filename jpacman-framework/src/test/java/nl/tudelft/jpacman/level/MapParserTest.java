package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.board.Board;
import nl.tudelft.jpacman.npc.ghost.Clyde;
import nl.tudelft.jpacman.npc.ghost.Inky;
import nl.tudelft.jpacman.npc.ghost.Pinky;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import nl.tudelft.jpacman.PacmanConfigurationException;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class MapParserTest {
    private MapParser mapParser;
    private final PacManSprites pacManSprites = new PacManSprites();

    @BeforeEach
    void setUp() {
        GhostFactory ghostFactory = new GhostFactory(pacManSprites);
        LevelFactory levelFactory = new LevelFactory(pacManSprites, ghostFactory);
        BoardFactory boardFactory = new BoardFactory(pacManSprites);
        mapParser = new MapParser(levelFactory, boardFactory);
    }

    @Test
    void testPacmanOnly() {
        List<String> map = List.of(
            "###",
            "#P#",
            "###"
        );
        Level level = mapParser.parseMap(map);
        // Check if the level is not null.
        assertThat(level).isNotNull();
        // Check if the level has a board.
        Board board = level.getBoard();
        assertThat(board).isNotNull();
        // Check if the board has the correct width.
        assertThat(board.getWidth()).isEqualTo(3);
        // Check if the board has the correct height.
        assertThat(board.getHeight()).isEqualTo(3);
        // Register the player.
        Player player = new Player(pacManSprites.getPacmanSprites(), pacManSprites.getPacManDeathAnimation());
        level.registerPlayer(player);
        // Check if the player is alive on the board.
        assertThat(level.isAnyPlayerAlive()).isTrue();
        // Check if the player is on the correct square.
        assertThat(board.squareAt(1, 1).getOccupants()).hasSize(1);
        // Check that other squares contain walls. In other words, check that they are not accessible to the player.
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (x == 1 && y == 1) {
                    assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                    assertThat(board.squareAt(x, y).getOccupants().get(0)).isEqualTo(player);
                    continue;
                }
                assertThat(board.squareAt(x, y).getOccupants()).isEmpty();
                assertThat(board.squareAt(x, y).isAccessibleTo(board.squareAt(1, 1).getOccupants().get(0))).isFalse();
            }
        }
    }

    @Test
    void testSingleGhost() {
        List<String> map = List.of(
            "###",
            "#G#",
            "###"
        );
        Level level = mapParser.parseMap(map);
        // Check if the level is not null.
        assertThat(level).isNotNull();
        // Check if the level has a board.
        Board board = level.getBoard();
        assertThat(board).isNotNull();
        // Check if the board has the correct width.
        assertThat(board.getWidth()).isEqualTo(3);
        // Check if the board has the correct height.
        assertThat(board.getHeight()).isEqualTo(3);
        // Check if the ghost is on the correct square.
        assertThat(board.squareAt(1, 1).getOccupants()).hasSize(1);
        // Check that other squares contain walls. In other words, check that they are not accessible to the ghost.
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (x == 1 && y == 1) {
                    assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                    assertThat(board.squareAt(x, y).getOccupants().get(0)).isInstanceOf(Blinky.class);
                    continue;
                }
                assertThat(board.squareAt(x, y).getOccupants()).isEmpty();
                assertThat(board.squareAt(x, y).isAccessibleTo(board.squareAt(1, 1).getOccupants().get(0))).isFalse();
            }
        }
    }

    @Test
    void testAllGhosts(){
        List<String> map = List.of(
            "######",
            "#GGGG#",
            "######"
        );
        Level level = mapParser.parseMap(map);
        // Check if the level is not null.
        assertThat(level).isNotNull();
        // Check if the level has a board.
        Board board = level.getBoard();
        assertThat(board).isNotNull();
        // Check if the board has the correct width.
        assertThat(board.getWidth()).isEqualTo(6);
        // Check if the board has the correct height.
        assertThat(board.getHeight()).isEqualTo(3);
        // Check if the ghosts are on the correct squares.
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (y == 1) {
                    if (x == 1) {
                        assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                        assertThat(board.squareAt(x, y).getOccupants().get(0)).isInstanceOf(Blinky.class);
                    }
                    else if (x == 2) {
                        assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                        assertThat(board.squareAt(x, y).getOccupants().get(0)).isInstanceOf(Inky.class);
                    }
                    else if (x == 3) {
                        assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                        assertThat(board.squareAt(x, y).getOccupants().get(0)).isInstanceOf(Pinky.class);
                    }
                    else if (x == 4) {
                        assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                        assertThat(board.squareAt(x, y).getOccupants().get(0)).isInstanceOf(Clyde.class);
                    }
                    else {
                        assertThat(board.squareAt(x, y).getOccupants()).isEmpty();
                    }
                }
                else {
                    assertThat(board.squareAt(x, y).getOccupants()).isEmpty();
                }
            }
        }
    }

    @Test
    void testPacmanWithAllGhosts(){
        List<String> map = List.of(
            "###########",
            "#P G G G G#",
            "###########"
        );
        Level level = mapParser.parseMap(map);
        // Check if the level is not null.
        assertThat(level).isNotNull();
        // Check if the level has a board.
        Board board = level.getBoard();
        assertThat(board).isNotNull();
        // Check if the board has the correct width.
        assertThat(board.getWidth()).isEqualTo(11);
        // Check if the board has the correct height.
        assertThat(board.getHeight()).isEqualTo(3);
        // Register the player.
        Player player = new Player(pacManSprites.getPacmanSprites(), pacManSprites.getPacManDeathAnimation());
        level.registerPlayer(player);
        // Check if the player is alive on the board.
        assertThat(level.isAnyPlayerAlive()).isTrue();
        // Check if the player is on the correct square.
        assertThat(board.squareAt(1, 1).getOccupants()).hasSize(1);
        // Check that squares around the map contain walls. In other words, check that they are not accessible to the player.
        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                // Squares on the same row as the player are accessible to the player except for the first square and the last square containing walls.
                if (y == 1) {
                    if (x == 0 || x == 10) {
                        assertThat(board.squareAt(x, y).getOccupants()).isEmpty();
                        assertThat(board.squareAt(x, y).isAccessibleTo(board.squareAt(1, 1).getOccupants().get(0))).isFalse();
                        continue;
                    }
                    else if (x == 1) {
                        assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                        assertThat(board.squareAt(x, y).getOccupants().get(0)).isEqualTo(player);
                        continue;
                    }
                    else if (x == 3) {
                        assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                        assertThat(board.squareAt(x, y).getOccupants().get(0)).isInstanceOf(Blinky.class);
                        continue;
                    }
                    else if (x == 5) {
                        assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                        assertThat(board.squareAt(x, y).getOccupants().get(0)).isInstanceOf(Inky.class);
                        continue;
                    }
                    else if (x == 7) {
                        assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                        assertThat(board.squareAt(x, y).getOccupants().get(0)).isInstanceOf(Pinky.class);
                        continue;
                    }
                    else if (x == 9) {
                        assertThat(board.squareAt(x, y).getOccupants()).isNotEmpty();
                        assertThat(board.squareAt(x, y).getOccupants().get(0)).isInstanceOf(Clyde.class);
                        continue;
                    }
                    else {
                        assertThat(board.squareAt(x, y).getOccupants()).isEmpty();
                        assertThat(board.squareAt(x, y).isAccessibleTo(board.squareAt(1, 1).getOccupants().get(0))).isTrue();
                    }
                }
                else {
                    // Squares on the other rows are not accessible to the player.
                    assertThat(board.squareAt(x, y).getOccupants()).isEmpty();
                    assertThat(board.squareAt(x, y).isAccessibleTo(board.squareAt(1, 1).getOccupants().get(0))).isFalse();
                }
            }
        }
    }

    @Test
    void testInvalidMap(){
        List<String> map = List.of(
            "###",
            "#P",
            "###"
        );
        try {
            mapParser.parseMap(map);
        } catch (PacmanConfigurationException e) {
            assertThat(e.getMessage()).isEqualTo("Input text lines are not of equal width.");
        }
    }

    @Test
    void testNullMap(){
        List<String> map = null;
        try {
            mapParser.parseMap(map);
        } catch (PacmanConfigurationException e) {
            assertThat(e.getMessage()).isEqualTo("Input text cannot be null.");
        }
    }

    @Test
    void testEmptyMap(){
        List<String> map = List.of();
        try {
            mapParser.parseMap(map);
        } catch (PacmanConfigurationException e) {
            assertThat(e.getMessage()).isEqualTo("Input text must consist of at least 1 row.");
        }
    }

    @Test
    void testEmptyMapLines(){
        List<String> map = List.of(
            "",
            "",
            ""
        );
        try {
            mapParser.parseMap(map);
        } catch (PacmanConfigurationException e) {
            assertThat(e.getMessage()).isEqualTo("Input text lines cannot be empty.");
        }
    }

    @Test
    void testInvalidMapCharacterAsOtherCharacter(){
        List<String> map = List.of(
            "###",
            "#X#",
            "#G#"
        );
        try {
            mapParser.parseMap(map);
        } catch (PacmanConfigurationException e) {
            assertThat(e.getMessage()).isEqualTo("Invalid character at 1,1: X");
        }
    }

    @Test
    void testInvalidMapCharacterAsNumber(){
        List<String> map = List.of(
            "###",
            "#9#",
            "#G#"
        );
        try {
            mapParser.parseMap(map);
        } catch (PacmanConfigurationException e) {
            assertThat(e.getMessage()).isEqualTo("Invalid character at 1,1: 9");
        }
    }
}
