package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.sprite.PacManSprites;
import nl.tudelft.jpacman.npc.ghost.GhostFactory;
import nl.tudelft.jpacman.board.Board;

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
        void



}
